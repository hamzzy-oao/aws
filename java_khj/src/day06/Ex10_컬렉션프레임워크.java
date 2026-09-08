package day06;

import java.util.ArrayList;

public class Ex10_컬렉션프레임워크 {

	public static void main(String[] args) {
		  /* 컬렉션 프레임워크 p.422
	       * - 자료를 편하게 관리하기 위해 만들어진 라이브러리
	       *   - 배열은 기능이 없어 관리가 불편 => 기능이 있는 배열이라고 생각하면 이해가 쉽다
	       * - 컬렉션 인터페이스와 맵 인터페이스로 구성
	       * - 컬렉션 프레임워크는 제넥릭 인터페이스로 구성되어 있음
	       * - 컬렉션 인터페이스
	       *   - 한 종류의 자료들을 관리
	       *   - List 인터페이스와 Set 인터페이스
	       * - Map 인터페이스
	       *   - 두 종류의 자료들을 관리
	       *   - Map 인터페이스
	       *   
	       * Collection 인터페이스 (List와 Set에 공통으로 있는 기능)
	       * - 기능 
	       *   - add(값) : 값을 뒤에 추가 
	       *   - remove(값) : 값과 일치하는 객체를 제거
	       *   - contains(값) : 값이 있으면 true, 없으면 false를 반환
	       *   - size() : 현재 저장된 크기
	       *   
	       * List p.427
	       * - 순서를 보장. 중복을 허용
	       * - 구현 클래스
	       *   - List 인터페이스에 있는 기능을 공통으로 가지고 있음
	       *   - ArrayList, Vector(쓰레드 지원), LinkedList
	       *   - ArrayList : 배열로 구성, 전체 탐색 빠름, 중간 추가 삭제 느림
	       *   - LinkedList: 링크로 연결, 전체 탐색 느림, 중감 추가 삭제 빠름
	       * - 기능
	       *   - get(번지) : 번지에 있는 객체를 반환
	       *   - remove(번지) : 번지에 있는 객체를 삭제하고 삭제한 객체를 반환
	       *   - set(번지, 값) : 번지에 있는 객체를 값으로 수정(덮어쓰기)
	       *   - indexOf(값) : 값이 있으면 위치를 없으면 -1를 반환 
	       * 
	       * Set p.441
	       * - 순서를 보장하지 않음. 중복을 허용하지 않음
	       * - 구현 클래스
	       *   - HashSet, TreeSet
	       *   - HashSet 
	       *     - 해시 값을 이용하여 중복 체크를 함
	       *       => 같은 해시 값을 가지는 객체들을 모아서 관리
	       *       => hashCode()를 이용하여 비교 후 => equals()를 이용하여 비교 
	       *   - TreeSet
	       *     - Tree 구조로 데이터를 관리
	       * 
	       * Map
	       * - 두 자료형을 관리
	       * - K(key), V(value)
	       *   - K는 중복 안됨
	       *   - V는 중복 허용
	       * - 기능
	       *   - get(k) : 키들 중에서 k와 일치하는 객체를 반환
	       *   - put(k,v) : 키들 중에서 k와 일치하는 객체가 없으면 k와 v를 추가. 있으면 v만 수정
	       *   - remove(k) : 키들 중에서 k와 일치하는 객체가 있으면 삭제 후 v를 반환
	       *   - keySet() : 키값들을 Set으로 만들어서 반환
	       *     - Map에서 반복문을 이용할 때 활용
	       *
	       * - 컬렉션 프레임워크에서 객체를 추가, 삭제할 때 
	       *  해당 객체가 있는지를 Objects.equals(o1,o2)를 이용하여 확인
	       * - Objects.equals(o1,o2)
	       *   - o1과 o2가 다른 클래스이면 false를 반환
	       *   - o1과 o2중 하나라도 null이면 false를 반환
	       *   - o1.equals(o2)를 호출
	       *   => equals를 오버라이딩해야 함
	       *   
	       * 
	       */
		
		//문자열로 된 리스트를 생성 : 제네릭 클래스, 리스트
		ArrayList<String>list = new ArrayList<String>();
		
		list.add("홍길동");
		list.add("임꺽정");
		
		System.out.println(list);
		
		list.remove("홍길동");
		
		list.add("홍길동");
		
		System.out.println(list);
		
		System.out.println("임꺽정이 있습니까?"+list.contains("임꺽정"));
		System.out.println("임꺽정이 몇번지에 있습니까?"+list.indexOf("임꺽정"));
		System.out.println("1번지에 누가 있습니까?"+list.get(1));
		
		list.set(0,"둘리"); //0번지에 있는 임꺽정을 둘리로 수정
		System.out.println(list);
		
		list.add("하니");
		System.out.println(list);
		
		list.remove(0);
		
		System.out.println(list);
		
		System.out.println("저장된 이름 개수 : "+list.size());
		
	}

}
