package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import FatPack.Values;
import model.ChargeAnimation;
import model.Collectable;
import model.CometTail;
import model.Oval;
import model.Pipe;
import model.PlayerAttempt;

public class GamePanel extends JPanel {

	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private ArrayList<Oval> ovals = new ArrayList<Oval>();
	private ArrayList<int[]> backGroundStars = new ArrayList<>();
	private ArrayList<Collectable> collectables = new ArrayList<>();
	private ArrayList<PlayerAttempt> players = new ArrayList<PlayerAttempt>();
	private ArrayList<ChargeAnimation> charge = new ArrayList<>();
	private ArrayList<CometTail> cometTail = new ArrayList<>();
	private int[] xywht;
	private int flappyY = Values.FLAPPY_Y;
	private int[] specialColor;
	private FontMetrics fontMetrics;
	private boolean gameOver = false;
	private boolean play = false;
	private boolean godMode = false;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawBackground(g);
		drawBackGroundStars(g);
		drawCometTail(g2);
		drawCollectable(g2);
		drawOvals(g2);
		drawPipes(g2);
		drawChargeAnimation(g);
		drawPlayer(g);
		drawFail(g);
		drawIdiot(g);
		drawGodMode(g);
		drawStartScreen(g);
	}

	private void drawStartScreen(Graphics g) {
		if (!play) {
			g.setColor(Values.FAIL_COLOR);

			g.setFont(new Font("Harrington", Font.BOLD, 80));
			fontMetrics = g.getFontMetrics();
			String starP = "StarPixelz";
			g.drawString(starP, (Values.FRAME_WIDTH - fontMetrics.stringWidth(starP)) / 2, fontMetrics.getHeight());

			g.setFont(new Font("Harrington", Font.BOLD, 50));
			fontMetrics = g.getFontMetrics();
			String start = "Press 'S' to start";
			g.drawString(start, (Values.FRAME_WIDTH - fontMetrics.stringWidth(start)) / 2,
					fontMetrics.getHeight() + 100);

			drawControls(g);
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
	
	private void drawGodMode(Graphics g) {
		if (godMode) {
			g.setColor(Values.FAIL_COLOR);
			g.setFont(new Font("Harrington", Font.BOLD, 50));
			g.drawString("GOD MODE", Values.FRAME_WIDTH /2 -50, 100);
		}
	}

	private void drawFail(Graphics g) {
		if (gameOver) {
			g.setColor(Values.FAIL_COLOR);

			g.setFont(new Font("Harrington", Font.BOLD, 80));
			fontMetrics = g.getFontMetrics();
			String fail = "FAIL";
			g.drawString(fail, (Values.FRAME_WIDTH - fontMetrics.stringWidth(fail)) / 2, fontMetrics.getHeight());

			g.setFont(new Font("Harrington", Font.PLAIN, 50));
			fontMetrics = g.getFontMetrics();
			String restart = "Press 'R' to restart";
			g.drawString(restart, (Values.FRAME_WIDTH - fontMetrics.stringWidth(restart)) / 2,
					fontMetrics.getHeight() + 100);

			drawControls(g);
			drawHighScoreList(g);
		}
	}

	private void drawPlayer(Graphics g) {
		if (play) {
			if(!godMode)
				g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2]));
			else
				g.setColor(new Color(255, 255, 255));
			
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
			if (!godMode)
				g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2], o.getTransparency()));
			else
				g.setColor(new Color(255, 255, 255, o.getTransparency()));
			
			ring = Values.createRingShape(o.getX(), o.getY(), o.getSize(), Values.OVAL_CIRCLE_RELATION);
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
	
	private void drawCometTail(Graphics2D g){
		if (cometTail.size() > 0){
			for (CometTail t : cometTail) {
				g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2], t.getTransparency()));
				g.fill(new Ellipse2D.Double(t.getX(), t.getY(), t.getSize(), t.getSize()));
			}
		}
	}

	private void drawHighScoreList(Graphics g) {
		g.setFont(new Font("Harrington", Font.BOLD, 50));
		fontMetrics = g.getFontMetrics();

		for (int i = 0; i < 10; i++) {
			int y = Values.HIGHSCORE_HEIGHT + fontMetrics.getHeight() * i;
			if (i < players.size()) {
				String name = players.get(i).getName();
				String score = Integer.toString(players.get(i).getScore());
				int x1 = Values.FIRST_HIGHSCORE_WIDTH - fontMetrics.stringWidth(name);
				int x2 = Values.SECOND_HIGHSCORE_WIDTH - fontMetrics.stringWidth(score);
				g.drawString(name, x1, y);
				g.drawString(score, x2, y);
			} else {
				g.drawString("-", Values.FIRST_HIGHSCORE_WIDTH - fontMetrics.stringWidth("-"), y);
				g.drawString("-", Values.SECOND_HIGHSCORE_WIDTH - fontMetrics.stringWidth("-"), y);
			}
		}
	}

	private void drawControls(Graphics g) {
		g.setFont(new Font("Harrington", Font.BOLD, 50));
		fontMetrics = g.getFontMetrics();
		g.drawString("Controls:", 10, Values.CONTROL_TEXT_HEIGHT - fontMetrics.getHeight() * 3);
		g.drawString("SPACE = Dash", 10, Values.CONTROL_TEXT_HEIGHT - fontMetrics.getHeight() * 2);
		g.drawString("UP/DOWN = Movement", 10, Values.CONTROL_TEXT_HEIGHT - fontMetrics.getHeight());
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
	
	public void updateCometTail(ArrayList<CometTail> tail){
		cometTail = tail;
	}

	public void updateFlappy(int flappyY) {
		this.flappyY = flappyY;
	}

	public void updateCharge(ArrayList<ChargeAnimation> charge) {
		this.charge = charge;
	}

	public void updatePlayer(ArrayList<PlayerAttempt> players) {
		this.players = players;
	}

	public void reset() {
		gameOver = false;
		repaint();
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void setGodMode(boolean godMode){
		this.godMode = godMode;
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