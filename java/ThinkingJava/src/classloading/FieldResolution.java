package classloading;
/**
* 
* @des 如果有一个同名字段同时出现在C的接口和父类中，或者同时在
* 自己或者父类的多个接口中出现，那么编译器可能拒绝编译
* @author yanjunShen
* @date 2018年12月16日  新建
*/

public class FieldResolution {
	interface Interface0 {
		int A = 0;
	}
	interface Interface1 extends Interface0 {
		int A = 1;
	}
	interface Interface2 {
		int A = 2;
	}
	static class Parent implements Interface1 {
		public static int A = 3;
	}
	static class Sub extends Parent implements Interface2 {
		public static int A = 4;
	}
	public static void main (String[] args ) {
		System.out.println(Sub.A);
	}
}
