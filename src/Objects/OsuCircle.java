package Objects;

import java.awt.*;

import Main.Game;

public class OsuCircle {

	public double x, y, inR, outR;
	public double maxDist, approachPercent, targetR, maxR, dir, vel = 2;
	
	Circle innerCircle;
	
	public boolean active = true;
	
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
		
//		if (outR < inR) {
//			outR = inR + Math.abs(outR - inR);
//			dir *= -1;
//			vel *= 0.9;
//			if (targetR > inR) {				
//				targetR *= 0.90;
//			}
//			else {
//				targetR = inR;
//			}
//		}
//		
//		if (outR > targetR) {
//			outR = targetR;
//			dir *= -1;
//		}
//		
//		outR -= vel * dir *Game.dt;
//		vel += 0.01 *Game.dt;
//		approachPercent = (outR - inR) / maxDist;
//		
////		System.out.println(approachPercent);
		
	}
	
	public double getApproachDistPercent() {
		return approachPercent;
	}
	
	
	public void click(int mx, int my, LatoLato latoLato) {
		if (innerCircle.collides(mx, my)) {
			
			System.out.println(inR/outR);
			
			if (inR/outR > 0.8) {
				latoLato.vel += 4;
				System.out.println("added");
			}
			else {
				latoLato.vel -= 4;
				System.out.println("subtracted");
			}
			
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
