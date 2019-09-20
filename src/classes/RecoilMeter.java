package classes;

public class RecoilMeter extends Thread {
	Player p;
	
	public RecoilMeter(Player p) {
		this.p = p;
		start();
	}
	
	public void run() {
		try {
				p.setRecoil(true);
				Thread.sleep(500);
				p.setRecoil(false);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
