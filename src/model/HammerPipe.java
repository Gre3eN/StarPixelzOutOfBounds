package model;

import model.Pipe;
import FatPack.Values;

public class HammerPipe extends Pipe{
	
	private boolean open;

	public HammerPipe() {
		x = Values.FRAME_WIDTH;
		
		gapY0 = Values.FRAME_HEIGHT / 2 - Values.HAMMERPIPE_START_GAP / 2;
		gapCount = 1;
		gap = new int[gapCount];
		gapHeight = new int[gapCount];
		gap[0] = gapY0;
		gapHeight[0] = Values.HAMMERPIPE_START_GAP;
		open = true;
	}
	
	public void animation(){
		if (open){
			gap[0] += Values.HAMMERPIPE_MOVE_TOGETHER;
			gapHeight[0] -= 2 * Values.HAMMERPIPE_MOVE_TOGETHER;
			
			if (gapHeight[0] <= 0)
				open = false;
		}
		if (!open){
			gap[0] -= Values.HAMMERPIPE_MOVE_APART;
			gapHeight[0] += 2 * Values.HAMMERPIPE_MOVE_APART;
			
			if (gapHeight[0] >= Values.HAMMERPIPE_START_GAP)
				open = true;
		}
	}	
}
