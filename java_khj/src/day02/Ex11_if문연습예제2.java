package day02;

import java.util.Scanner;

public class Ex11_if문연습예제2 {

	public static void main(String[] args) {
		/*Ex08_콘솔입력연습예제를 참고해서 두 정수와 연산자를 입력해서 연산 결과를 출력하는 코드를 작성하세요.
		 * - 연산자는 +,-,*,/,% 만 처리
		 * 
		 * 예시
		 * 정수1 입력:1
		 * 연산자 입력:/
		 * 정수2 입력: 2
		 * 1/2 =0.5
		 * 
		 * 정수1 입력:1
		 * 연산자 입력: ?
		 * 정수2 입력: 2
		 * ?는 산술연산자가 아닙니다. 
		 * */
		
		Scanner scan = new Scanner(System.in);
		 System.out.print("정수1 입력 : ");
	      int num = scan.nextInt();
	      System.out.print("연산자 입력 : ");
    
	      char op = scan.next().charAt(0);
	      System.out.print("정수2 입력 : ");
	      int num1 = scan.nextInt();
		
		
		if(op == '+') {
			System.out.println(""+num+op+num1+"="+(num+num1));
		}else if(op == '-') {
			System.out.println(""+num+op+num1+"="+(num-num1));
		}else if(op == '*') {
			System.out.println(""+num+op+num1+"="+(num*num1));
		}else if(op == '/') {
			System.out.println(""+num+op+num1+"="+(num/num1));
		}else if(op == '%') {
			System.out.println(""+num+op+num1+"="+(num%num1));
		}
		else{
			System.out.println(op+"는 산술연산자가 아닙니다");
		}
		

	}

}
