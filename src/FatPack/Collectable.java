package FatPack;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.sun.javafx.geom.Rectangle;

public class Collectable extends Observable {

	private Random randy;
	AffineTransform transform;


	private Rectangle core;
	private Shape rotatingCore, rotatingCoreHolder;
	private int x, y, anchorx, anchory;
	private boolean isInDangerZone;
	
	public Collectable() {
		randy = new Random();
		transform = new AffineTransform();

		x = Values.FRAME_WIDTH;
		y = randy.nextInt(Values.FRAME_HEIGHT - 100) + 50;
		core = new Rectangle(x, y, CollectableValues.WIDTH, CollectableValues.HEIGHT);

		rotatingCore = (Shape) new Rectangle(core);
		rotatingCoreHolder = (Shape) new Rectangle(core);

		anchorx = core.x + core.width / 2;
		anchory = core.y + core.height / 2;
		
		isInDangerZone=false;
	}

	public void update() {
		moveLeft();
		rotate();
		inDangerZone();
	}

	public void moveLeft() {
		transform.setToTranslation(-CollectableValues.SPEED, 0);
		rotatingCore = transform.createTransformedShape(rotatingCoreHolder);
		rotatingCoreHolder = rotatingCore;

		core.x -= CollectableValues.SPEED;
		anchorx -= CollectableValues.SPEED;
	}

	private void rotate() {
		transform.setToRotation(45, anchorx, anchory);
		rotatingCore = transform.createTransformedShape(rotatingCoreHolder);
		rotatingCoreHolder = rotatingCore;
	}

	public void charge() {
		transform.setToTranslation(-Values.FLAPPY_CHARGE_SPEED, 0);
		rotatingCore = transform.createTransformedShape(rotatingCoreHolder);
		rotatingCoreHolder = rotatingCore;

		core.x -= Values.FLAPPY_CHARGE_SPEED;
		anchorx -= Values.FLAPPY_CHARGE_SPEED;
	}

	private void inDangerZone() {
		if (core.x <= Values.FLAPPY_X2) {
			int core_X2 = core.x + core.width;
			if (core_X2 >= Values.FLAPPY_X) {
				isInDangerZone=true;
				setChanged();
				notifyObservers();
			}
		}
	}

	public Shape getRotatingCore() {
		return rotatingCore;
	}

	public Rectangle getCore() {
		return core;
	}

	public Collectable getCollectable() {
		return this;
	}

	public boolean getDanger() {
		return isInDangerZone;
	}
}
