package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Main.Game;
import Objects.*;

public class MainLoop extends GamePanel implements MouseListener, MouseMotionListener, KeyListener{
	
	
	double[] anchorPoint = new double[2];
	public Lato[] latoLato = new Lato[2];
	
	public MainLoop(Game game) {
		super(game);
		
		// generating the lato lato
		anchorPoint[0] = game.size.width/2;
		anchorPoint[1] = 300;
		
		latoLato[0] = new Lato(50, 400, 30, anchorPoint);
		latoLato[0].id = 0;
		latoLato[1] = new Lato(game.size.width-50, 400, 30, anchorPoint);
		latoLato[1].id = 1;
	}
	
	@Override
	public void update() {
		if (!active) return;
		
		for (int i = 0; i < latoLato.length; i++) {
			Lato lato = latoLato[i];
			
			lato.update(latoLato[i == 0 ? 1 : 0]);
		}
		
	}
	
	@Override
	public void draw(Graphics gr) {
		if (!visible) return;
		Graphics2D g = (Graphics2D) gr;
		
		// draw the background
		g.setColor(new Color(218, 218, 218));
		g.fillRect(0, 0, game.size.width, game.size.height);
		
		// draw the objects
		for (Lato lato : latoLato) {
			lato.draw(g);
		}
	}

	
	// listeners	
	
		// mouse motion listener
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
		// key listener
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
	
		// mouse listener
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
		for (Lato lato : latoLato) {
			lato.pull();
		}
		
	}
		
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
