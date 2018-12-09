package threadpool;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 固定大小的线程池
 * ClassName: FixedSizeThreadPool <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年8月26日 下午9:10:16 <br/> 
 * 
 * @author yanjunshen 
 * @version  
 * @since JDK 1.8
 */
public class FixedSizeThreadPool {
	//线程集合
	private List<Worker> workers;
	//仓库集合
	private BlockingQueue<Runnable> taskQueue;
	public FixedSizeThreadPool (int size,int queueSize) {
		super();
		if (size <=  0 || queueSize <= 0)
			throw new IllegalArgumentException("参数错误!");
		this.taskQueue = new LinkedBlockingQueue<>(queueSize);
		this.workers = new ArrayList<>();
		for (int i = 0 ; i < size; ++i) {
			workers.add( new Worker(this));
		}
	}
	public void shutDown () {
		this.working = false;
		//队列空；然后working=false
		for (Thread t : this.workers) {
			if (t.getState().equals(State.BLOCKED) ||t.getState().equals(State.TIMED_WAITING)
		||t.getState().equals(State.WAITING)) {
				t.interrupt();
			}
			
		}
	}
	/**
	 * 接受任务
	 * submit:(这里用一句话描述这个方法的作用). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author yanjunshen 
	 * @param task
	 * @return 
	 * @since JDK 1.8
	 */
	public boolean submit(Runnable task) {
		if (this.working)
			return this.taskQueue.offer(task);
		else return false;
	}
	private volatile boolean  working =  true;
	private static class Worker extends Thread {
		private FixedSizeThreadPool  pool;
		public Worker(FixedSizeThreadPool pool) {
			this.pool = pool;
		}
		public void run() {
			int count = 0;
			while (this.pool.working || !this.pool.taskQueue.isEmpty()) {
				Runnable task = null;
				try {
					if (this.pool.working)
						task = this.pool.taskQueue.take();
					else
						task = this.pool.taskQueue.poll();
					//if working=false ;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (task != null) {
					try {
						task.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
