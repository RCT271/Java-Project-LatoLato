package Objects;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	public Clip clip;
	URL soundURL;
	
	public Sound(String path) {
//		 File file = new File("src/sprites/lato.wav");
		File file = new File(path);
		 try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	
	public void loop() {
		clip.loop(0);
	}
	
	
	public void stop() {
		clip.stop();
	}
	
	
	
}
