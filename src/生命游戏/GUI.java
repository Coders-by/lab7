package 生命游戏;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI<ThreadService> extends JFrame implements ActionListener {
    public static JFrame frame;
    Cell[][] cells = new Cell[100][100];
    LifeGame game = new LifeGame();
    
    public int rlen = 0, clen = 0, gnr = 0; //细胞的行数和列数，初始化为0; 进化代数，初始化为0
    public JButton[][] gameBoard; //游戏棋盘
    public boolean[][] isSelected;  //细胞选中状态
    public JButton set_boardsize, nextGameStatus, init_gameBoard, set_generations; //
    public JComboBox rList, cList;//棋盘的行数和列数
    public JComboBox gList;//手动输入繁殖代数
    public Thread thread;

    public static void main(String arg[]) {
    	frame = new GUI("生命游戏");
	}
    /*public static void start(String name) {
		frame = new GUI(name);
	}*/
    
    public void set_rlen(int rlen) {
        this.rlen = rlen;
    }
    public void set_clen(int clen) {
    	this.clen = clen;
    }
    public void set_gnr(int generations) {
    	this.gnr = generations;
    }
    

    public void initGUI() {
    	// 默认棋盘大小
        if (rlen == 0)
            rlen = 10;
        if (clen == 0)
            clen = 10;
        
        JPanel backPanel, centerPanel, bottomPanel;
        JLabel jcollums, jrows, jgenerations;
        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(rlen, clen));
        bottomPanel = new JPanel();
        this.setContentPane(backPanel);
        backPanel.add(centerPanel, "Center");
        backPanel.add(bottomPanel, "South");

        gameBoard = new JButton[rlen][clen];
        isSelected = new boolean[rlen][clen];
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                gameBoard[i][j] = new JButton(""); //以button的颜色表示细胞的生死，则button内容置空
                centerPanel.add(gameBoard[i][j]);
            }
        }

        jrows = new JLabel("行数：");
        rList = new JComboBox();
        // 每个细胞要考虑周围8个细胞的状态，最少一共有9个细胞，9=3×3
        for (int i = 3; i <= 100; i++)
            rList.addItem(String.valueOf(i));
        rList.setSelectedIndex(rlen - 3); 

        jcollums = new JLabel("列数：");
        cList = new JComboBox();
        for (int i = 3; i <= 100; i++)
            cList.addItem(String.valueOf(i));
        cList.setSelectedIndex(clen - 3);

        jgenerations = new JLabel("自动繁殖代数：");
        gList = new JComboBox();
        //设置用户可选择的繁殖代数
        for (int i = 1; i <= 1000; i++)
            gList.addItem(String.valueOf(i - 1));
        gList.setSelectedIndex(gnr); 
        
        
        set_boardsize = new JButton("设置棋盘大小");
        init_gameBoard = new JButton("生成初始棋盘");
        nextGameStatus = new JButton("下一个状态");
        set_generations = new JButton("开始自动繁殖");
        
        bottomPanel.add(jrows);
        bottomPanel.add(rList);
        bottomPanel.add(jcollums);
        bottomPanel.add(cList);
        bottomPanel.add(set_boardsize);
        bottomPanel.add(init_gameBoard);
        bottomPanel.add(nextGameStatus);
        bottomPanel.add(jgenerations);
        bottomPanel.add(gList);
        bottomPanel.add(set_generations);

        // 设置窗口
        this.setSize(1000, 1000);
        this.setResizable(true);
        this.setVisible(true);// 窗口设置为可见

        // 注册监听
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        
        set_boardsize.addActionListener(this);
        init_gameBoard.addActionListener(this);
        nextGameStatus.addActionListener(this);
        set_generations.addActionListener(this);
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                gameBoard[i][j].addActionListener(this);
            }
        }
    }

    public GUI(String name) {
        super(name);
        initGUI();
    }

    public void showMap(Cell[][] cells) {//通过修改Button的颜色，实时显示游戏状态
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (cells[i][j].is_alive == 1) {
                    gameBoard[i][j].setBackground(Color.BLACK); //黑色表示活细胞
                } else {
                    gameBoard[i][j].setBackground(Color.WHITE); //白色表示死细胞
                }
            }
        }
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == init_gameBoard) { //随机初始化
            cells = game.init_game(cells);//初始化
            showMap(cells);
        }else if (e.getSource() == nextGameStatus) {//下一个状态
        	game.get_Next(cells);
        	showMap(cells);
        }else if (e.getSource() == set_boardsize) {//设置棋盘大小
            set_rlen(rList.getSelectedIndex() + 3);
            set_clen(cList.getSelectedIndex() + 3);
            initGUI();
        }
        else if (e.getSource() == set_generations) {//选择自动进化的代数
            set_gnr(gList.getSelectedIndex());
            
            new Thread(new Runnable() {//需要注意，界面刷新是线程同步的，若不这么写，只会刷新出界面的最终状态
            	public void run() {
            		for (int i = 0; i < gnr; i++) {
            			game.get_Next(cells);
                    	showMap(cells);
            			try {
        					Thread.sleep(100);//每1000毫秒为1秒
        				} catch (InterruptedException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				} 
            		}
                }
            }).start();
        }
    }
}
