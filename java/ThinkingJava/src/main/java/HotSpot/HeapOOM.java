package HotSpot;
//-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	static class OOMObject {
		
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		int c  = 0;
		while (true) {
			System.out.println("create a object ,number :" + c++);
			list.add(new OOMObject());
		}
	}
}
