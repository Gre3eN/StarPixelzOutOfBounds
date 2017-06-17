package controller;

import java.util.ArrayList;

import FatPack.Values;
import model.ChargeAnimation;
import model.Oval;

public class AnimationManager {
	
	ArrayList<ChargeAnimation> charge = new ArrayList<>();
	ArrayList<Oval> cometTail = new ArrayList<>();
	
	public void spawnCharge(){
		for(int i = 1; i <= Values.FLAPPY_ANIMATION_COUNT; i++)
			charge.add(new ChargeAnimation(i));
	}
	
	private void deleteCharge(){
		int k = 0;
		for(int i = 0; i < Values.FLAPPY_ANIMATION_TIME; i++)
			k++;
		if(k == Values.FLAPPY_ANIMATION_TIME)
			charge.clear();
	}
	
	public void update(){
		for(ChargeAnimation c : charge)
			c.update();
		deleteCharge();
	}
	
	public ArrayList<ChargeAnimation> getCharge(){
		return charge;
	}
	
	public void reset(){
		charge.clear();
	}
}

