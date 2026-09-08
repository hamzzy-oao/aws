package day06;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex12_List연습예제 {

	public static void main(String[] args) {
		// ArrayList를 이용하여 정수를 저장하는 list를 생성
		ArrayList<Integer>list = new ArrayList<Integer>();
		
		Scanner scan = new Scanner(System.in);
		
		/* list에 숫자를 3개 입력하여 저장
		list.add(1);
		list.add(2);
		list.add(3); 
		*/
		
		for(;list.size()<3;) {
			System.out.print("입력 : ");
			int num = scan.nextInt();
			list.add(num);
		}
		
		// list에 있는 값을 콘솔에 출력
		System.out.println(list);

	}

}
