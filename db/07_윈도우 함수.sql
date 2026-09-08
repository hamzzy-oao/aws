# 윈도우 함수 
# - 테이블의 행과 행 사이의 관계를 정의하기 위해 제공하는 함수
# - 행에 순위를 매길 때 사용
# - over절이 들어감

# select 윈도우 함수() over(orber by 컬럼 정렬방법) 별칭, 컬럼들 from 테이블

# 1. ROM_NUMBER : 값이 같더라도 다른 번호를 부여
#	무조건 1,2,3,4,5...가 나옴
# 2. RANK : 값이 같으면 같은 등수로 표시, 다음 등수는 같은 등수의 개수만큼 건너띄어서 진행
#	1,2,2,2,5 ...
# 3. DENSE_RANK : 값이 같으면 같은 등수로 표시, 다음 등수는 이어서 진행
#	1,2,2,2,3 ...

# NITLE
#	결과 전체를 지정된 그룹으로 분할하여 번호를 할당
#	등급 간격이 동일할 때 활용
# select ntile(그룹수) oever(order by 컬럼 정렬방법) from 테이블;

use shoppingmall;
# 제품별 가격이 높은 순으로 조회
select * 
from product
order by price desc;

# row_number를 이용하여 제품별 가격이 높은 순으로 조회
select row_number()over(order by price desc)as 순위,product.*
from product;

# rank를 이용하여 제품별 가격이 높은 순으로 조회
select rank()over(order by price desc)as 순위,product.*
from product;

# dense_rank를 이용하여 제품별 가격이 높은 순으로 조회
select dense_rank()over(order by price desc)as 순위,product.*
from product;

# 가격이 비싼 제품들을 3위까지 조회하는 쿼리
# 서브 쿼리를 이용해야 함
select * from
	(select dense_rank()over(order by price desc)as 순위,product.*
from product) as p
    where 순위 <= 3;
