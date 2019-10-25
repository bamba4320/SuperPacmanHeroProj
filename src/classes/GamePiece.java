package classes;

public interface GamePiece {
	int x = 0;
	int y = 0;
	int size = 0;
	
	public default int getX() {
		return x;
	}
	
	public default int getY() {
		return y;
	}
	
	public default int getSize() {
		return size;
	}
	
}
