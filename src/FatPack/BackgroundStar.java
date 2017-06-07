package FatPack;

public class BackgroundStar {
	
	private int x, y, size, originalSize, layer;
	private int transparency = 255;
	
	public BackgroundStar(int x, int y, int layer, int size){
		originalSize = size;
		this.size = size * layer;
		this.x = x - this.size / 2;
		this.y = y - this.size / 2;
		this.layer = layer;
		
		if(layer > 1){
			transparency /= layer * 5;
		}	
		
		System.out.println("layer" +layer+ "x" +this.x+ "y" +this.y+ "size" +this.size+ "t" +transparency);
	}
	
	public void moveLeft(){
		x -= Values.BACKGROUND_STAR_SPEED + originalSize;
	}
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED * originalSize / 45;
	}
	
	public boolean isDeadStar(){
		if(x < 0)
			return true;
		else return false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getTransparency(){
		return transparency;
	}
	
	public int getSize(){
		return size;
	}
	
}
