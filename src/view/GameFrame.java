package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FatPack.Sound;
import FatPack.Values;

public class GameFrame extends JFrame {


	private JPanel scorePanel;
	private JLabel scoreLabel;
	private int score = 0;

	private boolean restartNow = false;
	private boolean isUpTyped = false;
	private boolean isSpaceTyped = false;
	private boolean isDownTyped = false;
	private boolean fullScreen;

	public GameFrame(GamePanel gamePanel) {
	
		scorePanel = new JPanel();
		scorePanel.setLayout(new BorderLayout());
		
		fullScreen = false;

		scoreLabel = new JLabel("Score: " + score + "    ");

		scorePanel.add(scoreLabel, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(gamePanel);
		add(scorePanel, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F){
					if (fullScreen)
						fullScreen = false;
					else
						fullScreen = true;
					setFullScreen(fullScreen);
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					setFullScreen(false);
				
				if (e.getKeyCode() == KeyEvent.VK_UP){
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
					if (gamePanel.getGameOver()) {
						restartNow = true;
					}
				}		
			}

			@Override
			public void keyTyped(KeyEvent e) {}
		});
	}
	
	private void setFullScreen(boolean fullScreen) {
	    dispose();
	    setUndecorated(fullScreen);
	    setVisible(true);
	}

	public boolean getRestartNow() {
		return restartNow;
	}

	public void reset() {
		restartNow = false;
		score = 0;
		scoreLabel.setText("Score: " + score + "    ");
	}

	public void setScore(int score) {
		this.score=score;
		scoreLabel.setText("Score: " + score+"    ");
	}

	public boolean isUpTyped() {
		return isUpTyped;
	}
	
	public boolean isDownTyped() {
		return isDownTyped;
	}

	public boolean isSpaceTyped() {
		return isSpaceTyped;
	}
	
	public void setSpaceTyped(boolean isKeyTyped) {
		this.isSpaceTyped = isKeyTyped;
	}
}
