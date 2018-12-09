package thread.demo_026;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 * @author Jcon
 *
 */
public class T01_MyExecutor implements Executor{

	public static void main(String[] args) {

		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	//在java线程池里面的顶层方法
	//Exector里面的ececute
	@Override
	public void execute(Runnable command) {

		command.run();
	}
}
