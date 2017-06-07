package FatPack;
//package FlappyBird;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.w3c.dom.css.Rect;

public class Flappy {

	private int y;
	
	public Flappy() {
		y=Values.FRAME_HEIGHT / 2;
	}
	//TODO flappy Sturzflug
	
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