package classes;

public class MovementDetector extends Thread {
	
	GamePanel gp;
	
	public MovementDetector(GamePanel gamepanel) {
		gp = gamepanel;
		start();
	}
	
	public void run() {
		try {
				gp.movementDetectCounterUpdate(true);
				gp.setIsMoved(true);
				Thread.sleep(300);
				if(gp.movementDetectorsCounter == 1) {
					gp.setIsMoved(false);
				}
				gp.movementDetectCounterUpdate(false);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
