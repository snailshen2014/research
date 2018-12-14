 /**
 * 
 */
package DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @des:
 * @author shenyanjun1
 * @date: 2018年8月20日 上午10:18:34
 */
public class Client {
	public static void main(String[] args) {
		Subject subjectImpl = new SubjectImpl();
		InvocationHandler handler = new DynamicProxy(subjectImpl);
		Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), subjectImpl.getClass().getInterfaces(), handler);
		System.out.println(subject.getClass().getName());
		subject.rent();
		subject.hello("world");
	}
}
