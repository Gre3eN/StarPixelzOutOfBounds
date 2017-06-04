package Model;

import java.awt.Rectangle;
import java.util.ArrayList;

import MagicNumberWonderland.FlappyValues;
import MagicNumberWonderland.StarValues;

public class StarAuraManagement {

	private ArrayList<Star> stars;
	private ArrayList<Rectangle> auras0;
	private ArrayList<Rectangle> auras1;
	private ArrayList<Rectangle> auras2;
	
	public StarAuraManagement(ArrayList<Star> stars) {
		this.stars = stars;
		auras0=new ArrayList<>();
		auras1=new ArrayList<>();
		auras2=new ArrayList<>();
		calcAuras0();
		calcAuras1();
		calcAuras2();
	}

	public void update() {
		for (Rectangle r : auras0) {
			r.x -= StarValues.SPEED;
		}
		for (Rectangle r : auras1) {
			r.x -= StarValues.SPEED;
		}
		for (Rectangle r : auras2) {
			r.x -= StarValues.SPEED;
		}
		delete();
	}

	public void spawn(Star newStar) {

		calcAura0(newStar);
		calcAura1(newStar);
		calcAura2(newStar);
	}

	public void delete() {
		if (auras0.size() > 0) {
			for (int i = 0; i < auras0.size(); i++) {
				if (auras0.get(i).x < -auras0.get(i).width) {
					auras0.remove(i);
				}
			}
		}
		if (auras0.size() > 0) {
			for (int i = 0; i < auras1.size(); i++) {
				if (auras1.get(i).x < -auras1.get(i).width) {
					auras1.remove(i);
				}
			}
		}
		if (auras0.size() > 0) {
			for (int i = 0; i < auras2.size(); i++) {
				if (auras2.get(i).x < -auras2.get(i).width) {
					auras2.remove(i);
				}
			}
		}

	}

	public void reset(ArrayList<Star> newStars) {
		auras0.clear();
		auras1.clear();
		auras2.clear();

		calcAuras0();
		calcAuras1();
		calcAuras2();
	}

	public void flappyCharge() {
		for (int i = 0; i < auras0.size(); i++) {
			auras0.get(i).x -= FlappyValues.CHARGE_SPEED / 2;
		}
		for (int i = 0; i < auras1.size(); i++) {
			auras1.get(i).x -= FlappyValues.CHARGE_SPEED / 2;
		}
		for (int i = 0; i < auras2.size(); i++) {
			auras2.get(i).x -= FlappyValues.CHARGE_SPEED / 2;
		}

	}

	private void calcAuras0() {
		if(stars.size()>0) {
			for (int i = 0; i < stars.size(); i++) {
				Rectangle star_rec = stars.get(i).getStarRec();
				auras0.add(new Rectangle(
						(int) star_rec.x - star_rec.width / 2, 
						(int) star_rec.y - star_rec.height / 2,
						(int) star_rec.width * 2, 
						(int) star_rec.height * 2));
			}			
		}
	}

	private void calcAuras1() {
		for (int i = 0; i < stars.size(); i++) {
			Rectangle star_rec = stars.get(i).getStarRec();
			auras1.add(new Rectangle((int) star_rec.x - star_rec.width, (int) star_rec.y - star_rec.height,
					(int) star_rec.width * 3, (int) star_rec.height * 3));
		}
	}

	private void calcAuras2() {
		for (int i = 0; i < stars.size(); i++) {
			Rectangle star_rec = stars.get(i).getStarRec();
			auras2.add(new Rectangle((int) star_rec.x - star_rec.width * 2, (int) star_rec.y - star_rec.height * 2,
					(int) star_rec.width * 5, (int) star_rec.height * 5));
		}
	}

	
	
	
	
	private void calcAura0(Star star) {
		Rectangle star_rec = star.getStarRec();
		auras0.add(new Rectangle((int) star_rec.x - star_rec.width / 2, (int) star_rec.y - star_rec.height / 2,
				(int) star_rec.width * 2, (int) star_rec.height * 2));
	}

	private void calcAura1(Star star) {
		Rectangle star_rec = star.getStarRec();
		auras1.add(new Rectangle((int) star_rec.x - star_rec.width, (int) star_rec.y - star_rec.height,
				(int) star_rec.width * 3, (int) star_rec.height * 3));
	}

	private void calcAura2(Star star) {
		Rectangle star_rec = star.getStarRec();
		auras2.add(new Rectangle((int) star_rec.x - star_rec.width * 2, (int) star_rec.y - star_rec.height * 2,
				(int)star_rec.width * 5, (int)star_rec.height * 5));
	}

	public ArrayList<Rectangle> getAuras0() {
		return auras0;
	}

	public ArrayList<Rectangle> getAuras1() {
		return auras1;
	}

	public ArrayList<Rectangle> getAuras2() {
		return auras2;
	}
}
