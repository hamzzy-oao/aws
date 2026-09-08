use sample;

# DML : 데이터 조작어
#  - 데이터를 조회/추가/수정/삭제 하는 쿼리 

# 1. 데이터 추가 : insert 
# 1-1. 추가할 데이터를 다 아는 경우 : insert values
# - 문법1
#   insert into 테이블명(컬럼1, 컬럼2, ..., 컬럼N)
#   values(값1, 값2, ..., 값N), (값1, 값2, ..., 값N);
#   - 설명 : 생략된 컬럼들은 기본값이 지정되어 있어야 함.
#           테이블의 컬럼 순서와 insert할때 컬럼 순서는 달라도 됨 
# - 문법2
#   insert into 테이블명
#   values(값1, 값2, ..., 값N);
#   - 설명 : 값들은 테이블에 있는 컬럼 수만큼 써야 하고, 컬럼 순서대로 값 지정 

# 컴퓨터공학과, 학과 코드는 160, 주소는 1캠퍼스 A관 401호, 학과장은 미정 
insert into major(code, address, name, boss_id)
values(160, 'A관 401호', '컴퓨터공학과', null);

# 유아교육과, 학과 코드는 456, 주소는 4캠퍼스 B관 102호, 학과장은 미정
insert into major
values('456', '유아교육과', null, 'B관 102호');

# 1-2. 검색 결과를 활용해서 데이터를 추가하는 경우 : insert select


# 2. 데이터 삭제 : DELETE
# - 문법
# delete from 테이블명 where 조건; 

# !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 중요 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
# MYSQL 조건에서 =은 비교연산자 
# 대입연산자는 set 변수 = 값 형태로 대입 
# delete from sample.major where code = '456';
# delete from sample.major where name = '컴퓨터공학과';
# 워크벤치에서 unique가 아닌 속성을 조건에 쓰면 삭제가 안됨 
# => 안전모드가 켜져있어서 unique가 아닌 속성을 삭제하지 못하게 막아짐
# - 안전모드 끄는 방법
# Edit > Preferences > SQL Editor > Safe Updates 체크박스 체크해제 > OK클릭
delete from sample.major where address = 'A관 401호';

# 3. 데이터 수정 : update
# - 문법
# update 테이블명
# set 
# 	컬럼1 = 값1, 
#	컬럼2 = 값2, 
#	...
# 	where 조건; 

# 전공코드가 456인 과의 이름은 컴퓨터공학과로 수정 
update major
set
	name = '컴퓨터공학과'
where
	code = '456';