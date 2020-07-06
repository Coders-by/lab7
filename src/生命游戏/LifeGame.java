package 生命游戏;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;


public class LifeGame {//游戏规则，各种操作

	public void get_around_alive(Cell[][] cells) {//传参为二维数组
		int rlen = cells.length;//二维数组行数
		int clen = cells[0].length;//二维数组列数

		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
	        	
	            int alive = 0;
	            //1.判断正下方的位置
	            if (i + 1 < rlen && cells[i + 1][j].is_alive == 1) {
	                alive++;
	            }
	            //2.判断右下位置
	            if (i + 1 < rlen && j + 1 < clen && cells[i + 1][j + 1].is_alive == 1) {
	                alive++;
	            }
	            //3.判断左下位置
	            if (i + 1 < rlen && j - 1 >= 0 && cells[i + 1][j - 1].is_alive == 1) {
	                alive++;
	            }
	            //4.判断右侧位置
	            if (j + 1 < clen && cells[i][j + 1].is_alive == 1) {
	                alive++;
	            }
	            //5.判断左侧位置
	            if (j - 1 >= 0 && cells[i][j - 1].is_alive == 1) {
	                alive++;
	            }
	            //6.判断正上方位置
	            if (i - 1 >= 0 && cells[i - 1][j].is_alive == 1) {
	                alive++;
	            }
	            //7.判断右上位置
	            if (i - 1 >= 0 && j + 1 < clen && cells[i - 1][j + 1].is_alive == 1) {
	                alive++;
	            }
	            //8.判断左上位置
	            if (i - 1 >= 0 && j - 1 >= 0 && cells[i - 1][j - 1].is_alive == 1) {
	                alive++;
	            }
	            cells[i][j].around_alive = alive;//赋值
	        }
		}
	}
	
	public void get_Next(Cell[][] cells){
		int rlen = cells.length;//二维数组行数
		int clen = cells[0].length;//二维数组列数
		get_around_alive(cells);
		
		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
	            /*
			             每个细胞的状态由该细胞及周围 8 个细胞上一次的状态所决定； 
			            如果一个细胞周围有 3 个细胞为生，则该细胞为生，即该细胞若原先为死则转为生， 若原先为生则保持不变； 
			            如果一个细胞周围有 2 个细胞为生，则该细胞的生死状态保持不变； 
			            在其它情况下，该细胞为死，即该细胞若原先为生则转为死，若原先为死则保持不变
	            */
	        	switch(cells[i][j].around_alive) {
	        	case 3:
	        		cells[i][j].make_alive();
	        		break;
	        	case 2:
	        		break;
	        	default:
	        		cells[i][j].make_dead();
	        		break;
	        	}
	        	
	        	
//	        	if (cells[i][j].around_alive == 3)
//	        		cells[i][j].make_alive();
//	        	else if (cells[i][j].around_alive == 2)
//	        		continue;
//	        	else
//	        		cells[i][j].make_dead();
	        }
		}
	}
	
	public String printMap(Cell[][] cells){//String类型方便做Junit测试
		int rlen = cells.length;//二维数组行数
		int clen = cells[0].length;//二维数组列数

		String s = new String();
		
		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
                //打印状态
                if (cells[i][j].is_alive == 1)
                {
                	System.out.print("● ");
                	s = s + "● ";//为了做Junit测试用
                }
                if (cells[i][j].is_alive == 0)
                {
                	System.out.print("○ ");
                	s = s + "○ ";
                }
            }
            System.out.println();
        }
		return s;
		
	}
	
	public Cell[][] init_game(Cell [][] cells) {//随机生成游戏初始状态
		
		int rlen = cells.length;//二维数组行数
		int clen = cells[0].length;//二维数组列数

		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
            	cells[i][j] = new Cell();//要写此句，否则报空指针错误
                //随机给is_alive赋值，其值为1或0
            	int r = new Random().nextInt(2);
            	if (r == 0)
            		cells[i][j].make_dead();
            	else
            		cells[i][j].make_alive();
            }
        }
		return cells;
	}
	
	//以下为方便做Junit测试
	public Cell[][] init_game(Cell[][] cells, int rlen, int clen) {//方法重载，手动输入游戏初始状态
//		for (int i = 0; i < rlen ; i++) {
//			for (int j = 0; j < clen; j++) {
//	        	cells[i][j] = new Cell();//要写此句，否则报空指针错误
//	        }
//		}
//		cells[0][0].is_alive = 1;cells[0][1].is_alive = 1;cells[0][2].is_alive = 1;cells[0][3].is_alive = 0;
//		cells[1][0].is_alive = 1;cells[1][1].is_alive = 1;cells[1][2].is_alive = 0;cells[1][3].is_alive = 1;
//		cells[2][0].is_alive = 1;cells[2][1].is_alive = 1;cells[2][2].is_alive = 1;cells[2][3].is_alive = 0;
//		cells[3][0].is_alive = 0;cells[3][1].is_alive = 1;cells[3][2].is_alive = 0;cells[3][3].is_alive = 0;
		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
            	cells[i][j] = new Cell();//要写此句，否则报空指针错误
            	//Scanner in = new Scanner(System.in);//手动输入生死状态
            	//int isalive = in.nextInt();//得到输入的整数
            	int isalive = 0;
            	if (isalive == 0)
            		cells[i][j].make_dead();
            	else
            		cells[i][j].make_alive();
            }
        }
		return cells;
	}
	
	public void run_game(Cell[][] cells) {
		init_game(cells);
        //打印初始状态
        printMap(cells);
		System.out.println("1-下一步，0-退出游戏");
		int choice = new Scanner(System.in).nextInt();
		while (choice == 1) {
			System.out.println("---------------我是分割线----------------");
			get_Next(cells);
			//打印状态
			printMap(cells);
			System.out.println("1-下一步，0-退出游戏");
			choice = new Scanner(System.in).nextInt();
		}
		System.out.println("游戏已退出");	
	}
	
}