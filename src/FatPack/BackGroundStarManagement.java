package FatPack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BackGroundStarManagement {

	private ArrayList<int[]> backGroundStars = new ArrayList<>();;
	private Random randy = new Random();;
	private int random, massAccelerator;
	int[] xywht;

	public BackGroundStarManagement() {

		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			random = randy.nextInt(8) + 2;
			backGroundStars.addAll(new BackGroundStar(0, random).getBackGroundStar());
		}
	}

	public void update() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			xywht = backGroundStars.get(i);
			massAccelerator = (xywht[3] * 2) / 3;
			xywht[0] -= (StarValues.SPEED + massAccelerator);
			backGroundStars.set(i, xywht);

		}
		delete();
	}

	private void delete() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			if (backGroundStars.get(i)[0] <= 0) {
				backGroundStars.remove(i);
				spawn();
			}
		}
	}

	private void spawn() {
		if (backGroundStars.size() < StarValues.MAX_COUNT) {
			random = randy.nextInt(8) + 2;
			backGroundStars.addAll(new BackGroundStar(1, random).getBackGroundStar());
		}
	}

	public void charge() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			xywht = backGroundStars.get(i);
			massAccelerator = (xywht[3] * 2) / 3;
			xywht[0] -= (Values.FLAPPY_CHARGE_SPEED / 2 + massAccelerator);
			backGroundStars.set(i, xywht);
		}
	}

	public void reset() {
		backGroundStars.clear();

		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			random = randy.nextInt(7) + 1;
			backGroundStars.addAll(new BackGroundStar(0, random).getBackGroundStar());
		}
	}

	public ArrayList<int[]> getBackGroundStars() {
		return backGroundStars;
	}
}
