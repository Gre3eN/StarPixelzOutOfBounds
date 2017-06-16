package model;

import FatPack.Values;

public class MidOval extends Oval{
	
	public MidOval(int y) {
		this.y = y;
		transparency = Values.MID_OVAL_START_TRANSPERENCY;
	}
	
	public void expand() {
		size += Values.MID_OVAL_EXPAND_RATE;
		x -= Values.MID_OVAL_EXPAND_RATE / 2;
		y -= Values.MID_OVAL_EXPAND_RATE / 2;
		if(transparency > 0)
			transparency -= Values.INNER_OVAL_TRANSPERENCY_LOSS;
	}
}
