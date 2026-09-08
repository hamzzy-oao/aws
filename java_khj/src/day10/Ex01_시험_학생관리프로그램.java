package day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Ex01_시험_학생관리프로그램 {

    public static void main(String[] args) {
        StudentProgram program = new StudentProgram();
        program.run();
    }

    static class StudentProgram {

        Scanner scan = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        public void run() {

            Student std = new Student(2, 1, 5, "김수현", 0);
            std.updateScore(95);

            Student std1 = new Student(2, 1, 12, "최민수", 0);
            std1.updateScore(88);

            Student std2 = new Student(1, 3, 2, "이영희", 0);
            std2.updateScore(77);

            // 학생 추가
            manager.insertStudent(std);
            manager.insertStudent(std1);
            manager.insertStudent(std2);

            manager.printStudents();

            System.out.println();
            System.out.println("검색할 학생 정보 입력");

            System.out.print("학년 : ");
            int searchGrade = scan.nextInt();

            System.out.print("반 : ");
            int searchClassNum = scan.nextInt();

            System.out.print("번호 : ");
            int searchNum = scan.nextInt();

            Student searchStudent =
                    new Student(searchGrade, searchClassNum,
                            searchNum, null, 0);

            manager.searchStudent(searchStudent);

            // 학생 삭제
            System.out.println();
            System.out.println("삭제할 학생 정보 입력");

            System.out.print("학년 : ");
            int deleteGrade = scan.nextInt();

            System.out.print("반 : ");
            int deleteClassNum = scan.nextInt();

            System.out.print("번호 : ");
            int deleteNum = scan.nextInt();

            Student deleteStudent =
                    new Student(deleteGrade, deleteClassNum,
                            deleteNum, null, 0);

            manager.deleteStudent(deleteStudent);

            manager.sort();

            System.out.println();
            System.out.println("=== 정렬 ===");
            manager.printStudents();

            scan.close();
        }
    }

    static class StudentManager {

        ArrayList<Student> students = new ArrayList<>();

        public void insertStudent(Student student) {

            if (students.contains(student)) {
                System.out.println("이미 등록된 학생입니다.");
                return;
            }

            students.add(student);
        }

        public void searchStudent(Student searchStudent) {

            try {
                int index = students.indexOf(searchStudent);

                Student student = students.get(index);

                System.out.println(student);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("해당 학생을 찾을 수 없습니다.");
            }
        }

        public void deleteStudent(Student deleteStudent) {

            try {
                int index = students.indexOf(deleteStudent);

                students.remove(index);

                System.out.println("삭제완료");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("삭제할 학생을 찾을 수 없습니다.");
            }
        }

        public void printStudents() {

            try {
                students.get(0);

                for (Student student : students) {
                    System.out.println(student);
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("등록된 학생이 없습니다.");
            }
        }

        public void sort() {

            if (students.isEmpty()) {
                System.out.println("정렬할 학생이 없습니다.");
                return;
            }

            students.sort(
                    Comparator.comparingInt(Student::getScore)
            );
        }
    }

    static class Student {

        private int grade;
        private int classNum;
        private int num;
        private String name;
        private int score;

        public Student(int grade, int classNum, int num,
                       String name, int score) {

            this.grade = grade;
            this.classNum = classNum;
            this.num = num;
            this.name = name;
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

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void updateScore(int score) {
            this.score = score;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (getClass() != obj.getClass()) {
                return false;
            }

            Student other = (Student) obj;

            return grade == other.grade
                    && classNum == other.classNum
                    && num == other.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(grade, classNum, num);
        }

        @Override
        public String toString() {
            return grade + "학년 " + classNum + "반 " + num + "번 " + name + " - 점수 : " + score;
        }
    }
}