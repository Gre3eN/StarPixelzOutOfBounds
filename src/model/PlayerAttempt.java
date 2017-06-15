package model;

public class PlayerAttempt implements Comparable<PlayerAttempt> {
	
	private int score;
	private String name;

	public PlayerAttempt (String name, int score) {
		this.score = score;
		this.name = name;
	}

	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(PlayerAttempt p) {
		if (score > p.score)
			return -1;
		else if (score < p.score)
			return 1;
		return name.compareTo(p.name);
	}	
}
