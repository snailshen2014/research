/**
 * 
 */
package DynaProxy;

/**
 * @des:
 * @author shenyanjun1
 * @date: 2018年8月20日 上午10:15:02
 */
public class SubjectImpl implements Subject{

	/* (non-Javadoc)
	 * @see DynaProxy.Subject#rent()
	 */
	@Override
	public void rent() {
		// TODO Auto-generated method stub
		System.out.println("I want to rent my house.");
	}

	/* (non-Javadoc)
	 * @see DynaProxy.Subject#hello(java.lang.String)
	 */
	@Override
	public void hello(String str) {
		// TODO Auto-generated method stub
		System.out.println("Hello: " + str);
	}

}
