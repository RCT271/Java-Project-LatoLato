package Panels;

import java.awt.*;

import Main.Game;
import Objects.*;

public class GameOver extends GamePanel{

	Game game;
	Color black;
	
	public GameOver(Game game) {
		super(game);
		
	}
	
	
	@Override
	public void update() {
		if (!active) return;
		
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		if (!visible) return;
		
		// black background
		g.setColor(black);
		g.fillRect(0, 0, Game.size.width, Game.size.height);
		
		g.setColor(Color.white);
		g.setFont(new Font("Alkhemikal", Font.PLAIN, 100));
		g.drawString("Jump King", Game.size.width/2 - g.getFontMetrics().stringWidth("Jump King")/2, 220);
	}
	
	public void activate() {
		black = new Color(0, 0, 0, 150);
		Sound die = new Sound("src/sprites/die.wav");
		die.adjustVolume(-15f);
		die.play();
		this.active = true;
		this.visible = true;
	}

}
