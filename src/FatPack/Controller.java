package FatPack;

import javax.swing.Timer;

import Model.StarManagement;
//jo
public class Controller {

	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private StarManagement starManagement;
	private Flappy flappy;
	private FlappyChargeAnimation flappyChargeAni;
	private ColorManager colorManager;
	private Timer timer, timer2;

	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		starManagement = new StarManagement();
		flappy = new Flappy();
		flappyChargeAni = new FlappyChargeAnimation();
		colorManager = new ColorManager();

		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY / 10, listener -> timer2Action());
		timer.start();



	}

	public void timerAction() {
		gamePanel.updatePipes(pipeManagement.update());

		letTheStarsShine();
		
		gamePanel.updateFlappy(flappy.getY(), colorManager.getColor());
		flappyChargeAni.updateTransparency();
		gamePanel.updateFlappyAnimation(flappyChargeAni.getAnimation(), flappyChargeAni.getTransparency(),
				colorManager.getRGB());
		gamePanel.updatePanel();
		flappy.fall();
		gameFrame.setScore(pipeManagement.getScore());

		if (ovalManagement.getOvals().size() > 0)
			gamePanel.updateOvals(ovalManagement.update());

	
		if (gameFrame.isSpaceTyped()){
			colorManager.changeColor();
			flappy.jump();
			ovalManagement.setRGB(colorManager.getRGB());
			ovalManagement.spawnOval(Values.FLAPPY_X, flappy.getY());
			gameFrame.setSpaceTyped(false);
		}
		
		if (gameFrame.isEnterTyped()){
			pipeManagement.flappyCharge();
			ovalManagement.flappyCharge();
			flappyChargeAni.setAnimation();
			gameFrame.setEnterTyped(false);
		}

		if (gamePanel.gameOver()) {
			timer.stop();
			Sound.playClip("Resources/gameOverSound.wav");
			timer2.start();
		}

	}

	public void timer2Action() {
		if (gameFrame.getRestartNow()) {
			pipeManagement.reset();
			ovalManagement.reset();
			starManagement.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			flappyChargeAni.reset();
			timer.start();
			timer2.stop();
		}
	}

	private void letTheStarsShine() {
		starManagement.update();
		gamePanel.updateStars(starManagement.getStarShapes());
		gamePanel.updateAuras0(starManagement.getAuras0());
		gamePanel.updateAuras1(starManagement.getAuras1());
		gamePanel.updateAuras2(starManagement.getAuras2());
	}
}
