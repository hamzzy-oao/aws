function Signup() {
    return(
        <Container>
			<h1>회원가입</h1>
			<Form>
				<Form.Group className="mb-3" >
					<Form.Label>아이디</Form.Label>
					<Form.Control type="text" name="id"/>
				</Form.Group>
				<Form.Group className="mb-3" >
					<Form.Label>비번</Form.Label>
					<Form.Control type="password" name="pw"/>
				</Form.Group>
				<Form.Group className="mb-3" >
					<Form.Label>비번확인</Form.Label>
					<Form.Control type="password" name="pw2" />
				</Form.Group>
				<Form.Group className="mb-3" >
					<Form.Label>이메일</Form.Label>
					<Form.Control type="email" name="email" />
				</Form.Group>
				<Button variant="outline-success" type="submit">회원가입</Button>
			</Form>
		</Container>
    )
    

}

export default Signup;