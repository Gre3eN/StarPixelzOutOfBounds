package FatPack;

import java.util.ArrayList;

import javax.swing.Timer;

public class Controller {

	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private Flappy flappy;
	private FlappyAnimationManager flappyAniManager;
	private ColorManager colorManager;
	private BackgroundStarManager backgroundStarManager;
	private Timer timer, timer2;
	private boolean gameOver = false;

	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		flappy = new Flappy();
		flappyAniManager = new FlappyAnimationManager();
		colorManager = new ColorManager();
		backgroundStarManager = new BackgroundStarManager(); 
		
		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY / 10, listener -> timer2Action());
		timer.start();
		Sound.playClip("Resources/background3.wav");
	}

	public void timerAction() {
		backgroundStarManager.update();
		gamePanel.updateBackGroundStars(backgroundStarManager.getStars());
		pipeManagement.update();
		gamePanel.updatePipes(pipeManagement.getPipes());
		gamePanel.updateFlappy(flappy.getY(), colorManager.getColor());
		flappyAniManager.update();
		gamePanel.updateFlappyAnimation(flappyAniManager.getCharge(), colorManager.getRGB());
		collision();
		gamePanel.repaintPanel();
		flappy.fall();
		gameFrame.setScore(pipeManagement.getScore());
		
		if(ovalManagement.getOvals().size() > 0) 
			gamePanel.updateOvals(ovalManagement.update());
		
		if (gameFrame.isSpaceTyped()){
			colorManager.changeColor();
			flappy.jump();
			ovalManagement.setRGB(colorManager.getRGB());
			ovalManagement.spawnOval(flappy.getY());
			gameFrame.setSpaceTyped(false);
		}
		
		if (gameFrame.isEnterTyped()){
			pipeManagement.flappyCharge();
			ovalManagement.flappyCharge();
			flappyAniManager.spawnCharge();
			gameFrame.setEnterTyped(false);
		}
		
		if (gameOver) {
			timer.stop();
			Sound.playClip("Resources/gameOverSound.wav");
			timer2.start();
		}
		
		
	}

	public void timer2Action() {
		if (gameFrame.getRestartNow()) {
			pipeManagement.reset();
			ovalManagement.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			flappyAniManager.reset();
			gameOver = false;
			timer.start();
			timer2.stop();
		}
	}
	
	public void collision(){
		ArrayList<Pipe> pipes = new ArrayList<Pipe>(pipeManagement.getPipes());
		
		for (Pipe p : pipes){
			if(p.isCollisionPossible())
				if(flappy.getY() <= p.getGapUpperY() || flappy.getY() + Values.FLAPPY_HEIGHT >= p.getGapLowerY()){
					gamePanel.setGameOver();
					gameFrame.setGameOver();
					gameOver = true;
				}
		}
		
		if (flappy.frameCollision()){
			gamePanel.setGameOver();
			gameFrame.setGameOver();
			gameOver = true;
		}
	}
}
