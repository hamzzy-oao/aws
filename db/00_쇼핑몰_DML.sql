# 다음 회원을 추가
# 아이디: abc123, 비번:abc123, 이메일:abc123@naver.com
# 번호 : 011-1111-2222

insert into user(id,pw,email,phone)
values('abc123','abc123','abc123@naver.com','011-1111-2222');

# 제품 분류 추가
# 제품 분류 코드: ACC, 분류명 : 악세사리

insert into category(CODE,NAME)
values('ACC','악세사리');

# 제품 추가 
# 악세서리 -  제품명 : 18K목걸이, 내용 : 예쁜 목걸이입니다. 가격 : 100000, 썸네일 없음 

insert into product(code,NAME,content,price,thumbnail, ca_code)
values('ACC001','18K목걸이','예쁜 목걸이입니다.',100000,NULL,'ACC');

# 악세서리 -  제품명 : 18K반지, 내용 : 예쁜 반지입니다. 가격 : 100000, 썸네일 없음

insert into product(code,NAME,content,price,thumbnail, ca_code)
values('ACC002','18K반지','예쁜 반지입니다.',100000,NULL,'ACC');

# abc123회원이 ACC001을 2개 장바구니에 담음 
insert into cart(num,amount,id,code)
values(null,2,'abc123','ACC001');

# abc123회원이 ACC001을 1개 장바구니에 담음 

update cart
set
amount = 1
where
id='abc123' and code = 'ACC001';

# abc123회원이 ACC002를 1개 장바구니에 담음

insert into cart(num,amount,id,code)
values(null,1,'abc123','ACC002');

# 제품 입고(제품 수량 추가) 
update product
set
amount = amount + 10
where
code in('ACC001','ACC002');


# abc123회원이 장바구니에 있는 모든 제품들을 구매

insert into buy(id,code,amount)
values('abc123','ACC001',1),('abc123','ACC002',1);

select * from buy;

# 장바구니 목록에서 구매한 제품들 삭제 

delete from cart where id = 'abc123';

select * from cart;
# 수량 감소 

update product
set
amount = amount - 10
where
code in('ACC001','ACC002');

insert into cart(amount,id,code) values(1,'abc123','ACC002');
insert into cart(amount,id,code) values(2,'abc123','ACC001');

# 다음 회원을 추가
# 아이디: asdasd, 비번:asdasd, 이메일:asdasd@naver.com
# 번호 : 011-9999-9999

insert into user(id,pw,email,phone)
values('asdasd','asdasd','asdasd@naver.com','011-9999-9999');

#asdasd 회원이 acc01 제품 2개를 구매

insert into buy(code,amount,id)values('ACC001',2,'asdasd');

# 제품 수량 변경

update product set amount = amount-2 where code = 'ACC001';

# 제품 추가 
# 악세서리 -  제품명 : 백금목걸이, 내용 : 백금목걸이입니다. 가격 : 50000, 썸네일 없음 
insert into product(code,NAME,content,price,thumbnail, ca_code)
values('ACC003','백금목걸이','백금목걸이입니다.',50000,NULL,'ACC');

# 악세서리 -  제품명 : 커플목걸이, 내용 : 커플목걸이입니다. 가격 : 150000, 썸네일 없음 
insert into product(code,NAME,content,price,thumbnail, ca_code)
values('ACC004','커플목걸이','커플목걸이입니다.',150000,NULL,'ACC');

# 악세서리 -  제품명 : 돌목걸이, 내용 : 아이돌목걸이입니다. 가격 : 350000, 썸네일 없음 
insert into product(code,NAME,content,price,thumbnail, ca_code)
values('ACC005','돌목걸이','아이돌목걸이입니다.',350000,NULL,'ACC');

# 모든 제품 재고 입고
update product set amount = amount + 10;

