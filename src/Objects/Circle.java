package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import Main.Utils;

public class Circle {
	
	public double x, y, r;
	public double[] pos = new double[2];
	
	
	public Circle(double centerX, double centerY, double radius) {
		x = centerX;
		y = centerY;
		r = radius;
		
		updatePos();
	}
	
	
	private void updatePos() {
		pos[0] = x;
		pos[1] = y;
	}
	
	
	public boolean collides(Circle circle) {
		
		if (Utils.getDistance(circle.pos, this.pos) < circle.r + this.r) {
			return true;
		}
		
		return false;
	}
	
	
	public void draw(Graphics2D g) {
		int x = (int)this.x;
		int y = (int)this.y;
		int r = (int)this.r;
		
		g.drawRoundRect(x - r, y - r, r*2, r*2, 360, 360);
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
		this.x += xVel;
		updatePos();
	}
	
	public void moveY(double yVel) {
		this.y += yVel;
		updatePos();
	}
}

