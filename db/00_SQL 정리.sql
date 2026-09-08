/*
데이터베이서 설계 과정
1. 요구사항 명세서
2. ERD : 개념적 설계
3. 릴레이션 : 논리적 설계
4. DB 및 테이블 : 물리적 설계

DDL : DB 및 테이블 관리
- create, drop alter

DML : 데이터를 관리 
-insert, delete, update, select(중요)

insert : 데이터 추가
1. insert ~ values
추가할 데이터를 모두 알고 있을 때 사용 

2. insert ~ select
추가할 데이터 중 일부가 검색이 필요할 때 사용

delete : 데이터 삭제
- where절을 안쓰면 모든 데이터가 삭제됨 
단, 기본키의 ai값을 초기화 되지 않음

update : 데이터 수정
- where절을 안쓰면 모든 데이터가 수정됨

select : 데이터 조회
- where절 : 검색 조건을 설정
- order by절 :정렬
- group by절 : 데이터를 합쳐서 계산할 때 사용
- limit절 : 데이터를 잘라서 일부를 가져올 때 (왜 ? 다 가져오면 데이터가 많아서 성능이 저하됨)
- having절 : group by 이용시 사용하는 집계함수를 이용하여 조건을 설정할 때 사용
- distinct : 중복된 행을 제거

내장함수 : MySQL에서 제공하는 함수
윈도우함수 : 순위를 매기는 함수

DCL : 사용자 추가 및 삭제, 권한 부여 및 회수 
-create, drop, grant, revoke

TCL : 트랜잭션을 제어 
- MYSQL은 자동 커밋 > DML이 바로 반영
- 트랜잭션 시작, 저장, 되돌리기, 반영

*/