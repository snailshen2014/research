package classloading;
public class NotInitialization {
	public static void main(String[] args) {
		//非主动使用字段演示
		System.out.println(Subclass.value);
		System.out.println("-----------------------");
		//通过数组定义来引用类，不会触发此类的初始化
		SuperClass[] sc = new SuperClass[10];
		//常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量池
		//的类，因此不会触发定义常量类的初始化
		System.out.println(ConstClass.HELLOWORLD);
	}
}
