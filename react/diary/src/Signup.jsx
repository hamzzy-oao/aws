import { useState } from "react";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";
import Form from 'react-bootstrap/Form';

import "./Signup.css";


function Signup() {

    

    const [data, setData] = useState({email :'', pw : '', pw2: '', nickname : ''})

    const inputHandler = (e) => {

        const { name, value } = e.target;

        setData({...data, [name] : value})
    }


    const naviage = useNavigate();

    const submitHander = async e=>{
		e.preventDefault();

		try{
			const response = await fetch("/api/auth/signup", {
				method : "POST",
				headers : {
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(data)
			})
			const result = await response.json();
			alert(result.message);
			if(result.success){
				//메인페이지로 이동
				naviage("/")
			}
		}catch(e){
			console.error(e);
		}

	}



    return (
        <main className="signup-page">
            <Container className="signup-card">
                <div className="signup-title-area">
                    <span className="signup-icon">📖</span>
                    <h1>회원가입</h1>
                    <p>오늘의 이야기를 기록할 계정을 만들어보세요.</p>
                </div>

                <Form className="signup-form" onSubmit={submitHander}>
                    <Form.Group className="mb-3">
                        <Form.Label>이메일</Form.Label>
                        <Form.Control
                            type="email"
                            name="email"
                            placeholder="example@email.com"
                            value={data.email}
                            onChange={inputHandler}
                            required
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>비밀번호</Form.Label>
                        <Form.Control
                            type="password"
                            name="pw"
                            placeholder="비밀번호를 입력하세요"
                            value={data.pw}
                            onChange={inputHandler}
                            required
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>비밀번호 확인</Form.Label>
                        <Form.Control
                            type="password"
                            name="pw2"
                            placeholder="비밀번호를 다시 입력하세요"
                            value={data.pw2}
                            onChange={inputHandler}
                            required
                        />
                    </Form.Group>

                    <Form.Group className="mb-4">
                        <Form.Label>닉네임</Form.Label>
                        <Form.Control
                            type="text"
                            name="nickname"
                            placeholder="사용할 닉네임을 입력하세요"
                            value={data.nickname}
                            onChange={inputHandler}
                            required
                        />
                    </Form.Group>

                    <Button className="signup-submit" type="submit">
                        회원가입
                    </Button>
                </Form>
            </Container>
        </main>
    );
}

export default Signup;