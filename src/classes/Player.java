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
	double basePower;
	double extraPower;
	
	// player health points
	double hp;
	
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
		basePower = 25;
		extraPower = 0;
		lives = 3;
		hp = 500;
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
			if(   !checkBlockEncounter(Direction.NORTH)  
				&& !checkFieldBorderEncounter(Direction.NORTH)) {
				y += add;
			}
		}
		if(add>0) {
			if(   !checkBlockEncounter(Direction.SOUTH)  
					&& !checkFieldBorderEncounter(Direction.SOUTH)) {
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
		if(gp.enemies != null && !gp.enemies.isEmpty() && gp.targetedEnemy != null) {
			gp.addShot(new Shot(gp, x + size/4, y + size / 4, size/2, setHitPower(), setDirection()));
			new RecoilMeter(this);
		}
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
	 * @return Direction enum value;
	 */
	private Direction setDirection() {
		// check x-axis:
		// right to player, left to player or same as player x coordinate.
		// if player facing wrong way, flip his direction.
		
		// x-axis delta
		int deltaX = this.getX() - gp.targetedEnemy.getX();
	
		// y-axis delta
		int deltaY = this.getY() - gp.targetedEnemy.getY();
 		
		// if needs to face east - right
		if(deltaX < -50) {
			
			// flip player to right
			this.flipPlayer(true);
			
			// check y-axis:
			// top to player, bottom to player or same as player y coordinate.
			
			// if needs to face north - up
			if(deltaY > 50) {
				// set direction to north-east
				return Direction.NORTH_EAST;
				
			}else {
				// if needs to face south - down
				if(deltaY < -50) {
					// set direction to south-east
					return Direction.SOUTH_EAST;
					
				}else {
					// y coordinates are equals, shot is horizontal
					// set direction to east
					return Direction.EAST;
				}
			}
			
		}else {
			// if needs to face west - left
			if(deltaX > 50) {
				// flip player to right
				this.flipPlayer(false);
				
				// check y-axis:
				// top to player, bottom to player or same as player y coordinate.
				
				// if needs to face north - up
				if(deltaY > 50) {
					// set direction to north-west
					return Direction.NORTH_WEST;
					
				}else {
					// if needs to face south - down
					if(deltaY < -50) {
						// set direction to south-west
						return Direction.SOUTH_WEST;
						
					}else {
						// y coordinates are equals, shot is horizontal
						// set direction to west
						return Direction.WEST;
					}
				}
				
			}else {
				// x coordinates are equals, facing is not important, shot is vertical
				
				// check y-axis:
				// top to player, bottom to player or same as player y coordinate.
				
				// if needs to face north - up
				if(deltaY < 0) {
					// set direction to north
					return Direction.NORTH;
					
				}else {
					// if needs to face south - down
					if(deltaY > 0) {
						// set direction to south
						return Direction.SOUTH;
						
					}else {
						// y coordinates are equals, 
						// enemy is at same place as player.
						
						// as default, set direction to north
						return Direction.NORTH;
					}
				}
			}
		}
		
		
	
	}
	
	/**
	 * set the current new shot hit power.
	 * at next dev stage will calculate with 
	 * power ups.
	 * @return double value.
	 */
	private double setHitPower(){
		return basePower + extraPower;
	}
	
	/**
	 * check if the player hit any obstacle that block his movement
	 * @param d movement direction
	 * @return boolean
	 */

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
	
	/**
	 * check if the player has hit the boundaries of the screen
	 * @param d walking direction
	 * @return boolean
	 */
	public boolean checkFieldBorderEncounter(Direction d) {
		switch(d) {
		case EAST:
			return x + size >= 1000;
		
		case WEST:
			return x <= 0;
			
		case NORTH:
			return y <= 0;
			
		case SOUTH: 
			return y + size >= 960;
			
		default:
			return false;
		
		}
	}
	
	/**
	 * get player size
	 */
	public int getSize() {return size;}

	/**
	 * calc where the player need to be moving
	 */
	private void calcMovement() {
		if(moveUp)    updateY(-3);
		if(moveDown)  updateY( 3);
		if(moveLeft)  updateX(-3);
		if(moveRight) updateX( 3); 
	}

}
