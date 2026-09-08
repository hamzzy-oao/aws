# 데이터베이스 생성 및 테이블 추가
drop database if exists sample2;

create database if not exists sample2;

use sample2;

create table if not exists student(
	code int primary key auto_increment,
    grade int not null default 1,
    class int not null default 1,
    num int not null default 1,
    name varchar(10) 
);
# 과목(과목고유번호, 학년, 학기, 과목명)
create table if not exists subject(
	code int primary key auto_increment,
    grade int not null default 1,
    semester int not null default 1,
    title varchar(20) not null
);

# 성적(성적고유번호, 학생고유번호, 과목고유번호, 성적)
create table if not exists score(
	code int primary key auto_increment,
    st_code int not null,
    sj_code int not null,
    score int not null default 0,
    # st_code 컬럼이 student 테이블에 code를 참조 
    foreign key(st_code) references student(code),
    # sj_code 컬럼이 subject 테이블에 code를 참조 
    foreign key(sj_code) references subject(code)
);

# 샘플 데이터 추가
# 샘플 데이터 추가
insert into student(grade, class, num, name) values(1,1,1,"홍길동");
insert into student(grade, class, num, name) values(1,1,2,"임꺽정");
insert into student(grade, class, num, name) values(1,2,1,"홍가네");
insert into student(grade, class, num, name) values(2,1,1,"고길동");

# 과목 추가
# 1학년 1학기 국어 추가
insert into subject(grade, semester, title) values(1,1,"국어");
# 1학년 1학기 수학 추가
insert into subject(grade, semester, title) values(1,1,"수학");
# 1학년 2학기 국어, 수학 추가
insert into subject(grade, semester, title) 
values(1,2,"국어"), (1,2,"수학");
# 2학년 1학기 국어, 수학 추가 
insert into subject(grade, semester, title) 
values(2,1,"국어"), (2,1,"수학");

# 성적 추가 
# 1학년 1반 1번 학생의 1학년 1학기 성적 - 국어 : 90, 수학 : 80
insert into score(st_code, sj_code, score)
values(1, 1, 90), (1, 2, 80);
# 1학년 1반 2번 학생의 1학년 1학기 성적 - 국어 : 100, 수학 : 100
insert into score(st_code, sj_code, score)
values(2, 1, 100), (2, 2, 100);
# 1학년 2반 1번 학생의 1학년 1학기 성적 - 국어 : 50, 수학 : 100
insert into score(st_code, sj_code, score)
values(3, 1, 50), (3, 2, 100);
# 2학년 1반 1번 학생의 2학년 1학기 성적 - 국어 : 100, 수학 : 50
insert into score(st_code, sj_code, score)
values(4, 5, 100), (4, 6, 100);

# 3학년 1반 1번 둘리를 추가
insert into student(grade, class, num, name)
values(3,1,1,'둘리');
