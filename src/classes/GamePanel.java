package classes;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GamePanel extends JPanel {
	
	// main game frame
	private JFrame mainFrame;
	
	// background image
	private Image background;
	
	// all the objects that take part of this   
	private Player player;
	
	/**
	 * GamePanel constructor
	 * 
	 */
	public GamePanel() {
		ImageIcon ii = new ImageIcon("/images/main_background");
		this.background = ii.getImage();
		this.player = new Player(this);
		addKeyListener(new KL());
		
	}
	
	private class KL extends KeyAdapter{
		
		public void KeyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			
			switch(code) {
			case KeyEvent.VK_UP:
				player.updateY(-20);
				break;
			case KeyEvent.VK_DOWN:
				player.updateY(20);
				break;
			case KeyEvent.VK_LEFT:
				player.updateX(-20);
				break;
			case KeyEvent.VK_RIGHT:
				player.updateX(20);
				break;
			default:
				player.updateX(0);
				break;
			}
		}
	}
	
	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
