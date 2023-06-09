package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.JPanel;

import Panels.*;

public class Game extends JPanel{

	public static final int GAME_SPEED = 30; // speed is like working on 30 FPS
	GameLoop gameLoop;
	public Dimension size;
	public MainLoop mainLoop;
	public double prevTime, dt;
	
	public Game() {
		// initialize this class
		size = new Dimension(480, 720);
		this.setPreferredSize(size);
		
		// initialize game panels
		mainLoop = new MainLoop(this);
		mainLoop.active = true;
		mainLoop.visible = true;
		
		prevTime = System.currentTimeMillis();
		
		// add listeners
		this.addKeyListener(new AKL());
		this.addMouseMotionListener(new MML());
		
		this.setFocusable(true);
		this.setVisible(true);
		
		// start the game loop
		gameLoop = new GameLoop(this);
	}
	
	
	public void update() {
		
		// get the dt right
		dt = System.currentTimeMillis() - prevTime;
		dt *= GAME_SPEED;
		prevTime = System.currentTimeMillis();
		
		// update game panels
		mainLoop.update();
		
	}
	
	
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		
		// draw the game panels
		mainLoop.draw(g);
	}
	
	
	class AKL implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			mainLoop.keyTyped(e);
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Game.java: keyPressed()");
			mainLoop.keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			mainLoop.keyReleased(e);
			
		}
		
	}
	
	
	class MML implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			mainLoop.mouseDragged(e);
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mainLoop.mouseMoved(e);
			
		}
		
	}
	
}
