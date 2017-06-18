package model;

import model.Pipe;
import FatPack.Values;

public class ConfusingPipe extends Pipe{
	
	private double closingSpeed;

	public ConfusingPipe() {
		x = Values.FRAME_WIDTH;
		closingSpeed = Values.CONFUSPIPES_START_SPEED;
		
		gapY0 = Values.FRAME_HEIGHT / 2 + Values.FRAME_HEIGHT / 6;
		gapY1 = Values.FRAME_HEIGHT / 10;
		gapCount = 1;
		gap = new int[gapCount];
		gapHeight = new int[gapCount];
		gap[0] = gapY0;
		gapHeight[0] = Values.CONFUSPIPES_GAP;
	}
	
	public void animation(){
		gapHeight[0] -= closingSpeed;
		closingSpeed += Values.CONFUSPIPES_CLOSING_SPEED;
		if (gapHeight[0] <= Values.FLAPPY_HEIGHT && gap[0] == gapY0){
			gap[0] = gapY1;
			gapHeight[0] = Values.CONFUSPIPES_GAP;
			closingSpeed = Values.CONFUSPIPES_START_SPEED;
		}
		if (gapHeight[0] <= Values.FLAPPY_HEIGHT && gap[0] == gapY1){
			gap[0] = gapY0;
			gapHeight[0] = Values.CONFUSPIPES_GAP;
			closingSpeed = Values.CONFUSPIPES_START_SPEED;
		}
	}
}
