package classes;

import java.util.Random;

import javax.swing.ImageIcon;

enum MaskType{RED_MASK, YELLOW_MASK, GREEN_MASK};

public class MaskPeople extends Enemy {
	
	MaskType maskType;
	
	// this enemy movement will be 10 times in a direction, even if he can not move that way.
	int movmentCounter;
	
	int directionX, directionY;
	
	public MaskPeople(GamePanel gp, double power, int initX, int initY, int size, double hp) {
		super(gp, power, initX, initY, size, hp);
		setRandomMaskPersonColor();
		switch(maskType) {
		case GREEN_MASK:
			enemyLook = new ImageIcon("static/images/enemies/MaskPeople/greenMaskLeft.png").getImage();
			break;
		case RED_MASK:
			enemyLook = new ImageIcon("static/images/enemies/MaskPeople/redMaskLeft.png").getImage();
			break;
		case YELLOW_MASK:
			enemyLook = new ImageIcon("static/images/enemies/MaskPeople/yellowMaskLeft.png").getImage();
			break;
		default:
			enemyLook = new ImageIcon("static/images/enemies/MaskPeople/redMaskLeft.png").getImage();
			break;
		
		}
		this.isFacingRight = false;
		movmentCounter = 0;
		start();
	}
	
	private void setRandomMaskPersonColor() {
		Random rnd = new Random();
		int color = rnd.nextInt(3);
		switch(color) {
		case 0:
			maskType = MaskType.GREEN_MASK;
			break;
		case 1:
			maskType = MaskType.YELLOW_MASK;
			break;
		default:
			maskType = MaskType.RED_MASK;
			break;
		}
	}
	
	@Override
	protected void move() {
//			if(movmentCounter == 0) {
//				Random rnd = new Random();
//				directionX = rnd.nextInt(3);
//			}
//			switch(directionX) {
//			case 0:
//				// this case equal to moving right
//				if(!checkFieldBorderEncounter(Direction.EAST) && !checkBlockEncounter(Direction.EAST)) {
//					x+=3;
//				}
//				isFacingRight = true;
//				break;
//			case 1:
//				// this case equal to moving left
//				if(!checkFieldBorderEncounter(Direction.WEST) && !checkBlockEncounter(Direction.WEST)) {
//					x-=3;
//				}
//				isFacingRight = false;
//				break;
//			case 2:
//				// this case is for no moving on the x axis
//				break;
//			default:
//				break;
//				
//			}
//			if(movmentCounter == 0) {
//				Random rnd = new Random();
//				directionY = rnd.nextInt(3);
//			}
//			switch(directionY) {
//			case 0:
//				// this case equal to moving down
//				if(!checkFieldBorderEncounter(Direction.SOUTH) && !checkBlockEncounter(Direction.SOUTH)) {
//					y+=3;
//				}
//				break;
//			case 1:
//				// this case equal to moving up
//				if(!checkFieldBorderEncounter(Direction.NORTH) && !checkBlockEncounter(Direction.NORTH)) {
//					y-=3;
//				}
//				break;
//			case 2:
//				// this case is for no moving on the y axis
//				break;
//			default:
//				break;
//		}
		changeLookDirection();
	}
	
	private void changeLookDirection() {
		if(isFacingRight) {
			switch(maskType) {
			case GREEN_MASK:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/greenMaskRight.png").getImage();
				break;
			case RED_MASK:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/redMaskRight.png").getImage();
				break;
			case YELLOW_MASK:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/yellowMaskRight.png").getImage();
				break;
			default:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/redMaskRight.png").getImage();
				break;
			
			}
		}else {
			switch(maskType) {
			case GREEN_MASK:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/greenMaskLeft.png").getImage();
				break;
			case RED_MASK:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/redMaskLeft.png").getImage();
				break;
			case YELLOW_MASK:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/yellowMaskLeft.png").getImage();
				break;
			default:
				enemyLook = new ImageIcon("static/images/enemies/MaskPeople/redMaskLeft.png").getImage();
				break;
			
			}
		}
	}

}
