package Panels;

import java.awt.Graphics;
import java.awt.Graphics2D;

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

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
