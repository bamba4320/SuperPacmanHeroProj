package Utils;

public class LinearAlgebraClacsUtils {
	/**
	 * get the absolute distance between two points
	 * @param x1 First point x coordinate
	 * @param y1 First point y coordinate
	 * @param x2 Second point x coordinate
	 * @param y2 Second point y coordinate
	 * @return double value
	 */
	public static double getDistanceBetweenTwoPoints(int x1, int y1, int x2, int y2) {
		return Math.sqrt((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2)));
	}
	
	/**
	 * get the slope between to points
	 * @param x1 First point x coordinate
	 * @param y1 First point y coordinate
	 * @param x2 Second point x coordinate
	 * @param y2 Second point y coordinate
	 * @return double value
	 */
	public static double getSlopeBetweenTwoPoints(int x1, int y1, int x2, int y2) {
		return (y2 - y1) / (x2 - x1);
	}
}
