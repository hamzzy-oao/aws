package day08;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Ex08_네트워크스레드_클라이언트 {

	public static void main(String[] args) {
		
		 String ip = "127.0.0.1";
	      int port = 5000;
	      
	      //1. 서버의 IP주소와 포트를 이용하여 클라이언트 객체를 생성
	      //2. 연결 요청
	      try{
	         Socket socket = new Socket(ip, port);
	         //3. 연결이 되면 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
	         //서버에 id를 전송
	         Scanner scan = new Scanner(System.in);
	         System.out.print("ID 입력 : ");
	         String id = scan.nextLine();
	         
	         Ex08_Client client = new Ex08_Client(id, socket);
	         client.send();
	         client.recieve();
	         
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      //4. 통신을 종료
	      System.out.println("[클라이언트를 종료합니다.]");
	}

}

