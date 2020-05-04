package classes;

public class CollusionHandler {
	
	/**
	 * Extended and general check weather one game piece has encounter the other
	 * @param gp1 one game piece in the encounter
	 * @param gp2 other game piece in the encounter
	 * @param d in what direction did gp1 hit gp2.
	 * @return boolean value representing did collision occurred
	 */
	public static boolean DidCollusion(GamePiece gp1, GamePiece gp2, Direction d) {
		switch(d) {
		case NORTH:
			return checkNorth(gp1, gp2);
			
		case SOUTH:
			return checkSouth(gp1, gp2);
			
		case EAST:
			return checkEast(gp1, gp2);

		case WEST:
			return checkWest(gp1, gp2);
			
		case NORTH_WEST:
			return checkNorthWest(gp1, gp2);
		
		case NORTH_EAST:
			return checkNorthEast(gp1, gp2);
		
		case SOUTH_EAST:
			return checkSouthEast(gp1, gp2);
			
		case SOUTH_WEST:
			return checkSouthWest(gp1, gp2);
			
		default:
			return checkNorth(gp1, gp2) 
					|| checkSouth(gp1, gp2) 
					|| checkEast(gp1, gp2) 
					|| checkWest(gp1, gp2) 
					|| checkNorthWest(gp1, gp2) 
					|| checkNorthEast(gp1, gp2) 
					|| checkSouthEast(gp1, gp2)
					||checkSouthWest(gp1, gp2);
					
		}
				
				
	}
	
	/**
	 * Extended and general check weather one game piece has encounter the other
	 * @param gp1 gp1 one game piece in the encounter
	 * @param gp2 gp2 other game piece in the encounter
	 * @return boolean value representing did collision occurred
	 */
	public static boolean DidCollusion(GamePiece gp1, GamePiece gp2) {
		return DidCollusion(gp1, gp2, Direction.ALL);
	}

	// functions to check each direction

