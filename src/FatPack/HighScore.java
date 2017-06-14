package FatPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore {
	
	private ArrayList<Player> players;
	
	public HighScore() {
		readFile();
		sort();
	}
	
	private void readFile() {
		players = new ArrayList<Player>();	
		try	{
			BufferedReader reader = new BufferedReader(new FileReader("Resources/highscores.txt"));
			String currentLine;
			while((currentLine = reader.readLine()) != null) {
				String[] temp = currentLine.split(":");
				players.add(new Player(temp[0],Integer.valueOf(temp[1])));
			}
			reader.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writeFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Resources/highscores.txt"));
			for (int i=0;i<players.size();i++) {
				writer.write(players.get(i).getName()+":"+Integer.toString(players.get(i).getScore()));
				writer.newLine();
			}
			writer.close();		
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	private void sort() {
		Collections.sort(players, new PlayerComparator());
	}
	
	public void newPlayer(int score, String name) {
		players.add(new Player(name,score));
		sort();
		writeFile();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
