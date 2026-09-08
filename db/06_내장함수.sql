# 내장함수
# if(수식, 수식1, 수식2) : 수식이 참이면 수식1을, 거짓이면 수식2를 리턴
set @age = 10;
select if(@age > 19, '성인','미성년자') as '나이는';

# ifnill(수식1, 수식2) : 수식1이 null이 아니면 수식1을, null이면 수식2를 반환
#	null값일 때 넣을 기본값 설정할 때 사용 
set @age2 = null;
select ifnull(@age2,0) as '하나는 null일때' ,ifnull(@age,0) as '둘다 값일때';

# nullif(수식1, 수식2) : 수식1과 수식2과 같으면 null, 다르면 수식1을 반환
set @age3 = 10;
select nullif(@age,@age3) as '값이 같을때', nullif(@age,@age2) as '값이 다를때';

# case 컬럼
# when 값1 then
#	결과
# when 값2 then
#	결과
# else
#	결과
# end

set @grade = 'A+';
select
	case @grade
		when 'A+' then '장학금 가능'
		when 'F' then '학고 가능'
		else  '??'
    end as 결과;
    
# case
# when 조건 then 결과
# else 결과
# end

select
	case
		when @age > 19 then '성인'
		else '미성년자'
	end as 결과;
    
# 문자열 내장 함수 
# CHAR_LENGTH(문자열) : 문자열의 길이 
# LENGTH(문자열) : 문자열의 바이트수 
SELECT 
   CHAR_LENGTH("ABC") 'ABC길이', LENGTH("ABC") ABC크기,
   CHAR_LENGTH("가나다") '가나다길이', LENGTH("가나다") 가나다크기;

# CONCAT(문자열1, 문자열2, ...) : 문자열을 이어 붙임 
SELECT CONCAT("%", "검색어", "%");

# FIELD(찾을문자열, 문자열1, 문자열2, ...) : 찾을 문자열이 어디 있는지 위치를 반환
# 1부터 시작, 없으면 0 반환 
SELECT FIELD("ABC", "ABC123", "123ABC", "ABC");

# INSTR(기준문자열, 부분문자열) : 기준문자열에서 부분문자열을 찾아 시작 위치를 반환 
# LOCATE(부분만자열, 기준문자열) : 기준문자열에서 부분문자열을 찾아 시작 위치를 반환 
# INSTR과 LOCATE 차이는 매개변수 순서
SELECT INSTR("ABC123", "123"), LOCATE("123", "ABC123");

# FORMAT(숫자, 소수점자리수) : 숫자를 소수점 이하 자리까지 표현. 1000단위마다 ,추가 
SELECT CONCAT(FORMAT(1000000, 0), "원");

# BIN(숫자), OCT(숫자), HEX(숫자) : 2,8,16진수로 변환 
SELECT BIN(15), OCT(15), HEX(15);

# INSERT(기준문자열, 위치, 길이, 삽입할문자열) : 기준문자열에서 위치부터 길이만큼 지우고
# 그 위치에 삽입할문자열을 추가 
SELECT INSERT("I love JAVA", 8, 4, "PYTHON");

# LEFT(문자열, 길이), RIGHT(문자열, 길이) : 왼쪽/오른쪽부터 길이만큼 문자열을 반환 
SELECT LEFT("ACC001", 3) 카테고리명, RIGHT("BIRD.JPG", 3) 확장자;

# LOWER(문자열), UPPER(문자열) : 모든 문자열을 소문자/대문자로 
SELECT LOWER("aBCdef"), UPPER("aBCdef");

# LPAD(문자열, 길이, 채울문자열), RPAD(문자열, 길이, 채울문자열) 
# - 문자열을 길이만큼 늘리고 왼쪽/오른쪽 빈곳을 채울문자열로 채움 
SELECT LPAD(1, 3, '0'), RPAD(1, 3, '0');
SELECT CONCAT('ACC', LPAD(1,3,'0')) 제품코드;

