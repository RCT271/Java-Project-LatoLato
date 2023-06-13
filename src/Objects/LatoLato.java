package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import Main.Game;
import Main.Utils;

public class LatoLato {

	Sound sound;
	
	double[] anchorPoint = new double[2];
	
	Image[] imgs = new Image[13];
	double x, y;
	OsuCircle osu;
	boolean active = false, isFever = false;
	double idx = 0, maxIdx = 1000, vel = 5, velDir = 1, targetIdx = 0, minVel = 5;
	
	int score = 0;
	
	public LatoLato(double[] anchorPoint, OsuCircle osu) {
		this.anchorPoint = anchorPoint;
		this.osu = osu;
		
		x = anchorPoint[0];
		y = anchorPoint[1];
		
		sound  = new Sound();
		 
		// sprite
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = new ImageIcon(String.format("src/sprites/f%d.png", i+1)).getImage();
		}
	}
	
	public void update() {
		

		// flips the movement
		if (idx > targetIdx) {
			idx = targetIdx;
			velDir *= -1;
		}

		// what moves the lato lato
		if (!(idx == 0 && targetIdx == 0)) {			
			idx += vel * velDir *Game.dt;
		}
	
		// top collision
		if (idx > maxIdx) {
			new Sound().play();
		}
		
		
		// bot collision
		if (idx < 0) {
			idx = 0;
			velDir *= -1;
			new Sound().play();
		}
		
//		// gravity (experimental)
//		if (velDir > 0) {
//			if (vel > minVel) {				
//				vel -= 0.5 * Game.dt;
//			}
//			else {
//				vel = minVel;
//			}
//		}
//		else {
//			vel += 0.25 *Game.dt;
//		}
//		
		
		// updating the approach circle
		if (idx < maxIdx/2) {
			osu.outR = osu.inR + osu.maxDist * idx/(maxIdx/2);
		}
		else {
			if (isFever) {				
				osu.outR = osu.inR + osu.maxDist * (maxIdx - idx)/(maxIdx/2);
			}
		}
		
		
//		// calculating the accuracy of the osu circle (experimental)
//		double accuracy;
//		accuracy = 1 - (osu.outR - osu.inR) / osu.maxDist;
//		System.out.println("accu: " + accuracy);
	}
	
	public void draw(Graphics2D g) {
		int i = (int)((imgs.length-1) * idx/maxIdx);
		
		// x anchor point of lato lato relative to its image is 50% of its width (300/600)
		int drawX = (int)(x - imgs[i].getWidth(null)*0.5);
		// y anchor point of lato lato relative to its image is 50% of its height (292/584)
		int drawY = (int)(y - imgs[i].getHeight(null)*0.5);
		
		g.drawImage(imgs[i], drawX, drawY, imgs[i].getWidth(null), imgs[i].getHeight(null), null);
	}
}

