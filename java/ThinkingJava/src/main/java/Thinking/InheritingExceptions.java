package Thinking;
class SimpleException extends Exception {
	
}
public class InheritingExceptions {
	public void f() throws SimpleException {
		System.out.println("Throws SimpleException from f()");
		throw new SimpleException();
	}
	public static void main(String[] args) {
		InheritingExceptions in = new InheritingExceptions();
		try {
			in.f();
		}catch (SimpleException e) {
			System.out.println("Caught it");
		}
	}
}
