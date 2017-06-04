package FatPack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BackGroundStarManagement {

	private ArrayList<int[]> backGroundStars;
	private Random randy;
	private int random;
	int[] xywht;

	public BackGroundStarManagement() {
		backGroundStars = new ArrayList<>();
		randy = new Random();

		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			random = randy.nextInt(6) + 2;
			backGroundStars.addAll(new BackGroundStar("somewhere", random).getCompleteBackGroundStar());
		}
	}

	public void update() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			xywht = backGroundStars.get(i);
			xywht[0] -= StarValues.SPEED;
			backGroundStars.set(i, xywht);
		}
		delete();
	}

	private void delete() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			if (backGroundStars.get(i)[0] <= 0) {
				backGroundStars.remove(i);
				System.out.println("removed a star in sm");
			}
			spawn();
		}
	}

	private void spawn() {
		if (backGroundStars.size() < StarValues.MAX_COUNT) {
			random = randy.nextInt(6) + 2;
			backGroundStars.addAll(new BackGroundStar("east", random).getCompleteBackGroundStar());
			System.out.println("spawned a star in sm");
		}
	}

	public void reset() {
		backGroundStars.clear();

		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			random = randy.nextInt(7) + 1;
			backGroundStars.addAll(new BackGroundStar("somewhere", random).getCompleteBackGroundStar());
		}
	}

	public void charge() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			backGroundStars.get(i)[0] -= Values.FLAPPY_CHARGE_SPEED / 2; // siehe
																			// kommentar
																			// bei
																			// update
		}
	}

	public ArrayList<int[]> getBackGroundStars() {
		return backGroundStars;
	}
}
