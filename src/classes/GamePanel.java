package classes;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

import Utils.LinearAlgebraClacsUtils;

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
	Enemy targetedEnemy;
	LevelPassDoor door;
	
	boolean isPaused;

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
		// get closest enemy index to target
		initTargetedEnemy();
		sidebar = new Sidebar(this);
		sidebar.initTargetEnemyHpBar(targetedEnemy);
		door = new LevelPassDoor(this);
		addKeyListener(new KL());
		setFocusable(true);
		setPreferredSize(new Dimension(1000, 1000));
		isPaused = false;

	}

	/**
	 * paint all the game components
	 */
	public void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);
			checkTargetEnemy();
			g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), null);
			drawBlocks(g);
			drawDoor(g);
			player.drawPlayer(g);
			drawShots(g);
			drawEnemies(g);
			if (isPaused == true)
	          {
	            g.setColor(new Color (255,255,255,80));
	            g.fillRect(0, 0, getWidth(), getHeight());
	            Font myFont = new Font("Ariel", Font.BOLD, 72);
	            g.setFont(myFont);
	            g.setColor(Color.red);
	            g.drawString("PAUSE",getWidth()/2 - 50,getHeight()/2);
	          }
			if(!player.isAlive) {
				g.setColor(new Color (255,255,255,80));
	            g.fillRect(0, 0, getWidth(), getHeight());
	            Font myFont = new Font("Ariel", Font.BOLD, 72);
	            g.setFont(myFont);
	            g.setColor(Color.black);
	            g.drawString("GAME OVER!!!",getWidth()/2 - 200,getHeight()/2);
			}
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
			
			// change target
			if(code == KeyEvent.VK_F) {
				initTargetedEnemy();
			}else {			
				// pause menu
				if(code == KeyEvent.VK_ESCAPE) {
					isPaused = !isPaused;
					
				}else {
					// movement keys
					if (!movementKeyPressed.contains(code)) {
						movementKeyPressed.add(code);
					}
					checkMovement();
				}	
			}
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
	
	
	// update moving detector
	public void movementDetectCounterUpdate(boolean add) {
		movementDetectorsCounter += add ? 1 : -1;
		if (movementDetectorsCounter < 0)
			movementDetectorsCounter = 0;
	}

	// set is moved
	public void setIsMoved(boolean val) throws InterruptedException {
		movementDetectorsCounter = val ? movementDetectorsCounter : 0;
		isMoved = val || player.moveDown || player.moveLeft || player.moveRight || player.moveUp;

	}

	// add new enemy
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	// draw all enemies
	public void drawEnemies(Graphics g) {
		if (!enemies.isEmpty()) {
			for (Enemy e : enemies) {
				try {
					if (e != null && !e.isAlive)
						enemies.remove(e);
						sidebar.onEnemyDies();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}

			for (Enemy e : enemies) {
				try {
					if (e != null && e.isAlive) {
						e.drawEnemy(g);
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());

				}
			}
		}
	}
	
	public void drawDoor(Graphics g) {
		if(enemies != null && enemies.isEmpty()) {
			door.drawDoor(g);
		}
	}

	// get current stage
	public int getStage() {
		return stage;
	}

	/**
	 * organize field to next level
	 * spawn new enemies and reset player game
	 */
	public void nextLevel() {
		this.stage++;
		Enemy.initEnemies(this);
		isMoved = false;
		movementDetectorsCounter = 0;
		shots.clear();
		// get closest enemy index to target
		initTargetedEnemy();
		player.readyToNewLevel();
		sidebar.setEnemyWaveMax(enemies.size());
		
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

			// check if on exact block location
			if (
				b.x == pos[0] && b.y == pos[1] || (
				// check if not blocked in four sides
				// by block or screen border
				// check left
				((b.x == pos[0] - 100 && b.y == pos[1]) || (pos[0] - 100 <= 0)) &&
				// check right
				((b.x == pos[0] + 100 && b.y == pos[1]) || (pos[0] + 100 >= 1000)) &&
				// check top
				((b.x == pos[0] && b.y == pos[1] - 100) || (pos[1] - 100 <= 0)) &&
				// check bottom
				((b.x == pos[0] && b.y == pos[1] + 100) || (pos[1] + 100 >= 1000)))) {
				return false;
			}
		}
		return (pos[0] != player.getX() && pos[1] != player.getY());
	}

	/**
	 * find the closest enemy to the player location and set it as target
	 * */
	public void initTargetedEnemy() {
		int index = 0;
		int minimumIndex = 0;
		int mininumDistance = 100000;
		if(!enemies.isEmpty()) {
			for(Enemy e : enemies) {
				if(e.isAlive) {
					int currentDistance = 
							(int) LinearAlgebraClacsUtils.getDistanceBetweenTwoPoints(
									player.getX(), player.getY(), e.getX(), e.getY());
					if(currentDistance < mininumDistance) {
						mininumDistance = currentDistance;
						minimumIndex = index;
					}
				}
				index++;
			}
			targetedEnemy = enemies.get(minimumIndex);
			if(sidebar != null) {
				sidebar.initTargetEnemyHpBar(targetedEnemy);
			}
		}
	}
	
	// check if the target enemy is alive
	public void checkTargetEnemy() {
		if(!targetedEnemy.isAlive) {
			initTargetedEnemy();
		}
	}
}
