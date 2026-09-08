package day04;

public class Ex07_메소드오버로딩 {

	public static void main(String[] args) {
		/* 메서드 오버로딩
		 * > 기능은 같은데 다양한 형태의 매개변수를 처리하고 싶어서
		 * - 동일한 이름의 메서드가 여러개 있는 경우
		 * - 조건 : 매개변수가 다름. 둘 중 하나를 만족하면 됨
		 *   1. 매개변수의 종류가 다름
		 *   2. 매개변수의 개수가 다름
		 * */
		System.out.println(sum(1,2));
		System.out.println(sum(1,2,3));
		System.out.println(sum(1.2,3.4));
		/* 대표적인 메소드 오버로딩
		 * */

	}
	//매개변수의 개수가 달라서 메소드 오버로딩이 적용
	
	static int sum(int num1, int num2) {
		return num1 + num2;
	}
	static int sum(int num1, int num2, int num3) {
		return num1 + num2 + num3;
	}
	//매개변수의 종류가 달라서 메소드 오버로딩이 적용
	static double sum(double num1, double num2) {
		return num1 + num2;
	}


}
