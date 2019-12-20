package classes;

public class MovementDetector extends Thread {
	
	GamePanel gp;
	
	public MovementDetector(GamePanel gamepanel) {
		gp = gamepanel;
		start();
	}
	
	public void run() {
		try {
				System.out.println("starting");
				gp.movementDetectCounterUpdate(true);
				gp.setIsMoved(true);
				Thread.sleep(700);
				System.out.println("movementDetectorsCounter: " + gp.movementDetectorsCounter);
				if(gp.movementDetectorsCounter < 1) {
					Thread.sleep(200);
					gp.setIsMoved(false);
				}
				gp.movementDetectCounterUpdate(false);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
