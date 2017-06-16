package model;

import FatPack.Values;

public class InnerOval extends Oval{

	public InnerOval(int y) {
		this.y = y;
		transparency = Values.INNER_OVAL_START_TRANSPERENCY;
	}
	
	public void expand() {
		size += Values.INNER_OVAL_EXPAND_RATE;
		x -= Values.INNER_OVAL_EXPAND_RATE / 2;
		y -= Values.INNER_OVAL_EXPAND_RATE / 2;
		if(transparency > 0)
			transparency -= Values.OUTER_OVAL_TRANSPERENCY_LOSS;
	}
}
