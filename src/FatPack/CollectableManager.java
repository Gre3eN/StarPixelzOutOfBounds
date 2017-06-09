package FatPack;

import java.util.ArrayList;

public class CollectableManager {

	private int lastSpawnAt;
	private ArrayList<Collectable> collectables;
	private Controller controller;
	private Collectable temp;

	public CollectableManager(Controller controller) {
		this.controller = controller;
		lastSpawnAt = 0;
		
		collectables=new ArrayList<>();
		
		collectables.add(new Collectable());
	}

	public void update(int score) {
		if (score % CollectableValues.PIPES_TO_SPAWN == 0 && score != lastSpawnAt) {
			System.out.println("try to spawn");
			lastSpawnAt = score;
			spawnCollectable();
		}
		
		for(Collectable c : collectables) {
			c.update();
		}
			
		deleteCollectable();
	}

	public void charge() {
		if(collectables.size()>0) {
			for (Collectable c : collectables) {
				c.charge();
			}			
		}
	}

	public void spawnCollectable() {
		temp = new Collectable();
		temp.addObserver(controller);

		collectables.add(temp);
	}

	public void deleteCollectable() {
		if(collectables.size()>0) {
			for (int i=0; i< collectables.size(); i++) {
				if (collectables.get(i).getCore().x <= 0) {
					collectables.remove(i);
				}
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
}
