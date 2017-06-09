package FatPack;

import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.Timer;

public class Controller {

	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private BackGroundStarManagement backGroundStarManagement;
	private Flappy flappy;
	private AnimationManager animationManager;
	private ColorManager colorManager;
	private Timer timer, timer2;
	private int ovalJumpReduct = 0;
	private boolean gameOver = false;

	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		backGroundStarManagement = new BackGroundStarManagement();
		flappy = new Flappy();
		animationManager = new AnimationManager();
		colorManager = new ColorManager();
		gamePanel.updateSpecialColor(colorManager.getRGB());
		
		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY / 10, listener -> timer2Action());
		timer.start();
		gamePanel.updatePanel();
		Sound.playClip("Resources/through_space.wav");
	}
	

	public void timerAction() {
		if(gamePanel.getPlay()) {
			
			gamePanel.updateSpecialColor(colorManager.getRGB());
			pipeManagement.update();
			gamePanel.updatePipes(pipeManagement.getPipes());
			backGroundStarManagement.update();
			gamePanel.updateBackGroundStars(backGroundStarManagement.getBackGroundStars());
			gamePanel.updateFlappy(flappy.getY());
			flappy.fall();
			animationManager.update();
			gamePanel.updateCharge(animationManager.getCharge());
			gameFrame.setScore(pipeManagement.getScore());    

			if(ovalManagement.getOvals().size() > 0) 
				ovalManagement.update();
			gamePanel.updateOvals(ovalManagement.getOvals());
			
			keyAction();
			
			if(pipeManagement.getPipes().size() > 0)
				gameOver(pipeManagement.getPipes().get(0));
			gameFrame.setGameOver(gameOver);
			gamePanel.setGameOver(gameOver);
			
			gamePanel.updatePanel();
			
			if (gameOver) {
				timer.stop();
				Sound.playClip("Resources/gameOverSound.wav");
				timer2.start();
			}
		}
	}

	private void keyAction() {
		if (ovalJumpReduct >= 2)
			ovalJumpReduct = 0;
		
		if (gameFrame.isUpTyped()){
			colorManager.changeColor();
			flappy.jump();
			ovalManagement.spawnOval(flappy.getY());
			ovalJumpReduct++;	
		}
		
		if (gameFrame.isDownTyped()) {
			colorManager.changeColor();
			flappy.jumpDown();
			ovalManagement.spawnOval(flappy.getY());
			ovalJumpReduct++;
		}
		
		if (gameFrame.isSpaceTyped()){
			pipeManagement.flappyCharge();
			ovalManagement.flappyCharge();
			backGroundStarManagement.charge();
			animationManager.spawnCharge();
			gameFrame.setSpaceTyped(false);
		}	
	}
	
	public void timer2Action() {
		if (gameFrame.getRestartNow()) {
			gameOver = false;
			pipeManagement.reset();
			ovalManagement.reset();
			backGroundStarManagement.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			animationManager.reset();
			timer.start();
			timer2.stop();
		}
	}
	
	private static int X = Values.FLAPPY_X;
	private static int X2 = Values.FLAPPY_X2;
	
	private void gameOver(Pipe p){
		ArrayList<Shape> actualShape = p.gapShape();
		Pipe actualPipe = p;
		
		if (actualPipe.getX() <= X2 && X2 <= actualPipe.getX() + Values.PIPE_WIDTH){
			for(int i = 0; i < actualPipe.getGapCount(); i++){
				if(actualShape.get(i).contains(X2, flappy.getY())
					&& actualShape.get(i).contains(X2, flappy.getY() + Values.FLAPPY_HEIGHT))
					gameOver = false;
				else gameOver = true;
			}
		}else if (actualPipe.getX() <= X && X <= actualPipe.getX() + Values.PIPE_WIDTH){
			for(int i = 0; i < actualPipe.getGapCount(); i++){
				if(actualShape.get(i).contains(X, flappy.getY())
					&& actualShape.get(i).contains(X, flappy.getY() + Values.FLAPPY_HEIGHT))
					gameOver = false;
				else gameOver = true;
			}
		}else gameOver = false;		
	}
}
