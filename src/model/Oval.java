package model;

import FatPack.Values;

public class Oval {
	
	protected int transparency, y;
	protected int x = Values.FLAPPY_X;
	protected int size = Values.OVAL_HEIGHT;
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED;
	}
	
	public void expand(){}
	
	public int getTransparency(){
		if(transparency < 0) transparency = 0;
		return transparency;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getSize(){
		return size;
	}
}