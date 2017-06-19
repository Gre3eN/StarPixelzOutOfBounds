package controller;

import java.util.ArrayList;
import java.util.Random;

import FatPack.Sound;
import FatPack.Values;
import model.ConfusingPipe;
import model.HammerPipe;
import model.KnowingPipe;
import model.NormalPipe;
import model.Pipe;

public class PipeManagement {

	private ArrayList<Pipe> pipes = new ArrayList<>();
	private int pipeScore = 0;
	private int pipeCount;
	private Random randy = new Random();

	public PipeManagement() {
		pipes.add(new KnowingPipe()); //TODO just for testing confusing pipe. Should normally be .add(new NormalPipe())
		pipeCount = 1;
	}

	public void update(boolean up, boolean down) {
		for (int i = 0; i < pipes.size(); i++) {
			pipes.get(i).moveLeft();
			pipes.get(i).animation();
			pipes.get(i).setKeyActions(up, down);
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
				&& pipeCount != Values.SPECIALPIPES_SPAWN_INTERVALL) {
			pipes.add(new NormalPipe());
			pipeCount ++;
		}
		int temp = randy.nextInt(3);
		if (pipes.get(pipes.size() - 1).getX() <= Values.FRAME_WIDTH - Values.PIPE_SPAWN_GAP
				&& pipeCount == Values.SPECIALPIPES_SPAWN_INTERVALL){
			if (temp == 0) pipes.add(new HammerPipe());
			if (temp == 1) pipes.add(new ConfusingPipe());
			if (temp == 2) pipes.add(new KnowingPipe());
			pipeCount ++;
		}
		if (pipeCount > Values.SPECIALPIPES_SPAWN_INTERVALL)
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
		pipes.add(new KnowingPipe());
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
