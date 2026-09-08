# 데이터베이스 생성
# create database [if not exists] db명;
# [] : 생략 가능
# [] 차이
# [] 있으면 db가 있는 경우 생성하지 않음. 정상 종료 
# [] 없으면 db가 있는 경우 생성하지 않고 에러 발생
drop database if exists sample;
create database if not exists sample;

# 데이터베이스 삭제
# drop database [if exists] db명;
# drop database if exists sample;

/*
# 테이블 생성
CREATE TABLE [IF NOT EXISTS] 테이블명(
	컬럼명 타입	[ZEROFILL] [UNIQUE] [NOT NULL] [DEFAULT 기본값] [PRIMARY KEY][AUTO_INCREMENT] ,
	컬럼명 타입	[ZEROFILL] [UNIQUE] [NOT NULL] [DEFAULT 기본값],
	제약조건,
	CONSTRAINT 제약조건명 PRIMARY KEY(컬럼명),
	CONSTRAINT 제약조건명 FOREIGN KEY(컬럼명) REFERENCES 참조테이블(기본키명),
	CONSTRAINT CHECK(논리식)
);
*/
use sample;

# 테이블명이 키워드인 경우 반드시 `테이블명`을 이용 
create table if not exists student(
	num char(10) primary key, 
    name varchar(20) not null,
    contact varchar(13),
    res_num char(14),
    major_code char(3) not null # 전공코드는 외래키로 나중에 지정 
);

# 테이블 삭제
# drop table [if exists] 테이블명; 
# drop table if exists student;

# 테이블 수정 
# 컬럼(속성) 추가
# alter table 테이블명 add 컬럼명 타입 옵션;
alter table student add etc varchar(50);

# 컬럼 수정 
# alter table 테이블명 change 기존컬럼명 새컬럼명 타입 옵션;
alter table student change etc etc varchar(100);

# 컬럼 삭제 
# alter table 테이블명 drop 컬럼명;
alter table student drop etc;

# 다음 조건을 만족하는 테이블을 생성하세요.
# 테이블명 : major
# 컬럼1 - code, 타입 : 고정문자열3자, 기본키 
# 컬럼2 - name, 타입 : 가변문자열15자, null 허용 안함, 중복 안됨 
# 컬럼3 - boss_id, 타입 : 고정문자열10자, null 허용
# 컬럼4 - address, 타입 : 가변문자열30자, null 허용
drop table if exists major;
create table if not exists major(
	code char(3),
    name varchar(15) not null unique,
    boss_id char(10),
    address varchar(30),
    constraint pk_code primary key(code)
);

# 제약조건 추가
# alter table 테이블명 add constraint 제약조건명 제약조건종류(컬럼명) 
alter table student add constraint fk_student_major 
	foreign key(major_code) references major(code);
    
# 제약조건 삭제
# alter table 테이블명 drop constraint 제약조건명;
# alter table student drop constraint fk_student_major;