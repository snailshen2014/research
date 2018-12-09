import java.util.Arrays;

public class TestSplit {
	public static void main(String[] args) {
		String s = "2,3";
		String[] a = s.split(",");
		System.out.println(Arrays.toString(a));
		a = "liaocheng|1000,1001".split("\\|");
		System.out.println(Arrays.toString(a));
	}
}
