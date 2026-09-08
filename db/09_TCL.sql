# TCL
# - 트랜잭션 제어 언어 
# - 트랜잭션 : DB 상태를 변화 시키기 위해 수행하는 작업 단위 
# - 특징 : 원자성(Atomicity), 일관성(Consistency), 
#         고립성(Isolation), 영속성(Durability)
#   - 원자성 : 트랜잭션 작업은 전부 성공하거나 전부 실패하여 취소 되어야 함 
#   - 일관성 : 트랜잭션이 성공적으로 실행되면, 언제나 일관성 있는 상태로 유지 되어야 함 
#   - 고립성 : 트랜잭션이 실행되는 동안에 다른 트랜잭션이 현재 작업에 껴들수 없음 
#   - 영속성 : 성공적으로 완료된 트랜잭션의 결과는 시스템 장애가 발생해도 영구적으로 반영되어야 함

# MySQL은 자동 커밋이 설정 되어 있어서 우리가 작성한 쿼리들이 바로 커밋됨 

# START TRANSACTION/BEGIN/BEGIN WORK : 트랜잭션 시작 
# ROLLBACK : 트랜잭션 시작 전상태로 되돌림 
# ROLLBACK TO 저장명 : 저장명 전상태로 되돌림 
# SAVEPOINT 저장명 : 임시로 저장할 위치를 지정 
# COMMIT : 지금까지 했던 작업을 반영

use shoppingmall;

start transaction;

# 작업 - 제품추가
insert into product(code,name,content,price,ca_code)
values('ACC006','기본반지','기본반지입니다.',10000,'ACC');

# 롤백 - 가장 마지막으로 반영된 시점으로 되돌림
rollback;

# 트랜잭션 시작
begin;

# 작업 - 제품 추가
insert into product(code,name,content,price,ca_code)
values('ACC007','기본반지','기본반지입니다.',10000,'ACC');

# 저장 지점 설정
savepoint insert_product;

# 작업 - 장바구니에 담기
insert into cart(id,code,amount) values('abc123','ACC002',3);

rollback to insert_product;
# 제품은 추가 됐지만 장바구니에 담는 작업은 실행되지 않음
