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
