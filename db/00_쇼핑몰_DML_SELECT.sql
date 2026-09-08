# 쇼핑몰에 가입된 회원을 조회
use shoppingmall;
select * from user where authority = 'user';

# 모든 제품을 조회

select * from product;

# 분류가 ACC인 제품을 조회

select * from product where ca_code = 'ACC';

# 분류가 악세사리인 제품을 조회

select product.* from product
join category on product.ca_code = category.code
where category.name ='악세사리';


#abc회원이 장바구니에 담은 제품의 제품명과 수량을 조회
select * from cart
join product using(code)
where id='abc123';


insert into cart(amount,id,code) values(2,'abc123','ACC001');
insert into cart(amount,id,code) values(1,'abc123','ACC002');

# 검색어를 목걸이로 검색했을 때 결과를 조회
select * from product
where NAME like '%목걸이%';

# 제품별 판매 수량(판매된 제품만)을 조회
# 제품명, 제품코드, 가격, 판매수량 조회
select 
	code as 제품코드,
    name as 제품명,
    sum(buy.amount) as 판매량,
    sum(buy.amount *  product.price) as 판매액
from buy
join product using(code)
group by product.code;

# 회원별 제품 구매 횟수
# 회원아이디, 제품 구매횟수

select 
	id as 회원ID,
    count(buy.amount) as 구매횟수
from buy
group by id;

# abc123 회원의 장바구니를 조회
# cart 테이블에 샘플 데이터가 10만개라고 가정
# product 테이블은 샘플 데이터가 1만개라고 가정
# 회원은 1000명이라고 가정 
# 앞에서 했던 쿼리는 > 10만개 행에 제품 1만개를 join
select product.* from cart join product using(code) where id='abc123';

# 서브쿼리 이용 > abc회원이 구매한 횟수만큼 join (속도 향상)
select product.* from(select * from cart where id = 'abc123') as c
join product using(code);

# 악세서리 중 가장 많이 팔린 제품을 조회

select * from buy;
 
select
	code, sum(buy.amount) as 판매량, sum(buy.amount * price) 판매액
from 
	buy
	join product using(code)
group by code
order by 판매량 desc, 판매액 desc, code
limit 1;

