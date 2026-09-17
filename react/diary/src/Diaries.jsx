import { useEffect, useState } from "react";
import { useAuth } from "./AuthContext";
import { useNavigate } from "react-router-dom";

import "./CSS/Diaries.css";


function Diaries() {

    const { accessToken, user } = useAuth();
    const navigate = useNavigate();
    const [diaries, setDiaries] = useState([]);
    const [page, setPage] = useState(0);
    const [pageInfo, setPageInfo] = useState({ totalPages: 0 });

    const [keyword, setKeyword] = useState('');
    const [date, setDate] = useState('');
    const [emotionTagId, setEmotionTagId] = useState('');
    const [emotionTags, setEmotionTags] = useState([]);
    const [isSearchMode, setIsSearchMode] = useState(false);

    useEffect(() => {
        const getEmotionTags = async () => {
            const response = await fetch("/api/diary/emotion", {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setEmotionTags(result);
            console.log("user 전체:", user);
        };
        if (accessToken) getEmotionTags();
    }, [accessToken]);

    useEffect(() => {
        const fetchFeed = async () => {
            const response = await fetch(`/api/diary/feed?page=${page}&size=10`, {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setDiaries(result.content);
            setPageInfo(result);
        };
        if (accessToken && !isSearchMode) fetchFeed();
    }, [accessToken, page, isSearchMode]);

    const handleSearch = async () => {
        setIsSearchMode(true);
        setPage(0);

        const params = new URLSearchParams();
        if (keyword) params.append("keyword", keyword);
        if (date) params.append("date", date);
        if (emotionTagId) params.append("emotionTagId", emotionTagId);
        params.append("page", 0);
        params.append("size", 10);

        const response = await fetch(`/api/diary/feed?${params.toString()}`, {
    headers: { Authorization: `Bearer ${accessToken}` }
});
        const result = await response.json();
        setDiaries(result.content);
        setPageInfo(result);
    };

    const handleReset = () => {
        setKeyword('');
        setDate('');
        setEmotionTagId('');
        setIsSearchMode(false);
        setPage(0);
    };
    

    return(
            <main className="diaries-page">
        <section className="diaries-card">
            <div className="diaries-title-area">
                <span className="diaries-icon">📖</span>
                <h1>공개 일기 피드</h1>
                <p>다른 사람들의 공개된 하루를 읽어보세요.</p>
            </div>

            <div className="diaries-search-area">
                <input
                    type="text"
                    placeholder="제목 또는 내용 검색"
                    value={keyword}
                    onChange={e => setKeyword(e.target.value)}
                />

                <input
                    type="date"
                    value={date}
                    onChange={e => setDate(e.target.value)}
                />

                <select
                    value={emotionTagId}
                    onChange={e =>
                        setEmotionTagId(e.target.value)
                    }
                >
                    <option value="">감정 태그 전체</option>

                    {emotionTags.map(tag => (
                        <option
                            key={tag.id}
                            value={tag.id}
                        >
                            {tag.emoji} {tag.name}
                        </option>
                    ))}
                </select>

                <div className="diaries-search-buttons">
                    <button
                        type="button"
                        className="diaries-search-button"
                        onClick={handleSearch}
                    >
                        검색
                    </button>

                    {isSearchMode && (
                        <button
                            type="button"
                            className="diaries-reset-button"
                            onClick={handleReset}
                        >
                            초기화
                        </button>
                    )}
                </div>
            </div>

            <div className="diaries-list">
                {diaries.length === 0 ? (
                    <div className="diaries-empty">
                        공개된 일기가 없습니다.
                    </div>
                ) : (
                    diaries.map(diary => (
                        <article
                            key={diary.id}
                            className="diaries-item"
                            onClick={() =>
                                navigate(`/diary/${diary.id}`)
                            }
                        >
                            <div className="diaries-item-top">
                                <span className="diaries-date">
                                    {diary.diaryDate}
                                </span>

                                {diary.userId === user && (
                                    <span className="my-badge">
                                        내 일기
                                    </span>
                                )}
                            </div>

                            <h2>{diary.title}</h2>

                            <div className="diaries-emotions">
                                {(diary.emotionTags ?? []).map(
                                    (tag, index) => (
                                        <span
                                            key={index}
                                            className="diaries-emotion-tag"
                                        >
                                            {typeof tag === "string"
                                                ? tag
                                                : `${tag.emoji} ${tag.name}`}
                                        </span>
                                    )
                                )}
                            </div>

                            <span className="diaries-arrow">›</span>
                        </article>
                    ))
                )}
            </div>

            {pageInfo.totalPages > 0 && (
                <div className="diaries-pagination">
                    <button
                        type="button"
                        disabled={page === 0}
                        onClick={() =>
                            setPage(previous => previous - 1)
                        }
                    >
                        이전
                    </button>

                    <span>
                        {page + 1} / {pageInfo.totalPages}
                    </span>

                    <button
                        type="button"
                        disabled={
                            page + 1 >= pageInfo.totalPages
                        }
                        onClick={() =>
                            setPage(previous => previous + 1)
                        }
                    >
                        다음
                    </button>
                </div>
            )}
        </section>
    </main>
    );
    

}

export default Diaries;