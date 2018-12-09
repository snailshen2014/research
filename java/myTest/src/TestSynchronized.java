
public class TestSynchronized implements Runnable {
	private int b = 100;

	public void m1() {
		synchronized (this) {
			int i = 0;
			while (i++ < 300) {
				b = i;
				System.out.println("##b1 = " + b);
			}
		}

	}

	public void m2() {
		System.out.println("m2 running...");
		 synchronized (this) {
			 System.out.println("**b2 = " + b);
		 }
	}
	public void m3() {
		System.out.println("m3 running...");
		 synchronized (this) {
			 System.out.println("**b3 = " + b);
		 }
	}
	public void run() {
		this.m1();
	}

	public static void main(String[] args) throws Exception {
		TestSynchronized t = new TestSynchronized();
		Thread t1 = new Thread(t);
		t1.start();
		// Thread.sleep(500);
		t.m2();
		t.m3();
	}
}
