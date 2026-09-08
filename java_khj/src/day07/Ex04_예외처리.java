package day07;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex04_예외처리 {

	public static void main(String[] args) {
		  /* 오류 p.520
	       * - 컴파일 오류
	       *   - 개발자가 코드 작성 단계에서 문법을 제대로 안 지키는 경우 발생
	       * - 실행 오류
	       *   - 프로그램이 실행될 때 발생
	       *   - 시스템 오류
	       *     - JVM에서 생기는 오류로 프로그램 코드로 해결할 수 없음
	       *   - 예외
	       *     - 프로그램 코드로 처리할 수 있음
	       *     
	       * 예외 종류 p.521
	       * - Exception 클래스
	       *   - 예외 클래스 최고 조상 클래스
	       * - RuntimeException 클래스
	       *   - 프로그램 실행 시 발생하는 예외
	       *   - 예외처리를 하지 않아도 코드상 오류가 발생하지 않음
	       *   - NullPointerException : 객체가 만들어지지 않은 상황에서 객체에 접근
	       *   - ArithmeticException : 정수/정수에서 0으로 나눌 때 발생
	       *   - ArrayIndexOutOfBoundsException : 배열이나 리스트의 잘못된 번지에 접근
	       * - RuntimeException 클래스가 아닌 클래스
	       *   - 예외처리를 하지 않으면 코드상 오류가 발생 => 반드시 예외처리를 해야 함
	       *   
	       *   
	       * 예외 처리 방법
	       * 1. 예외가 발생한 곳에서 직접 처리 p.523
	       * - 예외처리하기
	       * - 문법
	       *  try{
	       *     예외가 발생할 수 있는 코드
	       *  }catch(예외클래스명1 e){
	       *  
	       *  }catch(예외클래스명2 e){
	       *  
	       *  }finally{
	       *     //예외가 발생하든 발생하지 않든 무조건 실행되는 코드
	       *     //예외처리 뒤에 코드를 넣는 것과 다름
	       *     //=> try나 catch에 return이 있어서 메서드가 종료 되어도
	       *     //   종료되기 전에 무조건 실행 
	       *  }
	       *  - 예외클래스명1은 예외클래스명2의 조상클래스가 오면 안됨
	       *  
	       * 
	       * 2. 예외가 발생한 곳에서 처리하지 않고 호출한 메서드에게 넘기는 방법 p.534
	       * - 예외 처리 미루기, 예외 처리 던지기 
	       * - 예외가 생길 코드에 예외 던지기
	       *   - 예외가 발생할 수 있는 상황을 조건문으로 처리하고 throw를 이용하여 예외를 던짐
	       * - 메서드 선언부에 throws를 입력 후 발생할 수 있는 예외를 적어줌
	       *   - 단, RuntimeException과 자손 클래스들은 생략 가능
	       *   
	       * 예외 처리를 왜 해야하는가?
	       * - 예외처리를 하지 않으면 프로그램이 중단됨
	       * - 예외처리를 해서 프로그램이 중단되는걸 방지 
	       */
		
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("정수1 입력 : ");
			int num1 = scan.nextInt();
			System.out.print("정수2 입력 : ");
			int num2 = scan.nextInt();
			//System.out.println(num1 + " / " + num2 + " = " + num1 / num2);
			printDivde(num1, num2);
		}
		catch (InputMismatchException e) {
			System.out.println("입력을 잘못해서 예외가 발생했습니다.");
		}
		catch (ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
		}
		catch(Exception e) {
			System.out.println("예외 발생!");
		}
		System.out.println("프로그램 종료");		
	}

	//예외를 처리하지 않고 시킨 곳에서 처리하도록 안내
	public static void printDivde(int num1, int num2) throws ArithmeticException {
		System.out.println(num1 + " / " + num2 + " = " + num1 / num2);
	}
}
