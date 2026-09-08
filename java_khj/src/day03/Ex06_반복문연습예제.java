package day03;

public class Ex06_반복문연습예제 {

	public static void main(String[] args) {
		/* 다음과 같이 출력되도록 코드를 작성하세요.
		 * 1 2 3 4
		 * 5 6 7 8
		 * 9 10 11 12
		 * 13 14 15 16
		 * */
		
		for(int i = 1 ; i <= 16 ; i++) {
			
			
			if( i % 4 == 0 ) {
				System.out.println(i);
			}else {
				System.out.print(i+" ");
				
			}
			
		}
	}

}
