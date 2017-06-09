package FatPack;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Pipe {
	
	protected Random randy;
	protected int x;
	protected int y1, y2;
	protected int height1, height2;
	protected int width;
	protected int gapCount;
	protected int[] gap;
	protected int[] gapHeight;
	ArrayList<Shape> gaps;
	
	public Pipe(){
		gaps = new ArrayList<Shape>();
		randy = new Random();
	}
	
	protected ArrayList<Shape> gapShape(){
		gaps.clear();
		for (int i=0; i < gapCount; i++){
			gaps.add(new Rectangle2D.Double(x, gap[i], width, gapHeight[i]));
		}
		return gaps;
	}

	public void moveLeft() {
		x -= Values.PIPE_SPEED;
	}
	
	public void flappyCharge(){
		x -= Values.FLAPPY_CHARGE_SPEED;
	}

	public int getX() {
		return x;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	public int getHeight1() {
		return height1;
	}
	
	public int getGapCount() {
		return gapCount;
	}
	
	public int getHeight2() {
		return height2;
	}

	public int getWidth() {
		return width;
	}
}
