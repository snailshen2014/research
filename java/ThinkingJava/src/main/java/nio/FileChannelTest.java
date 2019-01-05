package nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FileChannelTest {
	public static void main (String[] args) {
		String txtFile = "/Users/yanjunshen/Documents/workspace/test/1.txt";
		try	{
			RandomAccessFile aFile = new RandomAccessFile(txtFile,"rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				System.out.println("Read " + bytesRead);
				buf.flip();
				
				
				while(buf.hasRemaining()) {
					byte[] dst = new byte[10];
//					System.out.println((char)buf.get());
					buf.get(dst);
					System.out.println(Arrays.toString(dst));
					
					
				}
				buf.clear();
				bytesRead = inChannel.read(buf);
			}
			aFile.close();
			
		} catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	
}
