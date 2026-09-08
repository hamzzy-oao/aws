package day05;

public class Ex06_필드초기화 {

	public static void main(String[] args) {
		/* 필드 초기화 순서
	       * 1. 각 자료형의 기본값으로 초기화
	       * 2. 명시적 초기화
	       *   - 변수 선언 후 바로 초기값을 넣은 경우
	       * 3. 초기화 블록
	       *   - 클래스 안 {}로 되어 있는 블록에서 초기화가 진행 
	       * 4. 생성자
	       */
		초기화 객체 = new 초기화();
		객체.print();

	}

}

class 초기화{
	int num1; // int의 기본값인 0 으로 초기화
	int num2 = 10; //int의 기본값인 0으로 초기화가 됐다가 10으로 명시적 초기화
	int num3 = 10; //int의 기본값인 0으로 초기화가 됐다가 10으로 명시적 초기화
	int num4 = 10; //int의 기본값인 0으로 초기화가 됐다가 10으로 명시적 초기화

	{
		num3 = 20; //명시적 초기화인 10 => 초기화 블록값인 20으로 변경
		num4 = 20; //명시적 초기화인 10 => 초기화 블록값인 20으로 변경
	}
	
	public 초기화() {
		num4 = 30;// 명시적 => 초기화 블록 => 생성자값인 30으로 변경
	}
	
	public void print() {
		System.out.println("num1 : "+ num1);
		System.out.println("num2 : "+ num2);
		System.out.println("num3 : "+ num3);
		System.out.println("num4 : "+ num4);
	}
	
	

}