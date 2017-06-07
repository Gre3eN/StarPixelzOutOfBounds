package FatPack;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.sun.javafx.geom.Rectangle;

public class Collectable extends Observable {

	private ArrayList<Rectangle> aura;
	private Rectangle core, hitbox;
	private Shape rotatingCore, holder;
	private int y, anchorx, anchory;
	private Random randy;
	AffineTransform transformer;

	public Collectable() {
		randy = new Random();
		transformer = new AffineTransform();

		y = randy.nextInt(Values.FRAME_HEIGHT - 100) + 50;
		core = new Rectangle(Values.FRAME_WIDTH, y, CollectableValues.WIDTH, CollectableValues.HEIGHT);

		transformer.setToRotation(45, anchorx, anchory);

		rotatingCore = (Shape) new Rectangle(core);
		holder = rotatingCore;

		hitbox = new Rectangle(core);
	}

}
