package Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ImageIcon;

import static Main.Utils.*;

import Main.App;
import Main.Game;
import Objects.*;

public class MainLoop extends GamePanel implements MouseListener, MouseMotionListener, KeyListener{
	
	
	double[] anchorPoint = new double[2];
	public static LatoLato latoLato;
	OsuCircle osu;
	HealthBar healthBar;
	Sound bgMusic;
	public static HighScore hiScore; 
	Image BG;
	
	
	double[] clickedPos;
	
	public MainLoop() {
		super(App.game);
		
		// generating the lato lato
		anchorPoint[0] = Game.size.width/2;
		anchorPoint[1] = 300;
		
		healthBar = new HealthBar(20, Game.size.width*0.75, 30);
		
		osu = new OsuCircle(Game.size.width/2, Game.size.height*0.90, 30, 130);
		latoLato = new LatoLato(anchorPoint, osu);
		
		bgMusic = new Sound("src/sprites/cloud cute.wav");
		bgMusic.adjustVolume(-25f);
		bgMusic.loop();
		
		BG = new ImageIcon("src/sprites/background.jpg").getImage();
		
		File saveFile = new File("src/Objects/hiscore.sav");
		
		if (saveFile.exists()) {
			hiScore = (HighScore) loadObject("src/Objects/hiscore.sav");
		}
		else {
			hiScore = new HighScore(0);
		}
		
	}
	
	@Override
	public void update() {
		if (!active) return;
		
		latoLato.update();
		osu.update();
		
		if (!latoLato.doubleCollision) {			
			healthBar.hpPercent = latoLato.targetIdx/latoLato.maxIdx;
		}
		else {
			if (latoLato.doubleCollision) {				
				healthBar.hpBgColor = Color.green;
				healthBar.hpColor = Color.orange;
				healthBar.hpPercent = (latoLato.vel-latoLato.minVel)/latoLato.maxVel;
			}
			if (latoLato.fever) {
				healthBar.hpBgColor = Color.orange;
				healthBar.hpColor = Color.red;
				healthBar.hpPercent = latoLato.feverHp/latoLato.maxFeverHp;
			}
		}
		
		// game over
		if (!latoLato.active) {
			deactivate();
			Game.gameOverPanel.activate();
			if (latoLato.score > hiScore.value) {
				hiScore = new HighScore(latoLato.score);
				saveObject("src/Objects/hiscore.sav", hiScore);
			}
		}
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		if (!visible) return;
		
		// draw the background
		g.setColor(new Color(218, 218, 218));
		g.fillRect(0, 0, Game.size.width, Game.size.height);
		g.drawImage(BG, 0, 0, App.game.getWidth(), App.game.getHeight(), null);
		
		// draw the objects
		latoLato.draw(g);
		osu.draw(g);
		healthBar.draw(g);
		
		// drawing the score
		if (this.active) {			
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.PLAIN, 69));
			g.drawString("" + latoLato.score, Game.size.width/2 - g.getFontMetrics().stringWidth("" + latoLato.score)/2, 150);
		}
	}

	
	public void deactivate() {
		bgMusic.stop();
		this.active = false;
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
		if (!active) return;
		clickedPos = new double[] {e.getX(), e.getY()};
		osu.click((int)clickedPos[0], (int)clickedPos[1], latoLato);
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
