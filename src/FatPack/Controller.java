package FatPack;

import javax.swing.Timer;

public class Controller {

	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private PipeManagement pipeManagement;
	private OvalManagement ovalManagement;
	private BackGroundStarManagement backGroundStarManagement;
	private Flappy flappy;
	private AnimationManager animationManager;
	private ColorManager colorManager;
	private Timer timer, timer2;
	private int ovalJumpReduct = 0;

	public Controller() {
		gamePanel = new GamePanel();
		gameFrame = new GameFrame(gamePanel);
		pipeManagement = new PipeManagement();
		ovalManagement = new OvalManagement();
		backGroundStarManagement = new BackGroundStarManagement();
		flappy = new Flappy();
		animationManager = new AnimationManager();
		colorManager = new ColorManager();
		gamePanel.updateSpecialColor(colorManager.getRGB());
		
		timer = new Timer(Values.TIMER_DELAY, listener -> timerAction());
		timer2 = new Timer(Values.TIMER_DELAY / 10, listener -> timer2Action());
		timer.start();
		gamePanel.updatePanel();
		Sound.playClip("Resources/through_space.wav");
	}
	

	public void timerAction() {
		if(gamePanel.getPlay()) {
			gamePanel.updateSpecialColor(colorManager.getRGB());
			gamePanel.updatePipes(pipeManagement.update());
			backGroundStarManagement.update();
			gamePanel.updateBackGroundStars(backGroundStarManagement.getBackGroundStars());
			gamePanel.updateFlappy(flappy.getY());
			animationManager.update();
			gamePanel.updateCharge(animationManager.getCharge());
			gamePanel.updatePanel();
			flappy.fall();
			gameFrame.setScore(pipeManagement.getScore());
			long startTime = System.nanoTime();    
			  
			if(ovalManagement.getOvals().size() > 0) 
				ovalManagement.update();
				gamePanel.updateOvals(ovalManagement.getOvals());
			
			long estimatedTime = System.nanoTime() - startTime;
			//System.out.println("Oval controller"+estimatedTime);
			
			keyAction();
			
			if (gamePanel.getGameOver()) {
				timer.stop();
				Sound.playClip("Resources/gameOverSound.wav");
				timer2.start();
			}
		}
	}

	private void keyAction() {
		if (ovalJumpReduct >= 2)
			ovalJumpReduct = 0;
		
		if (gameFrame.isSpaceTyped()){
			colorManager.changeColor();
			flappy.jump();
			//if(ovalJumpReduct == 0){
				ovalManagement.spawnOval(flappy.getY());
			//}
			ovalJumpReduct++;	
		}
		
		if (gameFrame.isEnterTyped()){
			pipeManagement.flappyCharge();
			ovalManagement.flappyCharge();
			backGroundStarManagement.charge();
			animationManager.spawnCharge();
			gameFrame.setEnterTyped(false);
		}
		
		if (gameFrame.isDownTyped()) {
			colorManager.changeColor();
			flappy.jumpDown();
			//if(ovalJumpReduct == 0){
				ovalManagement.spawnOval(flappy.getY());
			//}
			ovalJumpReduct++;
		}
	}
	
	private void collision(){
		
	}

	public void timer2Action() {
		if (gameFrame.getRestartNow()) {
			pipeManagement.reset();
			ovalManagement.reset();
			backGroundStarManagement.reset();
			gamePanel.reset();
			gameFrame.reset();
			flappy.reset();
			animationManager.reset();
			timer.start();
			timer2.stop();
		}
	}
}
