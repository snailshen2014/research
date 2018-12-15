package classloading;

/**
 * 被动使用类字段演示1
 * 通过子类引用父类的静态字段，不会导致子类初始化
 * @author yanjunshen
 *
 */
public class SuperClass {
	static {
		System.out.println("Superclass init!");
	}
	public static int value = 123;
}
class Subclass extends SuperClass{
	static {
		System.out.println("Subclass init!");
	}
}
