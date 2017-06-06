package FatPack;

import java.util.ArrayList;
import java.util.Random;

public class BackGroundStar {

	private Random randy = new Random();
	private int x, y;
	private ArrayList<int[]> backgroundStar = new ArrayList<>();

	public BackGroundStar(int where, int size) {

		if (where == 0) {
			x = randy.nextInt(Values.FRAME_WIDTH);
			y = randy.nextInt(Values.FRAME_HEIGHT);
			backgroundStar.add(new int[] { x, y, size, size, 0 });

		} else if (where == 1) {
			x = Values.FRAME_WIDTH;
			y = randy.nextInt(Values.FRAME_HEIGHT);
			backgroundStar.add(new int[] { x, y, size, size, 0});
		}
	}

	public ArrayList<int[]> getBackGroundStar() {
		return backgroundStar;
	}
}
