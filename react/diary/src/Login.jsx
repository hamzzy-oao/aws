import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import { useState } from "react";
import { useNavigate } from "react-router-dom";


function Login() {

	const navigate = useNavigate();

	const [data, setData] = useState({email :'', pw : ''});
	const [loading, setLoading] = useState(false);

	const inputHandler = (e) => {

        const { name, value } = e.target;

        setData({...data, [name] : value})
    }

	const naviage = useNavigate();

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
			alert(result.state.message);

			localStorage.setItem(
				"accessToken",
				result.accessToken
			);

			alert(result.message || "로그인되었습니다.");


			navigate("/");

		}catch(e){
			console.error(e);

		}
	}



    return(
        <Container>
			<h1>로그인</h1>
			<Form onSubmit={submitHander}>
				<Form.Group className="mb-3" >
					<Form.Label>이메일</Form.Label>
					<Form.Control type="text" name="id" onChange={inputHandler}/>
				</Form.Group>
				<Form.Group className="mb-3" >
					<Form.Label>비밀번호</Form.Label>
					<Form.Control type="password" name="pw" onChange={inputHandler}/>
				</Form.Group>
				<Button variant="outline-success" type="submit">로그인</Button>
			</Form>
		</Container>
    )
    
}

export default Login;