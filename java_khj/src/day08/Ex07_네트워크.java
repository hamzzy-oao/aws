package day08;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex07_네트워크 {

	public static void main(String[] args) {
	   /* TCP
       * - 데이터 전송 속도가 느림
       * - 안정적으로 통신
       * - 연결 지향적
       * UDP
       * - 데이터 전송 속도가 빠름
       * - 신뢰성이 없음
       * - 비연결 지향적
       * 
       * 소켓 프로그래밍
       * - 소켓을 이용한 통신 프로그램
       * - 들어올수 있는 구멍을 만들고, 그 구멍을 통해 통신
       * 
       * 소켓 프로그래밍
       * - 서버
       * 1. 서버의 포트를 지정
       * 2. 서버용 소켓 객체를 생성
       * 3. 접속 대기
       * 4. 접속 요청이 오면 수락 후 클라이언트 소켓 객체를 생성
       * 5. 연결된 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
       * 6. 통신을 종료
       * 
       * - 클라이언트
       * 1. 서버의 IP주소와 포트를 이용하여 클라이언트 객체를 생성
       * 2. 연결 요청
       * 3. 연결이 되면 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
       * 4. 통신을 종료
       */
		
		//1. 서버의 포트를 지정
		int port = 5000;
		
		//2. 서버용 소켓 객체를 생성
		try(ServerSocket serversockrt = new ServerSocket(port)){
			System.out.println("[서버 소켓을 생성했습니다.]");
			
			//3.4. 접속 대기 후 접속 요청이 오면 수락 후 클라이언트 소켓 객체를 생성 
			System.out.println("[서버 소켓을 생성했습니다.]");
			Socket socket = serversockrt.accept();
			System.out.println("[클라이언트와 연결됐습니다.]");
			//5. 연결된 소켓 캑체에서 IO스트림을 이용하여 읽고 쓰기를 함
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String id = ois.readUTF();
			System.out.println("["+id+"]님 접속을 환영합니다.");
			
			//서버가 클라이언트에게 문구를 전송
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF("연결을 종료합니다.");
			oos.flush(); //버퍼에 있는 내용을 밀어서 전송
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		//6. 통신을 종료
		System.out.println("[서버를 종료합니다.]");

	}

}
