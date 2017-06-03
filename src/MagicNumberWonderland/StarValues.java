package MagicNumberWonderland;

import java.awt.Color;
import java.util.Random;

import FatPack.Values;

public class StarValues extends Values {


	
	public static int MAX_COUNT = 5000;
	public static int INITIAL_COUNT = 500;
	public static int INITIAL_SPAWN_TOLERANCE_X=20;
	//jo
	public static int SPEED = 4; //make it faster than pipes for nausea 
	
	public static int WIDTH = 1;
	public static int HEIGHT = 1;
	
	private int[] randyRestrictionsX = {FRAME_WIDTH/2 +10, FRAME_WIDTH};
	private int[] randyRestrictionsY = {};
	
	
	public static Color STAR_COLOR = new Color(255, 255, 255, 255);
	
	
	public static int[] STAR_AURA_TRANSPARENCY = {150, 100, 50};
	
	
	public static Color AURA_COLOR0 = new Color(255, 255, 255, 200);
	public static Color AURA_COLOR1 = new Color(255, 255, 255, 100);
	public static Color AURA_COLOR2 = new Color(255, 255, 255, 50);
}
