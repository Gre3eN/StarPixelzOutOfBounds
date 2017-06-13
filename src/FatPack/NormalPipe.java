package FatPack;

public class NormalPipe extends Pipe{
	
	public NormalPipe() {
		x = Values.FRAME_WIDTH;
		gapY0 = randy
				.nextInt(Values.FRAME_HEIGHT - Values.PIPE_GAP - 2* Values.MIN_PIPE_HEIGHT)
				+ Values.MIN_PIPE_HEIGHT;
		
		gapCount = 1;
		gap = new int[gapCount];
		gapHeight = new int[gapCount];
		gap[0] = gapY0;
		gapHeight[0] = Values.PIPE_GAP;
	}

}