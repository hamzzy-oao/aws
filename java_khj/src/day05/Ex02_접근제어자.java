package day05;

import day05_1.다른패키지클래스;

public class Ex02_접근제어자 {

	public static void main(String[] args) {
	 
	  /* 접근 제어자 p.162
       * - 정보 은닉하기 위해 접근 제어자를 활용
       * - 직접 접근해야 하는 정보와 기능을 통해 접근해야 하는 정보로 구분지어 사용
       *   => 기능을 통해 접근해야 하는 정보들을 정보 은닉이라고 함
       *   
       * - 종류
       *   - public : 모두 접근이 가능
       *     - 클래스에 public을 붙이 조건
       *       - 클래스명과 파일명이 같은 경우면 public
       *       
       *   - protected : 나 + 자식 클래스 + 같은 패키지
       *   
       *   - 디폴트(없음) : 나 + 같은 패키지 
       *   
       *   - private : 나
       *   
       * - 보통 필드(멤버 변수)는 private으로 만들고, 메서드는 public으로 만듬
       *   => 필드를 private으로 하고, 필드를 접근할 수 있는 메서드를 추가
       *   => getter(필드 값을 가져오는 메서드), setter(필드 값을 수정하는 메서드)p.163
       *   => 보통 IDE에서 getter/setter만드는 기능을 제공
       *   => lombok 라이브러리를 이용하면 어노테이션(@)을 이용하여 getter,setter를 쉽게
       *      추가할 수 있음
       *      
       * 캡슐화 p.167
       * - 사용자가 필요한 정보만 공개(public)하고 나머지는 숨기는 것(private, protected)
       * 
       */
		//아래 만든 클래스를 이용하여 인스턴스(객체)를 생성하세요.
		같은패키지클래스 같은패키지객체 = new 같은패키지클래스();
		System.out.println(같은패키지객체.퍼블릭변수);
		System.out.println(같은패키지객체.디폴트변수);
		//같은 패키지에 있는 클래스이어도 private 변수에는 접근할 수 없음
		//System.out.println(같은패키지객체.프라이빗변수);
		
		다른패키지클래스 다른패키지객체 = new 다른패키지클래스();
		System.out.println(같은패키지객체.퍼블릭변수);
		//다른 패키지에 있는 클래스이면 디폴트 변수에 접근할 수 없음
		System.out.println(같은패키지객체.디폴트변수);
		//같은 패키지에 있는 클래스이어도 private 변수에는 접근할 수 없음
		//System.out.println(같은패키지객체.프라이빗변수);

	}

}


	class 같은패키지클래스{
		public int 퍼블릭변수 = 1;
		int 디폴트변수 = 2;
		private int 프라이빗변수 = 3;
	}
