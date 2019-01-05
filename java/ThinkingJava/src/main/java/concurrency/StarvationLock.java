package concurrency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*Starvation describes a situation where a greedy thread holds a resource for a long time so other threads are blocked forever. The blocked threads are waiting to acquire the resource but they never get a chance. Thus they starve to death.
Starvation can occur due to the following reasons:
- Threads are blocked infinitely because a thread takes long time to execute some synchronized code (e.g. heavy I/O operations or infinite loop).

- A thread doesn’t get CPU’s time for execution because it has low priority as compared to other threads which have higher priority.

- Threads are waiting on a resource forever but they remain waiting forever because other threads are constantly notified instead of the hungry ones.

When a starvation situation occurs, the program is still running but doesn’t run to completion because some threads are not executed.
*/
/**
 * Worker.java
 * This class is used to demonstrate starvation situation.
 * @author www.codejava.net
 */
 class Worker {
 
    public synchronized void work() {
        String name = Thread.currentThread().getName();
        String fileName = name + ".txt";
 
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        ) {
            writer.write("Thread " + name + " wrote this mesasge");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //一直占着cpu其它线程无法获取
        //如果避免这种情况可以添加如下代码 
        //In general, you should design your program to avoid starvation situation.
//        try {
//        	        wait(1000);
//        	    } catch (InterruptedException ex) {
//        	        ex.printStackTrace();
//        	    }
        while (true) {
            System.out.println(name + " is working");
        }
    }
}
 /**
  * StarvationExample.java
  * This class is used to demonstrate starvation situation.
  * @author www.codejava.net
  */
public class StarvationLock {
	public static void main(String[] args) {
        Worker worker = new Worker();
 
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    worker.work();
                }
            }).start();
        }
    }
}
