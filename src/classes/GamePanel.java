package classes;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

enum Direction{
	EAST,NORTH_EAST,NORTH,NORTH_WEST,WEST,SOUTH_WEST,SOUTH,SOUTH_EAST
}

public class GamePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Player player;
	Image backGroundImage;
	ArrayList<Shot> shots;
	boolean isMoved;
	
	/**
	 * Constractor
	 */
	public GamePanel(){
		ImageIcon ii =new ImageIcon("static/images/backgrounds/main_background.jpg");
		backGroundImage= ii.getImage();
		player = new Player(this, 100);
		shots = new ArrayList<Shot>();
		isMoved = false;
		addKeyListener(new KL ());
		setFocusable(true);
		
	}
	
	/**
	 * paint all the game components
	 */
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		g.drawImage(backGroundImage, 0,0,getWidth(),getHeight(), null);
		
		player.drawPlayer(g);
		drawShots(g);
	}
	
	/**
	 * draw all live shots
	 * @param g graphics element
	 */
	private void drawShots(Graphics g) {
		for(Shot s : shots){
			if(s.isAlive) {
				s.drawShot(g);	
			}
			else {
				shots.remove(s);
			}
			
		}
	}
	
	/**
	 * fire a new shot
	 * @param s Shot object
	 */
	public void addShot(Shot s) {
		this.shots.add(s);
	}
	
	/**
	 * new private key event listener
	 * @author dodog
	 *
	 */
	
	class KL extends KeyAdapter{
		public void keyPressed(KeyEvent e)
			{
			
				int code=e.getKeyCode();
				if(code==KeyEvent.VK_RIGHT) {
					player.updateX(10);
					startMovementDetector();
				}
				
				if(code==KeyEvent.VK_LEFT) {
					player.updateX(-10);
					startMovementDetector();
				}
				
				if(code==KeyEvent.VK_UP) {
					player.updateY(-10);
					startMovementDetector();
				}
				
				if(code==KeyEvent.VK_DOWN) {
					player.updateY(10);
					startMovementDetector();
				}
				
			}
		}
	
	/**
	 * hide the cursor while inside game panel
	 */
	public void  hideMouseCursor(){
		 //Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorimg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorimg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JPanel.
		setCursor(blankCursor);	
	}
	
	private synchronized void startMovementDetector() {
		isMoved = true;
		MovementDetector md = new MovementDetector();
		while(md.isAlive());
		isMoved = false;
		
	}

	/**
	 * main program function. program starts here.
	 * @param args execute arguments
	 */
	public static void main(String[] args) {
		JFrame f=new JFrame("Super Pacman Hero ver 0 2019 (c)");
		GamePanel bp=new GamePanel();
		f.add(bp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1650,1000);
		f.setResizable(false);
		f.setVisible(true);	
		f.setFocusable(false);
		bp.hideMouseCursor();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

