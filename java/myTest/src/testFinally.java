
public class testFinally {
	public static  int  method () {
		try {
			System.out.println("try running...");
			return 1;
		} finally {
			System.out.println("Finally running...");
		}
		
	}
	public static void main (String[] args) {
		System.out.println(method());
		System.out.println("method finished.");
		
	}
}
