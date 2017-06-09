package FatPack;

public class NormalPipe extends Pipe{
	
	public NormalPipe() {
		x = Values.FRAME_WIDTH;
		y1 = 0;
		height1 = randy
				.nextInt(Values.FRAME_HEIGHT - Values.PIPE_GAP - 2* Values.MIN_PIPE_HEIGHT)
				+ Values.MIN_PIPE_HEIGHT;
		y2 = height1 + Values.PIPE_GAP;
		height2 = Values.FRAME_HEIGHT - y2;
		width = Values.PIPE_WIDTH;
		
		gapCount = 1;
		gap = new int[gapCount];
		gapHeight = new int[gapCount];
		gap[0] = height1;
		gapHeight[0] = Values.PIPE_GAP;
	}

}
