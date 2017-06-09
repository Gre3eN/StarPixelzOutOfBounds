package FatPack;

import java.util.ArrayList;
import java.util.Random;

public class BackGroundStar {

	private Random randy = new Random();
	private int x, y, massAccelerator;
	private ArrayList<int[]> backgroundStar = new ArrayList<>();

	public BackGroundStar(int where, int size) {
		
		if (where == 0) {
			x = randy.nextInt(Values.FRAME_WIDTH);
			y = randy.nextInt(Values.FRAME_HEIGHT);
			massAccelerator=(size*2)/3;
			backgroundStar.add(new int[] {x, y, size, size, massAccelerator});
			
		} else if (where == 1) {
			x = Values.FRAME_WIDTH;
			y = randy.nextInt(Values.FRAME_HEIGHT);
			massAccelerator=(size*2)/3;
			backgroundStar.add(new int[] { x, y, size, size, massAccelerator});
		}
	}

	public ArrayList<int[]> getBackGroundStar() {
		return backgroundStar;
	}
}
