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

	public GameFrame(GamePanel gamePanel) {

		scorePanel = new JPanel();
		scorePanel.setLayout(new BorderLayout());

		scoreLabel = new JLabel("Score: " + score + "    ");
		scorePanel.add(scoreLabel, BorderLayout.EAST);

		setLayout(new BorderLayout());
		add(gamePanel);
		add(scorePanel, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void reset() {
		score = 0;
		scoreLabel.setText("Score: " + score + "    ");
	}

	public void setScore(int score) {
		this.score = score;
		scoreLabel.setText("Score: " + score + "    ");
	}
}
