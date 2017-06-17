package FatPack;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Values {

	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	public static int TIMER_DELAY = 50;

	// Frame
	public static int FRAME_WIDTH = gd.getDisplayMode().getWidth();
	public static int FRAME_HEIGHT = gd.getDisplayMode().getHeight();

	// Pipes
	public static int MIN_PIPE_HEIGHT = 150;
	public static int PIPE_WIDTH = 50;
	public static int PIPE_GAP = 140;
	public static int PIPE_SPEED = 10;
	public static int PIPE_SPAWN_GAP = FRAME_WIDTH / 3 + FRAME_WIDTH / 6;
	
	// Hammer Pipes
	public static int HAMMERPIPE_START_GAP = 200;
	public static int HAMMERPIPE_MOVE_TOGETHER = 10;
	public static int HAMMERPIPE_MOVE_APART = HAMMERPIPE_MOVE_TOGETHER / 2;
	public static int HAMMERPIPE_SPAWN_INTERVALL = 4; // hammer pipe spawn every 5th pipe

	// Flappy
	public static int FLAPPY_X = 230;
	public static int FLAPPY_Y = FRAME_HEIGHT / 2;
	public static int FLAPPY_WIDTH = 50;
	public static int FLAPPY_HEIGHT = 50;
	public static int FLAPPY_X2 = FLAPPY_X + FLAPPY_WIDTH;

	public static int FLAPPY_JUMP_HEIGHT = 40;
	public static int FLAPPY_JUMP_DOWN_HEIGHT = 20;
	public static int FLAPPY_FALL_HEIGHT = FRAME_HEIGHT / 110;
	public static int FLAPPY_CHARGE_SPEED = 80;

	public static int FLAPPY_ANIMATION_COUNT = 8;
	public static int FLAPPY_ANIMATION_RANGE_RATE = FLAPPY_CHARGE_SPEED / FLAPPY_ANIMATION_COUNT
			- FLAPPY_CHARGE_SPEED / 20;
	public static int FLAPPY_ANIMATION_TIME = 3; // timer actions
	public static int FLAPPY_ANIMATION_START_TRANSPARENCY = 100;
	public static int FLAPPY_ANIMATION_TRANSPARENCY_LOSS = FLAPPY_ANIMATION_START_TRANSPARENCY / FLAPPY_ANIMATION_TIME;

	// Ovals
	public static int OVAL_HEIGHT = FLAPPY_HEIGHT;
	public static int OVAL_WIDTH = FLAPPY_WIDTH;
	public static int OUTER_OVAL_EXPAND_RATE = 80;
	public static int MID_OVAL_EXPAND_RATE = 60;
	public static int INNER_OVAL_EXPAND_RATE = 50;
	public static int OVAL_EXPAND_TIME = 12;
	public static int OVAL_COUNT = 5;
	public static int OUTER_OVAL_START_TRANSPERENCY = 127;
	public static int MID_OVAL_START_TRANSPERENCY = 255;
	public static int INNER_OVAL_START_TRANSPERENCY = 127;
	public static int OVAL_COLOR_CHANGE_RATE = 15; // nur teiler von 255
	public static int OUTER_OVAL_TRANSPERENCY_LOSS = 5;
	public static int INNER_OVAL_TRANSPERENCY_LOSS = 2 * OUTER_OVAL_TRANSPERENCY_LOSS;
	public static int OVALS_CAP = 15;
	public static double OVAL_CIRCLE_RELATION = 0.7;

	// Colores
	public static Color BACKGROUND_COLOR = new Color(0, 0, 0);
	public static Color FLOOR_COLOR = new Color(40, 40, 40);
	public static Color PIPE_COLOR = new Color(0, 0, 0);
	public static Color FAIL_COLOR = new Color(255, 255, 255, 200);

	// Stars
	public static int S_INITIAL_COUNT = 75;
	public static int S_MAX_COUNT = S_INITIAL_COUNT;
	public static int S_SPEED = 4; // make it faster than pipes for nausea

	// Collectables
	public static int C_WIDTH = 25;
	public static int C_HEIGHT = 25;
	public static int C_SPEED = 20;
	public static int C_PIPES_TO_SPAWN = 2;
	
	//CometTail
	public static int COMETTAIL_TRANSPARENCY_LOSS = 20;
	public static int COMETTAIL_START_TRANSPARENCY = 140;
	public static int COMETTAIL_EXPAND_RATE = 5; //minimum 2
	public static int COMETTAIL_LENGTH = 5;
	public static double COMETTAIL_CIRCLE_RELATION = 0.3;
	
	// Startscreen
	public static int CONTROL_TEXT_HEIGHT = FRAME_HEIGHT - 10;
	public static int HIGHSCORE_HEIGHT = FRAME_HEIGHT - 610;
	public static int FIRST_HIGHSCORE_WIDTH = FRAME_WIDTH - 140;
	public static int SECOND_HIGHSCORE_WIDTH = FRAME_WIDTH - 20;

	// Ring shape creator
	public static Shape createRingShape(double x, double y, double size, double relation1) {
		double relation2 = (1.0 - relation1) / 2;
		Ellipse2D outer = new Ellipse2D.Double(x, y, size, size);
		Ellipse2D inner = new Ellipse2D.Double(x + size * relation2, y + size * relation2, size * relation1, size * relation1);
		Area area = new Area(outer);
		area.subtract(new Area(inner));
		return area;
	}
}