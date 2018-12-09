package Thinking;

public class GenericMethods {
	public <T> void f (T x,T y,int z) {
		System.out.println(x.getClass().getName());
		System.out.println(y.getClass().getName());
		System.out.println(z);
	}
	public static void main (String[] args) {
		GenericMethods gm = new GenericMethods();
		gm.f("",2,2);
		gm.f(1,"",3);
		gm.f(1.0,"",2);
		gm.f(1.0F,"",1);
		gm.f('c',"",2);
		gm.f(gm,'c',3);
	}
}
