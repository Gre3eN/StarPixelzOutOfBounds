package FatPack;

import java.awt.Color;

public class StarValues {
	public static int INITIAL_COUNT = 250;
	public static int MAX_COUNT = INITIAL_COUNT*4;
	public static int INITIAL_SPAWN_TOLERANCE_X=20;
	
	//public static int INITIAL_SPAWN_RESTRICTION=50;
	
	
	public static int SPEED = 5; //make it faster than pipes for nausea 
	
	public static int WIDTH = 2;
	public static int HEIGHT = 2;
	
	
	public static Color STAR_COLOR = new Color(255, 255, 255, 255);
	
	
	public static int[] BACKGROUND_STAR_TRANSPARENCYS = {255,150,75,50};
		//{255, 150, 75, 50};
		
	
	
	
	public static Color AURA_COLOR0 = new Color(255, 255, 255, 150);
	public static Color AURA_COLOR1 = new Color(255, 255, 255, 75);
	public static Color AURA_COLOR2 = new Color(255, 255, 255, 50);
}
