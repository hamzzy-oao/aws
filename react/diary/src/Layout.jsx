import { NavLink } from "react-router-dom";

import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";

import "./Layout.css";

function Header() {
  return (
    <Navbar
      expand="lg"
      className="diary-navbar"
    >
      <Container>
        <Navbar.Brand
          as={NavLink}
          to="/"
          className="diary-brand"
        >
          <span className="diary-brand-icon">📖</span>

          <span>
            <strong>오늘의 일기</strong>
            <small>나의 하루를 기록하는 공간</small>
          </span>
        </Navbar.Brand>

        <Navbar.Toggle
          aria-controls="diary-navbar"
          className="diary-toggle"
        />

        <Navbar.Collapse id="diary-navbar">
          <Nav className="ms-auto diary-menu">
            <Nav.Link
              as={NavLink}
              to="/"
              end
              className="diary-nav-link"
            >
              홈
            </Nav.Link>

            <Nav.Link
              as={NavLink}
              to="/mydiary"
              className="diary-nav-link"
            >
              일기 작성
            </Nav.Link>

            <Nav.Link
              as={NavLink}
              to="/login"
              className="diary-nav-link"
            >
              로그인
            </Nav.Link>

            <Nav.Link
              as={NavLink}
              to="/signup"
              className="diary-signup-link"
            >
              회원가입
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export { Header };