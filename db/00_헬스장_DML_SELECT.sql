# 센터를 운영할 트레이너 등록
# 팀장 
# - 김철수, 전공 : 보디빌딩, 경력 10년 

insert into trainer(name, major, history)
values('김철수','보디빌딩',10);

# 신입 트레이너 
# - 이영희, 전공 : 요가, 경력 2년
# - 홍길동, 전공 : 보디빌딩, 경력 3년 

insert into trainer(name, major, history)
values('이영희','요가',2),('홍길동','보디빌딩',3);

# 신입 트레이너의 팀장 배정 
# - 이영희 : 김철수
# - 홍길동 : 김철수 

update trainer as t
	join trainer as leader on leader.name ='김철수'
set 
	t.leader_id = leader.id 
where
	t.name in('이영희','홍길동');
    

# 트레이너 테이블과 트레이너 테이트블 중 김철수 트레이너 정보를 join
# select * from trainer as t join trainer as leader on leader.name ='김철수';

# 사물함을 1번부터 10번까지 등록 
# 1번~5번 : 직접 추가
INSERT INTO locker
VALUES (null,null),(null,null),(null,null),(null,null),(null,null);


# 6번~10번 프로시저를 이용하여 추가
drop procedure if exists insert_locker;

delimiter //

create procedure insert_locker(
	in _begin int,
	in _end int
)

begin
	declare _i int default _begin;
    
    while _i <= _end do
    insert into locker(id, user_id) values(_i,null);
    set _i = _i +1;
	end while;
    
end //
delimiter ;

call insert_locker(6,10);
# 11번~ 15번 : 재귀 공통 테이블식(Recursive CTE)
insert into locker(id, user_id)
with recursive seq as(
	# 초기 행을 생성
	select 11 as n
	# 결과를 하나로 합침
    union all
	# 다음행을 생성
    select n + 1 from seq 
    where n < 15
)
select null, null from seq;

# 이순신 회원이 헬스장에 가입. 관리자는 1번 사물함을 배정.
# 연락처 : 020-1111-1111

insert into user(name,phone)
values('이순신','020-1111-1111');

update locker set user_id = 1 where id = 1;

# 성춘향 회원이 헬스장에 가입. 관리자는 2번 사물함을 배정. 
# 연락처 : 020-2222-2222

insert into user(name,phone)
values('성춘향','020-2222-2222');

update locker 
set 
	user_id = 2 
where 
	id = 2;


# 강좌 개설
# 강좌1 
# - 강좌명 : 모닝 요가, 정원 20명, 수강료 100,000원, 담당 : 이영희(2)
insert into program(title,personnel,fee,leader_id)
values('모닝 요가',20,100000,2);

# 강좌2 
# - 강좌명 : 지옥의 크로스핏, 정원 15명, 수강료 150,000원 담당 : 홍길동(3)
insert into program(title,personnel,fee,leader_id)
values('지옥의 크로스핏',15,150000,3);

# 새로운 강좌를 등록
# - 강좌명 : 크로스핏 초급반, 정원 : 30, 수강료 : 50,000
# - 담당 : 김철수(1)

insert into program(title,personnel,fee,leader_id)
values('크로스핏 초급반',30,50000,1);

# 수강 신청 
# - 이순신(1) 회원이 모닝 요가(1)와 지옥의 크로스핏(2)을 수강 신청 후 결재 완료 함
insert into course(program_id,user_id)
values(1,1),(2,1);
# - 성춘향(2) 회원이 모닝 요가(1)를 수강 신청 함 
insert into course(state,program_id,user_id)
values('신청',1,2);
# - 결재완료하면 '결재', 수강신청만 하면 '신청'으로 관리

# 출석체크 
# 이순신(1) 회원이 지옥의 크로스핏 강좌(2)를 출석체크 함(현재시간) 
insert into attendance(state,program_id,user_id)
values('o',2,1);

select * from attendance




