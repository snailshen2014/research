
public class TestString {
	

	public static void main(String[] args) {
		Stuff s = new Stuff();
		System.out.println(s.a);
		System.out.println(s.b);

		if (s.b.equals("")) {
			System.out.println("true");
		}
		System.out.println("Hi" + s.a);
		
		String sTest = "abc,123,cdf, ";
		String s2Test = "abc and 123 and cdf and ";
		System.out.println(sTest.substring(0, sTest.lastIndexOf(",")));
		System.out.println(s2Test.substring(0, s2Test.lastIndexOf("and")));
		System.out.println(sTest.lastIndexOf(",") + " "+ s2Test.lastIndexOf("and"));
		
		String aaa = null;
		aaa += " test null+";
		System.out.println("Test NULL:" + aaa);
	}

}
class Stuff {
	String a;
	String b = new String();
	
}