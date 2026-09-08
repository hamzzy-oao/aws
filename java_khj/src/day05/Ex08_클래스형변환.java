package day05;

public class Ex08_클래스형변환 {

	public static void main(String[] args) {
		/* 클래스 형변환
	       * - 조상 클래스와 자손 클래스 관계일 때 클래스 형변환이 가능
	       * - 업캐스팅
	       *   - 자동으로 변환이 가능
	       *   - 자식 클래스의 객체를 부모 클래스로 변환하는 경우
	       * - 다운캐스팅
	       *   - 강제로 변환
	       *   - 부모 클래스의 객체를 자식 클래스로 변환하는 경우
	       *   - 조건부로 가능
	       */
		
		Shape 도형1 = new Shape();
		도형1.draw();
		Rect 사각형1 = new Rect(0,0,10,10);
		사각형1.draw();
		Circle 원1 = new Circle(5,5,5);
		원1.draw();
		
		사각형1.resize(20,20);
		사각형1.draw();
		
		//업캐스팅. 자식객체 => 부모 객체로 변환.
		Shape 도형2 = new Rect(10,10,20,20);
		도형2.draw();
		//Shape에는 resize가 없어서 실행 안됨
		//실제 도형2 객체 안에는 resize 기능이 있긴 함
		// 하지만 도형2 Shape 클래스의 객체이고, Shape에는 resize기능이 없어서 안됨
		//도형2.resize(20,20);
		
		//다운 캐스팅. 부모객체 => 자식 객체로 변환. 조건부로 가능
		//안되는 경우
		Shape 도형3 = new Shape();
		//Rect 사각형3 = (Rect)도형3;
		//사각형3.draw();
		
		//되는 경우
		Rect 사각형4 = new Rect(2,2,10,10);
		Shape 도형4 = 사각형4;
		//다운캐스팅
		Rect 사각형4_1 =(Rect)도형4;
		사각형4_1.draw();
		사각형4_1.resize(10, 10);
		사각형4_1.draw();
	}

}


