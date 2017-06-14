package FatPack;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

	public int compare(Player p1, Player p2) {
		int p1Score = p1.getScore();
		int p2Score = p2.getScore();
		
		if (p1Score > p2Score)
			return -1;
		else if (p1Score < p2Score)
			return 1;
		else
			return 0;
	}
}
