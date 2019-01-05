package HotSpot;

/**
 * 
 * @author yanjunshen
 *
 *javap -v StaticResolution.class 可以看到静态方法是通过invokestatic来实现的 p245
 *public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=0, locals=1, args_size=1
         0: invokestatic  #31                 // Method sayHello:()V
         3: return
      LineNumberTable:
        line 9: 0
        line 10: 3
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       4     0  args   [Ljava/lang/String;
}

 */
public class StaticResolution {
	public static void sayHello() {
		System.out.println("hello world");
	}
	
	public static void main(String[] args) {
		StaticResolution.sayHello();
	}
}
