package concurrency;
import java.util.concurrent.*;

/**
 * BeepClock.java
 *
 * This program demonstrates how to schedule a task to execute after
 * an initial delay, and repeat after a fixed rate.
 *
 * @author www.codejava.net
 */
//http://www.codejava.net/java-core/concurrency/java-concurrency-scheduling-tasks-to-execute-after-a-given-delay-or-periodically
public class ScheduledExecutorServiceTest implements Runnable {

	public void run() {
		System.out.println(System.currentTimeMillis() + "DDDD");
		System.out.print("\007");
	}

	public static void main(String[] args) {
		ScheduledExecutorService scheduler
							= Executors.newSingleThreadScheduledExecutor();


		Runnable task = new ScheduledExecutorServiceTest();
		int initialDelay = 4;
		int periodicDelay = 2;

		scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,
														TimeUnit.SECONDS);
	}
}

