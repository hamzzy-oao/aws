package day02;

import java.util.Scanner;

public class Ex10_if문연습예제 {

	public static void main(String[] args) {
		/* scanner를 이용하여 정수를 입력받아 정수가 양수인지, 음수인지 판별하는 코드를 작성하세요.
		 * 
		 * 예시1
		 * 정수 입력 : 10
		 * 10은 양수
		 * 
		 * 예시2
		 * 정수 입력: -3
		 * -3은 음수
		 * 
		 * 예시3
		 * 정수 입력: 0
		 * 0
		 * 
		 * */ 
		
		Scanner scan = new Scanner(System.in);
		System.out.print("정수 입력 :");
		int num = scan.nextInt();
		
		
		if(num > 0) {
			System.out.println(num + "는 양수");
		}else if(num < 0) {
			System.out.println(num + "는 음수");
		}else{
			System.out.println(num);
		}
		
		
	
	}
}
