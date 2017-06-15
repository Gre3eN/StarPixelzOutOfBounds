package model;

import FatPack.Values;

public class InnerOval extends Oval{

	public InnerOval(int y) {
		this.y = y;
		transparency = Values.FOURTH_OVAL_START_TRANSPERENCY;
	}
	
	public void expand() {
		size += Values.FOURTH_OVAL_EXPAND_RATE;
		x -= Values.FOURTH_OVAL_EXPAND_RATE / 2;
		y -= Values.FOURTH_OVAL_EXPAND_RATE / 2;
		if(transparency > 0)
			transparency -= Values.OUTER_OVAL_TRANSPERENCY_LOSS;
	}
}
