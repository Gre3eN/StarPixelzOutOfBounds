package model;

import FatPack.Values;

public class CometTail extends Oval{	
	
	public CometTail(int x, int y) {
		this.x = x;
		this.y = y;
		transparency = Values.COMETTAIL_START_TRANSPARENCY;
		size = Values.C_HEIGHT;
	}
	
	public void expand() {
		size += Values.COMETTAIL_EXPAND_RATE;
		x -= Values.COMETTAIL_EXPAND_RATE / 2;
		y -= Values.COMETTAIL_EXPAND_RATE / 2;
		if(transparency > 0)
			transparency -= Values.COMETTAIL_TRANSPARENCY_LOSS;
	}
}