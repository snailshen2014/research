
public class ExchangeValue {
	public static void swap(StringBuffer s1, StringBuffer s2) {
	    StringBuffer temp = s1;
	    s1 = s2;
	    s2 = temp;
	}


	public static void main(String[] args) {
	    StringBuffer s1 = new StringBuffer("Hello");
	    StringBuffer s2 = new StringBuffer("World");
	    swap(s1, s2);
	    System.out.println(s1);
	    System.out.println(s2);
	}
}
