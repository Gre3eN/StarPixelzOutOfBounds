package FatPack;

import java.util.ArrayList;

public class OvalManagement {

	private ArrayList<Oval> ovals = new ArrayList<>();
	private int[] rgb;
	
	
	public void setRGB(int[] rgb){
		this.rgb = rgb;
	}
	
	public ArrayList<Oval> getOvals(){
		return ovals;
	}

	public ArrayList<Oval> update() {
		for (int i=0; i<ovals.size(); i++) {
			ovals.get(i).expand();
		}
		deleteOval();
		return ovals;
	}

	public void spawnOval(int x, int y) {
		for(int i=1; i<=Values.OVAL_COUNT; i++)
			ovals.add(new Oval(x, y, i, rgb[0], rgb[1], rgb[2]));
	}

	private void deleteOval() {
		if (ovals.get(0).getWidth() >= Values.OVAL_EXPAND_TIME * Values.FIRST_OVAL_EXPAND_RATE) 
			ovals.remove(0);
		if (ovals.size() > Values.OVALS_CAP){
			ovals.remove(0);
			ovals.remove(0);
		}
	}
	
	public void flappyCharge() {
		for (int i = 0; i < ovals.size(); i++) {
			ovals.get(i).flappyCharge();
		}
	}

	public void reset() {
		ovals.clear();
	}
}
