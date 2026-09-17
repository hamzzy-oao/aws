import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./AuthContext";

import "./DiaryDetail.css";

function DiaryDetail() {
    const { id } = useParams();
    const { accessToken } = useAuth();
    const [diary, setDiary] = useState(null);
    const extractFilename = (url) => url.substring(url.lastIndexOf("/") + 1);
    const navigate = useNavigate();

    const handleDownload = async () => {

        const response = await fetch(
            `http://localhost:8080/api/diary/download?filename=${extractFilename(diary.imageUrl)}`,
            { headers: { Authorization: `Bearer ${accessToken}` } }
        );
        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = extractFilename(diary.imageUrl);
        a.click();
        window.URL.revokeObjectURL(url);
    };

    useEffect(() => {
        const fetchDiary = async () => {
            const response = await fetch(`/api/diary/${id}`, {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setDiary(result);
        };
        if (accessToken) fetchDiary();
    }, [accessToken, id]);

    if (!diary) {
    return (
        <main className="detail-page">
            <div className="detail-loading">
                일기를 불러오는 중입니다...
            </div>
        </main>
    );
}

const handleDelete = async (e) => {
    if (!window.confirm("삭제하시겠습니까?")) return;

    await fetch(`/api/diary/${id}`, {
        method: "DELETE",
        headers: { Authorization: `Bearer ${accessToken}` }
    });

    navigate("/mypage");
};

const handleEdit = (e) => {
    navigate(`/diary/edit/${id}`);
};

return (
    <main className="detail-page">
        <article className="detail-card">
            <header className="detail-header">
                <div className="detail-meta">
                    <span className="detail-date">
                        {diary.diaryDate}
                    </span>

                    <span
                        className={
                            diary.isPublic
                                ? "detail-public"
                                : "detail-private"
                        }
                    >
                        {diary.isPublic ? "공개" : "비공개"}
                    </span>
                </div>

                <h1>{diary.title}</h1>

                <div className="detail-emotions">
                    {(diary.emotionTags ?? []).map(
                        (tag, index) => (
                            <span
                                key={index}
                                className="detail-emotion-tag"
                            >
                                <span>{tag}</span>
                            </span>
                        )
                    )}
                </div>
            </header>

            <div className="detail-divider" />

            <section className="detail-content">
                {diary.content}
            </section>

            {diary.imageUrl && (
                <section className="detail-image-area">
                    <h2>첨부 이미지</h2>

                    <img
                        src={diary.imageUrl}
                        alt="다운로드"
                        className="detail-image"
                        onClick={handleDownload}
                    />

                    
                </section>
            )}
            <div className="detail-actions">
            <button
                    type="button"
                    className="detail-edit-button"
                    onClick={handleEdit}
                >
                    수정
                </button>
            
                <button
                    type="button"
                    className="detail-delete-button"
                    onClick={handleDelete}
                >
                    삭제
                </button>
            </div>
        </article>
    </main>
);
}

export default DiaryDetail;