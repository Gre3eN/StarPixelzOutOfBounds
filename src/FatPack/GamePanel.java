package FatPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private ArrayList<Oval> ovals = new ArrayList<Oval>();
	private ArrayList<int[]> backGroundStars = new ArrayList<>();
	private ArrayList<Collectable> collectables = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<ChargeAnimation> charge = new ArrayList<>();
	private int[] xywht;
	private int flappyY = Values.FLAPPY_Y;
	private int[] specialColor;
	private boolean gameOver = false;
	private boolean play = false;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawBackground(g);
		drawBackGroundStars(g);
		drawCollectable(g2);
		drawOvals(g2);
		drawPipes(g2);
		drawChargeAnimation(g);
		drawPlayer(g);
		drawFail(g);
		drawIdiot(g);
		drawStartScreen(g);
	}

	private void drawStartScreen(Graphics g) {
		if (!play) {
			g.setColor(Values.FAIL_COLOR);
			g.setFont(new Font("Harrington", Font.BOLD, 150));
			g.drawString("Press 'S' to start", Values.FRAME_WIDTH / 11, Values.FRAME_HEIGHT / 2);
			
			drawHighScoreList(g);
		}
	}

	private void drawIdiot(Graphics g) {
		if (ovals.size() > 40) {
			g.setColor(Values.FAIL_COLOR);
			g.setFont(new Font("Harrington", Font.BOLD, 50));
			g.drawString("Idiot -.-", 100, 100);
		}
	}

	private void drawFail(Graphics g) {
		if (gameOver) {
			g.setColor(Values.FAIL_COLOR);
			g.setFont(new Font("Harrington", Font.BOLD, 150));
			g.drawString("FAIL", Values.FLAPPY_X + 160, Values.FRAME_HEIGHT / 2);

			g.setFont(new Font("Harrington", Font.PLAIN, 50));
			g.drawString("press R", Values.FLAPPY_X + 230, Values.FRAME_HEIGHT / 2 + 100);
			
			drawHighScoreList(g);	
		}
	}

	private void drawPlayer(Graphics g) {
		if (play) {
			g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2]));
			g.fillRect(Values.FLAPPY_X, flappyY, Values.FLAPPY_HEIGHT, Values.FLAPPY_WIDTH);
		}
	}

	private void drawChargeAnimation(Graphics g) {
		for (ChargeAnimation c : charge) {
			g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2], c.getTransparency()));
			g.fillRect(c.getX(), flappyY, Values.FLAPPY_WIDTH, Values.FLAPPY_HEIGHT);
		}
	}

	private void drawPipes(Graphics2D g) {
		for (Pipe p : pipes) {
			g.setColor(Values.PIPE_COLOR);
			g.fill(p.pipeShape());
		}
	}

	private void drawOvals(Graphics2D g) {
		Shape ring;
		for (Oval o : ovals) {
			g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2], o.getTransparency()));
			ring = Values.createRingShape(o.getX(), o.getY(), o.getSize());
			g.fill(ring);
		}
	}

	private void drawBackground(Graphics g) {
		g.setColor(Values.BACKGROUND_COLOR);
		g.fillRect(0, 0, Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
	}

	private void drawBackGroundStars(Graphics g) {
		for (int i = 0; i < backGroundStars.size(); i++) {
			xywht = backGroundStars.get(i);
			g.setColor(Color.WHITE);
			g.fillOval(xywht[0], xywht[1], xywht[2], xywht[3]);
		}
	}

	private void drawCollectable(Graphics2D g) {
		if (collectables.size() > 0) {
			g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2]));
			for (Collectable c : collectables) {
				g.fill(c.getRotatingCore());
			}
		}
	}
	
	private void drawHighScoreList(Graphics g) {
		g.setFont(new Font("Harrington", Font.BOLD, 50));
		FontMetrics fontMetrics = g.getFontMetrics();
			
		for (int i=0;i<10;i++) {		
			int y = Values.FRAME_HEIGHT - 610 + fontMetrics.getHeight() * i;
			if (i < players.size()) {
				String name = players.get(i).getName();
				String score = Integer.toString(players.get(i).getScore());
				int x1 = Values.FRAME_WIDTH - 140 - fontMetrics.stringWidth(name);
				int x2 = Values.FRAME_WIDTH - 20 - fontMetrics.stringWidth(score);
				g.drawString(name, x1, y);
				g.drawString(score, x2, y);
			} else {
				g.drawString("-", Values.FRAME_WIDTH - 20 - fontMetrics.stringWidth("-"), y);
				g.drawString("-", Values.FRAME_WIDTH - 140 - fontMetrics.stringWidth("-"), y);
			}
		}	
	}

	public void updatePanel() {
		repaint();
	}

	public void updateSpecialColor(int[] rgb) {
		specialColor = rgb;
	}

	public void updatePipes(ArrayList<Pipe> newPipes) {
		this.pipes = newPipes;
	}

	public void updateOvals(ArrayList<Oval> newOvals) {
		this.ovals = newOvals;
	}

	public void updateBackGroundStars(ArrayList<int[]> backgroundStars) {
		this.backGroundStars = backgroundStars;
	}

	public void updateCollectable(ArrayList<Collectable> collectables) {
		this.collectables = collectables;
	}

	public void updateFlappy(int flappyY) {
		this.flappyY = flappyY;
	}

	public void updateCharge(ArrayList<ChargeAnimation> charge) {
		this.charge = charge;
	}
	
	public void updatePlayer(ArrayList<Player> players) {
		this.players = players;
	}

	public void reset() {
		gameOver = false;
		repaint();
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public boolean getPlay() {
		return play;
	}
}