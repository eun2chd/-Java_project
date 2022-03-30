package avoidGameProject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class avoidGame extends JFrame{

	private Image bufferImage;
	private Graphics scrGraphics;
	
	
	ImageIcon lodingicon = new ImageIcon("images/loding.png");
	ImageIcon gameicon = new ImageIcon("images/game_screen.png");
	
	
	Image loding_img = lodingicon.getImage();
	Image loding_changeImg = loding_img.getScaledInstance(main.WIDTH_SCREEN, main.HEIGHT_SCREEN, Image.SCALE_SMOOTH);
	
	Image game_img = gameicon.getImage();
	Image game_changeImg = game_img.getScaledInstance(main.WIDTH_SCREEN, main.HEIGHT_SCREEN, Image.SCALE_SMOOTH);
	
	private Game game = new Game(); // game Ŭ���� ���� �޸𸮿� �ø���
	
	
	
	
	
	private boolean isloading_Screen, isgame_Screen;
	
	
	
	public avoidGame() {

		init();
		setInitLayout();

	}
	
	private void init() {
		
		isloading_Screen = true;
		isgame_Screen = false;
		
		setTitle("�� ���ϱ� ����");
		setSize(main.WIDTH_SCREEN,main.HEIGHT_SCREEN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(new keyListener());

	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
			
	}
	
	private void gameStart() {
		
		isloading_Screen = false;
		
		isgame_Screen = true;
		
		game.start();
		
	}
	
	public void paint(Graphics g) {
		
		bufferImage = createImage(main.WIDTH_SCREEN,main.HEIGHT_SCREEN);
		scrGraphics = bufferImage.getGraphics();
		screenDraw(scrGraphics);
		g.drawImage(bufferImage, 0, 0, null);
	}
	
	
	// ���� ���۾ȳ� ���� �׸���
	public void info(Graphics g) {
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Game Strat Button 'Enter' ", 195, 170);
	}
	
	
	// �� ȭ���� ������ true �϶����� �ٸ� ȭ���� �׸�
	
	public void screenDraw(Graphics g) {
		if(isloading_Screen) {
			g.drawImage(loding_changeImg, 0, 0, null);
			info(g);
		}
		if(isgame_Screen) {
			g.drawImage(game_changeImg, 0, 0, null);
			game.gameDraw(g); // ����ȭ�鿡 �÷��̾� �׷��ֱ�
		}
		this.repaint();
	}
	
	class keyListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_UP :
				game.setUp(true);
				break;
			case KeyEvent.VK_LEFT :
				game.setLeft(true);
				break;
			case KeyEvent.VK_RIGHT :
				game.setRight(true);
				break;
			case KeyEvent.VK_DOWN :
				game.setDown(true);
				break;
				
			case KeyEvent.VK_R : 
				if(game.isIsover()) {
					game.reset();
				}
				break;
	
			case KeyEvent.VK_ENTER :
				gameStart();	
				break;
			
			}
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			switch (e.getKeyCode()) {
			
			case KeyEvent.VK_UP :
				game.setUp(false);
				break;
			case KeyEvent.VK_LEFT :
				game.setLeft(false);
				break;
			case KeyEvent.VK_RIGHT :
				game.setRight(false);
				break;
			case KeyEvent.VK_DOWN :
				game.setDown(false);
				break;

			}
		
		}
		
	}

}
