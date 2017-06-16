package controller;

import java.util.ArrayList;

import FatPack.Sound;
import FatPack.Values;
import model.HammerPipe;
import model.NormalPipe;
import model.Pipe;

public class PipeManagement {

	private ArrayList<Pipe> pipes = new ArrayList<>();
	private int pipeScore = 0;
	private int pipeCount;

	public PipeManagement() {
		pipes.add(new NormalPipe());
		pipeCount = 1;
	}

	public void update() {
		for (int i = 0; i < pipes.size(); i++) {
			pipes.get(i).moveLeft();
			pipes.get(i).hammerAnimation();
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
		if (pipes.get(pipes.size() - 1).getX() <= Values.FRAME_WIDTH - Values.PIPE_SPAWN_GAP
				&& pipeCount != Values.HAMMERPIPE_SPAWN_INTERVALL) {
			pipes.add(new NormalPipe());
			pipeCount ++;
		}
		if (pipes.get(pipes.size() - 1).getX() <= Values.FRAME_WIDTH - Values.PIPE_SPAWN_GAP
				&& pipeCount == Values.HAMMERPIPE_SPAWN_INTERVALL){
			pipes.add(new HammerPipe());
			pipeCount ++;
		}
		if (pipeCount > Values.HAMMERPIPE_SPAWN_INTERVALL)
			pipeCount = 0;
	}

	private void deletePipe() {
		if (pipes.get(0).getX() < -Values.PIPE_WIDTH) {
			pipes.remove(0);
			pipeScore++;
			Sound.playClip("Resources/powerup4.wav");
		}
	}

	public void reset() {
		pipes.clear();
		pipes.add(new NormalPipe());
		pipeScore = 0;
		pipeCount = 1;
	}
	
	public ArrayList<Pipe> getPipes(){
		return pipes;
	}
	
	public int getScore() {
		return pipeScore;
	}
}
