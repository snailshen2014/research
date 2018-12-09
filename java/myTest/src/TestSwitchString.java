
public class TestSwitchString {
	public static  void test(String str) {
        switch(str) {
        case "abc":
            System.out.println("abc");
            break;
        case "def":
            System.out.println("def");
            break;
        default:
            System.out.println("default");
        }
    }
	public static void main(String[] args) {
		test("abc");
		test("def");
		test("ddd");
	}
}
