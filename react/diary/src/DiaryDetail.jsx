import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./AuthContext";


import "./CSS/DiaryDetail.css";

function DiaryDetail() {
    const { id } = useParams();
    const { accessToken, user } = useAuth();
    const [diary, setDiary] = useState(null);
    const extractFilename = (url) => url.substring(url.lastIndexOf("/") + 1);
    const navigate = useNavigate();
    const [comments, setComments] = useState([]);
    const [commentContent, setCommentContent] = useState('');
    const [editingCommentId, setEditingCommentId] = useState(null);
    const [editContent, setEditContent] = useState('');


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
    
    useEffect(() => {
        if (accessToken && diary?.isPublic) fetchComments();
    }, [accessToken, diary]);

    
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

    const fetchComments = async () => {
        const response = await fetch(`/api/diary/${id}/comments`, {
            headers: { Authorization: `Bearer ${accessToken}` }
        });
        const result = await response.json();
        setComments(result);
    };


    const handleCommentSubmit = async (e) => {
        e.preventDefault();
        if (!commentContent.trim()) return;

        await fetch(`/api/diary/${id}/comments`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${accessToken}`
            },
            body: JSON.stringify({ content: commentContent })
        });

        setCommentContent('');
        fetchComments();
    };

    const handleCommentDelete = async (commentId) => {
        if (!window.confirm("댓글을 삭제하시겠습니까?")) return;

        await fetch(`/api/diary/comments/${commentId}`, {
            method: "DELETE",
            headers: { Authorization: `Bearer ${accessToken}` }
        });

        fetchComments();
    };

    const handleEditStart = (comment) => {
        setEditingCommentId(comment.id);
        setEditContent(comment.content);
    };

const handleEditCancel = () => {
    setEditingCommentId(null);
    setEditContent('');
};

const handleEditSubmit = async (commentId) => {
    await fetch(`/api/diary/comments/${commentId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`
        },
        body: JSON.stringify({ content: editContent })
    });

    setEditingCommentId(null);
    fetchComments();
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
                        src={`http://localhost:8080/api/diary/download?filename=${diary.imageUrl}`}
                        alt="첨부이미지"
                        className="detail-image"
                        onClick={handleDownload}    
                    />
                </section>
            )}
            {diary.userId === user?.UserId && (
            <>
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
            </>
            )}
        </article>
            {diary.isPublic && (
                <section className="detail-comments">
                    <h2>댓글</h2>

                    <form onSubmit={handleCommentSubmit} className="comment-form">
                        <input
                            type="text"
                            placeholder="댓글을 입력하세요"
                            value={commentContent}
                            onChange={e => setCommentContent(e.target.value)}
                        />
                        <button type="submit">등록</button>
                    </form>
                    <div className="comment-list">
                            {comments.map(comment => (
                                <div key={comment.id} className="comment-item">
                                    <span className="comment-nickname">{comment.nickname}</span>

                                    {editingCommentId === comment.id ? (
                                        <>
                                            <input
                                                value={editContent}
                                                onChange={e => setEditContent(e.target.value)}
                                            />
                                            <button onClick={() => handleEditSubmit(comment.id)}>저장</button>
                                            <button onClick={handleEditCancel}>취소</button>
                                        </>
                                    ) : (
                                        <>
                                            <span className="comment-content">{comment.content}</span>
                                            {comment.userId === user?.UserId && (
                                                <>
                                                    <button onClick={() => handleEditStart(comment)}>수정</button>
                                                    <button onClick={() => handleCommentDelete(comment.id)}>삭제</button>
                                                </>
                                            )}
                                        </>
                                    )}
                                </div>
                            ))}
                        </div>

                </section>
            )}

    </main>
);
}

export default DiaryDetail;