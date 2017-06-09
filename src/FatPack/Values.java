package FatPack;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Values {

	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	protected static int FRAME_WIDTH = gd.getDisplayMode().getWidth();
	protected static int FRAME_HEIGHT = gd.getDisplayMode().getHeight() ;

	protected static int FLOOR_HEIGHT = FRAME_HEIGHT/10;
	protected static int MIN_PIPE_HEIGHT = 150;

	protected static int PIPE_WIDTH = 50;
	protected static int PIPE_GAP = 140;
	protected static int PIPE_SPEED = 10;

	protected static int PIPE_SPAWN_GAP = FRAME_WIDTH/3 + FRAME_WIDTH/6;
	
	public static int TIMER_DELAY = 50;
	
	public static int FLAPPY_X = 230;
	public static int FLAPPY_Y = FRAME_HEIGHT / 2;
	public static int FLAPPY_WIDTH = 50;
	public static int FLAPPY_HEIGHT = 50;
	
	protected static int FLAPPY_JUMP_HEIGHT = 40;
	protected static int FLAPPY_JUMP_DOWN_HEIGHT = 20;
	protected static int FLAPPY_FALL_HEIGHT = FRAME_HEIGHT/110;
	protected static int FLAPPY_CHARGE_SPEED = 80;
	
	protected static int FLAPPY_ANIMATION_COUNT = 3;
	protected static int FLAPPY_ANIMATION_RANGE_RATE = FLAPPY_CHARGE_SPEED / FLAPPY_ANIMATION_COUNT - FLAPPY_CHARGE_SPEED / 20;
	protected static int FLAPPY_ANIMATION_TIME = 3; 	//timer actions
	protected static int FLAPPY_ANIMATION_START_TRANSPARENCY = 100;
	protected static int FLAPPY_ANIMATION_TRANSPARENCY_LOSS = FLAPPY_ANIMATION_START_TRANSPARENCY / FLAPPY_ANIMATION_TIME;
	
	protected static int OVAL_HEIGHT = FLAPPY_HEIGHT;
	protected static int OVAL_WIDTH = FLAPPY_WIDTH;
	
	protected static int FIRST_OVAL_EXPAND_RATE = 80;
	protected static int SECOND_OVAL_EXPAND_RATE = 60;
	protected static int FOURTH_OVAL_EXPAND_RATE = 50;
	
	protected static int OVAL_EXPAND_TIME = 12;
	
	protected static int OVAL_COUNT = 5;
	
	protected static int FIRST_OVAL_START_TRANSPERENCY = 127;
	protected static int SECOND_OVAL_START_TRANSPERENCY = 255;
	protected static int FOURTH_OVAL_START_TRANSPERENCY = 127;
	
	protected static int OVAL_COLOR_CHANGE_RATE = 15; //can only be 5 or 15 because 255 % x has to be 0 or the code in the ColorManager.changeColor() needs to be changed
	
	protected static int OUTER_OVAL_TRANSPERENCY_LOSS = 5;
	protected static int INNER_OVAL_TRANSPERENCY_LOSS = 2 * OUTER_OVAL_TRANSPERENCY_LOSS;
	
	protected static int OVALS_CAP = 15;
	
	protected static Color BACKGROUND_COLOR = new Color(0, 0, 0);
	protected static Color FLOOR_COLOR = new Color(40, 40, 40);
	protected static Color PIPE_COLOR = new Color(0, 0, 0);
	protected static Color FAIL_COLOR = new Color(255, 255, 255, 200);
	
	protected static Shape createRingShape(double x, double y, double size) {
		Ellipse2D outer = new Ellipse2D.Double(x, y, size, size);
		Ellipse2D inner = new Ellipse2D.Double(x + size * 0.15, y + size * 0.15, size * 0.7, size * 0.7);
		Area area = new Area(outer);
		area.subtract(new Area(inner));
		return area;
	}
}