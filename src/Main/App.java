package Main;

import javax.swing.JFrame;

public class App {
	
	public static Game game;
	
	public static void main(String[] args) {
		JFrame win = new JFrame("LATO2");
		game = new Game();
		
		win.add(game);
		win.pack();
		win.setLocationRelativeTo(null);
		win.setFocusable(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}
	
	
}
