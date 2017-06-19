package model;

import model.Pipe;
import FatPack.Values;

public class KnowingPipe extends Pipe{
	
	private boolean up = false;
	private boolean down = false;

	public KnowingPipe() {
		x = Values.FRAME_WIDTH;
		
		gapY0 = Values.KNOWPIPES_START_GAP_Y;
		gapY1 = Values.KNOWPIPES_START_GAP_Y + Values.KNOWPIPES_START_GAP + Values.KNOWPIPES_MID_HEIGHT;
		gapCount = 2;
		gap = new int[gapCount];
		gapHeight = new int[gapCount];
		gap[0] = gapY0;
		gapHeight[0] = Values.KNOWPIPES_START_GAP;
		gap[1] = gapY1;
		gapHeight[1] = Values.KNOWPIPES_START_GAP;
		System.out.println(gap[1]);
	}
	
	public void animation(){
		if(up){
			upAnimation();
		}
		if(down){
			downAnimation();
		}
		gapHeight[0] += Values.KNOWPIPES_FALL_SPEED;
		gap[1] += Values.KNOWPIPES_FALL_SPEED;
		gapHeight[1] -= Values.KNOWPIPES_FALL_SPEED;
	}
	
	private void upAnimation(){
		gapHeight[0] -= Values.KNOWPIPES_UP_SPEED;
		gap[1] -= Values.KNOWPIPES_UP_SPEED;
		gapHeight[1] += Values.KNOWPIPES_UP_SPEED;
	}
	
	private void downAnimation(){
		gapHeight[0] += Values.KNOWPIPES_DOWN_SPEED;
		gap[1] += Values.KNOWPIPES_DOWN_SPEED;
		gapHeight[1] -= Values.KNOWPIPES_DOWN_SPEED;
	}
	
	public void setKeyActions(boolean up, boolean down){
		this.up = up;
		this.down = down;
	}
}
