package Objects;

import java.awt.Color;
import java.awt.Font;
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
	public boolean active = true, doubleCollision = false, fever = false;
	public double idx = 0, maxIdx = 1000, vel = 5, velDir = 1, targetIdx = 0;
	public double minVel, maxVel, feverHp = 1000, maxFeverHp = feverHp;
	
	public int score = 0, multiplier = 1;
	
	public LatoLato(double[] anchorPoint, OsuCircle osu) {
		this.anchorPoint = anchorPoint;
		this.osu = osu;
		
		x = anchorPoint[0];
		y = anchorPoint[1];
		
		sound  = new Sound("src/sprites/lato.wav");
		 
		// sprite
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = new ImageIcon(String.format("src/sprites/f%d.png", i+1)).getImage();
		}
	}
	
	public void update() {
		if (!active) return;
		
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
			score += 1*multiplier;
			
			if (doubleCollision) {
				if (!osu.clicked) {
					if (!fever) {						
						vel -= 5 *Game.dt;
					}
				}
				else {
					osu.clicked = false;
				}
			}
			
			new Sound("src/sprites/lato.wav").play();
		}
		
		
		// condition to start double collision mode
		if (targetIdx > maxIdx && !doubleCollision) {
			System.out.println("started double collision");
			minVel = vel;
			maxVel = vel + 60;
			vel += 20;
			multiplier = 1;
			targetIdx = maxIdx;
			doubleCollision = true;
		}
		
		
		// bot collision
		if (idx < 0) {
			idx = 0;
			velDir *= -1;
			new Sound("src/sprites/lato.wav").play();
			
			score += 1*multiplier;
			
			if (!doubleCollision) {
				targetIdx -= 50;
				// adjust the target
				if (targetIdx < 0) {
					targetIdx = 0;
				}
				
				//
				if (!osu.clicked) {
					vel -= 5 *Game.dt;
				}
				else {
					osu.clicked = false;
				}
			}
			else {
				if (!osu.clicked) {
					if (!fever) {						
						vel -= 5 *Game.dt;
					}
				}
				else {
					osu.clicked = false;
				}
			}
		}		
		
		// experimental game feature
		if (!doubleCollision) {
			targetIdx -= 2 *Game.dt;
			if (targetIdx < 0) {
				targetIdx = 0;
			}
		}
		
		else if (doubleCollision) {
			if ( (vel-minVel) > maxVel && !fever) {
				vel += 20;
				multiplier += 1;
				System.out.println("increased");
				System.out.println(multiplier);
				fever = true;
			}
			
			if (!fever) {
				vel -= 0.1 *Game.dt;
			}
			else {
				feverHp -= 2 *Game.dt;
			}
		}
		
		// conditions for game over
		if (!doubleCollision) {
			if (targetIdx == 0 && idx == 0 && vel != 5) {
				active = false;
			}
		}
		if (doubleCollision) {
			if (!fever) {
				if (vel < minVel) {
					active = false;
				}
			}
			else if (fever) {
				if (feverHp <= 0 ) {
					active = false;
				}
			}
		}
		
		
		// updating the approach circle
		if (idx < maxIdx/2) {
			osu.outR = osu.inR + osu.maxDist * idx/(maxIdx/2);
		}
		else {
			if (doubleCollision) {				
				osu.outR = osu.inR + osu.maxDist * (maxIdx - idx)/(maxIdx/2);
			}
		}
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

