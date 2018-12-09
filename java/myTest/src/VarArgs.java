class A{}
public class VarArgs {
	static void printArray(Object[] args) {
		for (Object obj : args)
			System.out.print(obj + " ");
		System.out.println();
	}
	static void printArrayNew(Object ...args){
		for (Object obj : args)
			System.out.print(obj + " ");
		System.out.println();
	}
	public static void main (String[] args) {
		printArray(new Object[]{
					new Integer(47),new Float(3.14),new Double(11.11)
		});
		printArray(new Object[] {
				"one","two","three"
		});
		printArray(new Object[]{
				new A(),new A(),new A()
		});
		System.out.println("new.............");
		printArrayNew(new Object[]{
				new Integer(47),new Float(3.14),new Double(11.11)
		});
		printArrayNew(new Object[] {
			"one","two","three"
		});
		printArrayNew(new Object[]{
			new A(),new A(),new A()
		});
		printArrayNew();
		printArrayNew("abc",45,6.8);
	}
}
