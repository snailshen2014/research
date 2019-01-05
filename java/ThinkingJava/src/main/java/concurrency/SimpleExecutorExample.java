package concurrency;

import java.util.concurrent.*;

/**
 * SimpleExecutorExample.java
 * This program demonstrates how to create a single-threaded executor
 * to execute a Runnable task.
 * @author www.codejava.net
 */
public class SimpleExecutorExample {
 
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
 
        Runnable task = new Runnable() {
            public void run() {
//            	while (true)
                System.out.println(Thread.currentThread().getName());
            }
        };
 
        pool.execute(task);
 
        pool.shutdown();
    }
}