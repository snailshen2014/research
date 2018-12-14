/**
 * 
 */
package DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @des:
 * @author shenyanjun1
 * @date: 2018年8月20日 上午10:16:13
 */
public class DynamicProxy implements InvocationHandler{
	private Object subject;
	public DynamicProxy(Object subject) {
		this.subject = subject;
	}
	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before rent house.");
		System.out.println("Method:" + arg1);
		arg1.invoke(subject, arg2);
		System.out.println("after rent house.");
		return null;
	}

}
