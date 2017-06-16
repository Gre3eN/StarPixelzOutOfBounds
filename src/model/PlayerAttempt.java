package model;

/**
 * The PlayerAttempt class returns the name and score
 * of its objects. It is also used to compare two
 * PlayerAttempt objects for sorting purposes.   
 * 
 * @see Comparable
 * 
 * @author timfock
 */
public class PlayerAttempt implements Comparable<PlayerAttempt> {
	
	private int score;
	private String name;

	/**
	 * Creates a new PlayerAttempt object with a name 
	 * and a score.
	 * 
	 * @param name the name of the player
	 * @param score the score of the player
	 */
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
	
	/**
	 * Compares the current object to another PlayerAttempt object.<br>
	 * Initially based on their scores and then based on their names<br>
	 * in case the score is equal.
	 * 
	 * @param p PlayerAttempt object that you want to compare to the current object
	 * @see java.util.Collections#sort(java.util.List)
	 */
	@Override
	public int compareTo(PlayerAttempt p) {
		if (score > p.score)
			return -1;
		else if (score < p.score)
			return 1;
		return name.compareTo(p.name);
	}	
}
