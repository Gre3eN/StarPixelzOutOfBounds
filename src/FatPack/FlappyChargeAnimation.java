package FatPack;

import java.util.ArrayList;

public class FlappyChargeAnimation {
	
	private int flappyX, j, k;
	private ArrayList<Integer> animation;
	private ArrayList<Integer> transparency;
	
	public FlappyChargeAnimation(){
		flappyX = Values.FLAPPY_X;
		animation = new ArrayList<Integer>();
		transparency = new ArrayList<Integer>();
	}
	
	public void setAnimation(){
		j = 0;
		k = 0;
		for(int i = flappyX - Values.FLAPPY_ANIMATION_RANGE_RATE; j < Values.FLAPPY_ANIMATION_COUNT; 
				i -= Values.FLAPPY_ANIMATION_RANGE_RATE){
			animation.add(i);
			j++;
		}
		for(int i = Values.FLAPPY_ANIMATION_START_TRANSPARENCY; k < Values.FLAPPY_ANIMATION_COUNT; 
				i -= Values.FLAPPY_ANIMATION_TRANSPARENCY_LOSS){
			transparency.add(i);
			k++;
		}
	}
	
	public void updateTransparency(){
		int timeCount = 0;
		timeCount++;
		for(int i = 0; i < transparency.size(); i++){
			int holder = transparency.get(i);
			holder -= Values.FLAPPY_ANIMATION_TRANSPARENCY_LOSS;
			if (holder < 0) 
				holder = 0;
			transparency.set(i, holder);
		}
		if (timeCount == Values.FLAPPY_ANIMATION_COUNT)
			reset();
	}
	
	public ArrayList<Integer> getAnimation(){
		return animation;
	}
	
	public ArrayList<Integer> getTransparency(){
		return transparency;
	}
	
	public void reset(){
		animation.clear();
		transparency.clear();
	}
}
