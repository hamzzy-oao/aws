package day05;

public class Ex07_상속 {

	public static void main(String[] args) {
		
		/* 클래스 상속 p.242
	       * - 클래스를 물려 받는 것
	       * - 부모 클래스의 필드, 메서드를 물려 받는 것
	       * - 왜 사용?
	       *   - 상속을 통해 코드의 중복을 제거
	       *   - 다형성을 통해 여러 객체를 관리할 수 있음
	       * - 상속은 is a 관계
	       *   - A는 B이다가 성립하면 A:자식 클래스, B:부모 클래스
	       *   - 스마트폰은 폰이다(O)
	       *     - 스마트폰 : 자식, 폰 : 부모
	       *   - 스마트폰은 카메라이다(X)
	       *     - 상속이 불가능
	       * - 포함은 has a 관계
	       *   - 클래스의 필드로 다른 클래스의 객체가 오는 경우
	       *   - A는 B를 가지고 있다가 성립하면 A클래스의 필드로 B를 선언
	       *   - 스마트폰은 카메라를 가지고 있다(O)
	       *     - 스마트폰 : 클래스, 카메라 : 필드
	       * - 상속하는 방법
	       *   - 부모클래스가 선언되어 있음
	       *   - extends 키워드 이용
	       *    class 자식클래스명 extends 부모클래스명{
	       *       //추가할 필드
	       * 
	       *       //추가할 메서드
	       * 
	       *       //생성자 추가
	       *    }
	       * - 상속을 받으면 부모 클래스의 모든 필드와 메서드를 사용 할 수 있다? X
	       *   => private으로 된 필드/메서드는 사용할 수 없다
	       *  
	       * - super 객체 p.257
	       *   - 부모를 가르키는 객체
	       *   - super.메서드명() : 부모 클래스의 메서드를 호출
	       * - super() 생성자
	       *   - 부모 클래스의 생성자를 호출
	       *   - 생성자 첫번째에 있어야 함
	       * - 메서드 오버라이딩(Overriding)
	       *   - 부모 클래스의 메서드를 재정의하는 것
	       *   - 부모 클래스의 메서드와 리턴타입, 메서드명, 매개변수가 동일해야 함
	       *   - 접근제어자 범위가 같거나 넓어져야 함.
	       * - 부모 클래스는 2개이상 올수 있다(X)
	       *   => 여러 클래스에게 한번에 상속 받을 수 없다
	       */
		
		스마트폰 내폰 = new 스마트폰("갤럭시26","삼성");
		내폰.정보출력();

	}

}


class 폰{
	protected String 번호;
	protected String 폰명;
	protected String 제조사;
	protected int 배터리잔량;
	protected boolean 전원;
	
	public 폰(String 폰명, String 제조사) {
		this.폰명 = 폰명;
		this.제조사 = 제조사;
	}
	
	public void 정보출력() {
		System.out.println("번호 : " + 번호);
		System.out.println("폰명 : " + 폰명);
		System.out.println("제조사 : " + 제조사);
		System.out.println("배터리잔량 : " + 배터리잔량+"%");
		System.out.println("전원 : " + (전원?"꺼짐":"꺼짐"));
	}
	
	
}
//스마트폰은 폰이다 > 성립
class 스마트폰 extends 폰{
	//스마트폰은 폰이다 > 성립하지 않음
	//스마트폰은 카메라를 가지고 있다 > 성립
	카메라 폰카메라 = new 카메라(1000);
	
	
	public 스마트폰(String 폰명, String 제조사) {
		super(폰명,제조사);
		배터리잔량 = 100;
		
	}
	
	//카메라 오버라이딩 : 상속받은 메서드를 재정의하는 것
	
	@Override
	public void 정보출력() {
		super.정보출력();
		System.out.println("카메라 화소: "+폰카메라.get화소()+"만");
	}
	
	
}

class 카메라{
	private int 화소; //100000
	
	public 카메라(int 화소) {
		this.화소 = 화소;
	}
	public int get화소() {
		return 화소;
	}
}
