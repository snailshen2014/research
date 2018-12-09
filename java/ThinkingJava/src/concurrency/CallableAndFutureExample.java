package concurrency;
import java.util.concurrent.*;

/**
 * CallableAndFutureExample.java
 * This program demonstrates how to execute value-returning tasks
 * and wait for the results available.
 * @author www.codejava.net
 */
public class CallableAndFutureExample {
	public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
 
        Future<Integer> sumResult = pool.submit(new SumCalculator(100000));
        Future<Integer> factorialResult = pool.submit(new FactorialCalculator(8));
 
 
        try {
 
            Integer sumValue = sumResult.get();
 
            System.out.println("Sum Value = " + sumValue);
 
            Integer factorialValue = factorialResult.get();
 
            System.out.println("Factorial Value = " + factorialValue);
 
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
 
        pool.shutdown();
    }
}
/**
 * FactorialCalculator.java
 * This class represents a task that computes and returns factorial value
 * of N numbers.
 * @author www.codejava.net
 */
 class FactorialCalculator implements Callable<Integer> {
    private int n;
 
    public FactorialCalculator(int n) {
        this.n = n;
    }
 
    public Integer call() {
        int result = 1;
 
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
 
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
 
        return result;
    }
}
/**
 * SumCalculator.java
 * This class represents a task that computes and returns value of
 * sum of N numbers.
 * @author www.codejava.net
 */
 class SumCalculator implements Callable<Integer> {
    private int n;
 
    public SumCalculator(int n) {
        this.n = n;
    }
 
    public Integer call() {
        int sum = 0;
 
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
 
        return sum;
    }
}