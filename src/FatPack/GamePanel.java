package FatPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JPanel;

import MagicNumberWonderland.StarValues;


public class GamePanel extends JPanel {

	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private ArrayList<Oval> ovals = new ArrayList<Oval>();

	private ArrayList<Shape> stars = new ArrayList<>();
	private ArrayList<Rectangle> auras0 = new ArrayList<>();
	private ArrayList<Rectangle> auras1 = new ArrayList<>();
	private ArrayList<Rectangle> auras2 = new ArrayList<>();

	private ArrayList<Integer> flappyChAni = new ArrayList<Integer>();
	private ArrayList<Integer> flappyAniTrans = new ArrayList<Integer>();

	private int flappyY;
	private int[] flappyAniColor;
	private Color flappyColor;
	private boolean gameOver = false;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		drawBackground(g);
		drawOvals(g);
		drawStars(g2);
		drawAuras(g);
		drawPipes(g);
		drawFlappyChargeAnimation(g);
		drawFlappy(g);
		drawFloor(g);
		if (gameOver) {
			drawFail(g);
		}
	}

	// TODO
	public boolean gameOver() {
		if (flappyY + Values.FLAPPY_HEIGHT + Values.FLOOR_HEIGHT >= Values.FRAME_HEIGHT || flappyY <= 0) {
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
	}

	public void reset() {
		gameOver = false;
		repaint();
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void updatePanel() {
		repaint();
	}

	public void updatePipes(ArrayList<Pipe> newPipes) {
		this.pipes = newPipes;
	}

	public void updateOvals(ArrayList<Oval> newOvals) {
		this.ovals = newOvals;
	}

	public void updateStars(ArrayList<Shape> stars) {
		this.stars = stars;
	}

	public void updateFlappyAnimation(ArrayList<Integer> animation, ArrayList<Integer> transparency, int[] rgb) {
		flappyChAni = animation;
		flappyAniTrans = transparency;
		flappyAniColor = rgb;
	}

	public void updateFlappy(int flappyY, Color flappyColor) {
		this.flappyY = flappyY;
		this.flappyColor = flappyColor;
	}

	public void updateAuras0(ArrayList<Rectangle> auras0) {
		this.auras0 = auras0;
	}

	public void updateAuras1(ArrayList<Rectangle> auras1) {
		this.auras1 = auras1;
	}

	public void updateAuras2(ArrayList<Rectangle> auras2) {
		this.auras2 = auras2;
	}

	private void drawAuras(Graphics g) {
		g.setColor(StarValues.AURA_COLOR0);
		for (Rectangle a : auras0) {
			g.fillOval(a.x, a.y, a.width, a.height);
		}
		g.setColor(StarValues.AURA_COLOR1);
		for (Rectangle a : auras1) {
			g.fillOval(a.x, a.y, a.width, a.height);
		}
		g.setColor(StarValues.AURA_COLOR2);
		for (Rectangle a : auras2) {
			g.fillOval(a.x, a.y, a.width, a.height);
		}
	}

	private void drawStars(Graphics2D g2) {
		g2.setColor(StarValues.STAR_COLOR);
		for (Shape s : stars) {
			g2.fill(s);
		}
	}

	private void drawFail(Graphics g) {
		g.setColor(Values.FAIL_COLOR);
		g.setFont(new Font("Harrington", Font.BOLD, 150));
		g.drawString("FAIL", Values.FLAPPY_X + 160, Values.FRAME_HEIGHT / 2);

		g.setFont(new Font("Harrington", Font.PLAIN, 50));
		g.drawString("press R", Values.FLAPPY_X + 230, Values.FRAME_HEIGHT / 2 + 100);
	}

	private void drawPipes(Graphics g) {
		for (Pipe p : pipes) {
			g.setColor(Values.PIPE_COLOR);
			g.fillRect(p.getX(), p.getY1(), p.getWidth(), p.getHeigth1());
			g.fillRect(p.getX(), p.getY2(), p.getWidth(), p.getHeigth2());
		}
	}

	private void drawOvals(Graphics g) {
		for (Oval o : ovals) {
			g.setColor(o.getOvalColor());
			g.fillOval(o.getOval()[0], o.getOval()[1], o.getOval()[2], o.getOval()[3]);
		}
	}

	private void drawFlappy(Graphics g) {
		g.setColor(flappyColor);
		g.fillRect(Values.FLAPPY_X, flappyY, Values.FLAPPY_HEIGHT, Values.FLAPPY_WIDTH);
	}

	private void drawFlappyChargeAnimation(Graphics g) {
		for (int t : flappyAniTrans) {
			g.setColor(new Color(flappyAniColor[0], flappyAniColor[1], flappyAniColor[2], t));
		}
		for (int a : flappyChAni) {
			g.fillRect(a, flappyY, Values.FLAPPY_WIDTH, Values.FLAPPY_HEIGHT);
		}
	}

	private void drawBackground(Graphics g) {
		g.setColor(Values.BACKGROUND_COLOR);
		g.fillRect(0, 0, Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
	}

	private void drawFloor(Graphics g) {
		g.setColor(Values.FLOOR_COLOR);
		g.fillRect(0, (Values.FRAME_HEIGHT - Values.FLOOR_HEIGHT), Values.FRAME_WIDTH, Values.FLOOR_HEIGHT);
	}
}
