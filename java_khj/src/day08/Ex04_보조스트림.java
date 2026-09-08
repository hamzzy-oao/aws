package day08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Ex04_보조스트림 {

	public static void main(String[] args) {
		/* 보조 스트림
		 * - 기반 스트림을 통해 열린 파일들을 보조해서 데이터를 쉽게 읽어오거나 쓰게 해주는 스트림
		 * 
		 * ObjectInputStream/ObjectOutputStream
		 * - 직렬화된 클래스의 객체를 저장하거나 읽어오게 도와주는 스트림
		 * 
		 * - 직렬화된 클래스
		 *   - Serializable 인터페이스를 구현한 구현 클래스
		 * */
		String fileName = "src/day08/student.txt";
		
		Student std = new Student(1, 1, 1, "홍길동");
		//FileOutputStream : 기반스트림
		//ObjectOutputStream : 보조 스트림
		//기반 스트림을 열고, 기반스트림을 통해 보조 스트림을 염
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			//파일에 객체를 저장
			oos.writeObject(std);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
			//파일에서 객체를 읽어옴
			Student tmp = (Student)ois.readObject();
			System.out.println(tmp);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

class Student implements Serializable{
	
	
	private static final long serialVersionUID = 6984199082039000067L;
	int grade, classNum, num;
	String name;
	
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}

	@Override
	public String toString() {
		return grade + "학년 " + classNum +"반 " + num + "번 " + name;
	}
	
	
}