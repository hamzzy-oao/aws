package day09;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*콘솔 프로그램에 들어갈 기능들을 가진 인터페이스*/
public interface ConsolePromgram {
	
	//메뉴 출력 기능
	void printMenu();
	
	//선택한 메뉴를 실행하는 기능
	void runMenu(int menu);
	
	//프로그램 종료 문구를 출력하는 기능
	void printExit();
	
	//프로그램 실행 전 초기 셋팅을 하는 기능
	void init();
	
	//저장하기
	default void save(String fileName, Object object) {
		try(ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream(fileName))){
			oos.writeObject(object);
			oos.flush();
			System.out.println("저장하기 완료!");
		}catch(Exception e) {
			System.out.println("저장하기 실패!");
		}
	}

	//불러오기
	default Object load(String fileName) {
		try(ObjectInputStream ois =
				new ObjectInputStream(new FileInputStream(fileName))){
			Object obj = ois.readObject();
			System.out.println("불러오기 성공!");
			return obj;
		}catch(Exception e) {
			System.out.println("불러오기 실패!");
			
		}
		return null;
	}
	
	//프로그램 실행
	void run();
}
