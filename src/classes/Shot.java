package classes;

import java.awt.*;

import javax.swing.ImageIcon;

public class Shot extends Thread { 
	GamePanel gp;
	Image shotLook;
	int x;
	int y;
	int size;
	double hitPower;
	int deltax;
	int deltay;
	boolean isAlive;
	
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
		setDelta(d);
		setImage(d);
		start();
	}
	
	/**
	 * Set the shot steps for each axis
	 * @param d: the Direction of the shot
	 */
	private void setDelta(Direction d) {
		switch(d) {
		case EAST:
			deltay=0;
			deltax=12;
			break;
		case NORTH_EAST:
			deltay=12;
			deltax=12;
			break;
		case NORTH:
			deltay=12;
			deltax=0;
			break;
		case NORTH_WEST:
			deltay=12;
			deltax=-12;
			break;
		case WEST:
			deltay=0;
			deltax=-12;
			break;
		case SOUTH_WEST:
			deltay=-12;
			deltax=-12;
			break;
		case SOUTH:
			deltay=-12;
			deltax=0;
			break;
		case SOUTH_EAST:
			deltay=-12;
			deltax=12;
			break;
			
		}
		
	}
	
	/**
	 * Set the shot image base on direction
	 * @param d: the Direction of the shot
	 */
	private void setImage(Direction d) {
		switch(d) {
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
			Thread.sleep(100);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
		   	if(doKillShot()) {
		   		isAlive = false;
		   		break;
		   	}
		   	else {
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
	 * at next dev stage will be checked with enemies impact.
	 * @return
	 */
	private boolean doKillShot() {
		return x == 0 || x == gp.getWidth() || y == 0 || y==gp.getHeight();
	}
	
	private void moveShot() {
		x+=deltax;
		y+=deltay;
	}
}
