package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.JPanel;

import Panels.*;

public class Game extends JPanel{

	public static final int GAME_SPEED = 30; // speed is like working on 24 FPS
	GameLoop gameLoop;
	public static Dimension size;
	public MainLoop mainLoop;
	public GameOver gameOverPanel;
	public double prevTime;
	public static double dt;
	
	public Game() {
		// initialize this class
		size = new Dimension(480, 720);
		this.setPreferredSize(size);
		
		// add listeners
		this.addKeyListener(new AKL());
		this.addMouseMotionListener(new MML());
		this.addMouseListener(new ML());
//		this.setFocusable(true);
		this.setVisible(true);
		
		// initialize game panels
		mainLoop = new MainLoop(this);
		mainLoop.active = true;
		mainLoop.visible = true;
		gameOverPanel = new GameOver(this);
		
		// for the dt
		prevTime = System.currentTimeMillis();
		
		// start the game loop
		gameLoop = new GameLoop(this);
	}
	
	
	public void update() {
		
		// get the dt right... dt means delta time (delta means change) so, dt means change in time
		// This is used to keep the movement of the game relative to the real life despite how many frames is
		// being displayed in a second
		Game.dt = (System.currentTimeMillis() - prevTime) /1000;
		Game.dt *= GAME_SPEED;
		prevTime = System.currentTimeMillis();
		
		// update game panels
		mainLoop.update();
		gameOverPanel.update();
		
	}
	
	
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		
		// draw the game panels
		mainLoop.draw(g);
		gameOverPanel.draw(g);
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
	
	
	class ML implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			mainLoop.mouseClicked(e);
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mainLoop.mousePressed(e);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mainLoop.mouseReleased(e);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			mainLoop.mouseEntered(e);
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			mainLoop.mouseExited(e);
			
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
