package controller;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import FatPack.Sound;
import FatPack.Values;
import model.Collectable;
import model.Flappy;
import model.Pipe;
import model.PlayerAttempt;
import view.GameFrame;
import view.GamePanel;

public class Controller implements Observer {

	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private BackGroundStarManagement backGroundStarManagement;
	private CollectableManager collectableManager;
	private Flappy flappy;
	private AnimationManager animationManager;
	private ColorManager colorManager;
	private HighScore highScore;
	private Timer timer, timer2;
	private int ovalJumpReduct = 0;
	private boolean gameOver = false;

	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		backGroundStarManagement = new BackGroundStarManagement();
		collectableManager = new CollectableManager(this);
		flappy = new Flappy();
		animationManager = new AnimationManager();
		colorManager = new ColorManager();
		highScore = new HighScore();
		
		gamePanel.updatePlayer(highScore.getPlayers());
		gamePanel.updateSpecialColor(colorManager.getRGB());
		gamePanel.updateBackGroundStars(backGroundStarManagement.getBackGroundStars());
		gamePanel.updatePanel();

		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY / 10, listener -> timer2Action());
		timer.start();
		
		Sound.playClip("Resources/through_space.wav");
	}

	public void timerAction() {
		if (gamePanel.getPlay()) {
			
			if (gameOver()) {
				timer.stop();
				Sound.playClip("Resources/gameOverSound.wav");
				timer2.start();
				highScoreCheck();
			}

			gamePanel.updateSpecialColor(colorManager.getRGB());
			pipeManagement.update();
			gamePanel.updatePipes(pipeManagement.getPipes());
			backGroundStarManagement.update();
			gamePanel.updateBackGroundStars(backGroundStarManagement.getBackGroundStars());
			collectableManager.update(pipeManagement.getScore());

			gamePanel.updateCollectable(collectableManager.getCollectables());
			gamePanel.updateFlappy(flappy.getY());
			if (!gameFrame.isUpTyped() || !gameFrame.isDownTyped())
				flappy.fall();
			animationManager.update();
			gamePanel.updateCharge(animationManager.getCharge());
			gameFrame.setScore(pipeManagement.getScore());

			if (ovalManagement.getOvals().size() > 0)
				ovalManagement.update();
			gamePanel.updateOvals(ovalManagement.getOvals());

			keyAction();

			if (flappy.getY() + Values.FLAPPY_HEIGHT <= 0)
				flappy.teleDown();

			if (flappy.getY() >= Values.FRAME_HEIGHT)
				flappy.teleUp();

			gamePanel.updatePanel();

		}
	}

	private void keyAction() {
		if (ovalJumpReduct >= 2)
			ovalJumpReduct = 0;

		if (gameFrame.isUpTyped()) {
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

		if (gameFrame.isSpaceTyped()) {
			pipeManagement.flappyCharge();
			ovalManagement.flappyCharge();
			backGroundStarManagement.charge();
			collectableManager.charge();
			animationManager.spawnCharge();
			gameFrame.setSpaceTyped(false);
		}
	}

	public void timer2Action() {
		if (gameFrame.getRestartNow()) {
			pipeManagement.reset();
			ovalManagement.reset();
			collectableManager.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			animationManager.reset();
			gameOver = false;
			timer.start();
			timer2.stop();
		}
	}

	public boolean gameOver() {
		Pipe firstPipe = pipeManagement.getPipes().get(0);
		int flappyY = flappy.getY();

		if (Values.FLAPPY_X2 >= firstPipe.getX()
				&& Values.FLAPPY_X <= firstPipe.getX() + Values.PIPE_WIDTH) {
			if (flappyY <= firstPipe.getGaps()[0]
					|| flappyY + Values.FLAPPY_HEIGHT >= firstPipe.getGaps()[0] + firstPipe.getGapHeight()[0]) {
				gameOver = true;
			}
		}

		gamePanel.setGameOver(gameOver);
		return gameOver;
	}
	
	public void highScoreCheck() {
		ArrayList<PlayerAttempt> players = highScore.getPlayers();
		if (pipeManagement.getScore() > 0) {
			if (players.size() < 10 || players.get(9).getScore() < pipeManagement.getScore()) {
				String name = JOptionPane.showInputDialog(gameFrame,"Enter your name","New Highscore!",JOptionPane.PLAIN_MESSAGE);
				highScore.newPlayer(pipeManagement.getScore(), name);
			}
		}
	}	

	@Override
	public void update(Observable o, Object arg) {

		if (o.getClass().isInstance(new Collectable())) {
			Rectangle core = (Rectangle) arg;
			Rectangle flappyRec = flappy.getRect();

			if (core.intersects(flappyRec)) {
				collectableManager.gotCaught();
				Sound.playClip("Resources/pickup.wav");

			}
		}
	}
}
