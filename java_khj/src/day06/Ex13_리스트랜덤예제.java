package day06;

import java.util.ArrayList;
import java.util.Random;

public class Ex13_리스트랜덤예제 {

	public static void main(String[] args) {
		/*1~9 사이의 랜덤한 수 3개를 생성하여 ArratList에 저장하고 콘솔에 출력하세요.
		 * */
		
	      
		Random random = new Random();
		int min = 1, max = 9;
		
		ArrayList<Integer>list = new ArrayList<Integer>();
		
		
		/*  while문도 사용 가능
		 * while(list.size()<3){
		 * 	int num = random.nextInt(min,max + 1);
		 * }
		 * */
		
		
		for(;list.size()<3;) {
			int num = random.nextInt(min,max + 1);
			list.add(num);
		}
		
		
		System.out.println(list);
	}

}



