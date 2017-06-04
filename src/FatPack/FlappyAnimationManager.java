package FatPack;

import java.util.ArrayList;

public class FlappyAnimationManager {
	
	ArrayList<FlappyChargeAnimation> charge = new ArrayList<>();
	
	public void spawnCharge(){
		for(int i = 1; i <= Values.FLAPPY_ANIMATION_COUNT; i++)
			charge.add(new FlappyChargeAnimation(i));
	}
	
	public void deleteCharge(){
		int k = 0;
		for(int i = 0; i < Values.FLAPPY_ANIMATION_TIME; i++)
			k++;
		if(k == Values.FLAPPY_ANIMATION_TIME){
			charge.clear();
		}
	}
	
	public void update(){
		for(FlappyChargeAnimation c : charge)
			c.update();
		deleteCharge();
	}
	
	public ArrayList<FlappyChargeAnimation> getCharge(){
		return charge;
	}
	
	public void reset(){
		charge.clear();
	}
	
}
