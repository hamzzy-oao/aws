package day08;

import java.util.Scanner;

public class Ex06_스레드 {

	public static void main(String[] args) {
	  /* 프로세스
       * - 실행중인 프로그램
       * - 하나 이상의 스레드로 구성
       * 
       * 스레드
       * - 프로세스 내에서 할당된 자원을 이용하여 실제 작업을 수행하는 작업 단위
       * 
       * 메인 스레드
       * - 모든 자바 프로그램은 메인 스레드가 main() 메서드를 실행
       * 
       * 멀티 스레드
       * - 하나의 프로세스 내에서 여러 스레드가 동시에 작업을 수행하는 것
       * - 장점
       *   - 자원을 효율적으로 사용 가능
       *   - 사용자에 대한 응답성 향상
       *   - 작업이 분리되어 코드가 간결해짐
       * - 단점
       *   - 동기화에 주의
       *   - 교착상태가 발생하지 않도록 주의
       * 
       * 스레드 생성 방법
       * 1. Thread 클래스 상속
       * - Thread 클래스를 상속받은 자식 클래스를 만들어서 run메서드를 오버라이딩
       * 
       * 2. Runnable 인터페이스 구현
       * - Runnable 인터페이스를 구현한 구현 클래스를 만들어 run메서드를 오버라이딩
       * 
       * 스레드 호출
       * - 스레드객체명.start();
       */
		
		//-와 +가 교대로 실행되게 하고 싶음
		for(int i =1;i <= 1000;i++) {
			System.out.print('-');
		}
		for(int i =1;i <= 1000;i++) {
			System.out.print('+');
		}
		System.out.println();
		MyThread1 t1 = new MyThread1();
		t1.start();
		
		Thread t2 = new Thread(new MyThread2());
		t2.start();
		
		//Runnable 인터페이스는 Run에서드만 있기 때문에 람다식 사용이 가능
		Thread t3 = new Thread(()->{
			for(int i = 1;i <= 10000;i++) {
				System.out.println('*');
			}
		});
		t3.start();
	}

}

class MyThread1 extends Thread{
	
	@Override
	public void run() {
		for(int i =1;i <= 1000;i++) {
			System.out.print('-');
		}
	}
}

class MyThread2 implements Runnable{
	
	@Override
	public void run() {
		for(int i = 1; i <= 10000; i++) {
			System.out.print('+');
		}
	}
}