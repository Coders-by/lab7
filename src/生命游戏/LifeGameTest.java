package 生命游戏;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LifeGameTest {
	LifeGame game = new LifeGame();
	private static Cell[][] cells = new Cell[4][4];
	//private static Cell[][] cellsTest = new Cell[100][100];
	@Before
	public void setUp() throws Exception {
		game.init_game(cells, 4, 4);
	}
	
	@After
	public void tearDown() throws Exception {
	    System.out.println("this is tearDown...");
	}
	
	@Test
	public void test_get_around_alive() {
		game.get_around_alive(cells);
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				assertEquals(0, cells[i][j].around_alive);
			}
		}
	}
//	@Test
//	public void test_printMap() {//也同时测试了init_game是否初始化成功
////		String [][] s={{"● ","● ","● ","○ "}, {"● ","● ","○ ","● "},
////				{"● ","● ","● ","○ "}, {"○ ","● ","○ ","○ "}};
////		for (int i = 0;i<4;i++)
////		{
////			for(int j = 0; j< 4; j++)
////				System.out.print(s[i][j]);
////			System.out.println();
////		}
//		String s = new String();
//		s = game.printMap(cells);
//		assertEquals("● ● ● ○ ● ● ○ ● ● ● ● ○ ○ ● ○ ○ ", s);
//	}

}
