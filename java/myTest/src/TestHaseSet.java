import java.util.HashSet;

public class TestHaseSet {

		public static void main(String[] args) {
			int num = (int) (5000000/0.75);
			HashSet<String> set = new HashSet<String>(num);
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long start = System.currentTimeMillis();
			System.out.println(start);
			for(long  i = 13800000000l;i < 13805000000l;i++){
				set.add("" + i);
			}
			long end = System.currentTimeMillis();
			System.out.println(end);
			System.out.println(end-start);
		}

}
