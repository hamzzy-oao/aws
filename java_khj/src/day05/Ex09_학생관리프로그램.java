package day05;

import java.util.Scanner;

import org.xml.sax.SAXNotRecognizedException;

public class Ex09_학생관리프로그램 {

	public static void main(String[] args) {
		
		int menu;
		학생[] 성적관리 = new 학생[10];
		int 학생수 = 0;
		
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			menu = scan.nextInt();
			switch(menu){
				case 1:
					printStudent(성적관리);
					
					break;
				case 2:
					학생수 = insertStudent(성적관리, scan, 학생수);
					break;
				case 3:
					printStudent(성적관리);
					updateScore(scan, 성적관리);
					break;
				case 4:
					break;
				default:
					System.out.println("잘못된 메뉴입니다.");
			}
		}while(menu != 4);
		System.out.println("프로그램을 종료합니다.");

	}
	/* 기능 : Scanner를 이용하여 학생정보를 입력받아 "성적관리"에 추가하고 변경된 학생수를 알려주는 메서드
	 * 매개변수 : 성적관리와 Scanner => 학생[] 성적관리, Scanner scan, int 학생수
	 * 리턴타입 : 변경된(추가된) 학생수 => int 
	 * 메소드명 : insertStudent
	 * */
	
	
	//3번 메서드
	
	public static void updateScore(Scanner scan, 학생[]성적관리) {
		//성적을 수정할 학생의 번호를 입력
		System.out.println("학생의 번호를 입력하세요.");
		int index = scan.nextInt() - 1;//배열의 번지는 0부터이기떄문에 -1함
		// 수정할 성적을 입력
		System.out.println("국어 성적 : ");
		int Kor =scan.nextInt();
		
		System.out.println("영어 성적 : ");
		int eng =scan.nextInt();
		
		System.out.println("수학 성적 : ");
		int math =scan.nextInt();
		// 해당 학생의 성적을 수정
		성적관리[index].성적수정(Kor,eng,math);
		
	}
	
	//2번 메서드
	public static int insertStudent(학생[]성적관리, Scanner scan, int 학생수) {
		//학생 정보 입력
		System.out.print("학년 : ");
		int grade = scan.nextInt();
		System.out.print("반 : ");
		int classNum = scan.nextInt();
		System.out.print("번호 : ");
		int num = scan.nextInt();
		System.out.print("이름 : ");
		String name = scan.next();
		
		//학생정보를 이용하여 객체를 생성
		학생 임시학생 = new 학생(grade,classNum,num,name);
		//생성된 객체를 배열에 저장
		성적관리[학생수] = 임시학생;
		++학생수;
		
		return 학생수;
	}
	
	//1번 조회 메서드
	public static void printStudent(학생[]성적관리) {
		int i = 0;
		for(학생 성적 : 성적관리) {
			if(성적 == null) {
				continue;
			}
			System.out.print(i+1+". ");
			성적.조회();
			i++;
		}
		
	}
	
	
	//필드 생성
	public static void printMenu() {
		
		System.out.println("메뉴");
		System.out.println("1. 학생 정보 조회");
		System.out.println("2. 학생 추가");
		System.out.println("3. 성적 수정");
		System.out.println("4. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		
	}

}


//클래스 생성

class 학생{
	private int grade, classNum, num;
	private String name;
	private int Kor, eng, math;
	
	public void 조회() {
		System.out.print(grade+"학년 "+classNum+"반 "+num+"번 "+name);
		System.out.println("[ 국어 : "+Kor+", 영어 : "+eng+", 수학 : "+math+ "]");
	}
	
	public void 성적수정(int Kor, int eng, int math) {
		this.Kor = Kor;
		this.eng = eng;
		this.math = math;
	}

	public 학생(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	
	
}