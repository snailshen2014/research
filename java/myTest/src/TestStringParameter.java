
public class TestStringParameter {
	public  String s = "before";
	public static void changeString(String ss) {
		ss = "changed";
	}
	
	public void print() {
		System.out.println(this.s);
	}
	public static void main (String[] args) {
		TestStringParameter t = new TestStringParameter();
		t.print();
		TestStringParameter.changeString(t.s);
		t.print();
	}
}
