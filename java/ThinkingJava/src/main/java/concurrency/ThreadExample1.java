package concurrency;

public class ThreadExample1 extends Thread{
	public void run() {
		System.out.println("My name is:" + Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		ThreadExample1 t1 = new ThreadExample1();
		t1.start();
		System.out.println("My name2 is:" + Thread.currentThread().getName());
	}
}