# LTRIM, RTRIM, TRIM(문자열) : 문자열 왼쪽/오른족/양쪽 공백을 제거 
SELECT 
   LTRIM("     A B C   "), CHAR_LENGTH(LTRIM("     A B C   ")),
    RTRIM("     A B C   "), CHAR_LENGTH(RTRIM("     A B C   ")),
    TRIM("     A B C   "), CHAR_LENGTH(TRIM("     A B C   "));

# REPEAT(문자열, 횟수) : 문자열을 횟수만큼 반복
SELECT REPEAT("ABC", 3);

# REVERSE(문자열) : 문자열 순서를 반대로
SELECT REVERSE("ABC");

# REPLACE(문자열, 문자열A, 문자열B) : 문자열에서 문자열A를 찾아 문자열B로 교체 
SELECT REPLACE("I love JAVA", "JAVA", "PYTHON");

# SUBSTRING(문자열, 시작위치, 길이) : 문자열에서 시작위치부터 길이만큼 부분문자열을 반환
SELECT SUBSTRING("I love JAVA", 8, 4);

# 날짜/시간 함수 
# NOW(), SYSDATE() : 현재 시간을 반환 
SELECT NOW(), SYSDATE();

# ADDDATE/SUBDATE(날짜, 차이)
# 날짜를 기준으로 차이만큼 일을 더한/뺀 날짜를 반환
SELECT ADDDATE(NOW(), 2), SUBDATE(NOW(), 2); 

# ADDTIME/SUBTIME(날짜|시간, 차이) 
# 날짜/시간을 기준으로 차이만큼 더한/뺀 날짜/시간을 반환 
# 차이에 "시:분:초" 형태로 넣어주어야 함. 
SELECT ADDTIME(NOW(), "3:00:00"), SUBTIME("17:00:00", "2:00:00");

# YEAR/MONTH/DAY(날짜) : 날짜에서 년/월/일을 반환
SELECT YEAR(NOW()), MONTH(NOW()), DAY(NOW());

# HOUR/MINUTE/SECOND/MICROSECOND(시간) : 시간에서 시/분/처/마이크로초를 반환 
SELECT 
   HOUR(NOW()) 시,
   MINUTE(NOW()) 분,
    SECOND(NOW()) 초,
    MICROSECOND("2025-12-16 12:13:12.123") 마이크로초;

# DATE/TIME(날자) : 날짜에서 년-월-일/시:분:초를 추출 
SELECT DATE(NOW()), TIME(NOW());

# DATEDIFF(날짜1, 날짜2) : 날짜1에서 날짜2의 차이를 일로 반환. 날짜1 - 날짜2 
SELECT DATEDIFF("2026-06-01", NOW()) 남은수강일;

# TIMEDIFF(날짜1/시간1, 날짜2/시간2) 
# - 날짜1/시간1에서 날짜/시간2의 차이를 시:분:초로 반환. 날짜1/시간1 - 날짜2/시간2 
SELECT TIMEDIFF("12:20:00", TIME(NOW()));

# DATE_ADD/DATE_SUB(기준날짜, INTERVAL)
# - 기준날짜에서 INTERVAL만큼 더한/뺀 날짜 
# - INTERVAL 종류
#   - 시간유형
#     - YEAR/MONTH/DAY : 년/월/일 
#     - HOUR/MINUTE/SECOND/MICROSECOND : 시/분/초/마이크로초
#     - QUARTER/WEEK : 분기/주 
#   - 조합유형
#     - YEAR_MONTH : 년월 
#     - DAY_HOUR(일 시간), DAY_MINUTE(일 시간:분), 
#       DAY_MICROSECOND(일 시간:분:초.마이크로초)
SELECT 
   DATE_ADD(NOW(), INTERVAL 1 YEAR) 1년후,
   DATE_ADD(NOW(), INTERVAL 1 QUARTER) 1분기후,
    DATE_ADD(NOW(), INTERVAL "1 1" YEAR_MONTH) 1년1달후,
    DATE_ADD(NOW(), INTERVAL "2 3:00:00.000" DAY_MICROSECOND) 2일3시간후;
