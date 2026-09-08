use health;

# 헬스장에 등록된 회원 목록을 조회
select * from user;
# 핼스장에 등록된 회원 수를 조회
select count(*) from user;

# 핼스장에 등록된 프로그램를 조회
select * from program;

# 모닝요가를 수강 신청 인원수를 조회
select 
	count(*) as 모닝요가수강인원
from 
	course
where
	program_id in(select 
			id 
		from 
			program 
		where 
			title ='모닝 요가');
            
# 프로그램별 수강 인원을 조회

select 
	count(*) as 프로그램수강인원
from 
	program
right join course on id = course.program_id
group by
	course.program_id;
    
select   
program.*, 
concat(count(program_id),'명') as 수강인원
 from 
  program
 left join 
	course on program_id = program.id
join
	trainer on trainer_id = trainer_id
 group by
  program.id;
  
  # 사물함을 조회
  
  # 사물함이 사용중이면 0 아니면x로 표현
  
  
  
  
  
  
  