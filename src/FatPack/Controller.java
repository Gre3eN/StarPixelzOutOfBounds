package FatPack;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import java.awt.Rectangle;

public class Controller implements Observer {
	private int score;
	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private BackGroundStarManagement backGroundStarManagement;

	private CollectableManager collectableManager;

	private Flappy flappy;
	private FlappyChargeAnimation flappyChargeAni;
	private ColorManager colorManager;
	private Timer timer, timer2;

	public Controller() {
		score = 0;
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		backGroundStarManagement = new BackGroundStarManagement();
		collectableManager = new CollectableManager(this);
		flappy = new Flappy();
		flappyChargeAni = new FlappyChargeAnimation();
		colorManager = new ColorManager();

		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY, listener -> timer2Action());
		timer.start();
		gamePanel.updatePanel();
		Sound.playClip("Resources/through_space.wav");
	}

	public void timerAction() {
		if (gamePanel.getPlay()) {
			score = pipeManagement.getScore();

			gamePanel.updatePipes(pipeManagement.update());

			backGroundStarManagement.update();
			gamePanel.updateBackGroundStars(backGroundStarManagement.getBackGroundStars());

			collectableManager.update(score);
			gamePanel.updateCollectable(collectableManager.getCollectables());

			gamePanel.updateFlappy(flappy.getY(), colorManager.getColor());
			flappyChargeAni.updateTransparency();
			gamePanel.updateFlappyAnimation(flappyChargeAni.getAnimation(), flappyChargeAni.getTransparency(),
					colorManager.getRGB());

			gamePanel.updatePanel();
			flappy.fall();

			gameFrame.setScore(score);

			if (ovalManagement.getOvals().size() > 0)
				gamePanel.updateOvals(ovalManagement.update());

			if (gameFrame.isSpaceTyped()) {
				colorManager.changeColor();
				flappy.jump();
				ovalManagement.setRGB(colorManager.getRGB());
				ovalManagement.spawnOval(Values.FLAPPY_X, flappy.getY());
			}

			if (gameFrame.isEnterTyped()) {
				pipeManagement.flappyCharge();
				ovalManagement.flappyCharge();
				backGroundStarManagement.charge();
				collectableManager.charge();
				flappyChargeAni.setAnimation();
				gameFrame.setEnterTyped(false);
			}

			if (gameFrame.isDownTyped()) {
				colorManager.changeColor();
				flappy.jumpDown();
				ovalManagement.setRGB(colorManager.getRGB());
				ovalManagement.spawnOval(Values.FLAPPY_X, flappy.getY());
			}

			if (gamePanel.gameOver()) {
				timer.stop();
				Sound.playClip("Resources/gameOverSound.wav");
				timer2.start();
			}
		}
	}

	public void timer2Action() {
		if (gameFrame.getRestartNow()) {
			pipeManagement.reset();
			ovalManagement.reset();
			backGroundStarManagement.reset();
			collectableManager.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			flappyChargeAni.reset();
			timer.start();
			timer2.stop();
		}
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o.getClass().isInstance(new Collectable())) {
			Rectangle core = (Rectangle) arg;
			Rectangle flappyRec = flappy.getRect();
			if (core.intersects(flappyRec)) {
				System.out.println("rect");
			}
		}

	}
}
