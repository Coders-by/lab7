package ������Ϸ;
import java.util.Scanner;


public class main {
	public static void main(String[] args) {
		int n = 8;//Ĭ�����̴�СΪ8
		LifeGame game = new LifeGame();
		
		Cell[][] cells = new Cell[n][n];
		game.run_game(cells);
		System.out.println(game.printMap(cells));
		
		GUI myGUI = new GUI("������Ϸ");
	}
}


