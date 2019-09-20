package classes;

public class MovementDetector extends Thread {
	
	GamePanel gp;
	
	public MovementDetector(GamePanel gamepanel) {
		gp = gamepanel;
		start();
	}
	
	public void run() {
		try {
				gp.setIsMoved(true);
				Thread.sleep(300);
				gp.setIsMoved(false);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
