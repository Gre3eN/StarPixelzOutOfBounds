package MagicNumberWonderland;

import java.awt.Color;
import java.util.Random;

import FatPack.Values;

public class StarValues extends Values {


	
	public static int MAX_COUNT = 1000;
	public static int INITIAL_COUNT = 500;
	public static int INITIAL_SPAWN_TOLERANCE_X=20;
	
	public static int SPEED = 5; //make it faster than pipes for nausea 
	
	public static int WIDTH = 2;
	public static int HEIGHT = 2;
	
	private int[] randyRestrictionsX = {FRAME_WIDTH/2 +10, FRAME_WIDTH};
	private int[] randyRestrictionsY = {};
	
	
	public static Color STAR_COLOR = new Color(255, 255, 255, 255);
	
	
	public static int[] STAR_AURA_TRANSPARENCY = {150, 100, 50};
	
	
	public static Color AURA_COLOR0 = new Color(255, 255, 255, 150);
	public static Color AURA_COLOR1 = new Color(255, 255, 255, 75);
	public static Color AURA_COLOR2 = new Color(255, 255, 255, 50);
}
