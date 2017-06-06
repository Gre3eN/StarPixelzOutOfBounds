 package FatPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private ArrayList<Oval> ovals = new ArrayList<Oval>();
	private ArrayList<FlappyChargeAnimation> charge = new ArrayList<>();
	private ArrayList<BackgroundStar[]> stars = new ArrayList<>();
	private int flappyY;
	private int[] flappyAniColor;
	private Color flappyColor;
	private boolean gameOver = false;
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// background
		g.setColor(Values.BACKGROUND_COLOR);
		g.fillRect(0, 0, Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
		// ovals
		for (Oval o : ovals){
			g.setColor(o.getOvalColor());
			g.fillOval(o.getOval()[0], o.getOval()[1], o.getOval()[2], o.getOval()[3]);
		}
		// background stars
				for (BackgroundStar[] star : stars){
					for (BackgroundStar layer : star){
						g.setColor(new Color(255, 255, 255, layer.getTransparency()));
						g.fillOval(layer.getX(), layer.getY(), layer.getSize(), layer.getSize());
					}
				}
		// pipes
		for (Pipe p : pipes) {
			g.setColor(Values.PIPE_COLOR);
			g.fillRect(p.getX(), p.getY1(), p.getWidth(), p.getHeigth1());
			g.fillRect(p.getX(), p.getY2(), p.getWidth(), p.getHeigth2());
		}
		//Flappy Charge Animation
		for (FlappyChargeAnimation c : charge){
			g.setColor(new Color(flappyAniColor[0], flappyAniColor[1], flappyAniColor[2], c.getTransparency()));
			g.fillRect(c.getX(), flappyY, Values.FLAPPY_WIDTH, Values.FLAPPY_HEIGHT);
		}
		// Flappy
		g.setColor(flappyColor);
		g.fillRect(Values.FLAPPY_X, flappyY, Values.FLAPPY_HEIGHT, Values.FLAPPY_WIDTH);
		// floor
		g.setColor(Values.FLOOR_COLOR);
		g.fillRect(0, (Values.FRAME_HEIGHT - Values.FLOOR_HEIGHT), Values.FRAME_WIDTH, Values.FLOOR_HEIGHT);
		
		// Fail
		if (gameOver) {
			g.setColor(Values.FAIL_COLOR);
			g.setFont(new Font("Harrington", Font.BOLD, 150));
			g.drawString("FAIL", Values.FLAPPY_X + 160, Values.FRAME_HEIGHT / 2);
	
			g.setFont(new Font("Harrington", Font.PLAIN, 50));
			g.drawString("press R", Values.FLAPPY_X + 230, Values.FRAME_HEIGHT / 2 + 100);
		}
	}
	
	public void repaintPanel(){
		repaint();
	}
	
	public void setGameOver() {
		gameOver = true;
	}

	public void updatePipes(ArrayList<Pipe> newPipes) {
		this.pipes = newPipes;
	}
	
	public void updateOvals(ArrayList<Oval> newOvals) {
		this.ovals = newOvals;
	}
	
	public void updateFlappyAnimation(ArrayList<FlappyChargeAnimation> charge, int[] rgb){
		this.charge = charge;
		flappyAniColor = rgb;	
	}
	
	public void updateFlappy(int flappyY, Color flappyColor){
		this.flappyY = flappyY;
		this.flappyColor = flappyColor;
	}
	
	public void updateBackGroundStars(ArrayList<BackgroundStar[]> stars){
		this.stars = stars;
	}

	public void reset() {
		gameOver = false;
		repaint();
	}

	public boolean getGameOver() {
		return gameOver;
	}
}
