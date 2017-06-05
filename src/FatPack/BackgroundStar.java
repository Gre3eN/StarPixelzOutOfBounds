package FatPack;

public class BackgroundStar {
	
	private int x, y, size, layer, originalSize;
	private int transparency = 255;
	
	public BackgroundStar(int x, int y, int layer, int size){
		this.layer = layer;
		this.x = x - (size * (layer * layer * Values.BACKGROUND_STAR_LAYER_INTERVALL)/4);
		this.y = y - (size * (layer * layer * Values.BACKGROUND_STAR_LAYER_INTERVALL)/4);
		originalSize = size;
			
		if(layer != 0){
			this.size = size * (layer * layer * Values.BACKGROUND_STAR_SIZE_INTERVALL)/4;
			transparency /= layer * 4;
		}
		
		System.out.println("layer" +layer+ "x" +this.x+ "y" +this.y+ "size" +this.size+ "t" +transparency);
	}
	
	public void moveLeft(){
		x -= Values.BACKGROUND_STAR_SPEED * (originalSize / Values.BACKGROUND_STAR_SIZE_SPEED_MODIFICATION);
	}
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED * (originalSize / Values.BACKGROUND_STAR_SIZE_SPEED_MODIFICATION);
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
