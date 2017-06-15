package controller;

import java.util.ArrayList;

import FatPack.CollectableValues;
import model.Collectable;

public class CollectableManager {

	private int lastSpawnAt;
	private ArrayList<Collectable> collectables;
	private Controller controller;
	private Collectable temp;

	public CollectableManager(Controller controller) {
		this.controller = controller;
		lastSpawnAt = 0;

		collectables = new ArrayList<>();

		temp = new Collectable();
		temp.addObserver(controller);
		collectables.add(temp);
	}

	public void update(int score) {
		if (score % CollectableValues.PIPES_TO_SPAWN == 0 && score != lastSpawnAt) {
			lastSpawnAt = score;
			spawnCollectable();
		}

		for(int i=0; i<collectables.size();i++ ) {
			collectables.get(i).update();
		}

		deleteCollectable();
	}

	public void charge() {
		if (collectables.size() > 0) {
			for (Collectable c : collectables) {
				c.charge();
			}
		}
	}

	public void reset() {
		collectables.clear();
		lastSpawnAt = 0;
	}

	public ArrayList<Collectable> getCollectables() {
		return collectables;
	}

	private void spawnCollectable() {
		temp = new Collectable();
		temp.addObserver(controller);

		collectables.add(temp);
	}

	private void deleteCollectable() {
		for (int i = 0; i < collectables.size(); i++) {
			if (collectables.get(i).getCore().x <= 0) {
				collectables.remove(i);
			}
		}
	}
	public void gotCaught() {
		collectables.remove(0);
	}
}
