package FatPack;

import java.util.Random;

public class Pipe {

	private Random randy = new Random();
	private int x;
	private int y1, y2;
	private int height1, heigth2;
	private int width;
	private int gapUpperY, gapLowerY;
	boolean collisionPossible = false;

	public Pipe() {
		x = Values.FRAME_WIDTH;
		y1 = 0;
		height1 = randy
				.nextInt(Values.FRAME_HEIGHT - Values.FLOOR_HEIGHT - Values.PIPE_GAP - 2 * Values.MIN_PIPE_HEIGHT)
				+ Values.MIN_PIPE_HEIGHT;
		y2 = height1 + Values.PIPE_GAP;
		heigth2 = Values.FRAME_HEIGHT - y2 - Values.FLOOR_HEIGHT;
		width = Values.PIPE_WIDTH;
		gapUpperY = height1;
		gapLowerY = height1 + Values.PIPE_GAP;
	}

	public void moveLeft() {
		x -= Values.PIPE_SPEED;
	}
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED;
	}
	
	public void collisionPossible(){
		if(x <= Values.FLAPPY_X_2 && x + Values.PIPE_WIDTH >= Values.FLAPPY_X)
			collisionPossible = true;
		else
			collisionPossible = false;
	}
	
	public boolean isCollisionPossible(){
		return collisionPossible;
	}
	
	public int getGapUpperY(){
		return gapUpperY;
	}
	
	public int getGapLowerY(){
		return gapLowerY;
	}

	public int getX() {
		return x;
	}

	public int getY1() {
		return y1;
	}

	public int getHeigth2() {
		return heigth2;
	}

	public int getWidth() {
		return width;
	}

	public int getY2() {
		return y2;
	}

	public int getHeigth1() {
		return height1;
	}
}
