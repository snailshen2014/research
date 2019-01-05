package concurrency;

/**
 * Business.java
 * This class is used to illustrate a deadlock situtation.
 * @author www.codejava.net
 */

//Java doesn’t have anything to escape deadlock state when it occurs, so you have to design your program to avoid deadlock situation. Avoid acquiring more than one lock at a time. If not, make sure that you acquire multiple locks in consistent order. In the above example, 
//you can avoid deadlock by synchronize two locks in the same order in both methods:
//public void foo() {
//    synchronized (lock1) {
//        synchronized (lock2) {
//            System.out.println("foo");
//        }
//    }
//}
// 
//public void bar() {
//    synchronized (lock1) {
//        synchronized (lock2) {
//            System.out.println("bar");
//        }
//    }
//}

public class DeadLock {
 
    private Object lock1 = new Object();
    private Object lock2 = new Object();
 
    public void foo() {
        synchronized (lock1) {
            synchronized (lock2) {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("foo");
            }
        }
    }
 
    public void bar() {
        synchronized (lock2) {
            synchronized (lock1) {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("bar");
            }
        }
    }
    /**
     * BusinessTest1.java
     * This program tests for deadlock situtation.
     * @author www.codejava.net
     */
    public static void main(String[] args) {
    	DeadLock business = new DeadLock();
    	 
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    business.foo();
                }
            }).start();
        }
 
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    business.bar();
                }
            }).start();
        }
    }
}
