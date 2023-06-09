package Main;

import javax.swing.JFrame;

public class App {
	
	public static void main(String[] args) {
		JFrame win = new JFrame("LATO2");
		Game game = new Game();
		
		win.setFocusable(true);
		win.add(game);
		win.pack();
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}
	
	
}
