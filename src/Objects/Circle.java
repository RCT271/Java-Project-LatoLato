package Objects;

import java.awt.Graphics;

public class Circle {
	
	public double x, y, r;
	public double[] pos = new double[2];
	
	
	public Circle(double centerX, double centerY, double radius) {
		x = centerX;
		y = centerY;
		r = radius;
		
		updatePos();
	}
	
	
	public void updatePos() {
		pos[0] = x;
		pos[1] = y;
	}
	
	
	public boolean collides(Circle circle) {
		
		if (getDistance(circle.pos, this.pos) <= circle.r + this.r) {
			return true;
		}
		
		return false;
	}
	
	
	public void draw(Graphics g) {
		int x = (int)this.x;
		int y = (int)this.y;
		int r = (int)this.r;
		
		g.drawRoundRect(x - r, y - r, r*2, r*2, 360, 360);
	}
	
	
	private double getDistance(double[] p1, double[] p2) {
		// distance formula sqrt( (x2 - x1)^2 + (y2 - y1)^2 )
		
		return Math.sqrt( Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2) );
	}
	
	
	public void setX(double xVal) {
		this.x = xVal;
		updatePos();
	}
	
	public void setY(double yVal) {
		this.y = yVal;
		updatePos();
	}
	
	public void moveX(double xVel) {
		this.x += x;
		updatePos();
	}
	
	public void moveY(double yVel) {
		this.y += yVel;
		updatePos();
	}
}

