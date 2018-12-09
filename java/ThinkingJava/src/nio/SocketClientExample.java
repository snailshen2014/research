package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class SocketClientExample {
	public void startClient()
            throws IOException, InterruptedException {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 9900);
        SocketChannel client = SocketChannel.open(hostAddress);
        System.out.println("Client... started");
        String threadName = Thread.currentThread().getName();
        // Send messages to server
        String [] messages = new String [] {threadName + ": test1",threadName + ": test2",threadName + ":test3"};
        for (int i = 0; i < messages.length; i++) {
            byte [] message = new String(messages [i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println(messages [i]);
            buffer.clear();
            Thread.sleep(5000);
            
            //read server responds
        	ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = client.read(buf);
            
			
			System.out.println("Read " + bytesRead);
			buf.flip();
			while(buf.hasRemaining()) {
				System.out.println((char)buf.get());
			}
			buf.clear();
			
			
            
        }
        
        client.close();
    }
	public static void main(String[] args) {
		try {
			new SocketClientExample().startClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
