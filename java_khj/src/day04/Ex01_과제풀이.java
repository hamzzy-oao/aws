package day04;

public class Ex01_과제풀이 {

	public static void main(String[] args) {
		/* 2번. 다음과 같이 출력되도록 코드를 작성하세요.
		 * 2-1.
		 * *     i=1, *=1개      
		 * **    i=2, *=2개
		 * ***   i=3, *=3개
		 * ****  i=4, *=4개
		 * ***** i=5, *=5개
		 *            *=i개
		 */
		for(int i = 1; i<=5; i++) {
			//*을 i개 출력
			for(int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			//엔터
			System.out.println();
		}

	}

}
