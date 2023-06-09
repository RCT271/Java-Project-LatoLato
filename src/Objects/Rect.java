package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rect {
	
	public double x, y, width, w, height, h;
	public double top, left, right, bot, centerX, centerY;
	public Color color = Color.black;
	
	public Rect(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		updateRect();
	}
	
	
	public void draw(Graphics2D g) {
		
		g.setColor(color);
		g.drawRect((int)x, (int)y, (int)w, (int)h);
	}
	
	
	public boolean collides(double x, double y) {
		
		if (x > left && x < right) {
			if (y > top && y < bot) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean colldies(Rect rect) {
		
		
		if (right > rect.left && left < rect.right) {
			if (bot > rect.top && top < rect.bot ) {
				return true;
			}
		}
		
		return false;
	}
	
	public void moveX(double xVal) {
		this.x += xVal;
		updateRect();
	}
	
	public void moveY(double yVal) {
		this.y += yVal;
		updateRect();
	}
	
	public void setX(double x) {
		this.x = x;
		updateRect();
	}
	
	public void setY(double y) {
		this.y = y;
		updateRect();
	}
	
	private void updateRect() {
		w = width;
		h = height;
		top = y;
		left = x;
		right = x + w;
		bot = y + w;
		centerX = x + w/2;
		centerY = y + h/2;
	}
	
}
