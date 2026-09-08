
/*
	- 뷰
	사용자에게는 일반 테이블과 똑같이 보이는 가상 테이블 
    물리적으로 데이터를 저장하지 않음
    하나의 select 문이 저장된 객체
    

사용하는 이유? 
	보안 : 원본 테이블의 민감한 컬럼은 숨기고, 필요한 정보면 보여줌
	편의성 : 여러 개의 테이블을 join한 뷰를 만들면 뷰를 통해 간단히 조회
	독립성 : 원본 테이블의 구조가 바뀌어도 뷰의 쿼리문만 수정하면 기존 방식 
			그대로 조회 가능 

뷰생성 
   create view 뷰명 as select문 
뷰조회
   select * from 뷰명; 
뷰삭제 
   drop view 뷰명;
*/

# 제품의 카테고리명과 제품정보를 가진 뷰를 생성

drop view if exists 제품;

create view  제품 as 
	select 
		product.name as 제품명,
        content as 제품상세,
        price as 가격,
        thumbnail as 제품썸네일,
        amount as 제고량,
        category.name as 카테고리
    from product 
		join category on category.code = ca_code;
        
select * from 제품;
    