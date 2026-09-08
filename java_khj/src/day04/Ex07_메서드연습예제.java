package day04;

public class Ex07_메서드연습예제 {

	public static void main(String[] args) {
		//다음은 최대 공약수를 구하는 코드입니다. 다음 코드를 이용하여 최대 공약수를 구하는 메서드를 만드세요.
		int num1 = 8, num2 = 12;
		
		int gcd = gcd(num1, num2);
		System.out.println(num1 +"과 " + num2 +"의 최대 공약수 : " + gcd);
		
		gcd2(num1, num2);
		//gcd를 이용해서 최소 공배수를 출력하는 코드를 작성하세요.
		//최소공배수 = A * B / gcd
		
		int lcm = num1 * num2 / gcd(num1, num2);
		System.out.println(num1 +"과 " + num2 +"의 최소 공배수 : " + lcm);
	}
	/* 기능 : 주어진 두 정수의 최대 공약수를 알려주는 메서드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 최대 공약수 => 정수 => int
	 * 메서드명 : gcd
	 * */
	static int gcd(int num1, int num2) {
		
		int gcd = 1;
		for(int i = 2; i<=num1; i++) {
			if(num1 % i == 0 && num2 % i == 0) {
				gcd = i;
			}
		}
		return gcd;
	}
	/* 기능 : 주어진 두 정수의 최대 공약수를 콘솔에 출력하는 메서드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 없음 => void
	 * 메서드명 : gcd2
	 * */
	static void gcd2(int num1, int num2) {
		int gcd = 1;
		for(int i = 2; i<=num1; i++) {
			if(num1 % i == 0 && num2 % i == 0) {
				gcd = i;
			}
		}
		System.out.println(num1 +"과 " + num2 +"의 최대 공약수 : " + gcd);
	}
}

