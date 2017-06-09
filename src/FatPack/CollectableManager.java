package FatPack;

import java.util.ArrayList;

public class CollectableManager {

	private int score, scoreHolder;
	private ArrayList<Collectable> comets;
	private Controller controller;
	private Collectable temp;
	
	public CollectableManager(Controller controller) {
		this.controller=controller;
		score=0;
		scoreHolder=0;
	}

	public void update(int score) {
		//this.score=scoreArg;
		
		if(score%CollectableValues.PIPES_TO_SPAWN==0 &&score!=scoreHolder) {
			scoreHolder=score;
			spawnCollectable();
		}
		deleteCollectable();
	}

	public void charge() {
		//fuck u flappy
	}

	public void spawnCollectable() {
		temp=new Collectable();
		temp.addObserver(controller);
		
		comets.add(temp);
	}

	public void deleteCollectable() {
		for(Collectable c : comets) {
			if(c.getCore().x < 0 ) {
				comets.remove(c);
			}
		}
	}

	public void reset() {

	}

	public void getCollectable() {

	}
}
