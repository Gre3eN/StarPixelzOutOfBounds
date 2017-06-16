package controller;

import java.util.ArrayList;
import java.util.Random;
import FatPack.Values;
import model.BackGroundStar;

public class BackGroundStarManagement {

	private ArrayList<int[]> backGroundStars = new ArrayList<>();
	private Random randy = new Random();
	private int random;
	int[] starData;

	public BackGroundStarManagement() {
		for (int i = 0; i < Values.S_INITIAL_COUNT; i++) {
			random = randy.nextInt(8) + 2;
			backGroundStars.addAll(new BackGroundStar(0, random).getBackGroundStar());
		}
	}

	public void update() {
		for (int i = 0; i < backGroundStars.size(); i++) {
			starData = backGroundStars.get(i);
			starData[0] -= (Values.S_SPEED +starData[4]);
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
		if (backGroundStars.size() < Values.S_MAX_COUNT) {
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

	public ArrayList<int[]> getBackGroundStars() {
		return backGroundStars;
	}
}
