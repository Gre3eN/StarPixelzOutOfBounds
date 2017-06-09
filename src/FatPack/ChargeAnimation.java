package FatPack;

public class ChargeAnimation {
	
	private int transparency, transparencyLoss, x;
	
	public ChargeAnimation(int rectangle){
		x = Values.FLAPPY_X - Values.FLAPPY_ANIMATION_RANGE_RATE * rectangle;
		transparency = Values.FLAPPY_ANIMATION_START_TRANSPARENCY / rectangle;
		transparencyLoss = transparency / Values.FLAPPY_ANIMATION_TIME +1;
	}
	
	public void update(){
		transparency -= transparencyLoss;
	}
	
	public int getTransparency(){
		if (transparency < 0) transparency = 0;
		return transparency;
	}
	
	public int getX(){
		return x;
	}
}
