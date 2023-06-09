package Panels;

import java.awt.Graphics;

import Main.*;

public abstract class GamePanel {

	public Game game;
	public boolean active = false;
	public boolean visible = false;
	
	public GamePanel(Game game) {
		this.game = game;
	}
	
	public void update() {
		// do something
	}
	
	public void draw(Graphics g) {
		// do something
	}
}
