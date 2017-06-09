package FatPack;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

public class Controller implements Observer{

	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private BackGroundStarManagement backGroundStarManagement;
	
	private Collectable collectable;
	private boolean spawnCollectable;
	private int score, scoreHolder;
	private Flappy flappy;
	private FlappyChargeAnimation flappyChargeAni;
	private ColorManager colorManager;
	private Timer timer, timer2;
	
	
	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		backGroundStarManagement = new BackGroundStarManagement();
		flappy = new Flappy();
		flappyChargeAni = new FlappyChargeAnimation();
		colorManager = new ColorManager();
		score=0;
		
		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY / 10, listener -> timer2Action());
		timer.start();
		gamePanel.updatePanel();
		Sound.playClip("Resources/through_space.wav");
	}
	

	public void timerAction() {
		if(gamePanel.getPlay()) {
			gamePanel.updatePipes(pipeManagement.update());
			backGroundStarManagement.update();
			gamePanel.updateBackGroundStars(backGroundStarManagement.getBackGroundStars());
			gamePanel.updateFlappy(flappy.getY(), colorManager.getColor());
			flappyChargeAni.updateTransparency();
			gamePanel.updateFlappyAnimation(flappyChargeAni.getAnimation(), flappyChargeAni.getTransparency(), colorManager.getRGB());
			gamePanel.updatePanel();
			flappy.fall();
			gameFrame.setScore(pipeManagement.getScore());
			score = pipeManagement.getScore();
			
			collectableAction();
			
		
			if(ovalManagement.getOvals().size() > 0) 
				gamePanel.updateOvals(ovalManagement.update());
			
			if (gameFrame.isSpaceTyped()){
				colorManager.changeColor();
				flappy.jump();
				ovalManagement.setRGB(colorManager.getRGB());
				ovalManagement.spawnOval(Values.FLAPPY_X, flappy.getY());
			}
			
			if (gameFrame.isEnterTyped()){
				pipeManagement.flappyCharge();
				ovalManagement.flappyCharge();
				backGroundStarManagement.charge();
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
			score=0;
			spawnCollectable=false;
			pipeManagement.reset();
			ovalManagement.reset();
			backGroundStarManagement.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			flappyChargeAni.reset();
			timer.start();
			timer2.stop();
		}
	}

	private void collectableAction() {

		if(score%2==0 && score!= scoreHolder) {
			spawnCollectable=true;
			scoreHolder=score;
		}
		
		collectableSpawn();
		collectableUpdate();
	}
	private void collectableSpawn() {
		if(spawnCollectable) {
			collectable=new Collectable();
			spawnCollectable=false;
			collectable.addObserver(this);
		}
	}
	private void collectableUpdate() {
		if(collectable!=null) {
			collectable.update();
			gamePanel.updateCollectable(collectable.getRotatingCore());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o==collectable) System.out.println("DANGER DANGER at: " +arg);
		
	}
}
