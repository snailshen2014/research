package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable{
	public final Selector selector;
	public final ServerSocketChannel serverSocketChannel;
	public final boolean isWithThreadPool;
	/*Reactor的主要工作： 
     * 1.给ServerSocketChannel设置一个Acceptor，接收请求 
     * 2.给每一个一个SocketChannel（代表一个Client）关联一个Handler 
     * 要注意其实Acceptor也是一个Handler（只是与它关联的channel是ServerSocketChannel而不是SocketChannel） 
     */  
	public Reactor(int port,boolean isWithThreadPool) throws IOException {
		this.isWithThreadPool = isWithThreadPool;
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);
		SelectionKey selectionKey0 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		selectionKey0.attach(new Acceptor());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Server listening to port" + serverSocketChannel.socket().getLocalPort());
		try {
			while (!Thread.interrupted()) {
				int readySelectionKeyCount = selector.select();
				if (readySelectionKeyCount == 0)
					continue;
				Set<SelectionKey> selected = selector.selectedKeys();
				Iterator<SelectionKey> it = selected.iterator();
				while (it.hasNext()) {
					dispatch((SelectionKey)it.next());
				}
				//不会自动remove，因此要手动清；下次事件到来会自动添加  
				selected.clear();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	//从SelectionKey中取出Handler并执行Handler的run方法，没有创建新线程  
    void dispatch(SelectionKey k) { 
    	System.out.println("Reactor dispatch key:" + k);
        Runnable r = (Runnable) (k.attachment());  
        if (r != null) {  
            r.run();  
        }   
    } 
//    /主要工作是为每一个连接成功后返回的SocketChannel关联一个Handler，详见Handler的构造函数  
    class Acceptor implements Runnable {  
        public void run() {  
            try {  
                SocketChannel socketChannel = serverSocketChannel.accept();  
                if (socketChannel != null) {  
                    if (isWithThreadPool)  
                        new HandlerWithThreadPool(selector, socketChannel);  
                    else  
                        new Handler(selector, socketChannel);  
                }  
                System.out.println("Connection Accepted by Reactor2");  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
    }  
      
    public static void main(String[] args) throws IOException{  
          
        int port = 9900;  
        boolean withThreadPool = false;  
        Reactor reactor  = new Reactor(port, withThreadPool);  
        new Thread(reactor).start();  
    }  
	
}
