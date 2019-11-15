package classes;

import java.awt.*;

import javax.swing.*;

public class Player extends Thread implements GamePiece {

	// the game panel
	GamePanel gp;
	
	// the player coordinates
	int x;
	int y;
	
	// which way the player facing
	boolean isFacingRight;
	
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
	
	// does the player able to shoot
	boolean recoilTime;
	
	// movement direction
	boolean moveUp, moveDown, moveRight, moveLeft;
	
	/**
	 * Player class constructor
	 */
	public Player(GamePanel gamePanel, int playerSize) {
		gp = gamePanel;
		x = 450;
		y = 850;
		size = playerSize;
		power = 1;
		lives = 3;
		hp = 200;
		isFacingRight = true;
		recoilTime = false;
		moveUp = false;
		moveDown = false;
		moveRight = false;
		moveLeft = false;
		playerLook = new ImageIcon("static/images/characters/iron-fist-right.png").getImage();
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
		if(add<0) {
			if(   !checkBlockEncounter(Direction.WEST)  
				&& !checkFieldBorderEncounter(Direction.WEST)) {
				x += add;
			}
		}
		if(add>0) {
			if(   !checkBlockEncounter(Direction.EAST)  
					&& !checkFieldBorderEncounter(Direction.EAST)) {
					x += add;
				}
		}
		gp.startMovementDetector();
		if(add<0 && isFacingRight) {
			flipPlayer(false);
		}
		if(add>0 && !isFacingRight) {
			flipPlayer(true);
		}
	}
	/**
	 * Update player y coordinate
	 * @param add To move up => add: negative value;   
	 * 			    To move down => add: positive value;  
	 */
	public void updateY(int add) {
		if(add<0) {
			if(   !checkBlockEncounter(Direction.SOUTH)  
				&& !checkFieldBorderEncounter(Direction.SOUTH)) {
				y += add;
			}
		}
		if(add>0) {
			if(   !checkBlockEncounter(Direction.NORTH)  
					&& !checkFieldBorderEncounter(Direction.NORTH)) {
					y += add;
				}
		}
		gp.startMovementDetector();
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
			   calcMovement();
			   Thread.sleep(20);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
		    
		    if(!didMoved()) {
		    	if(!recoilTime) {
		    		makeShot();
		    	}
		    }
			gp.repaint();
		}
	}
	
	public void drawPlayer(Graphics g){
		g.drawImage(playerLook,x,y,size,size, null);
	}
	
	/**
	 * flip the player image
	 * @param direction false-change to left, true- change to right
	 */
	private void flipPlayer(boolean direction) {
		
		if(isFacingRight && !direction ) {
			playerLook = new ImageIcon("static/images/characters/iron-fist-left.png").getImage();
			isFacingRight = false;
		}
		if(!isFacingRight && direction) {
			playerLook = new ImageIcon("static/images/characters/iron-fist-right.png").getImage();
			isFacingRight = true;
		}
	}
	
	/**
	 * Check weather the player moved
	 * @return did the player moved or not, boolean
	 */
	private boolean didMoved() {
		return gp.isMoved;
	}
	
	/**
	 * make a new shot and set recoil
	 */
	private void makeShot() {
		gp.addShot(new Shot(gp, x + size/4, y + size / 4, size/2, setHitPower(), setDirection()));
		new RecoilMeter(this);
	}
	
	/**
	 * used by recoil meter threads, this will keep the shots being fired with delay
	 * @param val
	 */
	public void setRecoil(boolean val) {
		recoilTime = val;
	}
	
	/**
	 * set the direction of the new shot.
	 * at next dev stage will calculate direction by finding 
	 * the differences between player and enemy positions.
	 * @return Direction enum value;
	 */
	private Direction setDirection() {
		if(isFacingRight) {
			return Direction.EAST;
		}
		return Direction.WEST; 	
	}
	
	/**
	 * set the current new shot hit power.
	 * at next dev stage will calculate with 
	 * power ups.
	 * @return double value.
	 */
	private double setHitPower(){
		return power;
	}
	
	public boolean checkBlockEncounter(Direction d) {
		boolean hitDetected = false;
		for(Block b : gp.blocks) {
			// if the shot has hit a wall, stop testing and end loop
			if(hitDetected) {
				break;
			}
			
			/*
			 * check 4 points of the shot and check if 
			 */
			hitDetected = CollusionHandler.DidCollusion(this, b,d);
		}
		return hitDetected;
	}
	
	public boolean checkFieldBorderEncounter(Direction d) {
		switch(d) {
		case EAST:
			return x + size >= 1000;
		
		case WEST:
			return x <= 0;
			
		case NORTH:
			return y <= 0;
			
		case SOUTH: 
			return y + size >= 1050;
			
		default:
			return false;
		
		}
	}
	
	public int getSize() {return size;}

	private void calcMovement() {
		if(moveUp)    updateY(-3);
		if(moveDown)  updateY( 3);
		if(moveLeft)  updateX(-3);
		if(moveRight) updateX( 3); 
	}

}
