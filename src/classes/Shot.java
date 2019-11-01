package classes;

import java.awt.*;

import javax.swing.ImageIcon;

public class Shot extends Thread implements GamePiece { 
	GamePanel gp;
	Image shotLook;
	int x;
	int y;
	int size;
	double hitPower;
	int deltax;
	int deltay;
	boolean isAlive;
	Direction movmentDirection;
	
	/**
	 * Constractor
	 * @param gamePanel the main game panel
	 * @param initx where to start on the x axis
	 * @param inity where to start on the y axis
	 * @param initsize what size will it be
	 * @param hit hit power
	 * @param d shot direction
	 */
	public Shot(GamePanel gamePanel,int initx, int inity, int initsize, double hit, Direction d) {
		gp = gamePanel;
		x = initx;
		y = inity;
		isAlive = true;
		size = initsize;
		hitPower = hit;
		movmentDirection = d;
		setDelta();
		setImage();
		start();
//		System.out.println(String.format("x: %d, y:%d",x,y));
	}
	
	/**
	 * Set the shot steps for each axis
	 * @param d: the Direction of the shot
	 */
	private void setDelta() {
		switch(movmentDirection) {
		case EAST:
			deltay=0;
			deltax=20;
			break;
		case NORTH_EAST:
			deltay=20;
			deltax=20;
			break;
		case NORTH:
			deltay=20;
			deltax=0;
			break;
		case NORTH_WEST:
			deltay=20;
			deltax=-20;
			break;
		case WEST:
			deltay=0;
			deltax=-20;
			break;
		case SOUTH_WEST:
			deltay=-20;
			deltax=-20;
			break;
		case SOUTH:
			deltay=-20;
			deltax=0;
			break;
		case SOUTH_EAST:
			deltay=-20;
			deltax=20;
			break;
		default:
			deltay=0;
			deltax=0;
			break;
			
		}
		
	}
	
	/**
	 * Set the shot image base on direction
	 * @param d: the Direction of the shot
	 */
	private void setImage() {
		switch(movmentDirection) {
		case EAST:
			shotLook = new ImageIcon("static/images/fists/fist-east.png").getImage();
			break;
		case NORTH_EAST:
			shotLook = new ImageIcon("static/images/fists/fist-north-east.png").getImage();
			break;
		case NORTH:
			shotLook = new ImageIcon("static/images/fists/fist-north.png").getImage();
			break;
		case NORTH_WEST:
			shotLook = new ImageIcon("static/images/fists/fist-north-west.png").getImage();
			break;
		case WEST:
			shotLook = new ImageIcon("static/images/fists/fist-west.png").getImage();
			break;
		case SOUTH_WEST:
			shotLook = new ImageIcon("static/images/fists/fist-north-west.png").getImage();
			break;
		case SOUTH:
			shotLook = new ImageIcon("static/images/fists/fist-south.png").getImage();
			break;
		case SOUTH_EAST:
			shotLook = new ImageIcon("static/images/fists/fist-south-east.png").getImage();
			break;
		default:
			break;
		}
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
			Thread.sleep(30);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
		    	  e.printStackTrace();
		    }
		   	if(doKillShot()) {
		   		isAlive = false;
		   		break;
		   	} else {
		   		moveShot();
		   	}
			
			gp.repaint();
		}	
	}
	
	/**
	 * Physically draw the image on the screen
	 * @param g Graphics element
	 */
	public void drawShot(Graphics g){
		g.drawImage(shotLook,x,y,size,size, null);
	}
	
	/**
	 * Check if the shot need to be killed.
	 * @return boolean value
	 */
	private boolean doKillShot() {
		return (x == 0 || x == gp.getWidth() || y == 0 || y==gp.getHeight()) || (didHit());
	}
	
	private void moveShot() {
		x+=deltax;
		y+=deltay;
	}
	
	/**
	 * check if shot has hit anything
	 * @return true or false
	 */
	private boolean didHit() {
	
		return checkHitBlock() || checkHitEnemy();
	}

	/**
	 * check if shot has hit an enemy
	 * @return true or false
	 */
	private boolean checkHitEnemy() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * check if shot has hit a wall
	 * @return true or false
	 */
	private boolean checkHitBlock() {
		boolean hitDetected = false;
		for(Block b : gp.blocks) {
			//System.out.println(String.format("block x: %d , block y: %d", b.getX(), b.getY()));
			// if the shot has hit a wall, stop testing and end loop
			if(hitDetected) {
				break;
			}
			
			/*
			 * check 4 points of the shot and check if 
			 */
				hitDetected = CollusionHandler.DidCollusion(this, b, movmentDirection);
		}
		return hitDetected;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSize() {return size;}
}
