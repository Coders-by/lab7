package 生命游戏;
import java.util.Scanner;


public class main {
	public static void main(String[] args) {
		int n = 8;//默认棋盘大小为8
		LifeGame game = new LifeGame();
		
		Cell[][] cells = new Cell[n][n];
		game.run_game(cells);
		System.out.println(game.printMap(cells));
		
		GUI myGUI = new GUI("生命游戏");
	}
}


