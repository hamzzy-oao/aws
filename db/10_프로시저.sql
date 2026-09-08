/*
프로시저 
일련의 쿼리를 하나의 함수처럼 실행하기 위한 쿼리의 집합

장점 : 하나의 요청으로 여러 SQL문을 실행
처리시간이 줄어듬 
참조 무결성 유지가 가능
제품을 구매 > 구매 테이블에 데이터 추가 > 제품의 재고량이 감소

단점 :
재사용이 나쁘다 
테이블명과 컬럼명이 완전히 일치하지 않으면 사용할 수 없음
업무 사양 변경 시 프로시저를 변경
*/
# 프로시저 목록 확인
show procedure status;
# 프로시저 스크립트 확인 방법
# show create procedure 프로시저명; create procedure컬럼 내용이 스크립트
use sakila;
show create procedure film_in_stock;

# 프로시저 삭제
# drop procedure if exists 프로시저명;

# 쿼리 마지막을 ;에서 //로 바꿈. 꼭 //일 필요는 없지만 기능이 없는 기호이어야 문제가 없음
# delimiter //
# create procedure if not exists 프로시저명([
# in | out | inout 변수명 타입 , 
# ...
# ])
# begin
# 프로시저 구현;
# 설명 : delimiter에서 선언한 기호와 같아야 함
# end //

# 프로시저 등록하면 쿼리 마지막을 ;으로 되돌림
# delimiter ;

# 프로시저 호출
# call 프로시저명(매개변수들)

# 프로시저 구현에 사용하는 문법들
# 문법들
# 	declare 변수명 타입 default 기본값;
# 	변수는 begin 바로 아래에 모여 있어야 함
# 변수값 설정
#	set 변수명 = 값
# 조건문 - if문
# if 조건식 then
#	실행문;
# else if 조건식 then
# 	실행문;
# else
#	실행문;
# end if;

# 조건문 - case then
#	 case 변수
# when 값 then
# 	실행문;
# when 값 then
# 	실행문;
# else
# 	실행문;
# end case;

# 조건문 - case then2
#	 case 변수
# when 조건식 then
# 	실행문;
# when 조건식 then
# 	실행문;
# else
# 	실행문;
# end case;

# 반복문 - while 
#	while 조건식 do
#		실행문
#	end while;
# 반복문 - repeat
#	repeat
#		실행문;
#	until 조건식
#	end repeat;
# 반복문 - cursor
# 	declare _done boolean;
# 	커서 변수 선언
#	declare_cursor cursor for select 속성들 from 테이블명;
#	멈출지 말지를 결정하는 변수 선언
#	declare continue handler for not found set _done = true;

# 커서 실행
# open _cursor;
# 반복설정
# 루프명 : loop
# fetch_cursor into 변수명1, 변수2, ....;

# 다 했다면 종료
# if _done then
# leave 루프명;
# end if;

# 반복문 실행문;

# 반복문 종료
# end loop;
# 커서 닫기
# close _cursor;

#
#
#

use shoppingmall;
drop procedure if exists 구매목록조회;

delimiter //
create procedure if not exists 구매목록조회(
	in _id varchar(13)
)
begin
	select * from buy join product using(code) where id = _id;
end //
delimiter ;

call 구매목록조회('abc123');

drop procedure if exists 제품등록;

delimiter //
create procedure if not exists 제품등록(
	in _name varchar(100),
    in _content text,
    in _price int,
    in _thumbnail varchar(255),
    in _ca_code char(3)
)
begin
	declare _code char(6);
    set _code = (select concat(ca_code, lpad(count(*)+1,3,'0')) 
    from product
		where ca_code = _ca_code
        group by ca_code);

	insert into product(code, name, content, price, thumbnail, ca_code)
    values(_code, _name, _content, _price, _thumbnail, _ca_code);
end //
delimiter ;

# 새제품을 추가
call 제품등록('24k목걸이2','24k 목걸이2입니다.','300000',null,'ACC');

drop procedure if exists 커서프로시저;

delimiter //
create procedure if not exists 커서프로시저(
	
)
begin
	declare _code char(6);
    declare _name varchar(100);
    declare _price int;
	declare _done boolean;
	declare _cursor cursor for select code,name,price from product;
    declare continue handler for not found set _done = true;
    
    open _cursor;
    반복:loop
		fetch _cursor into _code, _name, _price;
    
		if _done then
			leave 반복;
		end if;
        
        select concat(_code, _name, _price);
    
    end loop;
    close _cursor;
end //
delimiter ;

call 커서프로시저();
 