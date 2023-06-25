package Objects;

import java.io.Serializable;

public class HighScore implements Serializable{
	public int value = 0;
	
	public HighScore(int value) {
		this.value = value;
	}
}
