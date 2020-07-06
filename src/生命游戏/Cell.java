package 生命游戏;

import java.util.Random;

public class Cell {
	
    public int is_alive = 0;//1-细胞活，0-细胞死
    public int around_alive = 0;//周围活细胞的数目
    
    public void make_alive() {
    	is_alive = 1;
    }
    
    public void make_dead() {
    	is_alive = 0;
    }
}
