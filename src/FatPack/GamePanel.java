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
	private ArrayList<Integer> flappyChAni = new ArrayList<Integer>();
	private ArrayList<Integer> flappyAniTrans = new ArrayList<Integer>();
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
		// pipes
		for (Pipe p : pipes) {
			g.setColor(Values.PIPE_COLOR);
			g.fillRect(p.getX(), p.getY1(), p.getWidth(), p.getHeigth1());
			g.fillRect(p.getX(), p.getY2(), p.getWidth(), p.getHeigth2());
		}
		//Flappy Charge Animation
		for (int t : flappyAniTrans){
			g.setColor(new Color(flappyAniColor[0], flappyAniColor[1], flappyAniColor[2], t));
		}
		for (int a : flappyChAni){
			g.fillRect(a, flappyY, Values.FLAPPY_WIDTH, Values.FLAPPY_HEIGHT);
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
	
	public void updatePanel(){
		repaint();
	}
	
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

	public void updatePipes(ArrayList<Pipe> newPipes) {
		this.pipes = newPipes;
	}
	
	public void updateOvals(ArrayList<Oval> newOvals) {
		this.ovals = newOvals;
	}
	
	public void updateFlappyAnimation(ArrayList<Integer> animation, ArrayList<Integer> transparency, int[] rgb){
		flappyChAni = animation;
		flappyAniTrans = transparency;
		flappyAniColor = rgb;	
	}
	
	public void updateFlappy(int flappyY, Color flappyColor){
		this.flappyY = flappyY;
		this.flappyColor = flappyColor;
	}

	public void reset() {
		gameOver = false;
		repaint();
	}

	public boolean getGameOver() {
		return gameOver;
	}
}
