package day03;


public class Ex11_반복문연습예제1 {

	public static void main(String[] args) {
		/* 두 정수의 최대 공약수를 구하는 코드를 작성하세요.
		 * 약수 : 나누어 떨어지는 수
		 * 12의 약수 : 1 2 3 4 6 12
		 * 8의 약수 : 1 2 4 8
		 * 
		 * 공약수 : 약수들 중 공통으로 있는 수들
		 * 8과 12의 공약수 : 1 2 4
		 * 
		 * 최대 공약수 : 공약수 중 가장 큰수
		 * 8과 12의 공약수 : 4
		 * 반복횟수 : i는 1부터 num1까지 1까지 증가
		 * 실행문: i가 num1의 약수이고 i가 num2의 약수이면 i를 출력(공약수)
		 * > num1를 i로 나누었을 때 나머지가 0과 같고 num2를 i로 나누었을 때 나머지가 같다면 i를 출력
		 * */
		
		int num1 = 8, num2 =12;
		int gcd = 1;
		
		for(int i = 1 ; i <= num1 ; i++) {
				
			if( num1 % i == 0 && num2 % i == 0) {
				gcd =i;
			}
		}
		System.out.println(num1+"과 "+num2+"의 최대 공약수 : " + gcd);
		System.out.println("--------------------------------------");
		
		/*두 정수의 최소 공배수를 구하는 코드를 작성하세요. 
		 * 8의 배수 : 8, 16, 24, 32, 40, 48 ...
		 * 12의 배수 : 12, 24, 36, 48 60,...
		 * 8과 12의 공배수 : 24, 48, 72, ...
		 * 8과 12의 최소 공배수 : 24
		 * 
		 * */
		
		int num3 = 24;
		int 공배수 = 1;
		
		for(int i = 1 ; i <= 100; i++) {
			if(i % num1 == 0 && i % num2 == 0) {
				공배수=i;
				break;
			}
		}
		System.out.println(num1+"과 "+num2+"의 최소 공배수 : " + 공배수);
		System.out.println("--------------------------------------");
		
		/* 정수 num의 약수를 출력하는 코드를 작성하세요.
		 * 
		 * 정수 num가 소수인지 아닌지 판별하는 코드를 작성하세요.
		 * 소수 : 약수가 1과 자기 자신인 수
		 * 2의 약수 : 1, 2 => 소수
		 * 3의 약수 : 1, 3 => 소수
		 * 4의 약수 : 1, 2, 4 = > 소수 아님
		 * 5의 약수 : 1, 5 = > 소수
		 * 6의 약수 : 1, 2, 3, 6 = > 소수 아님
		 * 
		 * 
		 * */	
		
		int num5 = 5;
		
		for(int i = 1; i <= 6; i++) {
			if(num5 % i == 0) {
				System.out.print(i+" ");
			}
		}
		
		System.out.println("");
		System.out.println("--------------------------------------");
		
		int num = 3;
		int count = 0;
		
		for(int i = 1; i <= num; i++) {
			++count;
		}
		if(count == 2) {
			System.out.println(num+"는 소수");
		}
		else{
			System.out.println(num+"는 소수가 아님");
		}
		
		System.out.println("--------------------------------------");
		/* 반복횟수 : i는 2부터 num까지 1씩 증가
		 * 실행문 : i가 num의 약수이면 약수를 d에 저장후 반복문 종료
		 * 반복문 종료 후 : d와 num이 같으면 소수, 아니면 소수가 아님
		 * */
		int d = 1;
	      for(int i = 2; i <= num; i++) {
	         if(num % i == 0) {
	            d = i;
	            break;
	         }
	      }
	      if(d == num) {
	         System.out.println(num +"는 소수");
	      }
	      else {
	         System.out.println(num +"는 소수가 아님");
	      }
			
			
		
	}

}
