package controller;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	// for KeyListener and smooth controls
	private boolean gameOver = false;
	private boolean restartNow = false;
	private boolean isUpTyped = false;
	private boolean isSpaceTyped = false;
	private boolean isDownTyped = false;

	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		gameFrame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (!gamePanel.getGameOver()) {
						isUpTyped = true;
						Sound.playClip("Resources/jump42.wav");
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (!gamePanel.getGameOver()) {
						isDownTyped = true;
						Sound.playClip("Resources/jump22.wav");
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (!gamePanel.getGameOver()) {
						isSpaceTyped = true;
						Sound.playClip("Resources/jump23.wav");
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_S) {
					gamePanel.setPlay(true);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					isUpTyped = false;

				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					isDownTyped = false;

				if (e.getKeyCode() == KeyEvent.VK_R) {
					if (gameOver) {
						restartNow = true;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
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
			if (isUpTyped || !isDownTyped)
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

	public void timer2Action() {
		if (restartNow) {
			pipeManagement.reset();
			ovalManagement.reset();
			collectableManager.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			animationManager.reset();
			gameOver = false;
			restartNow = false;
			timer.start();
			timer2.stop();
		}
	}

	private void keyAction() {
		if (ovalJumpReduct >= 2)
			ovalJumpReduct = 0;

		if (isUpTyped) {
			colorManager.changeColor();
			flappy.jump();
			ovalManagement.spawnOval(flappy.getY());
			ovalJumpReduct++;
		}

		if (isDownTyped) {
			colorManager.changeColor();
			flappy.jumpDown();
			ovalManagement.spawnOval(flappy.getY());
			ovalJumpReduct++;
		}

		if (isSpaceTyped) {
			pipeManagement.flappyCharge();
			ovalManagement.flappyCharge();
			backGroundStarManagement.charge();
			collectableManager.charge();
			animationManager.spawnCharge();
			isSpaceTyped = false;
		}
	}

	public boolean gameOver() {
		ArrayList<Pipe> pipes = pipeManagement.getPipes();
		int flappyY = flappy.getY();

		if (Values.FLAPPY_X + Values.FLAPPY_WIDTH >= pipes.get(0).getX()
				&& Values.FLAPPY_X <= pipes.get(0).getX() + Values.PIPE_WIDTH) {
			if (flappyY <= pipes.get(0).getGaps()[0]
					|| flappyY + Values.FLAPPY_HEIGHT >= pipes.get(0).getGaps()[0] + Values.PIPE_GAP) {
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
				String name = JOptionPane.showInputDialog(gameFrame, "Enter your name", "New Highscore!",
						JOptionPane.PLAIN_MESSAGE);
				highScore.newPlayer(pipeManagement.getScore(), name);
			}
		}
	}

	// Collectables notify this and gives hitBox - so collision can be checked
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
