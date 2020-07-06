package ������Ϸ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI<ThreadService> extends JFrame implements ActionListener {
    public static JFrame frame;
    Cell[][] cells = new Cell[100][100];
    LifeGame game = new LifeGame();
    
    public int rlen = 0, clen = 0, gnr = 0; //ϸ������������������ʼ��Ϊ0; ������������ʼ��Ϊ0
    public JButton[][] gameBoard; //��Ϸ����
    public boolean[][] isSelected;  //ϸ��ѡ��״̬
    public JButton set_boardsize, nextGameStatus, init_gameBoard, set_generations; //
    public JComboBox rList, cList;//���̵�����������
    public JComboBox gList;//�ֶ����뷱ֳ����
    public Thread thread;

    public static void main(String arg[]) {
    	frame = new GUI("������Ϸ");
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
    	// Ĭ�����̴�С
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
                gameBoard[i][j] = new JButton(""); //��button����ɫ��ʾϸ������������button�����ÿ�
                centerPanel.add(gameBoard[i][j]);
            }
        }

        jrows = new JLabel("������");
        rList = new JComboBox();
        // ÿ��ϸ��Ҫ������Χ8��ϸ����״̬������һ����9��ϸ����9=3��3
        for (int i = 3; i <= 100; i++)
            rList.addItem(String.valueOf(i));
        rList.setSelectedIndex(rlen - 3); 

        jcollums = new JLabel("������");
        cList = new JComboBox();
        for (int i = 3; i <= 100; i++)
            cList.addItem(String.valueOf(i));
        cList.setSelectedIndex(clen - 3);

        jgenerations = new JLabel("�Զ���ֳ������");
        gList = new JComboBox();
        //�����û���ѡ��ķ�ֳ����
        for (int i = 1; i <= 1000; i++)
            gList.addItem(String.valueOf(i - 1));
        gList.setSelectedIndex(gnr); 
        
        
        set_boardsize = new JButton("�������̴�С");
        init_gameBoard = new JButton("���ɳ�ʼ����");
        nextGameStatus = new JButton("��һ��״̬");
        set_generations = new JButton("��ʼ�Զ���ֳ");
        
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

        // ���ô���
        this.setSize(1000, 1000);
        this.setResizable(true);
        this.setVisible(true);// ��������Ϊ�ɼ�

        // ע�����
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

    public void showMap(Cell[][] cells) {//ͨ���޸�Button����ɫ��ʵʱ��ʾ��Ϸ״̬
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (cells[i][j].is_alive == 1) {
                    gameBoard[i][j].setBackground(Color.BLACK); //��ɫ��ʾ��ϸ��
                } else {
                    gameBoard[i][j].setBackground(Color.WHITE); //��ɫ��ʾ��ϸ��
                }
            }
        }
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == init_gameBoard) { //�����ʼ��
            cells = game.init_game(cells);//��ʼ��
            showMap(cells);
        }else if (e.getSource() == nextGameStatus) {//��һ��״̬
        	game.get_Next(cells);
        	showMap(cells);
        }else if (e.getSource() == set_boardsize) {//�������̴�С
            set_rlen(rList.getSelectedIndex() + 3);
            set_clen(cList.getSelectedIndex() + 3);
            initGUI();
        }
        else if (e.getSource() == set_generations) {//ѡ���Զ������Ĵ���
            set_gnr(gList.getSelectedIndex());
            
            new Thread(new Runnable() {//��Ҫע�⣬����ˢ�����߳�ͬ���ģ�������ôд��ֻ��ˢ�³����������״̬
            	public void run() {
            		for (int i = 0; i < gnr; i++) {
            			game.get_Next(cells);
                    	showMap(cells);
            			try {
        					Thread.sleep(100);//ÿ1000����Ϊ1��
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
