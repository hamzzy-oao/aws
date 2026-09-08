package exam;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Exam {
	
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		//학생 3명 추가
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(2, 1, 5, "김수현", 95));
		list.add(new Student(2, 1, 12, "최민수", 88));
		list.add(new Student(2, 3, 2, "이영희", 77));
		
		
		
		//검색정보 입력
		Student info = inputBaseInfo();
		
		//검색학생 출력
		
		Student searchStudent = searchStudent(list, info);
		if(searchStudent == null) {
			System.out.println("일치하는 학생이 없습니다.");
		}
		else {
			System.out.println(searchStudent);
		}
		
		//삭제정보 입력
		info = inputBaseInfo();
		
		//삭제 결과
		boolean isDel = deleteStudent(list, info);
		
		if(isDel) {
			System.out.println("삭제하지 못했습니다.");
		}
		else {
			System.out.println("삭제했습니다.");
		}
		
		//정렬
		
		//결과

	}
	
	

	private static Student searchStudent(ArrayList<Student> list, Student info) {
		if(list == null || info == null ) {
			return null;
		}
		int index = list.indexOf(info);
		return index < 0 ? null : list.get(index);
	}


	
	public static Student inputBaseInfo() {
		try {
			System.out.print("학년 입력 : ");
			int grade = scan.nextInt();
			System.out.print("반 입력 : ");
			int classNum = scan.nextInt();
			System.out.print("번호 입력 : ");
			int num = scan.nextInt();
			
			return new Student (grade, classNum, num, null, 0);
		
		}catch(InputMismatchException e){
			System.out.println("잘못된 형식을 입력했습니다. 올바르게 입력하세요.");
			scan.nextLine();
			return null;
		}
	}

}

class Student{
	
	//필드
	private int grade, classNum, num;
	private String name;
	private int score;
	
	//매서드
	
	@Override
	public String toString() {
		return grade + "학년" + classNum + "반 " + num + "번 " + name + " - 점수 : "
				+ score;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum && grade == other.grade && Objects.equals(name, other.name)
				&& num == other.num && score == other.score;
	}


	//생성자
	public Student(int grade, int classNum, int num, String name, int score) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
		this.score = score;
	}

	
	
	
	
	
	
}