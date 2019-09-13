package classes;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
	
	public GamePanel(){
		ImageIcon ii =new ImageIcon("static/images/main_background.jpg");
		backGroundImage= ii.getImage();
		player = new Player(this, 100);
		addKeyListener(new KL ());
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		g.drawImage(backGroundImage, 0,0,getWidth(),getHeight(), null);
		
		player.drawPlayer(g);
	
		 
	}

		
		 class KL extends KeyAdapter
	     {
			public void keyPressed(KeyEvent e)
			{
			
				int code=e.getKeyCode();
				if(code==KeyEvent.VK_RIGHT) {
					player.updateX(10);
				}
				if(code==KeyEvent.VK_LEFT) {
					player.updateX(-10);
				}
				if(code==KeyEvent.VK_UP) {
					player.updateY(-10);
				}
				if(code==KeyEvent.VK_DOWN) {
					player.updateY(10);
				}
				
			}
			
		}
	

	
	public void  hideMouseCursor(){
		 //Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorimg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorimg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JPanel.
		setCursor(blankCursor);	
	}
	

	public static void main(String[] args) {
		JFrame f=new JFrame("Chichen Invader Pre MS ver 0 2019 (c)");
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

