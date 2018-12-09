
public class TestStatic {
	static String a = "A";
	static String b;
	static {
		System.out.println("init b");
		System.out.println("a=" + a);
		b = "B";
	}
	public void Print() {
		System.out.println("begin");
		System.out.println("a=" + a +",b="+b);
	}
	public static void main(String[] args){
		TestStatic t = new TestStatic();
		t.Print();
	}
}
