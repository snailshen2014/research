package concurrency;

public class ThreadExample2 implements Runnable {
	public void run() {
		System.out.println("My name is:" + Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		Runnable task = new ThreadExample2();
		Thread t2 = new Thread(task);
		t2.start();
		System.out.println("My name2 is:" + Thread.currentThread().getName());
	}
}
//The two programs behave the same. So what are the pros and cons of these two ways of creating a thread?
//Here’s the answer:
//- Extending the Thread class can be used for simple cases. It cannot be used if your class needs to extend another class because Java doesn’t allow multiple inheritances of class.
//
//- Implementing the Runnable interface is more flexible as Java allows a class can both extend another class and implement one or more interfaces.
