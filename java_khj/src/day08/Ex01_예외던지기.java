package day08;

import java.util.ArrayList;
import java.util.Random;

public class Ex01_예외던지기 {

	public static void main(String[] args) {
		/* 예외처리 방법
		 * 1. 직접 처리
		 *   => 문제가 발생한 곳에서 직접 해결
		 *   => try catch문으로 
		 *   
		 * 2. 예외를 넘기기
		 *   => 문제가 발생하면 일을 시킨 곳에게 문제를 넘김
		 *   => throws => 나 이런 예외 발생할 수 있으니 사용할 때 조심해줘라고 얘기를 해야 함
		 * 
		 * 예외 던지기
		 * - 개발자가 필요에 의해 예외를 발생시킴
		 * - throw
		 * */
		
		try {
			System.out.println(createRandomArray(1,2,3));
		}catch(Exception e) {
			//e.getMessage() : 예외 객체를 생성할 때 지정한 문구를 가져옴
			System.out.println(e.getMessage());
			//예외가 발생한 곳들을 추적해서 콘솔에 출력. 프로그램 중단이 아님
			e.printStackTrace();
		}
		System.out.println("프로그램종료");

	}
	
	//min~max 사이의 중복되지 않은 정수 size개를 만들어서 리스트로 리턴하는 메서드
	public static ArrayList<Integer> createRandomArray(int min, int max, int size)
		throws Exception{
			if(max - min + 1 < size) {
				throw new Exception(max - min + 1 + "개의 중복되지 않은 수 "+ size +"개를 만들수 없습니다.");
			}
		//중복되지 않게 size개 만큼 없으면 예외를 발생시킴
		//중복되지 않게 만들수 있는 숫자의 개수 : max - min +1
		/* RuntimeException e =
		 * new RuntimeException(max - min + 1 + "개의 중복되지 않은 수 " 
		 * + size +"개를 만들수 없습니다.");
		 * throw e;
		 */
		ArrayList<Integer> list = new ArrayList<Integer>();
		
	
		while(list.size() < size) {
			Random random = new Random();
			int num = random.nextInt(min, max + 1);
		
			if(!list.contains(num)) {
				list.add(num);
			}
		}
	return list;
	
	}
}