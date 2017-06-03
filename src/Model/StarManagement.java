package Model;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import MagicNumberWonderland.StarValues;

public class StarManagement implements Manager {

	private StarAuraManagement auraManagement;
	private ArrayList<Star> stars;
	private ArrayList<Shape> starShapes;
	//jo
	private int starScore;

	public StarManagement() {
		stars = new ArrayList<>();
		starShapes = new ArrayList<>();
		starScore = 0;

		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			stars.add(new Star("somewhere"));
		}
		auraManagement = new StarAuraManagement(stars);
	}

	@Override
	public void update() {
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).rotate();
			stars.get(i).moveLeft();
		}
		auraManagement.update();
		spawn();
		delete();
	}

	@Override
	public void spawn() {
		if (stars.size() < StarValues.MAX_COUNT) {
			Star newStar = new Star("east");
			stars.add(newStar);
			auraManagement.spawn(newStar);
		}
	}

	@Override
	public void delete() {
		if (stars.size() > 0) {
			for (int i = 0; i < stars.size(); i++) {
				if (stars.get(i).getX() < 0) {
					stars.remove(i);
				}
			}
		}

	}

	@Override
	public void reset() {
		stars.clear();
		starShapes.clear();
		starScore = 0;
		for (int i = 0; i < StarValues.INITIAL_COUNT; i++) {
			stars.add(new Star("somewhere"));
		}
		auraManagement.reset(stars);
	}

	public int getScore() {
		return starScore;
	}

	@Override
	public void flappyCharge() {
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).flappyCharge();
		}
		auraManagement.flappyCharge();
	}

	public ArrayList<Shape> getStarShapes() {
		starShapes.clear();
		for (int i = 0; i < stars.size(); i++) {
			starShapes.add(stars.get(i).getStarShape());
		}
		return starShapes;
	}
	
	public ArrayList<Rectangle> getAuras0(){
		return auraManagement.getAuras0();
	}
	public ArrayList<Rectangle> getAuras1(){
		return auraManagement.getAuras1();
	}
	public ArrayList<Rectangle> getAuras2(){
		return auraManagement.getAuras2();
	}

}
