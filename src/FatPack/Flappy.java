package FatPack;

import java.awt.Shape;
import java.util.ArrayList;

public class Flappy {
	// TODO Flappy Sturzflug
	private int y;
	private static int X = Values.FLAPPY_X;
	private static int X2 = Values.FLAPPY_X2;
	private boolean gameOver = false;
	
	public void gameOver(ArrayList<Shape> s, Pipe p){
		ArrayList<Shape> actualShape = s;
		Pipe actualPipe = p;
		
		if (actualPipe.x <= X2 && X2 <= actualPipe.x + Values.PIPE_WIDTH){
			for(int i = 0; i < actualPipe.gapCount; i++){
				if(actualShape.get(i).contains(X2, y)
					&& actualShape.get(i).contains(X2, y + Values.FLAPPY_HEIGHT))
					gameOver = false;
				else gameOver = true;
			}
		}else if (actualPipe.x <= X && X <= actualPipe.x + Values.PIPE_WIDTH){
			for(int i = 0; i < actualPipe.gapCount; i++){
				if(actualShape.get(i).contains(X, y)
					&& actualShape.get(i).contains(X, y + Values.FLAPPY_HEIGHT))
					gameOver = false;
				else gameOver = true;
			}
		}else gameOver = false;		
	}
	
	public Flappy() {
		y=Values.FLAPPY_Y;
	}
	
	public void fall() {
		y+=Values.FLAPPY_FALL_HEIGHT;
	}
	
	public void jump() {
		y-=Values.FLAPPY_JUMP_HEIGHT;
	}
	
	public void jumpDown() {
		y+=Values.FLAPPY_JUMP_DOWN_HEIGHT;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	
	public int getY() {
		return y;
	}
	
	public void reset() {
		y = Values.FRAME_HEIGHT / 2;
		gameOver = false;
	}
}