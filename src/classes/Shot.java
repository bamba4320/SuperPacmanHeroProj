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
	
	public Shot(GamePanel gamePanel,int initx, int inity, int initsize, double hit, Direction d, int size) {
		gp = gamePanel;
		x = initx;
		y = inity;
		this.size = size;
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
			shotLook = new ImageIcon("static/images/fists/fist-east").getImage();
			break;
		case NORTH_EAST:
			shotLook = new ImageIcon("static/images/fists/fist-north-east").getImage();
			break;
		case NORTH:
			shotLook = new ImageIcon("static/images/fists/fist-north").getImage();
			break;
		case NORTH_WEST:
			shotLook = new ImageIcon("static/images/fists/fist-north-west").getImage();
			break;
		case WEST:
			shotLook = new ImageIcon("static/images/fists/fist-west").getImage();
			break;
		case SOUTH_WEST:
			shotLook = new ImageIcon("static/images/fists/fist-north-west").getImage();
			break;
		case SOUTH:
			shotLook = new ImageIcon("static/images/fists/fist-south").getImage();
			break;
		case SOUTH_EAST:
			shotLook = new ImageIcon("static/images/fists/fist-south-east").getImage();
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
			Thread.sleep(50);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			gp.repaint();
		}	
	}
	
	public void drawShot(Graphics g){
		g.drawImage(shotLook,x,y,size,size, null);
	}
}
