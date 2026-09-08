package day05;

//도형 클래스
public class Shape {
	
	protected int left, top, right, bottom;
	
	public void draw() {
		System.out.println("도형입니다.");
	}
	
	//우클릭 > source >
	public Shape(int left, int top, int right, int bottom) {
		super();
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public Shape() {
		
	}

}
