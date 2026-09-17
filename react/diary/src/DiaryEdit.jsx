import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./AuthContext";

function DiaryEdit() {
    const { id } = useParams();
    const navigate = useNavigate();
    const { accessToken } = useAuth();
    const [emotionTags, setEmotionTags] = useState([]);
    const [image, setImage] = useState(null);
    const [data, setData] = useState({
        diaryDate: '',
        title: '',
        content: '',
        emotionTagIds: [],
        isPublic: false
    });

    const inputHandler = (e) => {
        const { name, value, type, checked } = e.target;
        setData(previousData => ({
            ...previousData,
            [name]: type === "checkbox" ? checked : value
        }));
    };

    // 기존 데이터 불러오기
    useEffect(() => {
        const fetchDiary = async () => {
            const response = await fetch(`/api/diary/${id}`, {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setData({
                diaryDate: result.diaryDate,
                title: result.title,
                content: result.content,
                emotionTagIds: result.emotionTagIds ?? '',
                isPublic: result.isPublic
            });
        };
        if (accessToken) fetchDiary();
    }, [accessToken, id]);

    useEffect(() => {
        const getEmotionTags = async () => {
            const response = await fetch("/api/diary/emotion", {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setEmotionTags(result);
        };
        if (accessToken) getEmotionTags();
    }, [accessToken]);

    const imageHandler = (e) => {
        const selectedImage = e.target.files[0];
        if (selectedImage && !selectedImage.type.startsWith("image/")) {
            alert("이미지 파일만 첨부할 수 있습니다.");
            e.target.value = "";
            setImage(null);
            return;
        }
        setImage(selectedImage || null);
    };

    const emotionTagHandler = (tagId) => {
        setData(prev => ({
            ...prev,
            emotionTagIds: prev.emotionTagIds.includes(tagId)
                ? prev.emotionTagIds.filter(id => id !== tagId)
                : [...prev.emotionTagIds, tagId]
        }));
    };

    const submitHander = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append("request", new Blob([JSON.stringify(data)], { type: "application/json" }));
        if (image) formData.append("files", image);

        try {
            const response = await fetch(`/api/diary/${id}`, {
                method: "PUT",
                headers: { Authorization: `Bearer ${accessToken}` },
                body: formData
            });
            const result = await response.json();
            alert(result.message);
            navigate(`/diary/${id}`);
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <div className="diary-page">
            <Container className="diary-card">
                <h1 className="diary-title">일기 수정</h1>
                <Form className="diary-form" onSubmit={submitHander}>
                    <div className="diary-field">
                        <label htmlFor="diaryDate">날짜</label>
                        <input type="date" id="diaryDate" name="diaryDate"
                            value={data.diaryDate} onChange={inputHandler} />
                    </div>

                    <div className="diary-field">
                        <label>감정 태그 (여러 개 선택 가능)</label>
                        <div className="emotion-tag-list">
                            {emotionTags.map(tag => (
                                <button
                                    type="button"
                                    key={tag.id}
                                    className={data.emotionTagIds.includes(tag.id) ? "selected" : ""}
                                    onClick={() => emotionTagHandler(tag.id)}
                                >
                                    {tag.emoji} {tag.name}
                                </button>
                            ))}
                        </div>
                    </div>

                    <div className="diary-field">
                        <label htmlFor="title">제목</label>
                        <input type="text" id="title" name="title"
                            value={data.title} onChange={inputHandler} />
                    </div>

                    <div className="diary-field">
                        <label htmlFor="content">본문</label>
                        <textarea id="content" name="content"
                            rows="12" value={data.content} onChange={inputHandler} />
                    </div>

                    <div className="diary-field">
                        <label htmlFor="image">이미지 첨부 (변경 시에만 업로드)</label>
                        <input type="file" id="image" accept="image/*" onChange={imageHandler} />
                    </div>

                    <div className="diary-field">
                        <label>공개 여부</label>
                        <input type="checkbox" checked={data.isPublic} onChange={(e) => {
                            setData(prev => ({ ...prev, isPublic: e.target.checked }));
                        }} />
                    </div>

                    <Button className="diary-submit" type="submit">수정 완료</Button>
                </Form>
            </Container>
        </div>
    );
}

export default DiaryEdit;