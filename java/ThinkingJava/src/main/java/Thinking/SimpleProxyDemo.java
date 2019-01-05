package Thinking;
//:typeinfo/SimpleProxyDeomo.java

interface Interface {
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface {
	public void doSomething() { System.out.println("doSomething");}
	public void somethingElse(String arg) { System.out.println("somethingElse " + arg);
	}
}
class SimpleProxy implements Interface {
	private Interface proxied;
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	public void doSomething() {
		System.out.println("SimpleProxy doSomething");
		proxied.doSomething();
	}
	public void somethingElse(String arg) {
		System.out.println("SimpleProxy somethingElse " + arg);
		proxied.somethingElse(arg);
	}
}

public   class SimpleProxyDemo {
	public   void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("Hello shenyj at 2017-3-01 08:07:58");
	}
	public static void main(String[] args) {
		SimpleProxyDemo demo = new SimpleProxyDemo();
	
		demo.consumer(new RealObject());
		demo.consumer(new SimpleProxy(new RealObject()));
	}


}



