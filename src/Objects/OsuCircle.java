package Objects;

import java.awt.*;

import Main.Game;

public class OsuCircle {

	public double x, y, inR, outR;
	public double maxDist, approachPercent, targetR, maxR, dir, vel = 2;
	
	Circle innerCircle;
	
	public boolean active = true, clicked = false;
	
	public OsuCircle(double centerX, double centerY, double innerCircleRadius, double outerCircleRadius) {
		// for collision
		innerCircle = new Circle(centerX, centerY, innerCircleRadius);
		
		inR = innerCircleRadius;
		outR = outerCircleRadius / 2;
		maxDist = outerCircleRadius - innerCircleRadius;
		maxR = outerCircleRadius;
		
		dir = 1;
		targetR = inR;
		
		//debug
		targetR = 30;
		vel = 4;
		
		// variables
		x = centerX;
		y = centerY;
	}
	
	
	public void update() {
		if (!active) return;
		
	}
	
	public double getApproachDistPercent() {
		return approachPercent;
	}
	
	
	public void click(int mx, int my, LatoLato latoLato) {
		if (innerCircle.collides(mx, my)) {
			
			double accuracy;
			accuracy = 1 - (outR - inR) / maxDist;
			accuracy *= 100;
			System.out.println("vel: " + latoLato.vel);
			
			if (!latoLato.isFever) {
				clicked = true;
				if (accuracy > 85) {
					latoLato.targetIdx += 200;
					latoLato.vel += 5;
				}
				else {
					latoLato.targetIdx -= 100; 
					latoLato.vel -= 10;
					if (latoLato.vel < 0) {
						vel = 4;
					}
				}
			}
			else {
				
				if (accuracy > 50) {
					latoLato.vel += 6;
				}
				else {
					latoLato.vel -= 6;
				}
				
			}
			
			clicked = true;
			
			
		}
	}
	
	
	public void draw(Graphics2D g) {

		// draw the inner circle
		g.setColor(Color.red);
		g.fillArc(
			(int)(x - inR), 
			(int)(y - inR), 
			(int)(inR*2), 
			(int)(inR*2), 
			0, 
			360
		);
			// stroke
		g.setColor(Color.white);
		g.setStroke(new BasicStroke(5));
		g.drawArc(
			(int)(x - inR), 
			(int)(y - inR), 
			(int)(inR*2), 
			(int)(inR*2), 
			0, 
			360
		);
		
		// draw the outer circle
			// stroke
		g.setColor(Color.red);
		g.setStroke(new BasicStroke(5));
		g.drawArc(
			(int)(x - outR), 
			(int)(y - outR), 
			(int)(outR*2), 
			(int)(outR*2), 
			0, 
			360
		);
		
		// its opacity depends on the distPercent      
	}
}
