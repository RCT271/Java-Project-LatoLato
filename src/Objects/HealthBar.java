package Objects;

import java.awt.*;

import Main.Game;

public class HealthBar {

	public double hpPercent = 0.4;
	Rect rect;
	public Color hpColor = Color.green, hpBgColor = new Color(0,255,0,0);
	
	
	public HealthBar(double y, double w, double h) {
		rect = new Rect(Game.size.width/2 - w/2, y, w, h);
	}

	
	public void draw(Graphics2D g) {
		// drawing the background of the health bar
		g.setColor(hpBgColor);
		g.fillRect((int)rect.x, (int)rect.y, (int)(rect.w), (int)rect.h);
		
		// drawing the hp
		g.setColor(hpColor);
		g.fillRect((int)rect.x, (int)rect.y, (int)(rect.w*hpPercent), (int)rect.h);
		
		// drawing the frame of the health bar
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(2));
		g.drawRect((int)rect.x, (int)rect.y, (int)rect.w, (int)rect.h);
		
		
	}
	
}
