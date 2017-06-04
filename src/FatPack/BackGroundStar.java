package FatPack;

import java.util.ArrayList;
import java.util.Random;

public class BackGroundStar {

	private Random randy = new Random();

	private int size;
	private int x, y, width, height, transparencyIndex;
	private int xa, ya, wa, ha, ta;

	private ArrayList<int[]> backgroundStar = new ArrayList<>();

	public BackGroundStar(String where, int size) {
		this.size = size;
		if (where.equals("somewhere")) {
			x = randy.nextInt(Values.FRAME_WIDTH);
			y = randy.nextInt(Values.FRAME_HEIGHT);
			width = size;
			height = size;
			transparencyIndex = 0;
			backgroundStar.add(new int[] { x, y, width, height, transparencyIndex });

			addAura1();
			addAura2();
			addAura3();
		} else if (where.equals("east")) {
			x = Values.FRAME_WIDTH;
			y = randy.nextInt(Values.FRAME_HEIGHT);
			width = size;
			height = size;
			transparencyIndex = 0;
			backgroundStar.add(new int[] { x, y, width, height, transparencyIndex });

			addAura1();
			addAura2();
			addAura3();
		}
	}

	private void addAura1() {
		xa = (int) x - width / 2;
		ya = (int) y - height / 2;
		wa = width * 2;
		ha = height * 2;
		transparencyIndex = 1;
		backgroundStar.add(new int[] { xa, ya, wa, ha, ta });
	}

	private void addAura2() {
		xa = (int) x - width;
		ya = (int) y - height;
		wa = width * 3;
		ha = height * 3;
		transparencyIndex = 2;
		backgroundStar.add(new int[] { xa, ya, wa, ha, ta });
	}

	private void addAura3() {
		xa = (int) x - width * 2;
		ya = (int) y - height * 2;
		wa = width * 5;
		ha = height * 5;
		transparencyIndex = 3;
		backgroundStar.add(new int[] { xa, ya, wa, ha, ta });
	}

	public ArrayList<int[]> getCompleteBackGroundStar() {
		return backgroundStar;
	}

	@Deprecated
	public void charge() {
		for (int i = 0; i < backgroundStar.size(); i++) {
			backgroundStar.get(i)[0] -= ((Values.FLAPPY_CHARGE_SPEED / 2) + size);
		}
	}

	@Deprecated
	public void moveLeft() {
		for (int i = 0; i < backgroundStar.size(); i++) {
			backgroundStar.get(i)[0] -= (StarValues.SPEED + size);
		}
	}
}
