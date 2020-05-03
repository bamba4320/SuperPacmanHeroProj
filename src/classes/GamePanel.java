package classes;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

enum Direction {
	EAST, NORTH_EAST, NORTH, NORTH_WEST, WEST, SOUTH_WEST, SOUTH, SOUTH_EAST, ALL
}

enum GamePieces {
	PLAYER, ENEMY, BLACK_MAGE, GHOST, MASK_PEOPEL
}

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	Player player;
	Image backGroundImage;
	ArrayList<Shot> shots;
	ArrayList<Block> blocks;
	BlocksManagment BM;
	ArrayList<Enemy> enemies;
	boolean isMoved;
	int movementDetectorsCounter;
	Sidebar sidebar;
	int stage;

	/**
	 * Constractor
	 */
	public GamePanel() {
		stage = 1;
		ImageIcon ii = new ImageIcon("static/images/backgrounds/main_background.jpg");
		backGroundImage = ii.getImage();
		player = new Player(this, 85);
		shots = new ArrayList<Shot>();
		blocks = new ArrayList<Block>();
		BM = new BlocksManagment(this);
		enemies = new ArrayList<Enemy>();
		Enemy.initEnemies(this);
		isMoved = false;
		movementDetectorsCounter = 0;
		sidebar = new Sidebar(this);
		addKeyListener(new KL());
		setFocusable(true);
		setPreferredSize(new Dimension(1000, 1000));

	}

	/**
	 * paint all the game components
	 */
	public void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);

			g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), null);
			drawBlocks(g);
			player.drawPlayer(g);
			drawShots(g);
			drawEnemies(g);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * draw all live shots
	 * 
	 * @param g graphics element
	 */
	private void drawShots(Graphics g) {
		if (!shots.isEmpty()) {
			for (Shot s : shots) {
				try {
					if (s != null && !s.isAlive)
						shots.remove(s);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			for (Shot s : shots) {
				try {
					if (s != null && s.isAlive) {
						s.drawShot(g);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());

				}
			}
		}

	}

	/**
	 * fire a new shot
	 * 
	 * @param s Shot object
	 */
	public void addShot(Shot s) {
		this.shots.add(s);
	}

	/**
	 * add new block to field.
	 * 
	 * @param b Block object.
	 */
	public void addBlock(Block b) {
		this.blocks.add(b);
	}

	public void drawBlocks(Graphics g) {
		for (Block b : blocks) {
			b.drawBlock(g);
		}
	}

	public Sidebar getSidebar() {
		return sidebar;
	}

	/**
	 * new private key event listener
	 * 
	 * @author dodog
	 *
	 */

	class KL extends KeyAdapter {

		// key pressed add key code to list if not exist
		// call to check movement at the end
		// key released remove key code from list
		// player.direction = false

		// check movement - set player.diration = true

		// in player thread call calc movement + short sleep

		// calc movement - check each direction if true, move

		ArrayList<Integer> movementKeyPressed = new ArrayList<Integer>();

		public void keyPressed(KeyEvent e) {

			int code = e.getKeyCode();
			if (!movementKeyPressed.contains(code)) {
				movementKeyPressed.add(code);
			}

			checkMovement();

		}

		private void checkMovement() {
			if (movementKeyPressed.contains(KeyEvent.VK_A)) {
				player.moveLeft = true;
				startMovementDetector();
			}
			if (movementKeyPressed.contains(KeyEvent.VK_D)) {
				player.moveRight = true;
				startMovementDetector();
			}
			if (movementKeyPressed.contains(KeyEvent.VK_S)) {
				player.moveDown = true;
				startMovementDetector();
			}
			if (movementKeyPressed.contains(KeyEvent.VK_W)) {
				player.moveUp = true;
				startMovementDetector();
			}
		}

		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			movementKeyPressed.remove((Object) code);
			if (code == KeyEvent.VK_A) {
				player.moveLeft = false;
				movementDetectCounterUpdate(false);
			}
			if (code == KeyEvent.VK_D) {
				player.moveRight = false;
				movementDetectCounterUpdate(false);
			}
			if (code == KeyEvent.VK_S) {
				player.moveDown = false;
				movementDetectCounterUpdate(false);
			}
			if (code == KeyEvent.VK_W) {
				player.moveUp = false;
				movementDetectCounterUpdate(false);
			}
		}
	}

	/**
	 * hide the cursor while inside game panel
	 */
	public void hideMouseCursor() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorimg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JPanel.
		setCursor(blankCursor);
	}

	/**
	 * work with the movement detector
	 */
	public void startMovementDetector() {
		new MovementDetector(this);
	}

	public void movementDetectCounterUpdate(boolean add) {
		movementDetectorsCounter += add ? 1 : -1;
		if (movementDetectorsCounter < 0)
			movementDetectorsCounter = 0;
	}

	public void setIsMoved(boolean val) throws InterruptedException {
		movementDetectorsCounter = val ? movementDetectorsCounter : 0;
		isMoved = val || player.moveDown || player.moveLeft || player.moveRight || player.moveUp;

	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public void drawEnemies(Graphics g) {
		for (Enemy e : enemies) {
			e.drawEnemy(g);
		}
	}

	public int getStage() {
		return stage;
	}

	/**
	 * check if a position is OK to spawn enemy
	 * 
	 * @param pos  enemy initial position
	 * @param axis 1 - x axis, 2 - y axis
	 * @return
	 */
	public boolean availableSpawnPlace(int[] pos) {
		// check that there is no block where pos is
		// verify there is at least one way out
		// meaning, in four direction at least one free
		for (Block b : blocks) {

			if (b.x == pos[0] && b.y == pos[1] || (
			// check if not blocked in four sides
			// by block or screen border
			// check left
			((b.x == pos[0] - 100 && b.y == pos[1]) || (pos[0] - 100 < 0)) &&
			// check right
					((b.x == pos[0] + 100 && b.y == pos[1]) || (pos[0] + 100 > 1000)) &&
					// check top
					((b.x == pos[0] && b.y == pos[1] - 100) || (pos[1] - 100 < 0)) &&
					// check bottom
					((b.x == pos[0] && b.y == pos[1] + 100) || (pos[1] + 100 > 1000)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * main program function. program starts here.
	 * 
	 * @param args execute arguments
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Super Pacman Hero ver 0.9 2019 (c)");
		JPanel container = new JPanel();
		JPanel sidebarContainer = new JPanel();
		JPanel gamePanelContainer = new JPanel();
		GamePanel gp = new GamePanel();

		sidebarContainer.setPreferredSize(new Dimension(500, 1050));
		sidebarContainer.setBackground(Color.darkGray);
		sidebarContainer.add(gp.getSidebar(), BorderLayout.CENTER);

		gamePanelContainer.setPreferredSize(new Dimension(1050, 1050));
		gamePanelContainer.setBackground(new Color(51, 51, 51));
		gamePanelContainer.add(gp, BorderLayout.NORTH);

		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(gamePanelContainer);
		container.add(sidebarContainer);

		f.add(container);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1550, 1050);
		f.setResizable(false);
		f.setVisible(true);
		f.setFocusable(false);
		gp.hideMouseCursor();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
