DROP DATABASE IF EXISTS NF;
CREATE DATABASE NF;
USE NF;
-- 실습용 비정규화 테이블 생성
CREATE TABLE enrollment_unf (
    student_id     INT,          -- 학번
    student_name   VARCHAR(20),  -- 이름
    phone_number   VARCHAR(100), -- 전화번호 (다중값 허용을 위해 길게 설정)
    course_id      CHAR(4),      -- 과목번호
    course_name    VARCHAR(50),  -- 과목명
    professor_name VARCHAR(20),  -- 담당교수
    professor_room VARCHAR(20),  -- 교수실
    grade          CHAR(1)       -- 성적
);

-- 비정규화된 샘플 데이터 삽입
INSERT INTO enrollment_unf 
VALUES (101, '홍길동', '010-1111, 010-2222', 'CS01', '데이터베이스', '홍길동', '301호', 'A');

INSERT INTO enrollment_unf 
VALUES (101, '홍길동', '010-1111, 010-2222', 'CS02', '알고리즘', '김철수', '302호', 'B');

INSERT INTO enrollment_unf 
VALUES (102, '김철수', '010-3333', 'CS01', '데이터베이스', '홍길동', '301호', 'A');