package Objects;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Game;
import Main.Utils;

public class Lato {

	double[] anchorPoint = new double[2];
	Circle circle;
	double ropeLength, angle, vel = 0;
	int swayDir;
	boolean collided = false;
	
	//debug
	public int id;
	double prevDist;
	boolean moving = true;
	
	public Lato(double x, double y, double radius, double[] anchorPoint) {
		this.anchorPoint = anchorPoint;
		circle = new Circle(x, y, radius);
				
		// physics variable
		ropeLength = Utils.getDistance(circle.pos, anchorPoint);
		swayDir = circle.x > anchorPoint[0] ? -1 : 1;
		angle = Utils.getDegreeAngleOf2Points(anchorPoint, circle.pos);
	}
	
	public void update(Lato otherLato) {
		if (angle > 180) angle = -360 + angle;
		if (angle < -180) angle = 360 + angle;
		
		circle.setX(anchorPoint[0] + Math.cos(Math.toRadians(angle))*ropeLength);		
		circle.setY(anchorPoint[1] - Math.sin(Math.toRadians(angle))*ropeLength);
			
		angle += vel * swayDir *Game.dt;
		
		if (circle.collides(otherLato.circle) && !collided) {
			angle -= vel * swayDir *Game.dt;
			vel = vel*-0.8;
			collided = true;
		}
		if (collided && Utils.getDistance(circle.pos, otherLato.circle.pos) >= circle.r*2) {
			collided = false;
		}
		
		if (id == 0) {
			System.out.println("vel: " + vel);
			System.out.println("distance: " + Utils.getDistance(circle.pos, otherLato.circle.pos));
		}
		
		if ((int)Utils.getDistance(circle.pos, otherLato.circle.pos) == 60 && prevDist == 60) {
			vel = 0;
			moving = false;
		}

		prevDist = (int)Utils.getDistance(circle.pos, otherLato.circle.pos);
		
		// gravity
		if (moving) {			
			vel += 0.5 *Game.dt;
		}
		
	}
	
	
	public void pull() {
		if (circle.y > anchorPoint[1]) {			
			this.vel -= 9;
		}
		else {
			this.vel += 9;
		}
	}
	
	
	public void draw(Graphics2D g) {
		if (id == 0)
			g.setColor(Color.red);
		else
			g.setColor(Color.blue);
		circle.draw(g);
		g.setColor(Color.black);
		g.drawLine((int)circle.x, (int)circle.y, (int)anchorPoint[0], (int)anchorPoint[1]);
	}
	
}
