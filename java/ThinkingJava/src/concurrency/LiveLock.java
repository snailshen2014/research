package concurrency;

/**
 * Criminal.java This class is used to demonstrate livelock situation
 * 
 * @author www.codejava.net
 */
class Criminal {
	private boolean hostageReleased = false;

	public void releaseHostage(Police police) {
		while (!police.isMoneySent()) {

			System.out.println("Criminal: waiting police to give ransom");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		System.out.println("Criminal: released hostage");

		this.hostageReleased = true;
	}

	public boolean isHostageReleased() {
		return this.hostageReleased;
	}
}

/**
 * Police.java This class is used to demonstrate livelock situation
 * 
 * @author www.codejava.net
 */
class Police {
	private boolean moneySent = false;

	public void giveRansom(Criminal criminal) {

		while (!criminal.isHostageReleased()) {

			System.out.println("Police: waiting criminal to release hostage");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		System.out.println("Police: sent money");

		this.moneySent = true;
	}

	public boolean isMoneySent() {
		return this.moneySent;
	}

}

public class LiveLock {
	public static void main(String[] args) {

		Police police = new Police();

		Criminal criminal = new Criminal();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				police.giveRansom(criminal);
			}
		});
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				criminal.releaseHostage(police);
			}
		});
		t2.start();
	}

}
