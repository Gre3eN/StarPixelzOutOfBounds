package FatPack;

public class Player implements Comparable<Player> {
	
	private int score;
	private String name;

	public Player (String name, int score) {
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
	public int compareTo(Player p) {
		if (score > p.score)
			return -1;
		else if (score < p.score)
			return 1;
		return name.compareTo(p.name);
	}	
}
