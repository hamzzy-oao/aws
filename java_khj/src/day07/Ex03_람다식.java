package day07;

public class Ex03_람다식 {

	public static void main(String[] args) {
		  /* 
	       * 람다식 p.479
	       * - 언제 사용?
	       *   - 함수형 인터페이스의 객체를 만들 때 사용(편리하게 만들 때)
	       *   - 인터페이스의 객체를 만들려면
	       *     => 구현 클래스를 이용하여 객체를 생성 해야 함
	       *     => 익명 클래스를 이용하여 객체를 생성할 때 람다식을 이용
	       *   
	       * - 함수형 인터페이스
	       *   - 추상 메서드가 1개로 구성된 인터페이스
	       * - 어떻게 적용
	       *  함수형인터페이스 객체명 = (매개변수명1, 매개변수명2)->{
	       *       구현
	       *  };
	       * - 매개변수가 1개이고 구현 코드가 한줄일 때
	       *  함수형인터페이스 객체명 = 매개변수명1-> 구현;
	       */
		
		//함수형 인터페이스의 객체를 만드는데 재사용도 안하고 코드도 간단한 경우 람다식 사용
		Mysum mysum = (num1 , num2)->{
			return num1 + num2;
		};
		System.out.println(mysum.sum(1,2));

	}

}
@FunctionalInterface //없어도 됨 역할 : 함수형 인터페이스인지 체크
interface Mysum{
	int sum(int num1, int num2);
}