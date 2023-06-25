package Panels;

import java.awt.*;
import java.awt.event.MouseEvent;

import Main.App;
import Main.Game;
import Objects.*;

public class GameOver extends GamePanel{

	Game game;
	Color black;
	
	double startTime = 0, displayScore = 0;
	
	public GameOver(Game game) {
		super(game);
	
		
	}
	
	
	@Override
	public void update() {
		if (!active) return;
		
		if (System.currentTimeMillis() - startTime > 2000) {			
			if (displayScore < MainLoop.latoLato.score) {
				double incrementer = 0.35;
				if (MainLoop.latoLato.score >= 10) {
					incrementer = 0.35 * (int)(MainLoop.latoLato.score/10);
				}
				displayScore += incrementer *Game.dt;
			}
			else if (displayScore > MainLoop.latoLato.score){
				displayScore = MainLoop.latoLato.score;
				startTime = System.currentTimeMillis();
			}
		}
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		if (!visible) return;
		
		// black background
		g.setColor(black);
		g.fillRect(0, 0, Game.size.width, Game.size.height);
		
		// label
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 80));
		int gameOverX = Game.size.width/2 - g.getFontMetrics().stringWidth("Game Over")/2;
		g.drawString("Game Over", gameOverX, 260);
		
		// score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 69));
		g.drawString("" + (int)displayScore, Game.size.width/2 - g.getFontMetrics().stringWidth("" + (int)displayScore)/2, 150);
		
		// high score
		g.setFont(new Font("Arial", Font.PLAIN, 39));
		g.drawString("Best: " + MainLoop.hiScore.value, gameOverX, 340);
		
		// you may continue message
		if (displayScore == MainLoop.latoLato.score) {			
			if (System.currentTimeMillis() - startTime > 1500) {
				g.drawString("Tap to play again",  Game.size.width/2 - g.getFontMetrics().stringWidth("Tap to play again")/2, (int) (App.game.getHeight() * 0.75));
			}
		}
	}
	
	
	
	public void activate() {
		black = new Color(0, 0, 0, 150);
		Sound die = new Sound("src/sprites/die.wav");
		die.adjustVolume(-15f);
		die.play();
		this.active = true;
		this.visible = true;
		
		startTime = System.currentTimeMillis();
	}
	
	
	public void mouseClicked(MouseEvent e) {
		if (!this.active) return;
		
		if (System.currentTimeMillis() - startTime < 1500 && displayScore == MainLoop.latoLato.score) return;
		
		Game.mainLoop = new MainLoop();
		Game.mainLoop.active = true;
		Game.mainLoop.visible = true;
		this.active = false;
		this.visible = false;
	}

}
