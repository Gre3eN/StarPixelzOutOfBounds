package model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Random;

import FatPack.CollectableValues;
import FatPack.Values;

public class Collectable extends Observable {

	private Random randy;
	AffineTransform transform;

	private Rectangle hitBox;
	private Shape rotatingCore, rotatingCoreHolder;
	private int x, y, core_X2,anchorx, anchory;
	private boolean isInDangerZone;

	public Collectable() {
		randy = new Random();
		transform = new AffineTransform();

		x = Values.FRAME_WIDTH;
		y = randy.nextInt(Values.FRAME_HEIGHT - 200) + 50;
		hitBox = new Rectangle(x, y, CollectableValues.WIDTH, CollectableValues.HEIGHT);

		rotatingCore = (Shape) hitBox;
		rotatingCoreHolder = (Shape) hitBox;

		anchorx = hitBox.x + hitBox.width / 2;
		anchory = hitBox.y + hitBox.height / 2;

		isInDangerZone = false;
	}

	public void update() {
		moveLeft();
		rotate();
		inDangerZone();
	}

	private void moveLeft() {
		transform.setToTranslation(-CollectableValues.SPEED, 0);
		rotatingCore = transform.createTransformedShape(rotatingCoreHolder);
		rotatingCoreHolder = rotatingCore;

		hitBox.x -= CollectableValues.SPEED;
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

		hitBox.x -= Values.FLAPPY_CHARGE_SPEED;
		anchorx -= Values.FLAPPY_CHARGE_SPEED;
	}

	private void inDangerZone() {
		if (hitBox.x <= Values.FLAPPY_X2) {
			core_X2 = hitBox.x + hitBox.width;
			
			if (core_X2 >= Values.FLAPPY_X) {
				setChanged();
				notifyObservers(hitBox);
			}
		}
	}

	public Shape getRotatingCore() {
		return rotatingCore;
	}

	public Rectangle getCore() {
		return hitBox;
	}

	public Collectable getCollectable() {
		return this;
	}

	public boolean getDanger() {
		return isInDangerZone;
	}
}
