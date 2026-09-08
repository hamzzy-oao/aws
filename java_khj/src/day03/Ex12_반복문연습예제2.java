package day03;

public class Ex12_반복문연습예제2 {

	public static void main(String[] args) {
		/* 다음과 같이 출력되도록 코드를 작성하세요.
		 * 1
		 * 12
		 * 123
		 * 1234
		 * 12345
		 * */ 
		
		
		for(int i = 1; i <= 5; i++) {
			
			for(int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	
		
	/* 다음과 같이 출력되도록 코드를 작성하세요.
	 * a
	 * ab
	 * abc
	 * abcd
	 * ....
	 * */ 
		
		for(char i = 97 ; i <= 122 ; i++) {
			for(char j = 97 ; j <= i ; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	 
	}

}
