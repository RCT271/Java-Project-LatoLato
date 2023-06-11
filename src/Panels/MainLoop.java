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
	public LatoLato latoLato;
	OsuCircle osu;
	
	double[] clickedPos;
	
	public MainLoop(Game game) {
		super(game);
		
		// generating the lato lato
		anchorPoint[0] = game.size.width/2;
		anchorPoint[1] = 300;
		
		osu = new OsuCircle(game.size.width/2, game.size.height*0.90, 40, 130);
		latoLato = new LatoLato(anchorPoint, osu);
	}
	
	@Override
	public void update() {
		if (!active) return;
		
		latoLato.update();
		osu.update();
		
		if (clickedPos != null) {
			
			osu.click((int)clickedPos[0], (int)clickedPos[1], latoLato);
			
			clickedPos = null;
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
		latoLato.draw(g);
		osu.draw(g);
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
		clickedPos = new double[] {e.getX(), e.getY()};
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
