create database if not exists mall;

create user 'mall_admin'@'%' identified by '1234';

grant all privileges on mall.* to 'mall_admin'@'%';



# 관계 데이터 모델
# 다음 조건에 맞는 회원(member) 테이블과 상품(product) 테이블을 생성하는 쿼리를 작성하세요.
# - member
#	- m_id : 아이디, 정수, 기본키
#	- m_name : 이름, 최대 20자, 필수
#	- m_point : 포인트, 정수, 기본값 0
# - product
#	- p_id : 제품번호, 정수, 기본키
#	- p_name : 제품명, 최대 50자
#	- p_price : 가격, 정수
#	- p_stock : 수량, 정수

USE mall;

CREATE TABLE member(
	`m_id`	INT NOT NULL PRIMARY KEY,
	`m_name` varchar(20) NOT NULL,	
	`m_point` int default 0	
);

CREATE TABLE product(
	`p_id`	INT NOT NULL PRIMARY KEY,
	`p_name` varchar(50) NULL,	
	`p_price` int NULL,
	`p_stock` int NULL 
);

# 데이터 모델링
# 회원이 상품을 주문하는 주문(orders) 테이블을 생성하고, 외래키를 설정하는 쿼리를 작성하세요.
#	- o_id : 주문번호, 정수, 기본키
#	- m_id : 주문자 아이디, 정수
#	- p_id : 주문 제품번호, 정수
#	- o_qty : 주문 수량, 정수
#	- o_date : 주문일, 날짜
# - 외래키 조건
#	- m_id는 회원(member) 테이블을, p_id는 상품(product)를 참조하도록 외래키를 설정

CREATE TABLE orders(
	`o_id`	int	primary KEY auto_increment,
	`m_id` int NULL,	
	`p_id` int NULL,
	`o_qty` int NULL,
    `o_date` datetime not null default current_timestamp 
);

ALTER TABLE `orders` ADD CONSTRAINT `FK_member_TO_orders_1` FOREIGN KEY (
	`m_id`
)
REFERENCES `member` (
	`m_id`
);

ALTER TABLE `orders` ADD CONSTRAINT `FK_product_TO_orders_1` FOREIGN KEY (
	`p_id`
)
REFERENCES `product` (
	`p_id`
);


#  시스템 운영을 위해 기초 데이터를 입력하고, 정상적으로 들어갔는지 확인해야 합니다.
#	- 4-1 : 다음 데이터를 추가하는 쿼리를 작성하세요.
# - 회원
#	- 아이디 : 1, 이름 :홍길동, 포인트 :1000
#	- 아이디 : 2, 이름 :김철수, 포인트 :500
insert into member(m_id,p_name,m_point)
values(1,'홍길동',1000),(2,'김철수',500);
#	- 상품
#	- 상품 번호 :101, 상품명 : 노트북, 가격 :1500000, 재고 :10
#	- 상품 번호 :102, 상품명 : 마우스, 가격 :30000, 재고 :50
insert into product(p_id,p_name,p_price,p_stock)
values(101,'노트북',1500000,10),(102,'마우스',30000,50);

#	- 4-2 : 모든 회원의 이름과 포인트, 그리고 모든 상품의 이름과 재고량을 각각 조회하여 데이터가 정확히 입력되었는지 확인하는 쿼리를 작성하세요.
select * from member;
select * from product;

# 정규화, 트랙잭션, 동시성 제어, 회복
# 문제 발생 시 결제와 재고 처리는 동시에 완결되어야 합니다. 다음 과정을 하나의 트랜잭션으로 처리하는 쿼리를 작성하고, 작업이 잘 되었는지 조회하는 쿼리를 작성하세요.
start transaction;

#	- 조건: 1번 회원이 101번 상품을 2개 주문함
insert into orders(o_id, m_id, p_id, o_qty, o_date)
VALUES (1, 1, 101, 2, CURDATE());
#	- product 테이블: 101번 상품 재고 2개 감소
update product
set 
	p_stock = p_stock - 2
WHERE p_id = 101;
#	- member 테이블: 1번 회원 포인트 100점 증가
UPDATE member
SET m_point = m_point + 100
WHERE m_id = 1;
#	- orders 테이블: 주문 내역 추가 (1번 회원, 101번 상품, 수량 2, 오늘 날짜)
COMMIT;
#	- 1번 회원이 주문한 상품명을 조회(JOIN 이용)
select   
orders.*, 
product.p_name
 from 
  orders
 left join 
	product on orders.p_id = product.p_id
 WHERE
  orders.m_id = 1;
# 배송 업체에 데이터를 전달해야 하지만, 회원의 포인트 정보는 보안상 숨겨야 합니다. member 테이블에서 포인트 컬럼을 제외하고, m_id, m_name만 조회하는 보안용 가상 테이블 v_member_public을 생성하는 쿼리를 작성하세요.

drop view if exists v_member_public;

create view  v_member_public as 
	select 
		member.m_id,
        member.m_name
    from member;
    
select * from v_member_public;