# 4. 데이터 조회 : SELECT
# - 문법
#   - 테이블의 모든 컬럼을 확인하고 싶으면 *을 이용 
# select 컬럼1, 컬럼2, ..., 컬럼N 
# from 테이블명
# [where 조건]

use sample2;
# 등록된 과목들을 조회 
select * from subject;
# 1학년 1학기 과목들을 조회
select title from subject where grade = 1 and semester = 1;

# 논리 연산자
# and : 자바의 &&
# or : 자바의 || 
# not : 자바의 !
# 중요!!!!! : and의 우선순위가 or보다 높음 

# distinct : 중복된 결과를 제거
# select distinct 컬럼1, 컬럼2, ..., 컬럼N 
# from 테이블명
# [where 조건]

# 1학년에 들어야할 과목을 조회 
select distinct title from subject where grade = 1;

# 비교 연산자 
# = : 같다 
# <>, != : 다르다 
# 컬럼 between a and b : a이상 b이하 
# => 컬럼 >= a and 컬럼 <= b
# not between a and b : a미만 b 초과 
# 컬럼 in(값1, 값2, ..., 값N) : 컬럼 값이 in()안에 있는 값들이면 참
#  => 컬럼 = 값1 or 컬럼 = 값2 or ... or 컬럼 = 값N
# 컬럼 not in(값1, ..., 값N) : 컬럼 값이 in()안에 있는 값들과 다르면 참
#  => 컬럼 != 값1 and 컬럼 != 값2 and ... and 컬럼 != 값N 
# 컬럼 like '패턴' : 패턴과 일치하는 값을 조회할 때 사용 
#   - _ : 한글자
#   - % : 0글자이상 
#   - ___ : _3개 => 3글자 
#   - 홍% : 홍으로 시작하는 문장

# 등록된 학생들을 조회 
select * from student;

# 1학년과 2학년을 조회
select * from student where grade = 1 or grade = 2;
select * from student where grade in(1,2);

# 등록된 성적을 조회
select * from score;
# 성적이 60이상 100이하를 조회
select * from score where score >= 90 and score <= 100;
select * from score where score between 90 and 100;
# 성이 홍씨인 학생을 조회
select * from student where name like '홍%';
# 이름이 3글자인 학생을 조회
select * from student where name like '___';


# 정렬
# select distinct 컬럼1, 컬럼2, ..., 컬럼N 
# from 테이블명
# [where 조건]
# [order by 컬럼1 [asc|desc] [,컬럼2 [asc|desc]]]
# 학년, 반, 번호 순으로 정렬(다 오름차순)
select * from student order by grade, class, num;

# 학년, 반, 번호 순으로 정렬(번호만 내림차순)
select * from student order by grade, class, num desc;

# limit : 결과 중 일부만 가져옴 => 페이지네이션 구현할 때 많이 사용 
# select distinct 컬럼1, 컬럼2, ..., 컬럼N 
# from 테이블명
# [where 조건]
# [order by 컬럼1 [asc|desc] [,컬럼2 [asc|desc]]]
# [limit 시작번지, 개수] 

# 학생 성적을 처음 2개만 조회 
select * from score limit 0, 2; # limit (페이지 - 1) * 개수, 개수 
# 학생 성적을 2번째 2개만 조회 
select * from score limit 2, 2;

# 그룹화 - group by
# 행들을 묶어서 처리할 때 사용 : 1학년 1학기 1학년 1반 1번 학생의 평균은? 
# select distinct 컬럼1, 컬럼2, ..., 컬럼N 
# from 테이블명
# [where 조건]
# [group by 컬럼1 [,컬럼2, ...]]
# [order by 컬럼1 [asc|desc] [,컬럼2 [asc|desc]]]
# [limit 시작번지, 개수] 

# 집계함수
# - group by로 묶인 데이터들을 합쳐서 어떤 계산을 하는 함수들 
# - count(컬럼) : 개수를 셀 때 사용
# - sum(컬럼) : 합을 계산할 때 사용
# - avg(컬럼) : 평균을 계산할 때 사용 
# - min(컬럼) : 최소값을 찾을 때 사용
# - max(컬럼) : 최대값을 찾을 때 사용 

# 학생별 평균을 조회(모든 학년 포함)
select st_code, avg(score) from score group by st_code;

# having : 조건에 집계함수가 필요한 경우 사용 
# select distinct 컬럼1, 컬럼2, ..., 컬럼N 
# from 테이블명
# [where 조건]
# [group by 컬럼1 [,컬럼2, ...]]
# [having 조건]
# [order by 컬럼1 [asc|desc] [,컬럼2 [asc|desc]]]
# [limit 시작번지, 개수] 

# 학생별 평균 중 평균이 90이상인 학생들을 조회
SELECT 
    st_code, AVG(score) as 평균
FROM
    score
GROUP BY st_code
HAVING 평균 >= 90;

# AS : 테이블이라 컬럼에 별명을 붙여줌 
# - 사용하는 이유 : 컬럼이 긴 경우 줄여서 쓰면 편해서 
# - 컬럼이나 테이블 옆에 쓰는데 as는 생략 가능 

# join
# - 2개 이상의 테이블을 묶어서 하나의 결과 테이블로 만들어 조회할 때 사용 
# - 왜? 한 테이블로 원하는 정보를 조회할 수 없어서 
# 1. inner join
# - 두 테이블의 교집합 
# select 컬럼들 
# from 테이블1 
# join 테이블2 
# on 테이블1.컬럼 = 테이블2.컬럼

# 테이블1이 테이블2를 참조하는 경우 테이블1.컬럼은 외래키, 테이블2.컬럼 기본키 
# 각 컬럼명이 다른 테이블 컬럼들과 중복되지 않으면 앞에 붙인 테이블명을 제거해도 됨 
	# on 컬럼1 = 컬럼2 # 컬럼1이 중복안됨, 컬럼2가 중복 안됨 
# 컬럼명이 같은 경우 
	# on 테이블1.컬럼 = 테이블2.컬럼 
    # 대신 
    # using(컬럼)을 사용 
    
# 학생별 성적의 평균 
SELECT 
    name as 이름, AVG(score) as 평균
FROM
    score
        JOIN
    student ON student.code = st_code
GROUP BY st_code;

# 2.outer join
# - 기준 테이블을 기준으로 합침
#	 기준 테이블 옆에 다른 테이블을 가져와서 값이 있는 애들은 붙이고 없으면 null을 채움
# - 제품별 판매액을 조회할 때 사용
#	 > 판매 안된 제품은 0원으로 조회할 때 사용 
# - left join/right join
#	테이블A left join 테이블B > 테이블A를 기준으로 테이블B를 연결
#	테이블A right join 테이블B > 테이블B를 기준으로 테이블A를 연결

# 모든 제품의 판매 기록을 조회: outer join
# 판매된 제품들만 판매 기록을 조회: inner join

use sample2;

select 
	name as 이름, 
	count(sj_code) as 과목수 
from score 
right join student on st_code = student.code
group by student.code;

# 서브쿼리
#	쿼리안에 들어가는 쿼리
#	select문 안에 들어가는 select문
# 장점
#	쿼리를 구조화 시킴
#	join보다 가독성이 좋음
#	성능을 향상시킬 수 있음
# 서브쿼리 사용위치
#	select문에서 컬럼 대신 사용
#	from에서 테이블 대신 사용 : 이때 as테이블 별칭
#	where절에서 특정 값 대신 사용 
#	having절에서 특정 값 대신 사용
#	insert문에서 values 대신 사용
#	update문에서 값을 검색해서 바꿀 때 사용