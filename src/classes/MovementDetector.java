package classes;

public class MovementDetector extends Thread {
	
	public MovementDetector() {
		start();
	}
	
	public void run() {
		try {
			Thread.sleep(26);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
