package FatPack;

import java.awt.Color;
import java.sql.Array;

public class Oval {

	private int x, y, height, width, oval, ovalExpandRate, r, g, b;
	private int transperency = 0;
	private boolean isInnerOval, isOuterOval;
	
	public Oval(int y, int oval, int r, int g, int b) {
		this.x = Values.FLAPPY_X;
		this.y = y;
		this.oval = oval;
		this.r = r;
		this.g = g;
		this.b = b;
		height = Values.OVAL_HEIGHT;
		width = Values.OVAL_WIDTH;
		
		setOvalKind();
	}
	
	private void setOvalKind(){
		if(oval == 1){
			isOuterOval = true;
			ovalExpandRate = Values.FIRST_OVAL_EXPAND_RATE;
			transperency = Values.FIRST_OVAL_START_TRANSPERENCY;
		}
		if(oval == 2){
			isInnerOval = true;
			ovalExpandRate = Values.SECOND_OVAL_EXPAND_RATE;
			transperency = Values.SECOND_OVAL_START_TRANSPERENCY;
		}
		if(oval == 3){
			ovalExpandRate = Values.THIRD_OVAL_EXPAND_RATE;
			transperency = Values.THIRD_OVAL_START_TRANSPERENCY;
			this.r = 0; this.g = 0; this.b = 0;
		}
		if(oval == 4){
			isOuterOval = true;
			ovalExpandRate = Values.FOURTH_OVAL_EXPAND_RATE;
			transperency = Values.FOURTH_OVAL_START_TRANSPERENCY;
			
		}
		if(oval == 5){
			ovalExpandRate = Values.FIFTH_OVAL_EXPAND_RATE;
			transperency = Values.FIFTH_OVAL_START_TRANSPERENCY;
			this.r = 0; this.g = 0; this.b = 0;
		}
	}
	
	public void expand() {
		height += ovalExpandRate;
		width += ovalExpandRate;
		x -= ovalExpandRate / 2;
		y -= ovalExpandRate / 2;
		if(transperency > 0){
			if(isOuterOval)
				transperency -= Values.OUTER_OVAL_TRANSPERENCY_LOSS;
			if(isInnerOval)
				transperency -= Values.INNER_OVAL_TRANSPERENCY_LOSS;
		}
	}
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED;
	}
	
	public Color getOvalColor(){
		if(transperency < 0)transperency = 0;
		return new Color(r, g, b, transperency);
	}
	
	public int[] getOval(){
		int[] oval = {x, y, width, height};
		return oval;
	}
	
	public int getWidth(){
		return width;
	}
}
