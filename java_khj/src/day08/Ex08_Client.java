package day08;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Ex08_Client {
	
	private String id;
	private Socket socket;

	
	public static void main(String[] args) {
		
	}
		
		//받기
		public void recieve() {
			Thread t = new Thread(()->{
				//무한 루프로 특정 단어가 입력될때까지 받아서 콘솔에 출력
				System.out.println("["+id+"]님의 수신기능이 활성화 됐습니다.");
				try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
					Scanner scan = new Scanner(System.in);
					
					while(true) {
						String msg = ois.readUTF();
						if(msg.equals("EXIT")) {
							break;
						}
						System.out.println(msg);
					}
				}catch(Exception e) {
					System.out.println("예외발생!");
					e.printStackTrace();
				}
				finally {
					System.out.println("["+id+"]님의 수신 기능이 종료되었습니다.");
				}
				System.out.println("["+id+"]님의 수신기능이 종료 됐습니다.");
			});
			t.start();
		}
		
		//보내기
		public void send() {
			
			Thread t = new Thread(()->{
				System.out.println("["+id+"]님의 수신기능이 활성화 됐습니다.");
				//무한 루프로 특정 단어가 입력될때까지 받아서 콘솔에 출력
				try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
					
					Scanner scan = new Scanner(System.in);
					
					while(true) {
						String msg = scan.nextLine();
						oos.writeUTF(msg);
						oos.flush();//버퍼에 있는 내용을 밀어서 전송
						if(msg.equals("EXIT")) {
							break;
						}
					}
				}catch(Exception e) {
					System.out.println("예외발생!");
					e.printStackTrace();
				}
				finally {
					System.out.println("["+id+"]님의 송신 기능이 종료되었습니다.");
				}
				
				System.out.println("["+id+"]님의 수신기능이 종료 됐습니다.");
			});
			t.start();
			
		}
		
	public Ex08_Client(String id, Socket socket) {
		this.id = id;
		this.socket =socket;
	}
}
