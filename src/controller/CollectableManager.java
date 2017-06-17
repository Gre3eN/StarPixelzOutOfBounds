package controller;

import java.util.ArrayList;
import FatPack.Values;
import model.Collectable;
import model.CometTail;

public class CollectableManager {

	private int lastSpawnAt;
	private ArrayList<Collectable> collectables;
	private ArrayList<CometTail> tail;
	private Controller controller;
	private Collectable temp;

	public CollectableManager(Controller controller) {
		this.controller = controller;
		lastSpawnAt = 0;

		collectables = new ArrayList<>();
		tail = new ArrayList<>();

		temp = new Collectable();
		temp.addObserver(controller);
		collectables.add(temp);
	}

	public void update(int score) {
		if (score % Values.C_PIPES_TO_SPAWN == 0 && score != lastSpawnAt) {
			lastSpawnAt = score;
			spawnCollectable();
		}
		
		createTail();

		for (int i = 0; i < collectables.size(); i++) {
			collectables.get(i).update();
		}
		
		for (CometTail t : tail)
			t.expand();

		deleteCollectable();
		deleteTail();
	}
	
	private void createTail(){
		if (collectables.size() > 0){
			for(Collectable c : collectables)
				tail.add(new CometTail(c.getCore().x, c.getCore().y));
		}
	}
	
	private void deleteTail(){
		if(tail.size() > Values.COMETTAIL_LENGTH)
			tail.remove(0);
	}

	public void charge() {
		if (collectables.size() > 0) {
			for (Collectable c : collectables) {
				c.charge();
			}
		}
		if (tail.size() > 0) {
			for (CometTail t : tail) {
				t.flappyCharge();
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
	
	public ArrayList<CometTail> getCometTail() {
		return tail;
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
				tail.clear();
			}
		}
	}

	public void gotCaught() {
		collectables.remove(0);
		tail.clear();
	}
}
