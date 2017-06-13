package FatPack;

public class Flappy {
	// TODO Flappy Sturzflug
	private int y;
	
	public Flappy() {
		y=Values.FRAME_HEIGHT / 2;
	}
	
	public void fall() {
		y+=Values.FLAPPY_FALL_HEIGHT;
	}
	
	public void jump() {
		y-=Values.FLAPPY_JUMP_HEIGHT;
	}
	
	public void jumpDown() {
		y+=Values.FLAPPY_JUMP_DOWN_HEIGHT;
	}
	
	public void teleUp() {
		y=-Values.FLAPPY_HEIGHT/2;
	}
	
	public void teleDown() {
		y=Values.FRAME_HEIGHT - Values.FLAPPY_HEIGHT/2;
	}
	
	public int getY() {
		return y;
	}
	
	public void reset() {
		y = Values.FRAME_HEIGHT / 2;
	}
}