package model;

import FatPack.Values;

public class OuterOval extends Oval{	
	
	public OuterOval(int y) {
		this.y = y;
		transparency = Values.OUTER_OVAL_START_TRANSPERENCY;
		x = Values.FLAPPY_X;
		size = Values.OVAL_HEIGHT;
	}
	
	public void expand() {
		size += Values.OUTER_OVAL_EXPAND_RATE;
		x -= Values.OUTER_OVAL_EXPAND_RATE / 2;
		y -= Values.OUTER_OVAL_EXPAND_RATE / 2;
		if(transparency > 0)
			transparency -= Values.OUTER_OVAL_TRANSPERENCY_LOSS;
	}
}