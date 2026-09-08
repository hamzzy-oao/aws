package day06;

import java.util.Scanner;

public class Ex04_인터페이스 {

	public static void main(String[] args) {
		/* 인터페이스 p.324
		 * 구성
		 * - 추상 메서드(abstract)와 클래스 상수(public final static)로 구성되어 있음
		 * 
	     * - 기능 목록만 나열
	     * - 인터페이스에서 변수를 선언하면 앞에 자동으로 public final static이 추가 됨
	     *   => 반드시 초기화를 해야 함
	     *   
	     * - 인터페이스에서 메서드를 선언하면 앞에 자동으로 public abstract가 추가 됨 
	     * 
	     * - 인터페이스 선언하기
	     *    interface 인터페이스명{
	     *       추상메서드;
	     *       클래스 상수;
	     *    }
	     *    
	     * - 인터페이스의 객체를 생성하려면 구현 클래스를 만들어서 생성
	     * 
	     * - 구현 클래스 선언하기 p.325
	     * 
	     *    class 클래스명 implements 인터페이스명1, 인터페이스명2, ...{
	     *       //추상 메서드 오버라이딩
	     *    }
	     *    
	     * - 왜 사용?
	     *   - 제품 표준을 지정하고, 표준에 맞추면 제조사들이 어떻게 만들든 사용자들은 표준에 맞춰
	     *     쓰면 되기 때문에
	     *   - 예를 들면 핸드폰 C타입
	     */
		
		StudentManger sm = new StudentManger();
		Scanner scan = new Scanner(System.in);
		int menu;
		do {
			sm.printMenu();
			menu = scan.nextInt();
			sm.runMenu(menu);
			
		}while(menu !=4);
		
		sm.printExit();
	}

}

interface ConsolePromgram{
	
	public abstract void printMenu();
	
	void printExit();
	
	void runMenu(int menu);
}

class StudentManger implements ConsolePromgram{

	@Override
	public void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 학생 정보 조회");
		System.out.println("2. 학생 추가");
		System.out.println("3. 성적 수정");
		System.out.println("4. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		
	}

	@Override
	public void printExit() {
		System.out.println("프로그램을 종료합니다.");
		
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1: System.out.println("학생 정보 조회 기능입니다."); break;
		case 2: System.out.println("학생 추가 기능입니다."); break;
		case 3: System.out.println("학생 성적 수정 기능입니다."); break;
		case 4: break;
		default:
			System.out.println("잘못된 메뉴입니다. ");
		}
		
	}
	
	
}