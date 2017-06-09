package FatPack;

import java.util.ArrayList;
import java.util.Random;

public class BackGroundStarManagement {

	private ArrayList<int[]> backGroundStars = new ArrayList<>();
	private Random randy = new Random();
	private int random;
	int[] starData;

	public BackGroundStarManagement() {
		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			random = randy.nextInt(8) + 2;
			backGroundStars.addAll(new BackGroundStar(0, random).getBackGroundStar());
		}
	}

	public void update() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			starData = backGroundStars.get(i);
			starData[0] -= (StarValues.SPEED +starData[4]);
			backGroundStars.set(i, starData);
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
			starData = backGroundStars.get(i);
			starData[0] -= (Values.FLAPPY_CHARGE_SPEED / 2 + starData[4]);
			backGroundStars.set(i, starData);
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
