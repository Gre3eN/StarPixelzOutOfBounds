package FatPack;

import java.awt.Shape;
import java.util.ArrayList;

public class Flappy {
	
	private int y;
	
	public Flappy() {
		y=Values.FLAPPY_Y;
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
	
	public int getY() {
		return y;
	}
	
	public void reset() {
		y = Values.FRAME_HEIGHT / 2;
	}
}