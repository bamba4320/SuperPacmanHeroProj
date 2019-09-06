package classes;

import java.awt.*;

import javax.swing.*;

public class Player implements Runnable {

	// the game panel
	private GamePanel gp;
	// the player coordinates
	private int x;
	private int y;
	
	// player appearance
	private Image playerLook;
	
	// TODO: add array of items for buffs and debuffs
	
	// player strength, might and will be effected during game 
	private double power;
	
	// player health points
	private int hp;
	
	// how many tries the player has until game over
	private int lives;
	
	/**
	 * Player class constructor
	 */
	public Player(GamePanel gp) {
		this.gp = gp;
		this.x = 500;
		this.y = 1600;
		this.power = 1;
		this.lives = 3;
		this.hp = 200;
		ImageIcon ii = new ImageIcon("/images/base_player");
		this.playerLook = ii.getImage();
	}

	/**
	 * get player x coordinate
	 * @return players x value
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * get player y coordinate
	 * @return players y value
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Update player x coordinate
	 * @param add To move Left => add: negative value;   
	 * 			    To move right => add: positive value;  
	 */
	public void updateX(int add) {
		this.x += add;
	}
	/**
	 * Update player y coordinate
	 * @param add To move up => add: negative value;   
	 * 			    To move down => add: positive value;  
	 */
	public void updateY(int add) {
		this.y += add;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
