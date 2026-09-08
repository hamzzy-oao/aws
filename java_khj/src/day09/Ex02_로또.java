package day09;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import day06.Ex14_List연습예제2;

public class Ex02_로또 {
	
	public static void main(String[] args) {
		/* 1~45 사이의 중복되지 않는 랜덤한 수 6자리를 맞추는 프로그램
		 * 1. 1~45 사이의 중복되지 않은 랜덤한 수 6자리 생성
		 * 2. 1번에서 생성한 번호를 제외한 번호를 하나 랜덤으로 선택
		 * 3. 사용자가 1~45사이의 중복되지 않은 수를 입력
		 * 4. 당첨 등수 확인
		 * - 1등 : 당첨 번호 6자리 일치
		 * - 2등 : 당첨 번호 5자리와 보너스 번호 일치
		 * - 3등 : 당첨 번호 5자리 일치
		 * - 4등 : 당첨 번호 4자리 일치
		 * - 5등 : 당첨 번호 3자리 일치
		 * - 꽝 : 나머지
		 * */
		
		//1~45까지 숫자 7개 생성 
		//앞에는 로또번호, 마지막 뒤는 보너스
		ArrayList<Integer>lotto = createRandomArray(1,45,7);
		int bonus = lotto.remove(6);
		
		System.out.println("로또 번호 : "+ lotto);
		System.out.println("보너스 : "+ bonus);
		//사용자 입력(예외처리 안함) > 사용자가 항상 입력 제대로 했다고 가정
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer>list =inputNumbers(7);
		System.out.print("입력 : ");
		//일치하는 번호가 몇개인지
		int count = getCount(lotto,user);
		Object bouns;
		//보너스 번호 맞았는지
		boolean isBonus = checkBonus(bonus, user);
		
		//일치하는 번호 개수와 보너스 번호 일치 여부를 잉ㅇ해서 등수 출력
		//2등이 먼저 출력
		
		printLottoResult(count,isBonus);
		
	    			
	 }
		
		
		
		

	private static boolean checkBonus(Object bonus, ArrayList<Integer> user) {
		//user에 보너스가 있으면 true 없으면 false 리턴
		if(user.contains(bonus)) {
			return true;
		}
			return false;
	}





	private static int getCount(ArrayList<Integer> lotto, ArrayList<Integer> user) {
		int sameCount = 0;
		//
		for(int num : user) {
			if(lotto.contains(num)) {
				sameCount++;
			}
		}
		return sameCount;
	}

	
	private static ArrayList<Integer> inputNumbers(int size){
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < size ;i++) {
			int num =scan.nextInt();
			list.add(num);
		}
	}



	private static void printLottoResult(int count, boolean isBonus) {
		switch(count) {
		case 6:
			System.out.println("1등입니다.");
			break;
		case 5:
			if(isBonus) {
				System.out.println("2등입니다.");
			}
			else {
				System.out.println("3등입니다.");
			}
			break;
		case 4:
			System.out.println("4등입니다.");
			break;
		case 3:
			System.out.println("5등입니다.");
			break;
		default:
			System.out.println("꽝");
			break;
			
		}
		
	}





	private static ArrayList<Integer> createRandomArray(int min, int max, int size) {
	
		  //정수 리스트를 생성
	      ArrayList<Integer> list = new ArrayList<Integer>();
	      
	      //리스트에 n개 저장될때까지 반복
	      while(list.size() < size) {
	    	  
	         Random random = new Random();
	         int num = random.nextInt(min, max + 1);
	         
	         //랜덤수가 리스트에 없으면 : Ex14예제 참고
	         if(!list.contains(num)) {
	            //랜덤수를 리스트에 추가
	            list.add(num);         
	         } 
	      }         
	      //리스트를 리턴
		return list;

	}
}


