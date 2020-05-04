package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Enemy extends Thread implements GamePiece {
	
	GamePanel gp;
	double hitPower;
	double hp;
	
	// is enemy alive
	boolean isAlive;
	
	// which way the enemy is facing
	boolean isFacingRight;
		
	// enemy appearance
	Image enemyLook;
	
	// enemy appearance base url
	String baseImageUrl;
	
	int x, y, size;
	
	public Enemy(GamePanel gp, double power, int initX,int initY, int size, double hp) {
		this.gp = gp;
		hitPower = power;
		x = initX;
		y = initY;
		this.size = size;
		this.hp = hp;
		isAlive = true;
	}

	@Override
	public int getX() {
		
		return x;
	}

	@Override
	public int getY() {
		
		return y;
	}

	@Override
	public int getSize() {
		
		return size;
	}
	
	/**
	 * this method has to be overridden by child classes. 
	 */
	protected void move(){}
	
	public void onHit(double hitPower) {
		this.hp -= hitPower;
		if(hp <= 0) {
			isAlive = false;
		}
	}
	
	public void drawEnemy(Graphics g){
		g.drawImage(enemyLook,x,y,size,size, null);
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
			   move();
			   Thread.sleep(20);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
		    
			gp.repaint();
		}
	}
	
	/**
	 * check if the enemy hit any obstacle that block his movement
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
	 * check if the enemy has hit the boundaries of the screen
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
	
	public static void initEnemies(GamePanel gp) {
		
		double power;
		int posX, posY;
		int size = 85;
		double hp;
		int[] pos;
		
		for(int i = 0; i<5; i++) {
			Random rnd = new Random();
//			int enemyType = rnd.nextInt(3);
			pos = setPlace(gp, rnd);
			posX = pos[0];
			posY = pos[1];
//			switch(enemyType) {
//			case 0:
//				power = gp.getStage() * 15;
//				hp = gp.getStage() * 150;
//				gp.addEnemy(new BlackMage(gp,power,posX,posY,size, hp));
//				break;
//			case 1:
//				power = gp.getStage() * 30.5;
//				hp = gp.getStage() * 200;
//				gp.addEnemy(new Ghost(gp,power,posX,posY,size, hp));
//				break;
//			default:
				power = gp.getStage() * 10;
				hp = gp.getStage() * 170;
				gp.addEnemy(new MaskPeople(gp,power,posX,posY,size, hp));
//				break;
			}
		}
		
//	}
	
	private static int[] setPlace(GamePanel gp, Random rnd) {
		// while no place found
		while(true) {
			int posX = rnd.nextInt(10)*100;
			int posY = rnd.nextInt(10)*100;
			int pos[] = new int[2];
			pos[0] = posX;
			pos[1] = posY;
			if(gp.availableSpawnPlace(pos)) {
				return pos;
			}
		}
	}

}
