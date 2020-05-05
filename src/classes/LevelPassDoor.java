package classes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LevelPassDoor extends Thread implements GamePiece {
	// the game panel
	GamePanel gp;
	
	// the door coordinates
	int x;
	int y;
	
	// door appearance
	Image doorLook;
	
	// door relative width and height
	int size;
	
	public LevelPassDoor(GamePanel gp) {
		this.gp = gp;
		x = 450;
		y = 0;
		size = 200;
		doorLook = new ImageIcon("static/images/gates/door.png").getImage();
		start();
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
	
	public void checkPlayerGotIN() {
		if(gp.enemies != null && gp.enemies.isEmpty()) {
			if(gp.player.getX() == x && gp.player.getY() <= (y + size)/2) {
				gp.nextLevel();
			}
		}
	}
	
	public void drawDoor(Graphics g){
		g.drawImage(doorLook,x,y,size/2,size, null);
	}
	
	public void run() {
		while(true) {
			try {
				checkPlayerGotIN();
			   Thread.sleep(20);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
		}
	}
}
