package classes;

import java.awt.*;

import javax.swing.ImageIcon;

import Utils.LinearAlgebraClacsUtils;

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
	double deltaSlope;
	
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
		deltaSlope = LinearAlgebraClacsUtils.getSlopeBetweenTwoPoints(x, y, gp.targetedEnemy.getX(), gp.targetedEnemy.getY());
		setDelta();
		setImage();
		start();
	}
	
	/**
	 * Set the shot steps for each axis
	 * @param d: the Direction of the shot
	 */
	private void setDelta() {
		switch(movmentDirection) {
		case EAST:
			deltay=0;
			deltax=15;
			break;
		case NORTH_EAST:
			deltax=15;
			deltay = -15;
			break;
		case NORTH:
			deltay=15;
			deltax=0;
			break;
		case NORTH_WEST:
			deltax=-15;
			deltay = -15;
			break;
		case WEST:
			deltay=0;
			deltax=-15;
			break;
		case SOUTH_WEST:
			deltax=-15;
			deltay =15;
			break;
		case SOUTH:
			deltay=-15;
			deltax=0;
			break;
		case SOUTH_EAST:
			deltax=15;
			deltay =15;
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
			shotLook = new ImageIcon("static/images/fists/fist-south-west.png").getImage();
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
		
		while(gp.player.isAlive)
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
		boolean hitDetected = false;
		for(Enemy e : gp.enemies) {
			/*
			 * check 4 points of the shot and check if 
			 */
			hitDetected = CollusionHandler.DidCollusion(this, e);
			
			if(hitDetected) {
				e.onHit(this.hitPower);
				break;
			}
			
		
		}
		return hitDetected;
	}

	/**
	 * check if shot has hit a wall
	 * @return true or false
	 */
	private boolean checkHitBlock() {
		boolean hitDetected = false;
		for(Block b : gp.blocks) {
			/*
			 * check 4 points of the shot and check if 
			 */
			hitDetected = CollusionHandler.DidCollusion(this, b, movmentDirection);
			
			//System.out.println(String.format("block x: %d , block y: %d", b.getX(), b.getY()));
			// if the shot has hit a wall, stop testing and end loop
			
			if(hitDetected) {
				break;
			}
			
			
		}
		return hitDetected;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSize() {return size;}
	
}
