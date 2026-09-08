package day08;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex08_네트워크스레드 {

	public static void main(String[] args) {
	      //1. 서버의 포트를 지정
	      int port = 5000;
	      
	      //2. 서버용 소캣 객체를 생성
	      try(ServerSocket serverSocket = new ServerSocket(port)){
	         System.out.println("[서버 소켓을 생성했습니다.]");
	         
	         //3.4. 접속 대기 후 접속 요청이 오면 수락 후 클라이언트 소켓 객체를 생성
	         System.out.println("[연결 대기중입니다.]");
	         Socket socket = serverSocket.accept();
	         System.out.println("[클라이언트와 연결됐습니다.]");
	         
	         //5. 연결된 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
	         Ex08_Client client = new Ex08_Client("관리자", socket);
	         client.send();
	         client.recieve();                     

	         
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      //6. 통신을 종료
	      System.out.println("[서버를 종료합니다.]");
   }

}

