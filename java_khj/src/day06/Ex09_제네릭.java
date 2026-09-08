package day06;

public class Ex09_제네릭 {

	public static void main(String[] args) {
		  /* 제네릭 p.406
	       * - 필드나 메서드의 타입을 고정으로 하지 않고 객체를 생성할 때 타입을 지정하는 방식
	       * - 객체 생성시 타입은 기본형 자료형을 쓸 수 없다
	       *   => 기본 자료형과 비슷한 Wrapper클래스를 제공
	       *   => int => Integer, char =>Charater, double =>Double
	       * - 제네릭 클래스 선언
	       *    class 클래스명<T>{
	       *       T 필드명;
	       * 
	       *       void set필드명(T 변수명){
	       *          this.필드명 = 필드명;
	       *       }   
	       *       T get필드명(){
	       *          return 필드명;
	       *       }
	       *    }
	       * - 제네릭 클래스 객체 생성
	       * 클래스명<타입> 객체명 = new 생성자<타입>();
	       * 
	       */
		
		Array<Integer> array = new Array<Integer>();
		array.array = new Integer[20];
		array.array[0] = 1;
		System.out.println(array.array[0]);
		
		Array<Character> array2 = new Array<Character>();
		array2.array = new Character[20];
		array2.array[0] = 97;
		System.out.println(array2.array[0]);

	}

}

class Array<T>{
	T [] array;
}