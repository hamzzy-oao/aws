package day04;

import java.util.Scanner;

public class Ex04_클래스 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* 객체 지향 프로그래밍(Object-Oriented Programming : OOP)
	       * - [내]가 기상
	       * - [내]가 [밥]을 먹기
	       * - [내]가 [버스]에 승차
	       * - [내]가 [버스]에 하차
	       * - [내]가 [학원]에 도착
	       * - [내], [밥], [버스], [학원] : 객체
	       * 절차 지향 프로그래밍
	       * - 기상
	       * - 밥 먹기
	       * - 버스 승차
	       * - 버스 하차
	       * - 학원 도착
	       * 
	       * 출석부를 출력한다
	       * - 절차
	       * 프린터가 출석부를 출력한다
	       * - 객체
	       */
	      
	      //[스캐너]를 통해 (콘솔에서 입력을 받으려고 한다.)
	      //스캐너 객체를 생성
	      //new : 객체를 생성하는 연산자
	      //Scanner(System.in) : 생성된 스캐너를 어떻게 초기화를 할건지
	      //System.in : 표준입력 - 키보드
	      Scanner scan = new Scanner(System.in);
	      //스캐너를 통해 콘솔에서 정수를 입력 받음. 입력 받음이 기능
	      //int num = scan.nextInt();
	      
	      //[콘솔]에 출력하려고 한다.
	      //콘솔에 출력하기 위해 System.out이라는 개체를 이용
	      //해당 객체에서 제공하는 println 기능을 사용
	      //System.out.println(num);
	      
	      /* 클래스 p.127
	       * - 객체를 만들기 위한 설계도 
	       * - 속성(정보들)과 기능으로 구성되어 있는 것
	       * 
	       * 인스턴스(객체)
	       * - 클래스를 이용하여 만들어진 것
	       * 
	       * 클래스 구성 요소
	       * - 멤버변수(정보들), 메서드(기능), 생성자(정보들 초기화)
	       * 
	       * 멤버변수(필드)
	       * - 클래스의 속성
	       * - 클래스를 표현할 수 있는 정보들
	       * 
	       * 메서드
	       * - 클래스의 기능
	       * - 기능을 하기 위해 모아놓은 코드
	       * - js에서 함수에 해당
	       * 
	       * 자동차 클래스
	       * - 멤버변수
	       *   - 핸들, 바퀴 종류, 바퀴 수, 차종, 차명, 제조사, 색상
	       * - 메서드
	       *   - 시동 켜기/끄기, 기어 변속, 속도UP/DOWN
	       * - 생성자
	       *   - 초기 자동차 바퀴 종류, 바퀴 수, 차종, 차명, 제조사, 색상들을 결정
	       * */
	      
	      /* 클래스 선언 방법
	       *    접근제어자 class 클래스명{
	       *       //멤버변수
	       * 
	       *       //메서드
	       * 
	       *       //생성자
	       *    }
	       * 
	       */
	   
	      /* 인스턴스(객체) 생성방법
	       * - 클래스명 인스턴스명 = new 클래스명();
	       * - 클래스명 인스턴스명 = new 생성자();
	       * 
	       * 인스턴스 필드를 사용하는 방법
	       * - 인스턴스명.필드명
	       * - 무조건 가져올 수 있는건 아님. 접근제어자에 따라 가져오지 못할 수도 있음.
	       * 
	       */
	      
	      /* 지역 변수
	       * - 메서드 안에서 사용하는 변수
	       * - 지역 변수는 자동으로 초기화가 안됨
	       * 
	       * 필드(멤버 변수)
	       * - 클래스 안에서 사용하는 변수
	       * - 필드는 자동으로 초기화가 됨
	       *   - 정수 : 0, 실수 : 0.0, 참조변수 : null, 문자 : \u0000, 논리 : false
	       * */
	      Car myCar = new Car();
	      //필드는 초기화가 되기 때문에 바로 사용할 수 있음
	      System.out.println("자동차 전원 : " + myCar.power);
	      myCar.turnOnAndOff();
	      System.out.println("자동차 전원 : " + myCar.power);
	      myCar.turnOnAndOff();
	      System.out.println("자동차 전원 : " + myCar.power);
	      int num;
	      //지역 변수는 초기화가 자동으로 안되기 때문에 초기화 하지 않고 사용할 수 없음
	      //System.out.println(num);
	      
	      Car myCar2 = new Car("현대", "검정", "금호타이어", "아반떼", "소형차");
	   }
}	
	class Car{
		//멤버변수(필드)
		//전원, 바퀴수, 타이어종류(모든바퀴가 같은종류라고 가정), 색상, 제조사, 차종, 차명
		//핸들각도, 속력, 기어
		boolean power;// true:전원꺼짐, false이면 전원꺼짐
		int wheelCount; //바퀴수
		String wheelType;//타이어종류
		String color;//차색상
		String company;//제조사
		String type;//차종
		String name;//차명
		int handle;//핸들각도
		int speed;//속력
		char gear;//기어. P:주차 D:드라이브, N:중립 R:후진
	
	//메서드(기능)
	/* 기능 : 자동차의 전원을 켜거나 끄는 기능
	 * 매개변수 : 없음
	 * 리턴타입 : 없음 > void
	 * 메서드명 : turnOnAndOff
	 * */
	
		void turnOnAndOff() {
			if(power) {
				System.out.println("전원이 꺼졌습니다.");
			}
			else {
				System.out.println("전원이 켜졌습니다.");
			}
			power = !power;
		}
	
	//생성자(초기화)
	//기본 생성자
		Car () {
			wheelCount = 4;
			wheelType = "한국타이어";
			color = "검정";
			company= "모름";
			gear='P';
		}
		
		Car (String company1, String color1, String wheelType1, String name1, String type1) {
			wheelCount = 4;
			wheelType = wheelType1;
			color = color1;
			company = company1;
			gear ='p';
			name = name1;
			type = type1;
	
	
		}
	}