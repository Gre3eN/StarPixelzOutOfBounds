package FatPack;
//package FlappyBird;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.w3c.dom.css.Rect;

public class Flappy {

	private int y;
	private int fallMultiplyer = 0;
	
	public Flappy() {
		y=Values.FRAME_HEIGHT / 2;
	}
	//TODO flappy Sturzflug
	
	public void fall() {
		y = y + Values.FLAPPY_FALL_HEIGHT + fallMultiplyer;
		fallMultiplyer += Values.FLAPPY_FALL_HEIGHT / 10;
	}
	public void jump() {
		
		y-=Values.FLAPPY_JUMP_HEIGHT;
		fallMultiplyer = 0;
	}
	
	public boolean frameCollision(){
		if(y < 0 || y > Values.FRAME_HEIGHT)
			return true;
		else return false;
	}
	
	public int getY() {
		return y;
	}
	
	public void reset() {
		y = Values.FRAME_HEIGHT / 2;
	}
}