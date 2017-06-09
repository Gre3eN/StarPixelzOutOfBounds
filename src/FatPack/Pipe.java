package FatPack;

import java.util.Random;

public class Pipe {

	protected Random randy = new Random();
	protected int x;
	protected int y1, y2;
	protected int height1, height2;
	protected int width;
	protected int gapY0, gapY1;

	public void moveLeft() {
		x -= Values.PIPE_SPEED;
	}
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED;
	}

	public int getX() {
		return x;
	}

	public int getY1() {
		return y1;
	}

	public int getHeigth2() {
		return height2;
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
	
	public int getGapY0() {
		return gapY0;
	}
	
	public int getGapY1() {
		return gapY1;
	}
}
