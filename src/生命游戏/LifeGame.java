package ������Ϸ;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;


public class LifeGame {//��Ϸ���򣬸��ֲ���

	public void get_around_alive(Cell[][] cells) {//����Ϊ��ά����
		int rlen = cells.length;//��ά��������
		int clen = cells[0].length;//��ά��������

		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
	        	
	            int alive = 0;
	            //1.�ж����·���λ��
	            if (i + 1 < rlen && cells[i + 1][j].is_alive == 1) {
	                alive++;
	            }
	            //2.�ж�����λ��
	            if (i + 1 < rlen && j + 1 < clen && cells[i + 1][j + 1].is_alive == 1) {
	                alive++;
	            }
	            //3.�ж�����λ��
	            if (i + 1 < rlen && j - 1 >= 0 && cells[i + 1][j - 1].is_alive == 1) {
	                alive++;
	            }
	            //4.�ж��Ҳ�λ��
	            if (j + 1 < clen && cells[i][j + 1].is_alive == 1) {
	                alive++;
	            }
	            //5.�ж����λ��
	            if (j - 1 >= 0 && cells[i][j - 1].is_alive == 1) {
	                alive++;
	            }
	            //6.�ж����Ϸ�λ��
	            if (i - 1 >= 0 && cells[i - 1][j].is_alive == 1) {
	                alive++;
	            }
	            //7.�ж�����λ��
	            if (i - 1 >= 0 && j + 1 < clen && cells[i - 1][j + 1].is_alive == 1) {
	                alive++;
	            }
	            //8.�ж�����λ��
	            if (i - 1 >= 0 && j - 1 >= 0 && cells[i - 1][j - 1].is_alive == 1) {
	                alive++;
	            }
	            cells[i][j].around_alive = alive;//��ֵ
	        }
		}
	}
	
	public void get_Next(Cell[][] cells){
		int rlen = cells.length;//��ά��������
		int clen = cells[0].length;//��ά��������
		get_around_alive(cells);
		
		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
	            /*
			             ÿ��ϸ����״̬�ɸ�ϸ������Χ 8 ��ϸ����һ�ε�״̬�������� 
			            ���һ��ϸ����Χ�� 3 ��ϸ��Ϊ�������ϸ��Ϊ��������ϸ����ԭ��Ϊ����תΪ���� ��ԭ��Ϊ���򱣳ֲ��䣻 
			            ���һ��ϸ����Χ�� 2 ��ϸ��Ϊ�������ϸ��������״̬���ֲ��䣻 
			            ����������£���ϸ��Ϊ��������ϸ����ԭ��Ϊ����תΪ������ԭ��Ϊ���򱣳ֲ���
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
	
	public String printMap(Cell[][] cells){//String���ͷ�����Junit����
		int rlen = cells.length;//��ά��������
		int clen = cells[0].length;//��ά��������

		String s = new String();
		
		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
                //��ӡ״̬
                if (cells[i][j].is_alive == 1)
                {
                	System.out.print("�� ");
                	s = s + "�� ";//Ϊ����Junit������
                }
                if (cells[i][j].is_alive == 0)
                {
                	System.out.print("�� ");
                	s = s + "�� ";
                }
            }
            System.out.println();
        }
		return s;
		
	}
	
	public Cell[][] init_game(Cell [][] cells) {//���������Ϸ��ʼ״̬
		
		int rlen = cells.length;//��ά��������
		int clen = cells[0].length;//��ά��������

		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
            	cells[i][j] = new Cell();//Ҫд�˾䣬���򱨿�ָ�����
                //�����is_alive��ֵ����ֵΪ1��0
            	int r = new Random().nextInt(2);
            	if (r == 0)
            		cells[i][j].make_dead();
            	else
            		cells[i][j].make_alive();
            }
        }
		return cells;
	}
	
	//����Ϊ������Junit����
	public Cell[][] init_game(Cell[][] cells, int rlen, int clen) {//�������أ��ֶ�������Ϸ��ʼ״̬
//		for (int i = 0; i < rlen ; i++) {
//			for (int j = 0; j < clen; j++) {
//	        	cells[i][j] = new Cell();//Ҫд�˾䣬���򱨿�ָ�����
//	        }
//		}
//		cells[0][0].is_alive = 1;cells[0][1].is_alive = 1;cells[0][2].is_alive = 1;cells[0][3].is_alive = 0;
//		cells[1][0].is_alive = 1;cells[1][1].is_alive = 1;cells[1][2].is_alive = 0;cells[1][3].is_alive = 1;
//		cells[2][0].is_alive = 1;cells[2][1].is_alive = 1;cells[2][2].is_alive = 1;cells[2][3].is_alive = 0;
//		cells[3][0].is_alive = 0;cells[3][1].is_alive = 1;cells[3][2].is_alive = 0;cells[3][3].is_alive = 0;
		for (int i = 0; i < rlen ; i++) {
	        for (int j = 0; j < clen; j++) {
            	cells[i][j] = new Cell();//Ҫд�˾䣬���򱨿�ָ�����
            	//Scanner in = new Scanner(System.in);//�ֶ���������״̬
            	//int isalive = in.nextInt();//�õ����������
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
        //��ӡ��ʼ״̬
        printMap(cells);
		System.out.println("1-��һ����0-�˳���Ϸ");
		int choice = new Scanner(System.in).nextInt();
		while (choice == 1) {
			System.out.println("---------------���Ƿָ���----------------");
			get_Next(cells);
			//��ӡ״̬
			printMap(cells);
			System.out.println("1-��һ����0-�˳���Ϸ");
			choice = new Scanner(System.in).nextInt();
		}
		System.out.println("��Ϸ���˳�");	
	}
	
}