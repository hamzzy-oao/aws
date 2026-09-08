package day05;

public class Ex04_this {

	public static void main(String[] args) {
		/* this p.176
       * - 나 자신을 가르키는 객체(주소를 저장)
       * - 클래스내에서 메서드나 생성자에서 사용
       * 
       * - 왜 사용?
       *   - 메서드, 생성자의 매개변수와 필드의 이름이 같은 경우 구분을 해야하기 때문에
       *     필드 앞에 this를 붙임
       *     
       * this() 생성자 p.182
       * - 생성자에서 다른 생성자를 이용하여 초기화 할 때 사용
       * - 생성자에서 첫번째 줄에 this()를 이용 => 안그러면 에러가 발생할 수 있음
       */
		Car car = new Car("현대","아반떼");
		car.print();
		
		Car car2 = new Car("현대","아반떼","흰색");
		car2.print(); 

	}

}

class Car{
	private String company;
	private String name;
	private String color;
	
	public void print() {
		System.out.println("제조사 : " + company);
		System.out.println("차명 : " + name);
		System.out.println("색상 : " + color);
	}
	
	public Car(String company, String name) {
		/* 메서드나 생성자에서 변수이름은 매개 변수나 지역변수를 우선으로 인식함
		 * 왼쪽은 필드 company 오른쪽 매개변수 company를 하고싶음 
		 * */
		
		this.company = company;
		this.name = name;
	}

	public Car(String company,String name, String color) {
		this(company,name);
		this.color = color;
	}
	
	
}