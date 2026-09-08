package day06;

public class Ex03_final {

	public static void main(String[] args) {
		/* final p.314
		 * - final : 바뀌지 않은
	     * - 변수, 메서드, 클래스에 final을 붙일 수 있음
	     * - 변수 앞에 붙이면 상수
	     * - 메서드 앞에 붙이면 오버라이딩이 불가능
	     * - 클래스 앞에 붙이면 상속이 불가능 => 부모 클래스가 될 수 없음
	     * - 대표적으로 String 클래스
	     * 
	     * 오버라이딩 : 부모 클래스의 메서드를 재정의 하는 것 
	     */
		final int MAX = 10;
		//MAX = 1; < 상수이기 때문에 재할당 불가능
		

	}

}

class P{
	
	void print() {}
	final void print2() {}
}

class C extends P{
	
	@Override
	void print() {}
	@Override
	void print2() {} //final 메서드를 재정의 하려고 해서 에러 발생
}

final class P2{}
//final 클래스인 P2는 자식 클래스를 만들 수 없음
class C2 extends P2{}