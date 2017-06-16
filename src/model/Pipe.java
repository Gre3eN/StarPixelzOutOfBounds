package model;

import java.util.ArrayList;
import java.util.Random;

import FatPack.Values;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Pipe {

	protected Random randy;
	protected int x;
	protected int gapY0, gapY1, gapY2;
	protected int gapCount;
	protected int[] gap;
	protected int[] gapHeight;

	public Pipe() {
		randy = new Random();
	}

	protected Shape fullPipeShape() {
		return new Rectangle2D.Double(x, 0, Values.PIPE_WIDTH, Values.FRAME_HEIGHT);
	}

	protected ArrayList<Shape> gapShape() {
		ArrayList<Shape> gaps = new ArrayList<>();
		for (int i = 0; i < gapCount; i++) {
			gaps.add(new Rectangle2D.Double(x, gap[i], Values.PIPE_WIDTH, gapHeight[i]));
		}
		return gaps;
	}

	public Shape pipeShape() {
		Area area = new Area(fullPipeShape());
		for (Shape s : gapShape())
			area.subtract(new Area(s));
		return area;
	}

	public void moveLeft() {
		x -= Values.PIPE_SPEED;
	}

	public void flappyCharge() {
		x -= Values.FLAPPY_CHARGE_SPEED;
	}

	public int getX() {
		return x;
	}

	public int[] getGaps() {
		return gap;
	}

	public int getGapCount() {
		return gapCount;
	}
}