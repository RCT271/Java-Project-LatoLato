package Main;

public class GameLoop implements Runnable{

	Thread thread;
	Game game;
	final static int MAX_UPS = 60;
	boolean running = false;
	long prevTime, elapsedTime, sleepTime;
	
	int updateCount, frameCount;
	private long averageUPS;
	private long averageFPS;
	
	public GameLoop(Game game) {
		this.game = game;
		
		startLoop();
	}
	
	
	public void startLoop() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	public int getAverageFPS() {
		return (int) averageFPS;
	}
	
	public int getAverageUPS() {
		return (int) averageUPS;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		prevTime = System.currentTimeMillis();
		
		updateCount = 0;
		frameCount = 0;
		
		while (running) {
			
			game.update();
			updateCount += 1;
			
			game.repaint();;
			frameCount += 1;
			
			elapsedTime = System.currentTimeMillis() - prevTime;
			sleepTime = (long)(updateCount*(1E+3/MAX_UPS) - elapsedTime);
			
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// skip frames
			while (sleepTime < 0 && updateCount < MAX_UPS-1) {
				game.update();
                updateCount += 1;
                elapsedTime = System.currentTimeMillis() - prevTime;
                sleepTime = (long)(updateCount*(1E+3/MAX_UPS) - elapsedTime);
			}
			
			// calculate FPS
			elapsedTime = System.currentTimeMillis() - prevTime;
			if (elapsedTime >= 1000) {
				averageUPS = (long) (updateCount / (elapsedTime * 1E-3));
				averageFPS = (long) (frameCount / (elapsedTime * 1E-3));
				updateCount = 0;
				frameCount = 0;
				prevTime = System.currentTimeMillis();
			}
			
		}
	}

}
