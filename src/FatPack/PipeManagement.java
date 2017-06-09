package FatPack;

import java.util.ArrayList;

public class PipeManagement {

	private ArrayList<Pipe> pipes = new ArrayList<>();
	private int pipeScore = 0;

	public PipeManagement() {
		pipes.add(new NormalPipe());
	}

	public void update() {
		for (int i = 0; i < pipes.size(); i++) {
			pipes.get(i).moveLeft();
		}
		spawnPipe();
		deletePipe();
	}
	
	public void flappyCharge() {
		for (int i = 0; i < pipes.size(); i++) {
			pipes.get(i).flappyCharge();
		}
	}

	private void spawnPipe() {
		if (pipes.get(pipes.size() - 1).getX() <= Values.FRAME_WIDTH - Values.PIPE_SPAWN_GAP) {
			pipes.add(new NormalPipe());
		}
	}

	private void deletePipe() {
		if (pipes.get(0).getX() < -pipes.get(0).getWidth()) {
			pipes.remove(0);
			pipeScore++;
			Sound.playClip("Resources/powerup4.wav");
		}
	}

	public void reset() {
		pipes.clear();
		pipes.add(new NormalPipe());
		pipeScore=0;
	}
	
	public ArrayList<Pipe> getPipes(){
		return pipes;
	}
	
	public int getScore() {
		return pipeScore;
	}
}
