package classes;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Block implements GamePiece {
	
	GamePanel gp;
	Image blockLook; 
	int x;
	int y;
	int size;
	
	public Block(GamePanel gamepanel, int x, int y, int size) {
		gp = gamepanel;
		blockLook = new ImageIcon("static/images/walls/gray-brick-wall.jpg").getImage();
		this.x = x;
		this.y = y;
		this.size = size;
		//System.out.println(String.format("x: %d, y: %d", this.x, this.y));
	
	}
	
	public void drawBlock(Graphics g) {
		g.drawImage(blockLook, x, y, size, size, null);
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSize() {return size;}
	
}
