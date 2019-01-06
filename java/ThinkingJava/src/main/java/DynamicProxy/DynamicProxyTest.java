package DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 
 * @author yanjunshen
 *
 */
public class DynamicProxyTest {
	interface IHello {
		void sayHello();
		void sayGoodBye(String boy);
	}
	static class Hello implements IHello {
		@Override
		public void sayHello() {
			System.out.println("hello world");
		}

		@Override
		public void sayGoodBye(String boy) {
			System.out.println("Good bye:" + boy);
			
		}
	}
	
	static class DynamicProxy implements InvocationHandler {
		Object originalObj;
		
		Object bind(Object originalObj) {
			this.originalObj = originalObj;
			return Proxy.newProxyInstance(this.originalObj.getClass().getClassLoader(), 
										this.originalObj.getClass().getInterfaces(), this);
			
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("welcome");
			return method.invoke(this.originalObj, args);
		}
	}
	
	public static void main(String[] args) {
		IHello hello = (IHello) new DynamicProxy().bind(new Hello());
		hello.sayHello();
		hello.sayGoodBye("Maya");
	}
}
