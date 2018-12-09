
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;





import java.util.Set;
import java.util.TreeMap;

public class MyTest {
//	private static final boolean True = false;
//	private final static Logger log= LoggerFactory.getLogger(MyTest.class);
	int i;

	MyTest() {
		System.out.println("i=" + i);
		i = 8;
	}

	public static void map1() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 10; ++i)
			map.put(Integer.toString(i), Integer.toString(i) + "v");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

	public static void map2() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 10; ++i)
			map.put(Integer.toString(i), Integer.toString(i) + "s");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

	}

	public static void list1() {
		List<String> fruits = new ArrayList<>();
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Strawberry");
		for (int i = 0; i < fruits.size(); i++) {
			System.out.println(fruits.get(i));
			if ("Apple".equals(fruits.get(i))) {
				fruits.remove(i);
			}
		}
		for (String fruit : fruits) {
			System.out.println("for:" + fruit);
			if ("Apple".equals(fruit)) {
				fruits.remove(fruit);
			}
		}
		Iterator<String> fruitIterator = fruits.iterator();
		while (fruitIterator.hasNext()) {
			String fruit = fruitIterator.next();
			System.out.println("Iterator:" + fruit);
			if ("Apple".equals(fruit)) {
				fruitIterator.remove();
			}
		}
	}

	public static String getReverseCondition(String con, int num) {

		String temp = null;
		if (con.indexOf("=") != -1)
			temp = con.replace("=", " != ");
		if (con.indexOf("!=") != -1)
			temp = con.replace("!=", " = ");
		if (con.indexOf("<") != -1)
			temp = con.replace("<", " > ");
		if (con.indexOf(">") != -1)
			temp = con.replace(">", " < ");

		if (num > 1)
			return " and " + temp;
		else

			return temp;

	}

	public static void main(String[] args) {
		String json = "filterCondition合约类型】=单卡lterConditionSql ACTIVITY_TYPE = '04'";
		System.out.println(json.replace("'", "\\'"));

		// case when

		/*
		 * "ord":"0", "recommenedInfo":"更换4G终端"
		 * 
		 * "ord":"2", "filterConditionSql":" ACTIVITY_TYPE = '04'",
		 * "recommenedInfo":"333" "ord":"1",
		 * "recommenedInfo":"请推荐用户参加购机送费、订业务送机、合约惠机活动"
		 * "filterConditionSql":" ACTIVITY_TYPE = '04'",
		 */
		/*
		 * SELECT REC_ID,( CASE WHEN CHANNEL_ID='5' THEN 'a' WHEN
		 * ACTIVITY_SEQ_ID=80 THEN 'b' WHEN ACTIVITY_SEQ_ID=90 THEN 'c' ELSE 'd'
		 * 
		 * END ) AS COMM, USER_ID FROM PLT_ORDER_INFO;
		 */
		
		
		Map<String, FilterCon> filterMap = new TreeMap<String, FilterCon>();
		FilterCon filter = new FilterCon();
		filter.setSql("CHANNEL_ID='5'");
		filter.setRecommend("a");
		filterMap.put("0", filter);

		FilterCon filter2 = new FilterCon();
		filter2.setSql("ACTIVITY_SEQ_ID=80");
		filter2.setRecommend("b");
		filterMap.put("2", filter2);
		System.out.println("size=" + filterMap.size());

		FilterCon filter3 = new FilterCon();
		filter3.setSql("ACTIVITY_SEQ_ID=90");
		filter3.setRecommend("c");
		filterMap.put("1", filter3);

		FilterCon filter4 = new FilterCon();
		filter4.setSql("ACTIVITY_SEQ_ID=100");
		filter4.setRecommend("d");
		filterMap.put("3", filter4);

		String tempCon = "";

		StringBuilder caseWhenSql = new StringBuilder();
		if (filterMap.size() == 1) {
			caseWhenSql.append("( case when " + filterMap.get("0").getSql());
			caseWhenSql.append(" then " + "'" + filterMap.get("0").getRecommend() + "'" + " end) as RECOMMEND");
		} else {
			int i = 0;
			int j = 0;
			String and = " and ";
			caseWhenSql.append("(");
			for (Map.Entry<String, FilterCon> entry : filterMap.entrySet()) {
				if (++i == 1)
					continue;
				// caseWhenSql.append
				caseWhenSql.append("case when " + tempCon);
				if (!tempCon.equals(""))
					caseWhenSql.append(and);

				caseWhenSql.append(entry.getValue().getSql());
				caseWhenSql.append(" then " + "'" + entry.getValue().getRecommend() + "' ");

				tempCon += MyTest.getReverseCondition(entry.getValue().getSql(), ++j);

			}
			caseWhenSql.append(" else " + "'" + filterMap.get("0").getRecommend() + "'");
			caseWhenSql.append(" end) as RECOMMEND");

		}
		System.out.println("sql=" + caseWhenSql.toString());

		String sdate = "20161228";
		System.out.println(sdate.substring(0, 6) + "|" + sdate.substring(0, 8));

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");// 可以方便地修改日期格式
		System.out.println(dateFormat.format(now));

		/////////// string test
		String sss = "1234567890asdf";
		System.out.println(sss.length() + "|" + sss.substring(0, 3) + "|" + sss);

		// test string + null
		String abc = null;
		System.out.println(abc);
		String cd = "cd";
		cd += abc;
		System.out.println(cd);
		if (2 > 3) {
			System.out.println("2>1");
		} else if (2 < 3)
			System.out.println("2<1");

		Set<User> s = new HashSet<User>(2);
		System.out.println(s.isEmpty());
		for (int j = 0; j < 10; j++) {
			User u = new User();
			u.setName("name" + j);
			s.add(u);
		}
		System.out.println(s.size());
		//System.out.println(s.contains(u));
		//for test list
		List<String> ls = new ArrayList<String>();
		ls.add("55");
		ls.add("66");
		ls.add("55");
		ls.add("44");

		System.out.println(ls);
		Set<String> ss = new HashSet<String>();
		ss.add("55");
		ss.add("66");
		ss.add("55");
		ss.add("44");
		System.out.println(ss);
		
		///////////////////////////
		String ts = " 2017-01-19 ";
		System.out.println(ts.length());
		System.out.println(ts.trim().length());
		String ssss = "113|456|abc|d38|jjj|,";
		
		String [] sList = ssss.split(",");
		System.out.println(sList.length);
		 for(int i=0;i<sList.length;i++)
		   {
		    System.out.println(sList[i]);//输出结果 1 2 3
		   }
		 
		 
		 Integer acd = 22222222;
		 System.out.println(acd.toString() + "," + acd);
		 String splitString = "abcdBBA1323";
		 System.out.println(splitString.split("BBA")[0]);
		 System.out.println(splitString.split("BBA")[1]);
		 System.out.println("".indexOf("DDD"));
		 Integer a = 4;
//		 Integer b = 5;
		 Integer a1 = 4;
		 if (a == a1) {
			 System.out.println("a<b");
		 }
		 
		 String se= "0870";
		 System.out.println(se.substring(0,2) + "   " + se.substring(2,se.length()));
		 String stt = "01040c000000000000000002006600bfaf";
		 String aValue = stt.substring(6,10);
			String bValue = stt.substring(10,14);
			String cValue = stt.substring(14,18);
			String xiaoshu = stt.substring(22,24);
			String status = stt.substring(24,26);
			String crc = stt.substring(30,stt.length());
			System.out.println(aValue + " " + bValue +" " + cValue + " " + xiaoshu + " " + status + " "
						+ crc); 
			System.out.println(stt.replace("bfaf", ""));
		
	}
	
	
}

class FilterCon {
	private String sql;
	private String recommend;

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return this.sql;
	}

	public void setRecommend(String s) {
		this.recommend = s;
	}

	public String getRecommend() {
		return this.recommend;
	}

}
