package day06;

public class Ex02_추상클래스 {

	public static void main(String[] args) {
		
		/* 추상 클래스 p.290
	       * - 클래스 앞에 abstract 키워드가 붙은 클래스
	       * - 보통 추상 클래스는 추상 메서드를 가지고 있음
	       * - 추상 클래스는 객체를 생성할 수 없음 p.298
	       *   => 기능 구현이 안된 메서드가 있기 때문에
	       *   => 추상 클래스의 객체를 생성하려면 추상 클래스를 상속받은 클래스를 만든 후
	       *      추상 메서드를 오버라이딩하여 구현 후 객체를 생성
	       * 
	       * 추상 메서드 p.290
	       * - 메서드를 정의하는 부분만 있고, 구현하는 부분이 없는 메서드
	       * - 이런 기능이 있을 예정인데, 아직 구현 안했음
	       * - 일반 메서드 형태
	       *   리턴타입 메서드명(매개변수) { 구현 }
	       * - 추상 메서드 형태
	       *   리턴타입 메서드명(매개변수);
	       * 
	       */
		
		Computer com1 = new LgComputer();
		com1.printState();
		
		Computer com2 = new SamsungComputer();
		com2.printState();
			
	}

}
//추상메서드
abstract class Computer{
	
	protected boolean power;
	
	public void turnOn() {
		power = true;
	}
	public void turnOff() {
		power = false;
	}
	//추상 메서드. 컴퓨터 상태를 출력하는 기능
	public abstract void printState();
}

class LgComputer extends Computer{
	
	@Override
	public void printState() {
		System.out.println("LG 컴퓨터입니다.");
	}
	
}
class SamsungComputer extends Computer{
	
	@Override
	public void printState() {
		System.out.println("삼성 컴퓨터입니다.");
	}
	
}
