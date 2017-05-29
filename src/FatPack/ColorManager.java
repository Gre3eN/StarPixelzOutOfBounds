package FatPack;

import java.awt.Color;

public class ColorManager {
	
	private int r = 255;
	private int g = 0;
	private int b = 0;
	
	public void changeColor(){
		if(r == 255 && g == 0 && b != 255) b += Values.OVAL_COLOR_CHANGE_RATE;
		if(r != 0 && g == 0 && b == 255) r -= Values.OVAL_COLOR_CHANGE_RATE;
		if(r == 0 && g != 255 && b == 255) g += Values.OVAL_COLOR_CHANGE_RATE;
		if(r == 0 && g == 255 && b != 0) b -= Values.OVAL_COLOR_CHANGE_RATE;
		if(r != 255 && g == 255 && b == 0) r += Values.OVAL_COLOR_CHANGE_RATE;
		if(r == 255 && g != 0 && b == 0) g -= Values.OVAL_COLOR_CHANGE_RATE;
	}
	
	public Color getColor(){
		return new Color(r, g, b);
	}
	
	public int[] getRGB(){
		int[] rgb = {r, g, b};
		return rgb;
	}
}
