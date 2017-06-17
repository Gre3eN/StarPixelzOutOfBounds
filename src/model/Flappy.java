package model;

import java.awt.Rectangle;

import FatPack.Values;

public class Flappy {
	// TODO Flappy Sturzflug
	private int y;

	public Flappy() {
		y=Values.FRAME_HEIGHT / 2;
	}
	
	public void fall() {
		y+=Values.FLAPPY_FALL_HEIGHT;
	}
	
	public void jump() {
		y-=Values.FLAPPY_JUMP_HEIGHT;
	}
	
	public void jumpDown() {
		y+=Values.FLAPPY_JUMP_DOWN_HEIGHT;
	}
	
	public void teleport() {
		if (y + Values.FLAPPY_HEIGHT <= 0)
			teleDown();

		if (y >= Values.FRAME_HEIGHT)
			teleUp();
	}
	
	private void teleUp() {
		y=-Values.FLAPPY_HEIGHT/2;
	}
	
	private void teleDown() {
		y=Values.FRAME_HEIGHT - Values.FLAPPY_HEIGHT/2;
	}
	
	public int getY() {
		return y;
	}
	public Rectangle getRect() {
		return new Rectangle(Values.FLAPPY_X, y, Values.FLAPPY_WIDTH,Values.FLAPPY_HEIGHT);
	}
	public void reset() {
		y = Values.FRAME_HEIGHT / 2;
	}
}