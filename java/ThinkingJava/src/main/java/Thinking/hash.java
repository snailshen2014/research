package Thinking;

public class hash {
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

	public static void main (String[] args) {
		String ss = "Hello";
		String ss1 = "world";
		Integer i = 50;
		Float f = 60.0f;
		class A {
			
		}
		A a = new A();
		System.out.println(ss.hashCode() + "," + hash(ss));
		System.out.println(ss1.hashCode() + "," + hash(ss1));
		System.out.println(i.hashCode() + "," + hash(i));
		System.out.println(f.hashCode() + "," + hash(f));
		System.out.println(a.hashCode() + "," + hash(a));
	}
}
