package model;

import java.util.ArrayList;
import java.util.Random;

import FatPack.Values;

/**
 * This class represents a BackGroundStar with: x, y, width, heigth and
 * massAccelerator in an int Array.
 * 
 * 
 * @author philippsteinke
 * @version simplistic Version
 * @see Values
 * @see BackGroundStarManagement
 *
 */
public class BackGroundStar {

	private Random randy = new Random();
	private int x, y, massAccelerator;
	private int[] backgroundStar;

	/**
	 * A BackGroundStar is eather created at Random position on screen, or at
	 * the right border of the screen(so it can start moving to the left)
	 * 
	 * @param where
	 *            0 for random
	 * @param where
	 *            1 for right border
	 * 
	 * @param size
	 *            any int you desire -> width and heigth -> massAccelerator will
	 *            be 2/3 of size
	 */
	public BackGroundStar(int where, int size) {

		if (where == 0) {
			x = randy.nextInt(Values.FRAME_WIDTH);
			y = randy.nextInt(Values.FRAME_HEIGHT);
			massAccelerator = (size * 2) / 3;
			backgroundStar = new int[] { x, y, size, size, massAccelerator };

		} else if (where == 1) {
			x = Values.FRAME_WIDTH;
			y = randy.nextInt(Values.FRAME_HEIGHT);
			massAccelerator = (size * 2) / 3;
			backgroundStar = new int[] { x, y, size, size, massAccelerator };
		}
	}

	public int[] getBackGroundStar() {
		return backgroundStar;
	}
}
