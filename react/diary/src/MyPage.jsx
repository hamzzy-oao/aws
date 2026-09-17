import { useEffect, useState } from "react";
import 'react-calendar/dist/Calendar.css';
import { useAuth } from "./AuthContext";
import { useNavigate } from "react-router-dom";
import Calendar from 'react-calendar';


import "./CSS/MyPage.css";

function DiaryCalendar() {
    const { accessToken } = useAuth();
    const navigate = useNavigate();
    const [activeDate, setActiveDate] = useState(new Date());
    const [diaries, setDiaries] = useState([]);
    

    useEffect(() => {
        const fetchAll = async () => {
            const response = await fetch("/api/diary/my/all", {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setDiaries(result);
        };
        if (accessToken) fetchAll();
    }, [accessToken]);

    const getDiaryByDate = (date) => {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const dateStr = `${year}-${month}-${day}`;
    return diaries.find(d => d.diaryDate === dateStr);
};

    return (
        <div className="calendar-area">
            <button
                type="button"
                className="calendar-today-button"
                onClick={() => setActiveDate(new Date())}
            >
                오늘
            </button>

            <Calendar
                className="diary-calendar"
                locale="ko-KR"
                calendarType="gregory"
                formatDay={(locale, date) => date.getDate()}
                prev2Label="«"
                next2Label="»"
                prevLabel="‹"
                nextLabel="›"
                activeStartDate={activeDate}
                onActiveStartDateChange={({ activeStartDate }) => setActiveDate(activeStartDate)}

                tileClassName={({ date, view }) => {
                    if (view === "month" && getDiaryByDate(date)) {
                        return "calendar-has-diary";
                    }
                    return null;
                }}

                tileContent={({ date, view }) => {
                    const diary = getDiaryByDate(date);
                    return view === "month" && diary ? (
                        <span className="calendar-diary-icon">📝</span>
                    ) : null;
                }}

                onClickDay={(date) => {
                    const diary = getDiaryByDate(date);

                    if (diary) {
                        navigate(`/diary/${diary.id}`);
                    } else {
                        const year = date.getFullYear();
                        const month = String(date.getMonth() + 1).padStart(2, '0');
                        const day = String(date.getDate()).padStart(2, '0');
                        const dateStr = `${year}-${month}-${day}`;
                        navigate(`/mydiary?date=${dateStr}`);
                    }
                }}
            />
        </div>
    );
}



function MyPage() {

    const [diaries, setDiaries] = useState([]);
    const { accessToken } = useAuth();
    const navigate = useNavigate();
    const [page, setPage] = useState(0);
    const [pageInfo, setPageInfo] = useState({ totalPages: 0 });
    

    useEffect(() => {
        const fetchMyDiaries = async () => {
            const response = await fetch(`/api/diary/my?page=${page}&size=10`, {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setDiaries(result.content);
            setPageInfo(result);
        };
        if (accessToken) fetchMyDiaries();
    }, [accessToken, page]);

    return(
        

        

    <main className="mypage-page">
        <section className="mypage-card">
            <div className="mypage-title-area">
                <span className="mypage-icon">📚</span>
                <h1>내 일기 목록</h1>
                <p>지금까지 작성한 일기를 확인해보세요.</p>
            </div>
                <DiaryCalendar />

            <div className="mypage-list">
                {diaries.length === 0 ? (
                    <div className="mypage-empty">
                        작성한 일기가 없습니다.
                    </div>
                ) : (
                    diaries.map(diary => (
                        <div
                            key={diary.id}
                            className="mypage-diary-item"
                            onClick={() =>
                                navigate(`/diary/${diary.id}`)
                            }
                        >
                           <div className="mypage-diary-content">
                                <span className="mypage-diary-date">
                                    {diary.diaryDate}
                                </span>

                                <span className={diary.isPublic ? "mypage-public" : "mypage-private"}>
                                    {diary.isPublic ? "공개" : "비공개"}
                                </span>
                            
                                <h2 className="mypage-diary-title">
                                    {diary.title}
                                </h2>
                            </div>

                            <div className="mypage-emotions">
                                {(diary.emotionTags ?? []).map(
                                    (tag, index) => (
                                        <span
                                            key={index}
                                            className="mypage-emotion-tag"
                                        >
                                            {tag}
                                        </span>
                                    )
                                )}
                            </div>

                            <span className="mypage-arrow">›</span>
                        </div>
                    ))
                )}
            </div>

            {pageInfo.totalPages > 0 && (
                <div className="mypage-pagination">
                    <button
                        type="button"
                        disabled={page === 0}
                        onClick={() => setPage(
                            previousPage => previousPage - 1
                        )}
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
                        onClick={() => setPage(
                            previousPage => previousPage + 1
                        )}
                    >
                        다음
                    </button>
                </div>
            )}
        </section>
    </main>
    )


}

export { DiaryCalendar, MyPage };