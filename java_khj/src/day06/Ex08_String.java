package day06;

public class Ex08_String {

	public static void main(String[] args) {
		  /* String 클래스 p.377
	       * - 문자열을 관리하는 클래스
	       * - final char[]로 문자열을 관리
	       * - 문자열이 변경되면 기존 문자열에서 변경되는게 아니라 새로운 문자열을 만들어서 연결
	       * 
	       *   예시
	       *   String str = "abc";
	       *   str = str + "d"; //abc 뒤에 d를 이어 붙이는게 아니라 abcd라는 문자열을 새로 만들어 연결
	       * 
	       * StringBuffer(쓰레드 지원)와 StringBuilder p.380
	       * - char[]로 문자열을 관리
	       * - 둘의 차이는 쓰레드 지원여부
	       * 
	       * String 클래스 메서드
	       * - 문자열A.indexOf(문자열B) : 문자열이 있으면 시작 번지를 알려줌
	       * - 문자열A.equals(문자열B) : 두 문자열이 같으면 비교하여 true/false를 반환
	       * - 문자열A.contains(문자열B) : 문자열이 있으면 true, 없으면 false
	       * - 문자열A.lenght() : 문자열의 길이
	       * - 문자열A.substring(시작위치) : 시작위치부터 끝까지 문자열 반환
	       * - 문자열A.trim() : 첫글자 앞 공백 제거, 마지막 글자 뒤 공백 제거
	       * - 문자열A.split(구분자) : 구분자를 기준으로 문자열을 추출하여 문자열 배열을 반환
	       */
	      String str1 = "Hello world";
	      String str2 = "world";
	      String str3 = "\n\t\t\t안녕 하세요\n\n\n";
	      String str4 = "사과,배,바나나,오렌지";
	      
	      int index = str1.indexOf(str2);
	      System.out.println("str1에 str2가 몇번지부터 시작됩니까? " + index);
	      System.out.println("str1에 str2가 있습니까? " + str1.contains(str2));
	      
	      System.out.println("str1이 str2와 같습니까? " + str1.equals(str2));
	      
	      System.out.println("str1의 길이는? " + str1.length());
	      
	      System.out.println("str1의 2번지부터 부분문자열은? " + str1.substring(2));
	      
	      System.out.println("str3.trim() : " + str3.trim());
	      
	      String fruits [] = str4.split(",");
	      for(String fruit : fruits) {
	         System.out.println(fruit);
	      }

	}

}
