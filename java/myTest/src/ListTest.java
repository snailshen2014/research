import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		// ArrayList add
		long startTime = System.nanoTime();

		for (int i = 0; i < 100000; i++) {
			arrayList.add(i);
		}
		System.out.println(arrayList);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("ArrayList add:  " + duration);
		
		// LinkedList add
		startTime = System.nanoTime();

		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}
		
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList add: " + duration);

		// ArrayList get
		startTime = System.nanoTime();

		for (int i = 0; i < 10000; i++) {
			arrayList.get(i);
			
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ArrayList get:  " + duration);

		// LinkedList get
		startTime = System.nanoTime();

		for (int i = 0; i < 10000; i++) {
			linkedList.get(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList get: " + duration);

		// ArrayList remove
		startTime = System.nanoTime();

		for (int i = 9999; i >= 0; i--) {
			arrayList.remove(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ArrayList remove:  " + duration);

		// LinkedList remove
		startTime = System.nanoTime();

		for (int i = 9999; i >= 0; i--) {
			linkedList.remove(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList remove: " + duration);
		
		ArrayList<String> ssList = new ArrayList<String>();
		ssList.add("my");
		ssList.add("you");
		System.out.println("ArrayList contains:  " + ssList.contains("my"));
		System.out.println("ArrayList contains:  " + ssList.contains("aa"));
		System.out.println("ArrayList contains:  " + ssList.contains(null));
		ArrayList<String> ssList2 = new ArrayList<String>();
		System.out.println("ArrayList2 contains:  " + ssList2.contains("my"));
		System.out.println("ArrayList2 contains:  " + ssList2.contains("aa"));
		System.out.println("ArrayList2 contains:  " + ssList2.contains(null));
		
		List<String> off = new ArrayList<>();
		off.add("1000");
		System.out.println(off);
		off.remove("1000");
	
		System.out.println(off);
		
	}
}
