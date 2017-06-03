package Model;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Random;

//jo
import MagicNumberWonderland.FlappyValues;
import MagicNumberWonderland.GeneralValues;
import MagicNumberWonderland.StarValues;

public class Star {

	private Random randy = new Random();
	private AffineTransform transform = new AffineTransform();

	private Rectangle star_rec;

	private Shape star_shape;
	private Shape star_holder;

	// to translate canter to 0,0
	private int star_anchorX;
	private int star_anchorY;

	private int x, y;

	public Star(String where) {

		if (where.equals("somewhere")) {
			//TODO bisschen hübsch auf oktanten verteilen
			x = randy.nextInt(GeneralValues.FRAME_WIDTH);
			y = randy.nextInt(GeneralValues.FRAME_HEIGHT);

			star_rec = new Rectangle(x, y, StarValues.WIDTH, StarValues.HEIGHT);
			star_holder = star_rec;
			star_shape = star_holder;
		} else if (where.equals("east")) {
			int x = GeneralValues.FRAME_WIDTH - randy.nextInt(StarValues.INITIAL_SPAWN_TOLERANCE_X);
			int y = randy.nextInt(GeneralValues.FRAME_HEIGHT); //TODO min, max rand einbauen

			star_rec = new Rectangle(x, y, StarValues.WIDTH, StarValues.HEIGHT);
			star_holder = star_rec;
			star_shape = star_holder;
		}

		star_anchorX = star_rec.x + star_rec.width / 2;
		star_anchorY = star_rec.y + star_rec.height / 2;
	}

	public void flappyCharge() {
		transform.setToTranslation(-FlappyValues.CHARGE_SPEED/2, 0);
		
		star_rec.x -= FlappyValues.CHARGE_SPEED/2;
		
		star_shape = transform.createTransformedShape(star_holder);
		star_holder = star_shape;

		star_anchorX -= FlappyValues.CHARGE_SPEED/2;
	}

	public void moveLeft() {
		transform.setToTranslation(-StarValues.SPEED, 0);
		star_rec.x -= StarValues.SPEED;

		star_shape = transform.createTransformedShape(star_holder);
		star_holder = star_shape;

		star_anchorX-=StarValues.SPEED;
	}

	public void rotate() {
		transform.setToRotation(45, star_anchorX, star_anchorY);

		star_shape = transform.createTransformedShape(star_holder);
		star_holder = star_shape;
	}

	public Shape getStarShape() {
		return star_shape;
	}
	
	public Rectangle getStarRec() {
		return star_rec;
	}
	public int getX() {
		return star_rec.x;
	}

}
