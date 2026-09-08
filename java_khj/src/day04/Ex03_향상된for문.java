package day04;

public class Ex03_향상된for문 {

	public static void main(String[] args) {
		/* 향상된 for문
		 * - 배열이나 컬렉션 프레임워크(List,Set)에서 for문을 편리하게 사용하는 방법
		 * - 특징
		 *   - 전체 탐색만 가능(수정 불가능. 수정하려면 일반 for문 이용)
		 * 기존 사용 방법
		 * 	for(int i=0; i < 배열.length; i++){
		 * 		자료형 변수명 = 배열[i];
		 * 		실행문;
		 * 	}
		 * 문법
		 * 	for(자료형 변수명 : 배열명){
		 * 		실행문;
		 * 	}
		 * */
		int [] array = {7, 8, 9};
		for(int i = 0 ; i<array.length; i++) {
			int tmp = array[i];
			System.out.println(tmp);
		}
		for(int tmp : array) {
			System.out.println(tmp);
		}
	}
}
