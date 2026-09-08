package day02;

import java.util.Scanner;

public class Ex08_콘솔입력예제 {

	public static void main(String[] args) {
		// 다음과 같이 입력이 되도록 코드를 작성해서 입력 받으세요.
		
		/*정수 연산자 정수 순으로 입력
		 * 예시 
		 * 정수를 입력하세요 : 1
		 * 
		 * 연산자를 입력하세요 : +
		 * 정수를 입력하세요 : 2
		 * 결과 : 1 + 2
		 * */
		Scanner scan = new Scanner(System.in); 
	      System.out.print("정수 입력하세요 : ");
	      int num = scan.nextInt();
	      System.out.print("연산자를 입력하세요 : ");
	      String str1 = scan.next();
	      System.out.print("정수 입력하세요 : ");
	      int num1 = scan.nextInt();
	      
	      System.out.print( "결과 :"+num+str1 +num1 );
    
	   

	}

}
