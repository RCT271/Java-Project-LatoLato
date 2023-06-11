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
	
	Image[] imgs = new Image[12];
	double x, y;
	
	OsuCircle osu;
	
	boolean active = false;
	
	double idx = 0, maxIdx = 500, vel = 5, velDir = 1;
	
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
		
		idx += vel * velDir *Game.dt;
		
		if (idx > maxIdx) {
			new Sound().play();
			idx = maxIdx;
			velDir *= -1;
		}
		
		if (idx < 0) {
			new Sound().play();
			idx = 0;
			velDir *= -1;
		}
		
//		if (idx < maxIdx/2) {
//			osu.outR = osu.maxR * (idx/(maxIdx/2));
//		}
//		else if (idx > maxIdx/2) {
//			osu.outR = osu.maxR * ((maxIdx/2)/idx);
//		}
		
		osu.outR = osu.inR * (1 + idx/maxIdx);
		
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

