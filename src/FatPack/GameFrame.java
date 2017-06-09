package FatPack;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame {


	private JPanel scorePanel;
	private JLabel jumpLabel, scoreLabel;
	private int jumps = 0;
	private int score = 0;

	private boolean restartNow = false;
	private boolean isUpTyped = false;
	private boolean isSpaceTyped = false;
	private boolean isDownTyped = false;

	public GameFrame(GamePanel gamePanel) {
	
		scorePanel = new JPanel();
		scorePanel.setLayout(new BorderLayout());

		scoreLabel = new JLabel("Score: " + score + "    ");
		jumpLabel = new JLabel("Jumps: " + jumps);

		scorePanel.add(scoreLabel, BorderLayout.EAST);
		scorePanel.add(jumpLabel, BorderLayout.WEST);

		setLayout(new BorderLayout());
		add(gamePanel);
		add(scorePanel, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
		setVisible(true);

		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP){
					if (!gamePanel.getGameOver()) {
						isUpTyped = true;
						jumps++;
						jumpLabel.setText("Jumps: " + jumps);
						Sound.playClip("Resources/jump22.wav");
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (!gamePanel.getGameOver()) {
						isDownTyped = true;
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					isSpaceTyped = true;
					Sound.playClip("Resources/jump42.wav");
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
					if (gamePanel.gameOver()) {
						restartNow = true;
					}
				}		
			}

			@Override
			public void keyTyped(KeyEvent e) {}
		});
	}

	public boolean getRestartNow() {
		return restartNow;
	}

	public void reset() {
		restartNow = false;
		jumps = 0;
		jumpLabel.setText("Jumps: " + jumps);
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
