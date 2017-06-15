package model;

import FatPack.Values;

public class OuterOval extends Oval{	
	
	public OuterOval(int y) {
		this.y = y;
		transparency = Values.FIRST_OVAL_START_TRANSPERENCY;
	}
	
	public void expand() {
		size += Values.FIRST_OVAL_EXPAND_RATE;
		x -= Values.FIRST_OVAL_EXPAND_RATE / 2;
		y -= Values.FIRST_OVAL_EXPAND_RATE / 2;
		if(transparency > 0)
			transparency -= Values.OUTER_OVAL_TRANSPERENCY_LOSS;
	}
}