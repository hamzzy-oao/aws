package day07;

import java.util.HashMap;
import java.util.Set;


public class Ex02_Map {

	public static void main(String[] args) {
		/* Map
		 * - Key-Value 형태로 데이터를 관리
		 * - Key : 중복 허용 X, 아이디 이행
		 * - Value: 중복 허용. 비번 이해
		 * - 기능
		 * 	- get(key) : 키들 중 key와 일치하는 value를 리턴
		 * 	- put(key, value) : 키들 중 key 일치하는 객체가 있으면 수정, 없으면 추가
		 *  - remove(key) : 키들 중 key와 일치하는 객체가 있으면 삭제 후 value를 리턴
		 * 	- keySet() : 키들을 모아 Set으로 만들어서 반환
		 * */
		
		//과일 상자에 들어 있는 과일 개수
				HashMap<String, Integer> fruitBoxes = new HashMap<String, Integer>();
				
				//사과 10개를 추가
				fruitBoxes.put("사과", 10);
				//포도 10송이를 추가
				fruitBoxes.put("포도", 10);
				//사과 20개를 추가
				fruitBoxes.put("사과", 20);
				//바나나 5개를 추가
				fruitBoxes.put("바나나", 5);
				
				//포도를 제거
				fruitBoxes.remove("포도");
				
				System.out.println(fruitBoxes);
				
				//Map을 반복문으로 활용하는 예제
				
				//Map의 키들을 모아놓은 셋을 만듬
				Set<String> fruits =  fruitBoxes.keySet();
				
				for(String fruit : fruits) {
					System.out.println(fruit+" 상자 : " + fruitBoxes.get(fruit));
				}
			}

		}
