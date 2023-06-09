package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Main.Game;
import Objects.*;

public class MainLoop extends GamePanel implements MouseMotionListener, KeyListener{
	
	public MainLoop(Game game) {
		super(game);
	
	}
	
	@Override
	public void update() {
		if (!active) return;
				
		
	}
	
	@Override
	public void draw(Graphics g) {
		if (!visible) return;
		
		g.setColor(new Color(218, 218, 218));
		g.fillRect(0, 0, game.size.width, game.size.height);
	}

	
	// listeners	
	
		// mouse motion
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

		// key
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
