package Ball_avoid_Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{

	private int delay = 20; // ������
	private long pretime; // ����ð��� ����
	private int cnt; // cnt ���� ������Ű�鼭 ���÷��̾ �����ҿ���
	private int score; // �÷��̾� ����
	
	ImageIcon player = new ImageIcon("images/among.png");
	
	Image play_img = player.getImage();
	Image play_change = play_img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	
    int playwidth = 80;
	int playheight = 80;
	

	
	
	
	
	private Rock rock;
	
	private int playerX, playerY; // �÷��̾� ��ǥ��
	private int playerSpeed = 11; // �÷��̾� �ӵ�
	private int playerHp = 100; // �÷��̾� ü��
	
	private boolean up,down,left,right; // �÷��̾� ����Ű
	
	private boolean isover;
	
	// ��ֹ��� ������ ���⸶�� �迭�� �����ϰ� ��ֹ��� ����
	private ArrayList<Rock> rockList = new ArrayList<Rock>(); 

	
	
	@Override
	public void run() {
			
		reset();
		
		while(true) {
			while(!isover) {
				
				pretime = System.currentTimeMillis();
				if(System.currentTimeMillis() - pretime < delay) { // ����ð� - cnt�����ϱ��� �ð� < delay �� ��� �����̸�ŭ �����忡 sleep �� ����
					try {
						Thread.sleep(30);
						keyProcess();
						rockApperProcess();
						rockMoveProcess();
						

						hitAttackProcess();
						
						cnt++;
						score += 50;
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
		
			}
			
			try {
				
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void reset() {
		
		isover = false;
		cnt = 0;
		score = 0;
		playerHp = 100;
		playerX = 300;
		playerY = 300;
		
		rockList.clear();
		
	}
	

	private void keyProcess() {
		
		if(up && playerY - playerSpeed > 15) {
			playerY -= playerSpeed;
		}
		if(down && playerY + playheight + playerSpeed < 495) {
			playerY += playerSpeed;
			
		}
		if(left && playerX - playerSpeed > 0) {
			playerX -= playerSpeed;
		}
		if(right && playerX + playwidth + playerSpeed < 652) {
			playerX += playerSpeed;
		}
		
		
	}
	
	// ���Ӿ��� ��ҵ��� �׷���
	public void gameDraw(Graphics g) {
		playerDraw(g);
		rockDraw(g);
		infoDraw(g);
	}
	
	// �÷��̾� �׸���
	public void playerDraw(Graphics g) {
	
		g.drawImage(play_change, playerX, playerY, null);		
		g.setColor(Color.GREEN);
		g.fillRect(playerX+10, playerY - 5 , playerHp / 2, 10);

		
	}
	
	// �÷��̾ �¾�����
	private void hitAttackProcess() {
	
		
		for(int i = 0; i < rockList.size(); i++) {
			rock = rockList.get(i);
			if(rock.x > playerX && rock.x < playerX + playwidth && rock.y > playerY && rock.y < playerY + playheight) {
				
				playerHp -= rock.power;
				rockList.remove(rock);
				if(playerHp <= 0) {
					isover = true;
				}
			}
			
		}
		

	}
	
	
	// ��ֹ� �׸���
	public void rockDraw(Graphics g) {
		for(int i = 0; i < rockList.size(); i++) {
			rock = rockList.get(i);
			g.drawImage(rock.rock_change, rock.x, rock.y, null);
		}
	
	
	}
	

	
	
	// ��ֹ� ����
	public void rockApperProcess() {
		if(cnt % 20 == 0) {
			
			int x = (int)(Math.random() * 720);
			int y = (int)(Math.random() * 500) + 18;
			
			rock = new Rock(x,y);
			rockList.add(rock);
			
		}
		
		if(score > 500) {
			if(cnt % 10 == 0) {
				
				int x = (int)(Math.random() * 720);
				int y = (int)(Math.random() * 500) + 18;
				
				rock = new Rock(x,y);
				rockList.add(rock);
				
			}
		}
		

		
	}
	

		// --------------------------------------------------------------------
		// --------------------------------------------------------------------
		
		
	
	// ��ֹ� �̵�
	public void rockMoveProcess() {
		for(int i = 0; i < rockList.size(); i++) {
			rock = rockList.get(i);
				rock.move();
			if(score > 1000) {
				rock.moveSpeed_Up(); 
			}
		
		}
		
	}
	
	
	public void infoDraw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE : " + score, 10, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("HP : " + playerHp, 150, 50);
		
		if(isover) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 35));
			g.drawString("Game over -> R key to reset", 130,250);
		}
		
		
	}


	

	public void setUp(boolean up) {
		this.up = up;
	}


	public void setDown(boolean down) {
		this.down = down;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isIsover() {
		return isover;
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	

	
	
}

