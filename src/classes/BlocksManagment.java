package classes;

import java.util.Random;

enum FORMS{
		T_FORM,
		LEFT_ROTATE_T_FORM,
		RIGHT_ROTATE_T_FORM,
		REVERT_T_FORM,
		PLUS_FORM,
		HORIZONTAL_LINE_FORM,
		VERTICAL_LINE_FORM,
		SLASH_FORM,
		BACK_SLASH_FORM,
		X_FORM
}

public class BlocksManagment {
	GamePanel gp;
	Block[][] field;
	
	public BlocksManagment(GamePanel gamepanel) {
		gp = gamepanel;
		field = new Block[10][10];
		initBlocks();
		
	}
	
	/**
	 * initialize blocks configuration.
	 */
	private void initBlocks() {
		for(int i = 0; i < field.length; i++ ) {
			for(int j = 0; j < field.length; j++) {
				field[i][j] = null;
			}
		}
		Random rand = new Random();
		int counter = 0;
		while(counter < 3) {
			int xCord = rand.nextInt((field.length - 3 / 2) + 1);
			int yCord = rand.nextInt(field.length - 3);
			int blockForm = rand.nextInt(10);
			switch(blockForm) {
			
			// for each case, check if the form can be placed there. 
			// the calculated coordinates are for the center block.
			// if it is, place it and increment counter, if not, do another round.
			// for symmetrical field, we will check on half of the field and mirror it to the other.
			// assuming that the field is empty and creating first, there is no need to check the mirror side.  
			case 0:
				
				// form of:  * * *
				//             *
				
				if(field[xCord][yCord] == null 
					&& xCord - 1 > -1 
					&& field[xCord - 1][yCord] == null
					&& xCord + 1 < field.length / 2
					&& field[xCord + 1][yCord] == null
					&& yCord + 1 < field.length
					&& field[xCord][yCord + 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					
					// create left side
					createForm(FORMS.T_FORM,
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.T_FORM,
								(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 1:
				
				// form of:    * 
				//             * *
				//             *
				
				if(field[xCord][yCord] == null 
					&& xCord + 1 < field.length / 2
					&& field[xCord + 1][yCord] == null
					&& yCord + 1 < field.length 
					&& field[xCord][yCord + 1] == null
					&& yCord - 1 > -1
					&& field[xCord][yCord - 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					
					// create left side
					createForm(FORMS.LEFT_ROTATE_T_FORM,
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.RIGHT_ROTATE_T_FORM,	
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 2:
				
				// form of:    * 
				//           * *
				//  		   *
				
				if(field[xCord][yCord] == null 
					&& xCord - 1 > -1
					&& field[xCord - 1][yCord] == null
					&& yCord + 1 < field.length  
					&& field[xCord][yCord + 1] == null
					&& yCord - 1 > -1 
					&& field[xCord][yCord - 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {

					// create left side
					createForm(FORMS.RIGHT_ROTATE_T_FORM, 
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.LEFT_ROTATE_T_FORM,
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 3:
				
				// form of:    * 
				//           * * *
				
				if(field[xCord][yCord] == null 
					&& xCord - 1 > -1 
					&& field[xCord - 1][yCord] == null
					&& xCord + 1 < field.length / 2
					&& field[xCord + 1][yCord] == null
					&& yCord - 1 > -1 
					&& field[xCord][yCord - 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					// create left side
					createForm(FORMS.REVERT_T_FORM, 
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.REVERT_T_FORM, 
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 4:
				
				// form of:    * 
				//           * * *
				//             *
				
				if(field[xCord][yCord] == null 
					&& xCord - 1 > -1 
					&& field[xCord - 1][yCord] == null
					&& xCord + 1 < field.length / 2
					&& field[xCord + 1][yCord] == null
					&& yCord - 1 > -1  
					&& field[xCord][yCord - 1] == null
					&& yCord + 1 < 10 
					&& field[xCord][yCord + 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {

					// create left side
					createForm(FORMS.PLUS_FORM, 
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.PLUS_FORM, 
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 5:
				
				// form of:  * * * 
				
				if(field[xCord][yCord] == null 
					&& xCord - 1 > -1 
					&& field[xCord - 1][yCord] == null
					&& xCord + 1 < field.length / 2
					&& field[xCord + 1][yCord] == null 
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					
					// create left side
					createForm(FORMS.HORIZONTAL_LINE_FORM,
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.HORIZONTAL_LINE_FORM,
								(field.length - 1) - xCord, yCord);
					counter++;
				}
				break;
				
			case 6:
				
				// form of:    * 
				//             * 
				//             *
				
				if(field[xCord][yCord] == null 
					&& yCord + 1 < field.length
					&& field[xCord][yCord + 1] == null
					&& yCord - 1 > -1 
					&& field[xCord][yCord - 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					
					// create left side
					createForm(FORMS.VERTICAL_LINE_FORM,
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.VERTICAL_LINE_FORM,
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 7:
				
				// form of:  * 
				//             * 
				//               *
				
				if(field[xCord][yCord] == null 
					&& yCord + 1 < field.length
					&& xCord + 1 < field.length / 2 
					&& field[xCord + 1][yCord + 1] == null
					&& yCord - 1 > -1 
					&& xCord - 1 > -1
					&& field[xCord - 1][yCord - 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					// create left side
					createForm(FORMS.SLASH_FORM, xCord, yCord);
					// mirror to right side
					createForm(FORMS.BACK_SLASH_FORM, 
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			case 8:
				
				// form of:      *
				//             * 
				//           *
				
				if(field[xCord][yCord] == null 
					&& yCord - 1 > -1
					&& xCord + 1 < field.length / 2 
					&& field[xCord + 1][yCord - 1] == null
					&& yCord + 1 < field.length 
					&& xCord - 1 > -1
					&& field[xCord - 1][yCord + 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					// create left side
					createForm(FORMS.BACK_SLASH_FORM, xCord, yCord);
					// mirror to right side
					createForm(FORMS.SLASH_FORM, 
							(field.length - 1) - xCord, yCord);
					
					
					
					counter++;
				}
				
				break;
				
			case 9:
				
				// form of:  *   *
				//             * 
				//           *   *
				
				if(field[xCord][yCord] == null 
					&& yCord - 1 > -1 
					&& xCord + 1 < field.length / 2
					&& field[xCord + 1][yCord - 1] == null
					&& yCord + 1 < field.length 
					&& xCord - 1 > -1
					&& field[xCord - 1][yCord + 1] == null 
					&& field[xCord + 1][yCord + 1] == null
					&& field[xCord - 1][yCord - 1] == null
					&& xCord - 1 != 4 
					&& xCord + 1 != 5
					&& xCord - 1 != 5 
					&& xCord + 1 != 4) {
					// create left side
					createForm(FORMS.X_FORM, 
								xCord, yCord);
					// mirror to right side
					createForm(FORMS.X_FORM,
							(field.length - 1) - xCord, yCord);
					counter++;
				}
				
				break;
				
			default:
				break;
			}
		}
	}
	
	/**
	 * Insert the wished form to the field matrix.
	 * @param f - ENUM value, what form is to insert.
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createForm(FORMS f, int x, int y) {
		switch(f) {
		case T_FORM:
			createT(x, y);
			break;
			
		case LEFT_ROTATE_T_FORM:
			createLRT(x, y);
			break;
			
		case RIGHT_ROTATE_T_FORM:
			createRRT(x, y);
			break;
			
		case REVERT_T_FORM:
			createRT(x, y);
			break;
			
		case PLUS_FORM:
			createP(x, y);
			break;
			
		case HORIZONTAL_LINE_FORM:
			createHL(x, y);
			break;
			
		case VERTICAL_LINE_FORM:
			createVL(x, y);
			break;
		
		case SLASH_FORM:
			createS(x, y);
			break;
			
		case BACK_SLASH_FORM:
			createBS(x, y);
			break;
			
		case X_FORM:
			createX(x, y);
			break;
		}
		
	}
	
	/**
	 * create T form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createT(int x, int y) {
//		System.out.println("create T");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100 );
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y]: %b", field[x + 1][y]));
		field[x + 1][y] = new Block(gp, 100 * (x + 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x + 1][y]: %b, x: %d, y: %d", field[x + 1][y], field[x + 1][y].getX(), field[x + 1][y].getY()));
//		System.out.println(String.format("object at field[x - 1][y]: %b", field[x - 1][y]));
		field[x - 1][y] = new Block(gp, 100 * (x - 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x - 1][y]: %b, x: %d, y: %d", field[x - 1][y], field[x - 1][y].getX(), field[x - 1][y].getY()));
//		System.out.println(String.format("object at field[x][y + 1]: %b", field[x][y + 1]));
		field[x][y + 1] = new Block(gp, 100 * x, 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x][y + 1]: %b, x: %d, y: %d", field[x][y + 1], field[x][y + 1].getX(), field[x][y + 1].getY()));
		
		addBlocksToGP(field[x][y], field[x + 1][y],
						field[x - 1][y], field[x][y + 1]);
		
	}
	
	/**
	 * create left rotated T form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createLRT(int x, int y) {
//		System.out.println("create LRT");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y]: %b", field[x + 1][y]));
		field[x + 1][y] = new Block(gp, 100 * (x + 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x + 1][y]: %b, x: %d, y: %d", field[x + 1][y], field[x + 1][y].getX(), field[x + 1][y].getY()));
//		System.out.println(String.format("object at field[x][y - 1]: %b", field[x][y - 1]));
		field[x][y - 1] = new Block(gp, 100 * x, 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x][y - 1]: %b, x: %d, y: %d", field[x][y - 1], field[x][y - 1].getX(), field[x][y - 1].getY()));
//		System.out.println(String.format("object at field[x][y + 1]: %b", field[x][y + 1]));
		field[x][y + 1] = new Block(gp, 100 * x, 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x][y + 1]: %b, x: %d, y: %d", field[x][y + 1], field[x][y + 1].getX(), field[x][y + 1].getY()));
		
		addBlocksToGP(field[x][y], field[x + 1][y],
						field[x][y - 1], field[x][y + 1]);
		
	}
	
	/**
	 * create right rotated T form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createRRT(int x, int y) {
//		System.out.println("create RRT");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x - 1][y]: %b", field[x - 1][y]));
		field[x - 1][y] = new Block(gp, 100 * (x - 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x - 1][y]: %b, x: %d, y: %d", field[x - 1][y], field[x - 1][y].getX(), field[x - 1][y].getY()));
//		System.out.println(String.format("object at field[x][y - 1]: %b", field[x][y - 1]));
		field[x][y - 1] = new Block(gp, 100 * x, 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x][y - 1]: %b, x: %d, y: %d", field[x][y - 1], field[x][y - 1].getX(), field[x][y - 1].getY()));
//		System.out.println(String.format("object at field[x][y + 1]: %b", field[x][y + 1]));
		field[x][y + 1] = new Block(gp, 100 * x, 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x][y + 1]: %b, x: %d, y: %d", field[x][y + 1], field[x][y + 1].getX(), field[x][y + 1].getY()));
		
		addBlocksToGP(field[x][y], field[x - 1][y], 
						field[x][y - 1], field[x][y + 1]);
	}
	
	/**
	 * create reverted T form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createRT(int x, int y) {
//		System.out.println("create RT");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y]: %b", field[x + 1][y]));
		field[x + 1][y] = new Block(gp, 100 * (x + 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x + 1][y]: %b, x: %d, y: %d", field[x + 1][y], field[x + 1][y].getX(), field[x + 1][y].getY()));
//		System.out.println(String.format("object at field[x - 1][y]: %b", field[x - 1][y]));
		field[x - 1][y] = new Block(gp, 100 * (x - 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x - 1][y]: %b, x: %d, y: %d", field[x - 1][y], field[x - 1][y].getX(), field[x - 1][y].getY()));
//		System.out.println(String.format("object at field[x][y - 1]: %b", field[x][y - 1]));
		field[x][y - 1] = new Block(gp, 100 * x, 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x][y - 1]: %b, x: %d, y: %d", field[x][y - 1], field[x][y - 1].getX(), field[x][y - 1].getY()));
		
		addBlocksToGP(field[x][y], field[x + 1][y],
						field[x - 1][y], field[x][y - 1]);
	}
	
	/**
	 * create plus form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createP(int x, int y) {
//		System.out.println("create P");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y]: %b", field[x + 1][y]));
		field[x + 1][y] = new Block(gp, 100 * (x + 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x + 1][y]: %b, x: %d, y: %d", field[x + 1][y], field[x + 1][y].getX(), field[x + 1][y].getY()));
//		System.out.println(String.format("object at field[x - 1][y]: %b", field[x - 1][y]));
		field[x - 1][y] = new Block(gp, 100 * (x - 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x - 1][y]: %b, x: %d, y: %d", field[x - 1][y], field[x - 1][y].getX(), field[x - 1][y].getY()));
//		System.out.println(String.format("object at field[x][y - 1]: %b", field[x][y - 1]));
		field[x][y - 1] = new Block(gp, 100 * x, 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x][y - 1]: %b, x: %d, y: %d", field[x][y - 1], field[x][y - 1].getX(), field[x][y - 1].getY()));
//		System.out.println(String.format("object at field[x][y + 1]: %b", field[x][y + 1]));
		field[x][y + 1] = new Block(gp, 100 * x, 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x][y + 1]: %b, x: %d, y: %d", field[x][y + 1], field[x][y + 1].getX(), field[x][y + 1].getY()));
		
		addBlocksToGP(field[x][y], field[x + 1][y], 
						field[x - 1][y], field[x][y - 1],
							field[x][y + 1]);
	}
	
	/**
	 * create horizontal line form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createHL(int x, int y) {
//		System.out.println("create HL");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y]: %b", field[x + 1][y]));
		field[x + 1][y] = new Block(gp, 100 * (x + 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x + 1][y]: %b, x: %d, y: %d", field[x + 1][y], field[x + 1][y].getX(), field[x + 1][y].getY()));
//		System.out.println(String.format("object at field[x - 1][y]: %b", field[x - 1][y]));
		field[x - 1][y] = new Block(gp, 100 * (x - 1), 100 * y, 100);
//		System.out.println(String.format("object at field[x - 1][y]: %b, x: %d, y: %d", field[x - 1][y], field[x - 1][y].getX(), field[x - 1][y].getY()));
		
		addBlocksToGP(field[x][y],field[x + 1][y],
						field[x - 1][y]);
		
	}
	
	/**
	 * create vertical line form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createVL(int x, int y) {
//		System.out.println("create VL");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]     = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x][y - 1]: %b", field[x][y - 1]));
		field[x][y - 1] = new Block(gp, 100 * x, 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x][y - 1]: %b, x: %d, y: %d", field[x][y - 1], field[x][y - 1].getX(), field[x][y - 1].getY()));
//		System.out.println(String.format("object at field[x][y + 1]: %b", field[x][y + 1]));
		field[x][y + 1] = new Block(gp, 100 * x, 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x][y + 1]: %b, x: %d, y: %d", field[x][y + 1], field[x][y + 1].getX(), field[x][y + 1].getY()));
		
		addBlocksToGP(field[x][y],field[x][y - 1],
						field[x][y + 1]);
	}
	
	/**
	 * create slash form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createS(int x, int y) {
//		System.out.println("create S");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]         = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x - 1][y - 1]: %b", field[x - 1][y - 1]));
		field[x - 1][y - 1] = new Block(gp, 100 * (x - 1), 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x - 1][y - 1]: %b, x: %d, y: %d", field[x + 1][y - 1], field[x + 1][y - 1].getX(), field[x + 1][y - 1].getY()));
//		System.out.println(String.format("object at field[x + 1][y + 1]: %b", field[x + 1][y + 1]));
		field[x + 1][y + 1] = new Block(gp, 100 * (x + 1), 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x + 1][y + 1]: %b, x: %d, y: %d", field[x + 1][y + 1], field[x + 1][y + 1].getX(), field[x + 1][y + 1].getY()));
		
		addBlocksToGP(field[x][y],field[x - 1][y - 1],
						field[x + 1][y + 1]);
		
	}
	
	/**
	 * create back slash form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createBS(int x, int y) {
//		System.out.println("create BS");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]         = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y - 1]: %b", field[x + 1][y - 1]));
		field[x + 1][y - 1] = new Block(gp, 100 * (x + 1), 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x + 1][y - 1]: %b, x: %d, y: %d", field[x + 1][y - 1], field[x + 1][y - 1].getX(), field[x + 1][y - 1].getY()));
//		System.out.println(String.format("object at field[x - 1][y + 1]: %b", field[x - 1][y + 1]));
		field[x - 1][y + 1] = new Block(gp, 100 * (x - 1), 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x - 1][y + 1]: %b, x: %d, y: %d", field[x - 1][y + 1], field[x - 1][y + 1].getX(), field[x - 1][y + 1].getY()));

		addBlocksToGP(field[x][y],field[x + 1][y - 1],
				field[x - 1][y + 1]);
		
	}
	
	/**
	 * create X form
	 * @param x - row number in the matrix.
	 * @param y - column number in the matrix.
	 */
	private void createX(int x, int y) {
//		System.out.println("create X");
//		System.out.println(String.format("object at field[x][y]: %b", field[x][y]));
		field[x][y]         = new Block(gp, 100 * x, 100 * y, 100);
//		System.out.println(String.format("object at field[x][y]: %b, x: %d, y: %d", field[x][y], field[x][y].getX(), field[x][y].getY()));
//		System.out.println(String.format("object at field[x + 1][y - 1]: %b", field[x + 1][y - 1]));
		field[x + 1][y - 1] = new Block(gp, 100 * (x + 1), 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x + 1][y - 1]: %b, x: %d, y: %d", field[x + 1][y - 1], field[x + 1][y - 1].getX(), field[x + 1][y - 1].getY()));
//		System.out.println(String.format("object at field[x - 1][y + 1]: %b", field[x - 1][y + 1]));
		field[x - 1][y + 1] = new Block(gp, 100 * (x - 1), 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x - 1][y + 1]: %b, x: %d, y: %d", field[x - 1][y + 1], field[x - 1][y + 1].getX(), field[x - 1][y + 1].getY()));
//		System.out.println(String.format("object at field[x + 1][y + 1]: %b", field[x + 1][y + 1]));
		field[x + 1][y + 1] = new Block(gp, 100 * (x + 1), 100 * (y + 1), 100);
//		System.out.println(String.format("object at field[x + 1][y + 1]: %b, x: %d, y: %d", field[x + 1][y + 1], field[x + 1][y + 1].getX(), field[x + 1][y + 1].getY()));
//		System.out.println(String.format("object at field[x - 1][y - 1]: %b", field[x - 1][y - 1]));
		field[x - 1][y - 1] = new Block(gp, 100 * (x - 1), 100 * (y - 1), 100);
//		System.out.println(String.format("object at field[x - 1][y - 1]: %b, x: %d, y: %d", field[x + 1][y - 1], field[x + 1][y - 1].getX(), field[x + 1][y - 1].getY()));
		
		addBlocksToGP(field[x][y], field[x + 1][y + 1], 
				field[x - 1][y - 1], field[x + 1][y - 1],
					field[x - 1][y + 1]);
		
	}
	
	private void addBlocksToGP(Block... blocks) {
		for(Block b : blocks) {
//			System.out.print(String.format("adding block to arraylist, x: %d, y: %d",b.getX(), b.getY()));
			gp.addBlock(b);
		}
	}
	
}