	private static boolean checkEast(GamePiece gp1, GamePiece gp2) {
		// in case the encounter is from the west to game piece 2,
					// check if game piece 1 is touching game piece 2 from the left
					// or up to half size in it, and verify that game piece 1
					// boundaries are partially or fully within game piece 2
					// length and coordinates range.
					
					return (
							
							// first check, verify that game piece 1 X coordinate 
							// is within game piece 2 X coordinates range
						 	   
							   gp1.getX() + gp1.getSize() <= (gp2.getX() + (gp2.getSize() / 2))
							&& gp1.getX() + gp1.getSize() >= (gp2.getX()) 
							
							// then, check x coordinates. there are three possible
							// states: 
							// 1 - game piece 1 is partially with in piece 2 y
							//     coordinates, from the left side.
							// 2 - game piece 1 is fully within game piece 2 y 
							//     coordinates range.
							// 3 - game piece 1 is partially with in piece 2 y
							//     coordinates, from the right side.
							&&(
							// state 1:
								   // check left up corner to be outside of range.
							    (  gp1.getY() <= gp2.getY()         
							   	   // check right up corner to be within range and not passing it.
							    && gp1.getY() + gp1.getSize() >= gp2.getY()
							    && gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
							||
							// stage 2:
								   // check left up corner to be within range 
								(  gp1.getY() >= gp2.getY()
								   // check right up corner to be within range
								&& gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
							||
							// stage 3:
							       // check left up corner to be within range.
								(  gp1.getY() >= gp2.getY()
								&& gp1.getY() <= gp2.getY() + gp2.getSize()
								   // check right up corner to be outside of range
								&& gp1.getY() + gp1.getSize() >= gp2.getY() + gp2.getSize())
						   ));
	}
	
	private static boolean checkWest(GamePiece gp1, GamePiece gp2) {
		// in case the encounter is from the east to game piece 2,
					// check if game piece 1 is touching game piece 2 from the right
					// or up to half size in it, and verify that game piece 1
					// boundaries are partially or fully within game piece 2
					// length and coordinates range.
					
					return (
							
							// first check, verify that game piece 1 X coordinate 
							// is within game piece 2 X coordinates range
						 	   
							   gp1.getX() >= (gp2.getX() + (gp2.getSize() / 2))
							&& gp1.getX() <= (gp2.getX() + gp2.getSize()) 
							
							// then, check x coordinates. there are three possible
							// states: 
							// 1 - game piece 1 is partially with in piece 2 y
							//     coordinates, from the left side.
							// 2 - game piece 1 is fully within game piece 2 y 
							//     coordinates range.
							// 3 - game piece 1 is partially with in piece 2 y
							//     coordinates, from the right side.
							&&(
							// state 1:
								   // check left up corner to be outside of range.
							    (  gp1.getY() <= gp2.getY()                
							   	   // check right up corner to be within range and not passing it.
							    && gp1.getY() + gp1.getSize() >= gp2.getY()
							    && gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
							||
							// stage 2:
								   // check left up corner to be within range 
								(  gp1.getY() >= gp2.getY()
								   // check right up corner to be within range
								&& gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
							||
							// stage 3:
							       // check left up corner to be within range.
								(  gp1.getY() >= gp2.getY()
								&& gp1.getY() <= gp2.getY() + gp2.getSize()
								   // check right up corner to be outside of range
								&& gp1.getY() + gp1.getSize() >= gp2.getY() + gp2.getSize())
						   ));
	}
	
	private static boolean checkNorth(GamePiece gp1, GamePiece gp2) {
		// in case the encounter is from the south to game piece 2,
					// check if game piece 1 is touching game piece 2 from below
					// or up to half size in it, and verify that game piece 1
					// boundaries are partially or fully within game piece 2
					// length and coordinates range.
					return (
							
							// first check, verify that game piece 1 y coordinate 
							// is within game piece 2 y coordinates range
							   gp1.getY() >= (gp2.getY() - (gp2.getSize() / 2))
							&& gp1.getY() <= (gp2.getY() + gp2.getSize())
							
							// then, check x coordinates. there are three possible
							// states: 
							// 1 - game piece 1 is partially with in piece 2 x
							//     coordinates, from the left side.
							// 2 - game piece 1 is fully within game piece 2 x 
							//     coordinates range.
							// 3 - game piece 1 is partially with in piece 2 x
							//     coordinates, from the right side.
							&&(
							// state 1:
								   // check left up corner to be outside of range.
							    (  gp1.getX() <= gp2.getX()                
							   	   // check right up corner to be within range and not passing it.
							    && gp1.getX() + gp1.getSize() >= gp2.getX()
							    && gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
							||
							// stage 2:
								   // check left up corner to be within range 
								(  gp1.getX() >= gp2.getX()
								   // check right up corner to be within range
								&& gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
							||
							// stage 3:
							       // check left up corner to be within range.
								(  gp1.getX() >= gp2.getX()
								&& gp1.getX() <= gp2.getX() + gp2.getSize()
								   // check right up corner to be outside of range
								&& gp1.getX() + gp1.getSize() >= gp2.getX() + gp2.getSize())
						   ));
	}
	
	private static boolean checkSouth(GamePiece gp1, GamePiece gp2) {
		// in case the encounter is from the north to game piece 2,
					// check if game piece 1 is touching game piece 2 from above
					// or up to half size in it, and verify that game piece 1
					// boundaries are partially or fully within game piece 2
					// length and coordinates range.
					
					return (
							
							// first check, verify that game piece 1 y coordinate 
							// is within game piece 2 y coordinates range
						 	   
							   gp1.getY() + gp1.getSize() <= (gp2.getY() + (gp2.getSize() / 2))
							&& gp1.getY() + gp1.getSize() >=  (gp2.getY())
							
							// then, check x coordinates. there are three possible
							// states: 
							// 1 - game piece 1 is partially with in piece 2 x
							//     coordinates, from the left side.
							// 2 - game piece 1 is fully within game piece 2 x 
							//     coordinates range.
							// 3 - game piece 1 is partially with in piece 2 x
							//     coordinates, from the right side.
							&&(
							// state 1:
								   // check left up corner to be outside of range.
							    (  gp1.getX() <= gp2.getX()        
							   	   // check right up corner to be within range and not passing it.
							    && gp1.getX() + gp1.getSize() >= gp2.getX()
							    && gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
							||
							// stage 2:
								   // check left up corner to be within range 
								(  gp1.getX() >= gp2.getX()
								   // check right up corner to be within range
								&& gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
							||
							// stage 3:
							       // check left up corner to be within range.
								(  gp1.getX() >= gp2.getX()
								&& gp1.getX() <= gp2.getX() + gp2.getSize()
								   // check right up corner to be outside of range
								&& gp1.getX() + gp1.getSize() >= gp2.getX() + gp2.getSize())
						   ));
	}
	
	/*
	 *  check the multi-axis directions
	 *  the multiply values are the rational size of the non fist parts of the image
	 *  the thumb of the fist if calculated with: 
	 *  	x: (gp1.getX() + gp1.getSize()) * 0.138
	 *  	y: (gp1.getY() + gp1.getSize()) * 0.118 
	 *  the rear of the fist if calculated with: 
	 *  	x: (gp1.getX() + gp1.getSize()) * 0.434
	 *  	y: (gp1.getY() + gp1.getSize()) * 0.233
	 *  the front part of the fist if calculated with: 
	 *  	x: (gp1.getX() + gp1.getSize()) * 0.328
	 *  	y: (gp1.getY() + gp1.getSize()) * 0.387  
	 */
	private static boolean checkNorthEast(GamePiece gp1, GamePiece gp2) {
		// thumb coordinates
		double thumbYCoords = (gp1.getSize() * (1 - 0.118)); 
		double thumbXCoords = (gp1.getSize() * (1 - 0.138));
		
		// fist front coordinates
		double fistFrontYCoords = (gp1.getSize() * 0.3875);
		double fistFrontXCoords = (gp1.getSize() * (1 - 0.328));
		return(
				// thumb - hit on top left corner
						// check y coordinates to be at thumb area only or up
							gp2.getY() <= (gp1.getY() + thumbYCoords)
						&&	gp2.getY() >= gp1.getY()
					&&
						// check x coordinates to be at thumb area
							gp2.getX() <= (gp1.getX() + thumbXCoords)
						&&	gp2.getX() >= gp1.getX()
				||
				// front of fist - hit on bottom left corner 
						// check y coordinates to be at fist flat zone area
							gp2.getY() + gp2.getSize() >= gp1.getY()
						&&	gp2.getY() + gp2.getSize() <= gp1.getY() + fistFrontYCoords
					&&
						// check x coordinates to be at fist flat zone area
							gp2.getX() >= gp1.getX() + fistFrontXCoords
						&&	gp2.getX() <= gp1.getX() + gp1.getSize()
				||
				// front of fist - fingers joints part
						// check y coordinates of joints to be at gp2 y area
							gp1.getY() + fistFrontYCoords >= gp2.getY()
						&&	gp1.getY() + fistFrontYCoords <= gp2.getY() + gp2.getSize()
					&&
						// check x coordinates of joints to be at gp2 x coordinate
							gp1.getX() + gp1.getSize() >= gp2.getX()
						&& 	gp1.getX() + gp1.getSize() <= (gp2.getX() + (gp2.getSize() / 2))
				||
				// front of fist - top fingers joints part
						// check x coordinates of joints to be at gp2 x area
							gp1.getX() + fistFrontXCoords >= gp2.getX()
						&&	gp1.getX() + fistFrontXCoords <= gp2.getX() + gp2.getSize()
					&&
						// check y coordinates of joints to be at gp2 y coordinate
							gp1.getY() <= gp2.getY() + gp2.getSize()
						&& 	gp1.getY() >= (gp2.getY() + (gp2.getSize() / 2))
				);
				
	}
	
	private static boolean checkNorthWest(GamePiece gp1, GamePiece gp2) {
		// thumb coordinates
		double thumbYCoords = (gp1.getSize() * (1 - 0.118)); 
		double thumbXCoords = (gp1.getSize() * 0.138);
		
		// fist front coordinates
		double fistFrontYCoords = (gp1.getSize() * 0.3875);
		double fistFrontXCoords = (gp1.getSize() * 0.328);
		return(
				// thumb - hit on top right corner
						// check y coordinates to be at thumb area only or up
							gp2.getY() <= (gp1.getY() + thumbYCoords)
						&&	gp2.getY() >= gp1.getY()
					&&
						// check x coordinates to be at thumb area
							gp2.getX() + gp2.getSize() <= (gp1.getX() + thumbXCoords)
						&&	gp2.getX() + gp2.getSize() >= gp1.getX()
				||
				// front of fist - hit on bottom right corner 
						// check y coordinates to be at fist flat zone area
							gp2.getY() + gp2.getSize() >= gp1.getY()
						&&	gp2.getY() + gp2.getSize() <= gp1.getY() + fistFrontYCoords
					&&
						// check x coordinates to be at fist flat zone area
							gp2.getX() + gp2.getSize() >= gp1.getX() + fistFrontXCoords
						&&	gp2.getX() + gp2.getSize() <= gp1.getX() + gp1.getSize()
				||
				// front of fist - fingers joints part
						// check y coordinates of joints to be at gp2 y area
							gp1.getY() + fistFrontYCoords >= gp2.getY()
						&&	gp1.getY() + fistFrontYCoords <= gp2.getY() + gp2.getSize()
					&&
						// check x coordinates of joints to be at gp2 x coordinate
							gp1.getX() <= gp2.getX() + gp2.getSize()
						&& 	gp1.getX() >= (gp2.getX() + (gp2.getSize() / 2))
				||
				// front of fist - top fingers joints part
						// check x coordinates of joints to be at gp2 x area
							gp1.getX() + fistFrontXCoords >= gp2.getX()
						&&	gp1.getX() + fistFrontXCoords <= gp2.getX() + gp2.getSize()
					&&
						// check y coordinates of joints to be at gp2 y coordinate
							gp1.getY() <= gp2.getY() + gp2.getSize()
						&& 	gp1.getY() >= (gp2.getY() + (gp2.getSize() / 2))
				);
	}
	
	private static boolean checkSouthWest(GamePiece gp1, GamePiece gp2) {
		// thumb coordinates
		double thumbYCoords = (gp1.getSize() * (1 - 0.118)); 
		double thumbXCoords = (gp1.getSize() * (1 - 0.138));
		
		// fist front coordinates
		double fistFrontYCoords = (gp1.getSize() * (1 - 0.3875));
		double fistFrontXCoords = (gp1.getSize() * 0.328);
		return(
				// thumb - hit on top right corner
						// check y coordinates to be at thumb area only or up
							gp2.getY() <= (gp1.getY() + thumbYCoords)
						&&	gp2.getY() >= gp1.getY()
					&&
						// check x coordinates to be at thumb area
							gp2.getX() + gp2.getSize() <= (gp1.getX() + thumbXCoords)
						&&	gp2.getX() + gp2.getSize() >= gp1.getX()
				||
				// front of fist - hit on top left corner 
						// check y coordinates to be at fist flat zone area
							gp2.getY() + gp2.getSize() >= gp1.getY()
						&&	gp2.getY() + gp2.getSize() <= gp1.getY() + fistFrontYCoords
					&&
						// check x coordinates to be at fist flat zone area
							gp2.getX() >= gp1.getX() + fistFrontXCoords
						&&	gp2.getX() <= gp1.getX() + gp1.getSize()
				||
				// front of fist - fingers joints part
					// check y coordinates of joints to be at gp2 y area
							gp1.getY() + fistFrontYCoords >= gp2.getY()
						&&	gp1.getY() + fistFrontYCoords <= gp2.getY() + gp2.getSize()
					&&
						// check x coordinates of joints to be at gp2 x coordinate
							gp1.getX() <= gp2.getX() + gp2.getSize()
						&& 	gp1.getX() >= (gp2.getX() + (gp2.getSize() / 2))
				||
				// front of fist - bottom fingers joints part
						// check x coordinates of joints to be at gp2 y area
							gp1.getX() + fistFrontXCoords >= gp2.getX()
						&&	gp1.getX() + fistFrontXCoords <= gp2.getX() + gp2.getSize()
					&&
						// check y coordinates of joints to be at gp2 x coordinate
							gp1.getY() + gp1.getSize() >= gp2.getY()
						&& 	gp1.getY() + gp1.getSize() <= (gp2.getY() + (gp2.getSize() / 2))		
				);
	}
	
	private static boolean checkSouthEast(GamePiece gp1, GamePiece gp2) {
		// thumb coordinates
		double thumbYCoords = (gp1.getSize() * (1 - 0.118)); 
		double thumbXCoords = (gp1.getSize() * (1 - 0.138));
		
		// fist front coordinates
		double fistFrontYCoords = (gp1.getSize() * (1 - 0.3875));
		double fistFrontXCoords = (gp1.getSize() * 0.328);
		return(
				// thumb - hit on top right corner
						// check y coordinates to be at thumb area only or up
							gp2.getY() <= (gp1.getY() + thumbYCoords)
						&&	gp2.getY() >= gp1.getY()
					&&
						// check x coordinates to be at thumb area
							gp2.getX() + gp2.getSize() <= (gp1.getX() + thumbXCoords)
						&&	gp2.getX() + gp2.getSize() >= gp1.getX()
				||
				// front of fist - hit on top left corner 
						// check y coordinates to be at fist flat zone area
							gp2.getY() + gp2.getSize() >= gp1.getY()
						&&	gp2.getY() + gp2.getSize() <= gp1.getY() + fistFrontYCoords
					&&
						// check x coordinates to be at fist flat zone area
							gp2.getX() >= gp1.getX() + fistFrontXCoords
						&&	gp2.getX() <= gp1.getX() + gp1.getSize()
				||
				// front of fist - fingers joints part
					// check y coordinates of joints to be at gp2 y area
							gp1.getY() + fistFrontYCoords >= gp2.getY()
						&&	gp1.getY() + fistFrontYCoords <= gp2.getY() + gp2.getSize()
					&&
						// check x coordinates of joints to be at gp2 x coordinate
							gp1.getX() + gp1.getSize() >= gp2.getX()
						&& 	gp1.getX() + gp1.getSize() <= (gp2.getX() + (gp2.getSize() / 2))
				||
				// front of fist - bottom fingers joints part
						// check x coordinates of joints to be at gp2 y area
							gp1.getX() + fistFrontXCoords >= gp2.getX()
						&&	gp1.getX() + fistFrontXCoords <= gp2.getX() + gp2.getSize()
					&&
						// check y coordinates of joints to be at gp2 x coordinate
							gp1.getY() + gp1.getSize() >= gp2.getY()
						&& 	gp1.getY() + gp1.getSize() <= (gp2.getY() + (gp2.getSize() / 2))		
				);
	}
}
