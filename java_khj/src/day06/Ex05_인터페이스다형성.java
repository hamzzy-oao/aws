package day06;

public class Ex05_인터페이스다형성 {

	public static void main(String[] args) {
		
		Game1 game1 = new Game1();
		Game2 game2 = new Game2();
		
		play(game1);

	}
	
	public static void play(Player Player) {
		Player.play();
	}

}

interface Player{
	
	void play();
}

class Game1 implements Player{
	
	public void play() {
		System.out.println("게임1을 플레이합니다.");
	}
}

class Game2 implements Player{
	
	public void play() {
		System.out.println("게임2을 플레이합니다.");
	}
}