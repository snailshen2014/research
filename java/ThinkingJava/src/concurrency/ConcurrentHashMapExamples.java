package concurrency;
import java.util.*;
import java.util.concurrent.*;

 class WriterThread extends Thread {
    private ConcurrentMap<Integer, String> map;
    private Random random;
    private String name;
 
    public WriterThread(ConcurrentMap<Integer, String> map,
                        String threadName, long randomSeed) {
        this.map = map;
        this.random = new Random(randomSeed);
        this.name = threadName;
    }
 
    public void run() {
        while (true) {
            Integer key = random.nextInt(10);
            String value = name;
 
            if(map.putIfAbsent(key, value) == null) {
                long time = System.currentTimeMillis();
                String output = String.format("%d: %s has put [%d => %s]",
                                                time, name, key, value);
                System.out.println(output);
            }
 
 
            Integer keyToRemove = random.nextInt(10);
 
            if (map.remove(keyToRemove, value)) {
                long time = System.currentTimeMillis();
                String output = String.format("%d: %s has removed [%d => %s]",
                                                time, name, keyToRemove, value);
                System.out.println(output);
            }
 
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
 class ReaderThread extends Thread {
    private ConcurrentHashMap<Integer, String> map;
    private String name;
 
    public ReaderThread(ConcurrentHashMap<Integer, String> map, String threadName) {
        this.map = map;
        this.name = threadName;
    }
 
    public void run() {
        while (true) {
            ConcurrentHashMap.KeySetView<Integer, String> keySetView = map.keySet();
            Iterator<Integer> iterator = keySetView.iterator();
 
            long time = System.currentTimeMillis();
            String output = time + ": " + name + ": ";
 
            while (iterator.hasNext()) {
                Integer key = iterator.next();
                String value = map.get(key);
                output += key + "=>" + value + "; ";
            }
 
            System.out.println(output);
 
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
public class ConcurrentHashMapExamples {
	public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
 
        new WriterThread(map, "Writer-1", 1).start();
        new WriterThread(map, "Writer-2", 2).start();
 
        for (int i = 1; i <= 5; i++) {
            new ReaderThread(map, "Reader-" + i).start();
        }
    }
}
