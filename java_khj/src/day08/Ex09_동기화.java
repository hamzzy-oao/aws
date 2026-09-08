package day08;

public class Ex09_동기화 {

	public static void main(String[] args) {
	  /* 동기화
       * - 메서드나 블럭에 스레드가 실행중이면 뒤늦게 온 스레드는 앞 스레드가 다 끝날 때까지
       *   대기하는 것
       * - synchronized를 메서드나 블록에 추가 
       * 
       * 비동기
       * - 앞에 작업이 다 끝나지 않아도 다음 작업이 실행되는 것
       * 
       * */
		
		//홍길동 계좌 생성
		// 홍길동이 본인 계좌에 10000이 있음
		BankAccount account = new BankAccount("홍길동",10000);
		// 홍길동이 본인 계좌에 10000이 입금
		Customer customer1 = new Customer("홍길동",account);
		customer1.start();
		//홍길동 아빠가 아들 계좌에 10000원을 입금
		Customer customer2 = new Customer("홍길동 아빠",account);
		customer2.start();
		
		/* 동기화 전 결과
		 * > 홍길동 입금 처리가 완료되기 전에 아빠가 입금을 시도해서 통장 기록이 이상해짐
		 * 입금 전 금액 : 10000
		 * 입금 전 금액 : 10000
		 * 홍길동님 10000원, 잔액 : 30000
		 * 홍길동 아빠님 10000원, 잔액 : 30000
		 * 
		 * 동기화 후 결과
		 * > 홍길동 입금 처리가 완료되기 전에 아빠가 입금을 시도하지만 동기화 때문에 입금 시도를 막고 있다가
		 * 홍길동 입금 처리가 완료되면 아빠 입금처리가 진행되서 정상 결과가 나옴
		 * 입금 전 금액 : 10000
		 * 홍길동님 10000원, 잔액 : 20000
		 * 입금 전 금액 : 20000
		 * 홍길동 아빠님 10000원, 잔액 : 30000
		 * */

	}

}

//은행 고객
class Customer extends Thread{
	private String name;
	private BankAccount bankAccount;
	
	public void run() {
		int money = 10000;
		bankAccount.deposit(name, money);
	}

	public Customer(String name, BankAccount bankAccount) {
		this.name = name;
		this.bankAccount = bankAccount;
	}
	
	
}

//계좌

class BankAccount{
	private String name;
	private int balance;
	
	//예금기능
	public synchronized void deposit(String name, int money) {
		System.out.println("입금 전 금액 : "+balance);
		
		if(money <= 0) {
			System.out.println("입금액은 0보다 커야 합니다.");
			return;
		}
		
		balance += money;
		
		try {
			Thread.sleep(2000);//입금중 2초동안 스레드를 멈춤
		}
		catch(Exception e) {
			
		}
		System.out.println(name+"님 "+money+"원, 잔액 : "+balance);
	}

	public BankAccount(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}
	
	
}
