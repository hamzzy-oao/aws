package day09;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*학생을 관리하는 프로그램 클래스*/
public class StudentProgram implements ConsolePromgram{
	
	private final int EXIT = 4;
	private Scanner scan = new Scanner(System.in);
	StudentManager manager;
	
	@Override
	public void printMenu() {
		System.out.println("-----------------");
		System.out.println("1. 학생 정보 조회");
		System.out.println("2. 학생 추가");
		System.out.println("3. 성적 수정");
		System.out.println("4. 프로그램 종료");
		System.out.println("-----------------");
		System.out.print("메뉴 선택 : ");
		
	}

	@Override
	public void runMenu(int menu) {
		switch(menu){
			case 1:
				manager.printStudents();
				break;
			case 2:
				insertStudent();
				break;
			case 3:
				updateScore();
				break;
			case 4:
				printExit();
				break;
			default:
				printBar("올바른 메뉴를 선택하세요.");
		}
		
	}

	private void updateScore() {
		//학년, 반, 번호를 입력
		printBar("수정할 학생 정보 입력");
		System.out.print("학년 :");
		int grade = scan.nextInt();
		System.out.print("반 : ");
		int classNum = scan.nextInt();
		System.out.print("번호 : ");
		int num = scan.nextInt();
		//학생 객체를 생성
		Student std = new Student(grade,classNum,num,null);
		//국어, 영어, 수학 성적 입력
		System.out.print("국어 :");
		int kor = scan.nextInt();
		System.out.print("영어 :");
		int eng = scan.nextInt();
		System.out.print("수학 :");
		int math = scan.nextInt();
		//매니저에게 학생객체와 국어, 영수, 수학을 주면서 수정하라고 시킴
		if(manager.updateScore(std, kor, eng, math)) {
			printBar("성적을 수정하였습니다.");
			//실패하면 실패했다고 알림을 띄움
		}else {
			printBar("등록되지 않은 학생입니다.");
		}
		
	}

	@Override
	public void printExit() {
		printBar("학생 관리 프로그램을 종료합니다.");
		
	}

	private void insertStudent() {
		printBar("추가할 학생 정보 입력");
		//학년, 반, 번호ㅡ 이름을 입력 받음 이름은 next()로 받음
		System.out.print("학년 :");
		int grade = scan.nextInt();
		System.out.print("반 : ");
		int classNum = scan.nextInt();
		System.out.print("번호 : ");
		int num = scan.nextInt();
		System.out.print("이름 :");
		String name = scan.next();
		
		//학년, 반, 번호, 이름을 이용학생 객체 생성
		Student std = new Student(grade,classNum,num,name);
		//매니저에게 학생 등록하라고 시킨 후 성공하면 성공 알림을
		if(manager.insertStudent(std)) {
			printBar("학생을 추가했습니다.");
			//실패하면 실패했다고 알림을 띄움
		}else {
			printBar("이미 등록된 학생이어서 추가하지 못했습니다.");
		}
	}

	public void init() {
		
	}

	@Override
	public void run() {
		
		/* day05 패키지에 있는 Ex09_학생관리프로그램 에제를 참고하여 
		 * 저장기능과 불러오기 기능이 있는 프로그램을 구현하세요.
		 * 참고예제
		 * - day05.Ex09_학생관리프로그램
		 * - day08.Ex04_보조스트림
		 * 
		 * 추가
		 * 배열로된 코드를 리스트로 변경
		 * 학생 추가할 때 학년, 반, 번호가 같은 학생은 추가로 입력 못하게 구현하세요
		 * */
		String fileName = "src/day09/student.txt";
		//불러오기
		 List<Student> list = (List<Student>)load(fileName);
		int menu = 0;
		manager = new StudentManager(list);
		//프로그램 실행
		do {
			//메뉴 출력
			printMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				//선택한 메뉴 실행
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("올바른 메뉴를 선택하세요.");
				//잘못입력된 값 제거
				scan.nextLine();
			}
		}while(menu != EXIT);
		
		//프로그램 종료
		
		//저장하기
		save(fileName, manager.getList());
	}

	private void printBar() {
		System.out.println("---------------------");
	}	
	private void printBar(String str) {
		System.out.println("---------------------");
		System.out.println(str);
		System.out.println("---------------------");
	}	

	
}

