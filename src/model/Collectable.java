package model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Random;
import FatPack.Values;

/**
 * This class Represents a rotating Rectangular Shape. <br>
 * <br>
 * With main attributes: <br>
 * "Rectangle : hitbox" and <br>
 * "Shape : rotatingCore".
 * 
 * <br>
 * <br>
 * AffineTransform with setToTranslation() and setToRotation is used.
 *
 * @see Values
 * @see CollectableManager
 * @see AffineTransform
 *
 * @author philippsteinke
 */
public class Collectable extends Observable {

	private Random randy;
	AffineTransform transform;

	private Rectangle hitBox;
	private Shape rotatingCore, rotatingCoreHolder;
	private int x, y, core_X2, anchorx, anchory;

	/**
	 * A Collectable will be created at the very right side of the screen with
	 * Random y value and static width/height.
	 * 
	 * <br>
	 * A Rectangle as hitBox/Collider will be created. <br>
	 * Anchors x&y will be created to Transform the Shape to origin, before
	 * rotating
	 * 
	 * 
	 */
	public Collectable() {
		randy = new Random();
		transform = new AffineTransform();

		x = Values.FRAME_WIDTH;
		y = randy.nextInt(Values.FRAME_HEIGHT - 200) + 50;
		hitBox = new Rectangle(x, y, Values.C_WIDTH, Values.C_HEIGHT);

		rotatingCore = (Shape) hitBox;
		rotatingCoreHolder = (Shape) hitBox;

		anchorx = hitBox.x + hitBox.width / 2;
		anchory = hitBox.y + hitBox.height / 2;
	}

	/**
	 * Updates the Collectable -> calls private Methods: <br>
	 * moveLeft() which translates the Shape for a static Value and also moves
	 * the Hitbox. <br>
	 * rotate() which rotates the Shape by 45° counterClockWise . <br>
	 * inDangerZone() which checks if the HitBox is in "Flappy´s x-Range" and if
	 * so: notifyes Observer, to handle Collision further.
	 * 
	 * @see Values
	 */
	public void update() {
		moveLeft();
		rotate();
		inDangerZone();
	}

	private void moveLeft() {
		transform.setToTranslation(-Values.C_SPEED, 0);
		rotatingCore = transform.createTransformedShape(rotatingCoreHolder);
		rotatingCoreHolder = rotatingCore;

		hitBox.x -= Values.C_SPEED;
		anchorx -= Values.C_SPEED;
	}

	private void rotate() {
		transform.setToRotation(45, anchorx, anchory);
		rotatingCore = transform.createTransformedShape(rotatingCoreHolder);
		rotatingCoreHolder = rotatingCore;
	}

	/**
	 * Charges the Collectable for a static Value to the left.
	 * 
	 * @see Values
	 */
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

}
