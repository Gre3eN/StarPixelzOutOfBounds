package FatPack;

import java.util.ArrayList;
import java.util.Random;

public class BackgroundStarManager {
	
	private ArrayList<BackgroundStar[]> stars = new ArrayList<>();
	private BackgroundStar[] star;
	private Random randy = new Random();
	
	public void startSpawn(){
		int x, y, size;
		int upperRandomSize = Values.BACKGROUND_STAR_UPPER_RANDOM_SIZE;
		int lowerRandomSize = Values.BACKGROUND_STAR_LOWER_RANDOM_SIZE;
		star = new BackgroundStar[Values.BACKGROUND_STAR_LAYERS];
		
		for(int i = 0; i < Values.BACKGROUND_STAR_START_SPAWN; i++){
			x = randy.nextInt(Values.FRAME_WIDTH);
			y = randy.nextInt(Values.FRAME_HEIGHT);
			size = randy.nextInt(upperRandomSize - lowerRandomSize) + lowerRandomSize;
			
			for(int j = 1; j <= Values.BACKGROUND_STAR_LAYERS; j++){
				star[j - 1] = new BackgroundStar(x, y, j, size);
			}
			//System.out.println("x" +x+ "y" +y);
			stars.add(star);
		}
		System.out.println("konstruktor stars" + stars.size());
	}
	
	private void spawn(){
		int x, y, size;
		int upperRandomSize = Values.BACKGROUND_STAR_UPPER_RANDOM_SIZE;
		int lowerRandomSize = Values.BACKGROUND_STAR_LOWER_RANDOM_SIZE;
		star = new BackgroundStar[Values.BACKGROUND_STAR_LAYERS];
		
		for(int i = 0; i < Values.BACKGROUND_STAR_SPAWN; i++){
			x = Values.FRAME_WIDTH + upperRandomSize;
			y = randy.nextInt(Values.FRAME_HEIGHT);
			size = randy.nextInt(upperRandomSize - lowerRandomSize) + lowerRandomSize;
			
			for(int j = 1; j <= Values.BACKGROUND_STAR_LAYERS; j++){
				star[j - 1] = new BackgroundStar(x, y, j, size);
			}
			stars.add(star);
		}
		System.out.println("spawn stars" + stars.size());
	}
	
	private void delete(){
		for(int i = 0; i < stars.size(); i++){
			if (stars.get(i)[Values.BACKGROUND_STAR_LAYERS - 1].getX() 
					< -stars.get(i)[Values.BACKGROUND_STAR_LAYERS - 1].getSize())
				stars.remove(i);
		}
		System.out.println("delete stars" + stars.size());
	}
	
	public void update(){
		for(BackgroundStar[] star : stars)
			for(BackgroundStar layer : star)
				layer.moveLeft();
		spawn();
		delete();
	}
	
	public void flappyCharge(){
		for(BackgroundStar[] star : stars)
			for(BackgroundStar layer : star)
				layer.flappyCharge();
	}
	
	public ArrayList<BackgroundStar[]> getStars(){
		return stars;
	}
	
}
