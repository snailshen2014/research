package classloading;

/**
 * @des  const class test
 * @author yanjunshen
 * @date 2018年12月15日 下午8:18:10
 */
public class ConstClass {
	static {
		System.out.println("Constclass init!!");
	}
	public static final String HELLOWORLD = "Hello world";
}
