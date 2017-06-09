package FatPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private ArrayList<Oval> ovals = new ArrayList<Oval>();
	private ArrayList<int[]> backGroundStars = new ArrayList<>();
	private int[] xywht;
	private ArrayList<ChargeAnimation> charge = new ArrayList<>();
	public int flappyY = Values.FLAPPY_Y;
	private int[] specialColor;
	private boolean gameOver = false;
	private boolean play = false;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawBackground(g);
		drawBackGroundStars(g);
		drawOvals(g2);
		drawPipes(g);
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
		}
	}

	private void drawIdiot(Graphics g) {
		if (ovals.size() > 40){
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

	private void drawPipes(Graphics g) {
		for (Pipe p : pipes) {
			g.setColor(Values.PIPE_COLOR);
			g.fillRect(p.getX(), p.getY1(), p.getWidth(), p.getHeigth1());
			g.fillRect(p.getX(), p.getY2(), p.getWidth(), p.getHeigth2());
		}
	}

	private void drawOvals(Graphics2D g2) {
		Shape ring;
		for (Oval o : ovals) {
			g2.setColor(new Color(specialColor[0], specialColor[1], specialColor[2], o.getTransparency()));
			ring = Values.createRingShape(o.getX(), o.getY(), o.getSize());
			g2.fill(ring);
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

	public void updatePanel() {
		repaint();
	}

	/*public boolean gameOver() {
		if (flappyY + Values.FLAPPY_HEIGHT >= Values.FRAME_HEIGHT || flappyY + Values.FLAPPY_HEIGHT <= 0) {
			gameOver = true;
		}
		if (Values.FLAPPY_X + Values.FLAPPY_WIDTH >= pipes.get(0).getX()
				&& Values.FLAPPY_X <= pipes.get(0).getX() + pipes.get(0).getWidth()) {
			if (flappyY <= pipes.get(0).getHeigth1()
					|| flappyY + Values.FLAPPY_HEIGHT >= pipes.get(0).getHeigth1() + Values.PIPE_GAP) {
				gameOver = true;
			}
		}
		return gameOver;
	}*/
	
	public void updateSpecialColor (int[] rgb){
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

	public void updateFlappy(int flappyY) {
		this.flappyY = flappyY;
	}
	
	public void updateCharge(ArrayList<ChargeAnimation> charge){
		this.charge = charge;
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
