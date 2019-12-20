package classes;

import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends Thread implements GamePiece {
	
	GamePanel gp;
	double hitPower;
	double hp;
	
	// which way the enemy is facing
	boolean isFacingRight;
		
	// enemy appearance
	Image enemyLook;
	
	int x, y, size;
	
	public Enemy(GamePanel gp, double power, int initX,int initY, int size) {
		this.gp = gp;
		hitPower = power;
		x = initX;
		y = initY;
		this.size = size;
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
	
	public void move() {
		
	}
	
	public void drawEnemy(Graphics g){
		g.drawImage(enemyLook,x,y,size,size, null);
	}

}
