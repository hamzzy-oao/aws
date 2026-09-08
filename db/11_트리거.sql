# 트리거
# 	테이블에 대한 이벤트에 반응해 자동으로 실행되는 작업
#	이벤트 : insert, update, delete
#	데이터 무결석을 지킬 수 있음

# 트리거 확인
# 	show triggers;

# 트리거 삭제
#	drop trigger [if exists] 트리거명;

# 트리거 정의
# delimiter
# create trigger 트리거명 트리거동작시간 트리거이벤트 on 테이블명
# for each row
# begin
# 	실행문;
# end //
# delimiter ;

# 트리거 동작 시점 :
#	트리거를 실행하는 시점
#	실행되기 전(before)또는 이벤트가 싱행된 후(after)

# 트리거 이벤트 :
# 	트리거를 실행할 이벤트. insert | update | delete

# old : 이벤트가 실행되기 전 데이터. delete, update 이벤트에서 활용함
# new : 이벤트가 실행된 후 데이터. insert, update 이벤트에서 활용함
#######


use shoppingmall;

drop trigger if exists 구매_트리거;

delimiter //
create trigger if not exists 구매_트리거
after insert on buy
for each row
begin
	# 제품 재고를 변경
	update product
    set
		amount = amount-new.amount
	where
		code = new.code;
end //
delimiter ;

insert into buy(id, code, amount) values('abc123','ACC003',3);

select * from product;
