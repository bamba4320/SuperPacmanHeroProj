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
		
		// NOTE: There is additional size added to the parameter of 
		//       game piece 2, to cover option of close but no touching
		//		 encounter.
		
		switch(d) {
		case SOUTH:
			
			// in case the encounter is from the south to game piece 2,
			// check if game piece 1 is touching game piece 2 from below
			// or up to half size in it, and verify that game piece 1
			// boundaries are partially or fully within game piece 2
			// length and coordinates range.
			
			return (
					
					// first check, verify that game piece 1 y coordinate 
					// is within game piece 2 y coordinates range
				 	   gp1.getY() == (gp2.getY() + gp2.getSize() + 5) 
					|| gp1.getY() >= (gp2.getY() + (gp2.getSize() / 2))
					
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
					    (  gp1.getX() <= gp2.getX() - 5                  
					   	   // check right up corner to be within range and not passing it.
					    && gp1.getX() + gp1.getSize() >= gp2.getX()
					    && gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize() + 5)
					||
					// stage 2:
					true
					||
					// stage 3:
					true
				   ));
		
			
		default:
			return false;
					
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
}
