import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import { useState, useEffect } from "react";
import{ useNavigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

import "./MyDiary.css";

function MyDiary() {
    
    const inputHandler = (e) => {
    const { name, value, type, checked } = e.target;

    setData(previousData => ({
        ...previousData,
        [name]: type === "checkbox" ? checked : value
    }));
};

    const navigate = useNavigate();
    const { accessToken } = useAuth();
    const [emotionTags, setEmotionTags] = useState([]);
    const [image, setImage] = useState(null);
    const [data, setData] = useState({
        diaryDate : '',
        title : '',
        content : '',
        emotionTagId: '',
        isPublic: ''

    });
    
    const submitHander = async(e) =>{
        e.preventDefault();

        const formData = new FormData();

        formData.append(
        "request",
            new Blob([JSON.stringify(data)], {
                type: "application/json"
            })
        );

        if (image) {
            formData.append("files", image);
        }
        
        
        try{
            const response = await fetch("/api/diary/post",{
                method : "post",
                headers : {
                    Authorization: `Bearer ${accessToken}`
                },
                body : formData

            });
            


        const result = await response.json();

        console.log(accessToken);
        alert(result.message);
        navigate("/mypage");

        }catch(e){
            console.log(e);

        }

    }

    const imageHandler = (e) => {

        const selectedImage = e.target.files[0];

        if (selectedImage && !selectedImage.type.startsWith("image/")) {
            alert("이미지 파일만 첨부할 수 있습니다.");
            e.target.value = "";
            setImage(null);
            return;

        }

        setImage(selectedImage || null);

    }

    useEffect(()=>{

        const getEmotionTags = async () => {
            if(!accessToken){
                return;

            }

            try{
                const response = await fetch("/api/diary/emotion",
                    
                    {
                        method:"get",
                        headers : {Authorization: `Bearer ${accessToken}` 
                    }

                });

                const result = await response.json();
                setEmotionTags(result);

            }catch(e){
                console.error(e);

            }

        }
        
        getEmotionTags();


    },[accessToken]);
    
    return(
            <div className="diary-page">
                <Container className="diary-card">
                    <h1 className="diary-title">일기 작성</h1>

                    <Form className="diary-form" onSubmit={submitHander}>
                        <div className="diary-field">
                            <label htmlFor="diaryDate">날짜</label>
                            <input type="date" id="diaryDate" name="diaryDate"
                                onChange={inputHandler} />
                        </div>

                        <div className="diary-field">
                            <label htmlFor="emotionTagId">감정 태그</label>
                            <select id="emotionTagId" name="emotionTagId"
                            value={data.emotionTagId} onChange={inputHandler}>
                            <option value="">감정 태그를 선택하세요.</option>

                            {emotionTags.map((emotionTag) => (
                                <option key={emotionTag.id} value={emotionTag.id}>
                                    {emotionTag.emoji} {emotionTag.name}
                                </option>
                            ))}
                            </select>
                        </div>

                        <div className="diary-field">
                            <label htmlFor="title">제목</label>
                            <input type="text" id="title" name="title"
                            placeholder="제목을 입력하세요"
                            onChange={inputHandler} />
                        </div>

                        <div className="diary-field">
                            <label htmlFor="content">본문</label>
                            <textarea id="content" name="content"
                            placeholder="내용을 입력하세요."
                            rows="12"
                            onChange={inputHandler} />
                        </div>

                        <div className="diary-field">
                            <label htmlFor="image">이미지 첨부</label>
                            <input type="file" id="image" accept="image/*" onChange={imageHandler}/>
                        </div>
                        <div className="diary-field">
                            <label>공개 여부</label>
                            <input type="checkbox" id="isPublic" checked={data.isPublic} onChange={(e) => {  
                                setData(previousData => ({
                                    ...previousData,
                                    isPublic: e.target.checked
                                    }));
                                }}
                            />
                        </div>

                        <Button className="diary-submit" type="submit">
                            일기 저장
                        </Button>
                    </Form>
                </Container>
            </div>
    )
    

}

export default MyDiary;