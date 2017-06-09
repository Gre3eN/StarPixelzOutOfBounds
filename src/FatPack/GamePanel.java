package FatPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private ArrayList<Oval> ovals = new ArrayList<Oval>();
	private ArrayList<int[]> backGroundStars = new ArrayList<>();
	private int[] xywht;
	private ArrayList<ChargeAnimation> charge = new ArrayList<>();
	private int flappyY;
	private int[] specialColor;
	private boolean gameOver = false;
	private boolean play = false;
	
	// tests
	long startTime;
	long estimatedTime;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		drawBackground(g);
		
		startTime = System.nanoTime(); 
		drawBackGroundStars(g);
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Star draw"+estimatedTime);
		
		startTime = System.nanoTime(); 
		drawOvals(g2);
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Oval draw"+estimatedTime);

		startTime = System.nanoTime(); 
		drawPipes(g);
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Pipes draw"+estimatedTime);
		
		startTime = System.nanoTime(); 
		drawChargeAnimation(g);
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Charge draw"+estimatedTime);
		
		if(play)
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
		//g.setColor(new Color(0, 0, 0, 160));
		//g.fillOval(Values.FLAPPY_X - Values.FLAPPY_WIDTH, flappyY - Values.FLAPPY_HEIGHT, Values.FLAPPY_WIDTH *3, Values.FLAPPY_HEIGHT *3);
		g.setColor(new Color(specialColor[0], specialColor[1], specialColor[2]));
		g.fillRect(Values.FLAPPY_X, flappyY, Values.FLAPPY_WIDTH, Values.FLAPPY_HEIGHT);
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

	public void updatePanel() {
		if(pipes.size() > 0)
			gameOver(pipes.get(0).gapShape(), pipes.get(0));
		repaint();
	}
	
	private void gameOver(ArrayList<Shape> s, Pipe p){
		ArrayList<Shape> actualShape = s;
		Pipe actualPipe = p;
		
		if (actualPipe.x <= Values.FLAPPY_X2 && Values.FLAPPY_X2 <= actualPipe.x + Values.PIPE_WIDTH){
			for(int i = 0; i < actualPipe.gapCount; i++){
				if(actualShape.get(i).contains(Values.FLAPPY_X2, flappyY)
					&& actualShape.get(i).contains(Values.FLAPPY_X2, flappyY + Values.FLAPPY_HEIGHT))
					gameOver = false;
				else gameOver = true;
			}
		}else if (actualPipe.x <= Values.FLAPPY_X && Values.FLAPPY_X <= actualPipe.x + Values.PIPE_WIDTH){
			for(int i = 0; i < actualPipe.gapCount; i++){
				if(actualShape.get(i).contains(Values.FLAPPY_X, flappyY)
					&& actualShape.get(i).contains(Values.FLAPPY_X, flappyY + Values.FLAPPY_HEIGHT))
					gameOver = false;
				else gameOver = true;
			}
		}else gameOver = false;		
	}
	
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

	public boolean getGameOver() {
		return gameOver;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public boolean getPlay() {
		return play;
	}

	private void drawBackGroundStars(Graphics g) {
		for (int i = 0; i < backGroundStars.size(); i++) {
			xywht = backGroundStars.get(i);
			g.setColor(Color.WHITE);
			g.fillOval(xywht[0], xywht[1], xywht[2], xywht[3]);
		}
	}
}
