package day05;

public class Ex05_static {

	public static void main(String[] args) {
		/* static p.192
	       * - static이 없는 필드와 메서드는 객체명을 통해 호출
	       *   - static 없는 필드/메서드
	       *     - 객체마다 가지고 있는 필드/메서드
	       *       => 각 객체가 공유하지 않고 자기만 사용
	       * - static이 있는 필드와 메서드는 클래스명을 통해 호출
	       *   - static 필드/메서드
	       *     - 클래스가 가지고 있는 필드/메서드
	       *       => 모든 객체가 공통으로 사용 가능(공유)
	       * 
	       * - static이 있는 필드 : 클래스 필드
	       * - static이 없는 필드 : 인스턴스 필드
	       * 
	       * - 클래스 필드는 언제 생성?
	       *   - 클래스가 메모리에 올라가면 생성
	       * - 인스턴스 필드는 언제 생성?
	       *   - new 연산자를 이용하여 객체를 만들 때 생성
	       * 
	       * p.200
	       * - 클래스 메서드에서 클래스 필드가 사용 가능?(O)
	       * - 클래스 메서드에서 인스턴스 필드가 사용 가능?(X)
	       *   => 클래스 메서드는 객체가 생성되기 전에 호출할 수 있음
	       *   => 객체가 생성되기 전이라면 인스턴스 필드가 없음
	       *   => 그래서 인스턴스 필드를 호출 할 수 없음
	       * - 인스턴스 메서드에서 인스턴스 필드가 사용 가능?(O)
	       * - 인스턴스 메서드에서 클래스 필드가 사용 가능?(O)
	       *   => 클래스 필드는 객체 생성없이도 사용 가능하기 때문에
	       *   => 객체를 생성한 후에는 사용이 가능
	       * 
	       */
		클래스A.name = "홍길동";
		//클래스A.address = "서울";//static이 안붙은 => 에러 발생
		
		클래스A 객체A = new 클래스A();
		객체A.address ="서울";
		
		클래스A 객체B = new 클래스A();
		객체B.address ="부산";
		
		객체A.print();
		객체B.print();
		
		System.out.println("static 변수 name을 임꺽정으로 수정");
		클래스A.name = "임꺽정";
		객체A.print();
		객체B.print();
		
		System.out.println("객체A의 주소를 경기도로 변경(static 변수아님)");
		객체A.address = "경기도";
		
		객체A.print();
		객체B.print();

	}

}


class 클래스A{
	public static String name;
	public String address;
	
	public void print() {
		System.out.println("------------------");
		System.out.println("이름 : "+name);
		System.out.println("주소 : "+address);
		System.out.println("------------------");
	}
	
	public static void print2() {
		System.out.println("------------------");
		System.out.println("이름 : "+name);
		//System.out.println("주소 : "+address);
		System.out.println("------------------");
	}
}
