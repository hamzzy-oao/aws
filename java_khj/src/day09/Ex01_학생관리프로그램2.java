package day09;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ex01_학생관리프로그램2 {

   public static void main(String[] args) {
      StudentProgram promgram = new StudentProgram();
      promgram.run();
   }

}

class StudentProgram  {
   
   private Scanner scan = new Scanner(System.in);
   StudentManager manager;

   public void run() {
      //학년, 반, 번호를 입력
      System.out.print("학년 : ");
      int grade = scan.nextInt();
      System.out.print("반  : ");
      int classNum = scan.nextInt();
      System.out.print("번호 : ");
      int num = scan.nextInt();
      
      //학생 객체를 생성(이름 대신 null)
      Student std = new Student(grade, classNum, num, null);
   }
   
}

class StudentManager {

   //학생 목록
   List<Student> list;

   //학생 목록이 있으면 가져와서 관리
   public StudentManager(List<Student> list) {
      //넘겨준 학생 목록이 없으면 새 학생 목록을 만듬
      if(list == null) {
         this.list = new ArrayList<Student>();
         return;
      }
      //학생 목록이 있으면 받아와서 사용
      this.list = list;      
      
   }

   public boolean insertStudent(Student std) {
      //리스트에 std가 등록됐지는 확인해서 등록되어 있으면 false를 리턴
      //Student.equals()를 오버라이딩해서 처리해야 함
      if(list.contains(std)) {
         return false;
      }
      //등록이 안되어 있으면 리스트에 학생을 추가하고 true를 리턴
      //List.add()는 추가한 후 추가하면 true를 리턴, 
      //return list.add(std);
      list.add(std);
      return true;
   }

   public void printStudents() {
      sort();
      if(list.size() == 0) {
         System.out.println("등록된 학생이 없습니다.");
      }
      for(Student std : list) {
         System.out.println(std);
      }
   }
   
   private void sort() {
      //출력전 학년, 반, 번호 순으로 정렬
      list.sort(
         //학년을 기준으로 정렬
         Comparator.comparing(Student::getGrade)
         //학년이 같으면 반을 기준으로 정렬 
         .thenComparing(Student::getClassNum)
         //반이 같으면 번호를 기준으로 정렬
         .thenComparing(Student::getNum)
         //내림차순은 .reverse()추가
      );
   }

   public boolean updateScore(Student std, int score) {
      if(std == null) {
         return false;
      }
      //등록된 학생이 아니면
      //indexOf는 몇번지에 있는지 알려줌. -1 => 없음
      int index = list.indexOf(std);
      if(index == -1) {
         return false;
      }
      //index 번지에 있는 학생 성적을 수정
      //list.get(index) => 수정할 학생 정보
      list.get(index).updateScore(score);
      return true;
   }

   public Object getList() {
      return this.list;
   }
   
   //기능들
   
   
}

   
   
   private int grade, classNum, num;
   private String name;
   private int score;
   
   //학생의 학년, 반, 번호, 이름을 이용하여 학생 정보를 생성
   public Student(int grade, int classNum, int num, String name) {
      this.grade = grade;
      this.classNum = classNum;
      this.num = num;
      this.name = name;
   }

   @Override
   public String toString() {
      return grade + "학년 " + classNum + "반 " + num + "번 " + name 
            + "- 점수 : " + score;
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
      return classNum == other.classNum && grade == other.grade && num == other.num;
   }

   public void updateScore(int score) {
      this.score = score;
      
   }

   public int getGrade() {
      return grade;
   }

   public int getClassNum() {
      return classNum;
   }

   public int getNum() {
      return num;
   }
   
}