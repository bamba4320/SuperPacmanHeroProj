package classes;

import java.awt.*;

public class Shot extends Thread {
	GamePanel gp;
	Image shotLook;
	int x;
	int y;
	int size;
	double hitPower;
	int deltax;
	int deltay;
	
	public Shot(GamePanel gamePanel,int initx, int inity, int initsize, double hit, Direction d) {
		gp = gamePanel;
		x = initx;
		y = inity;
		setDelta(d);
		setImage(d);
		
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
			break;
		case NORTH_EAST:
			break;
		case NORTH:
			break;
		case NORTH_WEST:
			break;
		case WEST:
			break;
		case SOUTH_WEST:
			break;
		case SOUTH:
			break;
		case SOUTH_EAST:
			break;
			
		}
	}
}
