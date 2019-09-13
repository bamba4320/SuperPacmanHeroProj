package classes;

import java.awt.*;

import javax.swing.*;

public class Player extends Thread {

	// the game panel
	GamePanel gp;
	// the player coordinates
	int x;
	int y;
	
	// player appearance
	Image playerLook;
	
	// player relative width and height
	int size;
	
	// TODO: add array of items for buffs and debuffs
	
	// player strength, might and will be effected during game 
	double power;
	
	// player health points
	int hp;
	
	// how many tries the player has until game over
	int lives;
	
	/**
	 * Player class constructor
	 */
	public Player(GamePanel gamePanel, int playerSize) {
		gp = gamePanel;
		x = 500;
		y = 500;
		size = playerSize;
		power = 1;
		lives = 3;
		hp = 200;
		playerLook = new ImageIcon("static/images/iron-fist.png").getImage();
		start();
	}

	/**
	 * get player x coordinate
	 * @return players x value
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * get player y coordinate
	 * @return players y value
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Update player x coordinate
	 * @param add To move Left => add: negative value;   
	 * 			    To move right => add: positive value;  
	 */
	public void updateX(int add) {
		x += add;
	}
	/**
	 * Update player y coordinate
	 * @param add To move up => add: negative value;   
	 * 			    To move down => add: positive value;  
	 */
	public void updateY(int add) {
		y += add;
	}
	
	/**
	 * an auto-execute function that get called from start()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
		
		   try {
			Thread.sleep(100);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			gp.repaint();
		}	
	}
	
	public void drawPlayer(Graphics g){
		g.drawImage(playerLook,x,y,size,size, null);
	}
	
}
