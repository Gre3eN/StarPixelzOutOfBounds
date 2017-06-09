package FatPack;

import java.util.Random;

public class Pipe {

	private Random randy = new Random();
	private int x;
	private int y1, y2;
	private int heigth1, heigth2;
	private int width;

	public Pipe() {
		x = Values.FRAME_WIDTH;
		y1 = 0;
		heigth1 = randy.nextInt(Values.FRAME_HEIGHT - Values.PIPE_GAP - 2* Values.MIN_PIPE_HEIGHT)
				+ Values.MIN_PIPE_HEIGHT;
		y2 = heigth1 + Values.PIPE_GAP;
		heigth2 = Values.FRAME_HEIGHT - y2;
		width = Values.PIPE_WIDTH;
	}

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
	
	public int getY2() {
		return y2;
	}

	public int getHeigth1() {
		return heigth1;
	}
	
	public int getHeigth2() {
		return heigth2;
	}

	public int getWidth() {
		return width;
	}
}
