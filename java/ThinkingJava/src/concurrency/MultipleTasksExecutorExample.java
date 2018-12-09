package concurrency;

import java.util.concurrent.*;

/**
 * MultipleTasksExecutorExample.java
 * This program demonstrates how to execute multiple tasks
 * with different kinds of executors.
 * @author www.codejava.net
 */
public class MultipleTasksExecutorExample {
 
    public static void main(String[] args) {
 
//    	ExecutorService pool = Executors.newCachedThreadPool();
    	
    	//Fixed Thread Pool Executor Example
//    	ExecutorService pool = Executors.newFixedThreadPool(2);
    	
    	//Single-threaded Pool Executor Example
//    	ExecutorService pool = Executors.newSingleThreadExecutor();
       
        
        //7. Creating a Custom Thread Pool Executor
//    	In case you want to have more control over the behaviors of a thread pool, you can create a thread pool executor directly from the ThreadPoolExecutor 
//    	class instead of the factory methods of the Executors utility class.
    	
        int corePoolSize = 10;
        int maxPoolSize = 1000;
        int keepAliveTime = 120;
        BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();
         
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
                                 maxPoolSize,
                                 keepAliveTime,
                                 TimeUnit.SECONDS,
                                 workQueue);
   
        pool.execute(new CountDownClock("A"));
        pool.execute(new CountDownClock("B"));
        pool.execute(new CountDownClock("C"));
        pool.execute(new CountDownClock("D"));
 
        pool.shutdown();
    }
}