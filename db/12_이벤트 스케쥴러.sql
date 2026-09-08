# 이벤트 스케쥴러
/*
	특정 정기적으로 실행되게 예약하는 기능
    이벤트 : 정기적으로 실행하는 작업
    스케줄 : 이벤트가 실행되는 시간
    MySQL 서버에서 이벤트를 관리하고 실행되는 엔진
    
이벤트 스케쥴러 상태확인
	ON : 스케쥴러 사용. 	OFF: 스케쥴러 사용안함
*/
show variables like 'event%';

# 이벤트 스케쥴러 상태 변경
# set global event_scheduler = ON | OFF;
# set : 변수 값 수정할 때 사용
# global : 전역 변수 선택
set global event_scheduler = ON;

# 이벤트 스케쥴러 확인 
select * from information_schema.events;

# 이벤트 스케쥴러 생성

# create event 이벤트명
# on schedule every 숫자 단위
# [staets 시간]
# [on completion preserve | on completion not preserve]
# [comment '설명']
# do
# 실행할 쿼리' 또는 프로시저 호출

# 	 preserve : 이벤트 성공 후 이벤트를 보존 > 주기적으로 반복
#	 not preserve : 이벤트 성공 후 이벤트를 삭제 > 한번만

# 	 단위 year | quarter | month | day | hour | minte | ..

# 이벤트 스케쥴러 삭제 
#	 drop event [if exists] 이벤트명;

drop event if exists 이벤트1;

create event 이벤트1
on schedule every 1 minute
on completion preserve
do
	update product set amount = amount + 1;
    
select * from product;