package day06;

public class Ex07_Object {

	public static void main(String[] args) {
		/* Object p.360
	     * - 모든 클래스의 최상위 조상이 되는 클래스
	     * - Object에 있는 메서드를 사용 또는 오버라이딩 하여 사용할 수 있다
	     * 
	     * equals(클래스명 객체) p.367
	     * - 객체를 비교할 때 사용하는 메서드
	     * - Object.equals는 주소를 비교하도록 구현
	     *   => 메서드 오버라이딩(재정의)해서 필드를 비교하여 같다와 같지 않다를 판별
	     *   
	     * toString() p.363
	     * - 객체의 필드(멤버 변수)를 이용하여 문자열을 만들어 반환하는 메서드
	     */
		
		A a1 = new A(1);
		A a2 = new A(1);
		
		//a1이 저장한 주소와 a2가 저장한 주소가 달라서 false가 다름
		System.out.println(a1 == a2);
		
		System.out.println(a1.equals(a2));
		System.out.println(a1.equals("1"));
		
		System.out.println(a1);

	}

}

class A{
	int num;
	public A(int num) {
		this.num = num;
	}
	public void print() {
		System.out.println(num);
	}
	
	@Override
	public boolean equals(Object obj) {
		//참조하는 객체가 같은 주소인지 확인
		if(this == obj)
			return true;
		//비교대상이 없는 경우
		if(this == obj)
			return false;
		//클래스가 다르면
		if(getClass() !=obj.getClass())
			return false;
		//클래스가 A가 같으면
		A other =(A) obj;
		//필드를 비교하여 같은지 다른지를 판별
		return num == other.num;
	}
	@Override
	public String toString() {
		return " [num=" + num + "]";
	}
	
	
	
}