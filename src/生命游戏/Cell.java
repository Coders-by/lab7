package ������Ϸ;

import java.util.Random;

public class Cell {
	
    public int is_alive = 0;//1-ϸ���0-ϸ����
    public int around_alive = 0;//��Χ��ϸ������Ŀ
    
    public void make_alive() {
    	is_alive = 1;
    }
    
    public void make_dead() {
    	is_alive = 0;
    }
}
