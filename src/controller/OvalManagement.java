package controller;

import java.util.ArrayList;

import FatPack.Values;
import model.InnerOval;
import model.MidOval;
import model.OuterOval;
import model.Oval;

public class OvalManagement {

	private ArrayList<Oval> ovals = new ArrayList<>();
	
	public ArrayList<Oval> getOvals(){
		return ovals;
	}

	public void update() {
		for (int i=0; i<ovals.size(); i++) {
			ovals.get(i).expand();
		}
		deleteOval();
	}

	public void spawnOval(int flappyY) {
		ovals.add(new MidOval(flappyY));
		ovals.add(new OuterOval(flappyY));
		ovals.add(new InnerOval(flappyY));
	}

	private void deleteOval() { 
		if (ovals.get(0).getTransparency() <= 0)
			ovals.remove(0);
		if (ovals.size() > Values.OVALS_CAP) {
			ovals.remove(0);
			ovals.remove(0);
			ovals.remove(0);
		}
		if (ovals.size() > 45)
			ovals.clear();
	}
	
	public void flappyCharge() {
		for (int i = 0; i < ovals.size(); i++)
			ovals.get(i).flappyCharge();
	}

	public void reset() {
		ovals.clear();
	}
}