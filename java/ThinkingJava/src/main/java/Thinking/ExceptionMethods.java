package Thinking;

public class ExceptionMethods {
	public static void main (String[] args) {
		try {
			throw new Exception ("My Exception1.");
		}catch (Exception e) {
			System.out.println("Caught My Exception1.");
			System.out.println("getMessage():" + e.getMessage());
			System.out.println("getLocalizedMessage:" + e.getLocalizedMessage());
			System.out.println("toString():" + e);
			System.out.println("printStackTrace():" );
			e.printStackTrace(System.out);
			try {
				throw new Exception("My Exception2");
			} catch (Exception e2) {
				System.out.println("Caught My Exception2.");
			}
			
			
		}
		finally {
			try {
				throw new Exception("My Exception3 finally");
			} catch (Exception e3) {
				System.out.println("Caught Exception3");
			}
		}
		
	}
}
