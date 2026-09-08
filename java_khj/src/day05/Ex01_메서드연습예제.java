package day05;

public class Ex01_메서드연습예제 {

	public static void main(String[] args) {
		//예제 목적 : return을 만나면 메서드를 빠져 나오다는 것을 보여주기 위한 예제
		// => return이 끝에만 있는 것이 아님
		int num1 = 8, num2 = 12;
		System.out.println(num1+"과 "+num2+"의 최소 공배수 : "+lcm(num1,num2));

	}
	
	/* 기능: 두 정수의 최소 공배수를 구하는 메서드
	 * 매개변수 : 두 정수 > int num1, int num2
	 * 리턴타입: 최소 공배수 > 정수 > int
	 * 메서드명 : Lcm
	 * */
	
	static int lcm(int num1, int num2) {
		//int lcm = 1;
		for(int i = num1; i <= num1 * num2; i += num1) {
			if(i % num2 == 0) {
				return i;
				//lcm =i;
				//break;
			}
		}
		return num1 * num2;
	}
}