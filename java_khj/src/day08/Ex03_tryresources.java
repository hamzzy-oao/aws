package day08;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ex03_tryresources {

	public static void main(String[] args) {
		  /* try resources p.533
	       * - 스트림을 열면 닫아야 해서 보통 finally에 닫는 작업을 함
	       *   => 닫을 때도 예외가 발생할 수 있어서 finally에 다시 예외처리를 해야하는
	       *      번거로움이 발생
	       *   => 이를 해결하기 위해 try resource를 이용하면 코드가 간결해 짐
	       * - 문법
	       *  try(스트림을 염){
	       *     코드 구현
	       *  }catch(예외클래스명 e){
	       *     예외처리
	       *  }
	       */

		String fileName = "src/day08/data.txt";
		
		
		//파일을 염. 파일이 없으면 예외 발생
		try(FileReader fr = new FileReader(fileName)) {
			
			//파일이 준비되면 반복. 아니면 종료
			while (fr.ready()) {
				//한 문자를 읽어옴. 왜? FileReader는 문자기반으로 읽는 스트림이어서
				char ch = (char)fr.read();
				System.out.print(ch);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			//try resources를 이용했기 때문에 닫는 코드가 여기에 없어도 됨
		}

	}

}
	

