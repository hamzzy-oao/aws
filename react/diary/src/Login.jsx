import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

import "./CSS/Login.css";


function Login() {

	const navigate = useNavigate();

	const [data, setData] = useState({email :'', pw : ''});
    const { login } = useAuth();

	const inputHandler = (e) => {

        const { name, value } = e.target;

        setData({...data, [name] : value})
    }


	const submitHander = async e=>{
		e.preventDefault();


		try{
			const response = await fetch("/api/auth/login",{
				method: "POST",
				headers: {
					"Content-Type": "application/json"
				},
				body: JSON.stringify(data)

			});
			const result = await response.json();

            if (!response.ok) {
                alert(result.message || "로그인에 실패했습니다.");
                return;
            }

            login(result.accessToken);

            alert(result.message || "로그인되었습니다.");

            navigate("/");

		}catch(e){
			console.error(e);

		}
	}



    return(
        <main className="login-page">
        <Container className="login-card">
            <div className="login-title-area">
                <span className="login-icon">📖</span>
                <h1>로그인</h1>
                <p>오늘의 기록을 이어서 작성해보세요.</p>
            </div>

            <Form
                className="login-form"
                onSubmit={submitHander}
            >
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

                <Form.Group className="mb-4">
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

                <Button
                    className="login-submit"
                    type="submit"
                >
                    로그인
                </Button>
            </Form>
        </Container>
    </main>
    )
    
}

export default Login;