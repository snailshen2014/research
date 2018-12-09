import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.GregorianCalendar;

public class TestDate {
	public static void main(String[] args) {
		Date begin = new Date();
		Calendar calendar = Calendar.getInstance();
        Date date =  calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date)); //2013/10/15 16:16:39
        
        
        //add 1 day
        calendar.add(Calendar.DATE, -1);
        Date date2 = calendar.getTime();
        System.out.println(dateFormat.format(date2)); //2013/10/15 16:16:39
        
        //add 1 month
        calendar.add(Calendar.MONTH, 0);
        Date date3 = calendar.getTime();
        System.out.println("month " + dateFormat.format(date3)); //2013/10/15 16:16:39
//        System.out.println(calendar.DAY_OF_MONTH);
        //compare date
        System.out.println(date.before(date2));
        System.out.println(date3.after(date));
        
        //hour
        calendar.add(Calendar.HOUR, -1);
        Date date4 = calendar.getTime();
        System.out.println("Before one hour:" + dateFormat.format(date4)); //2013/10/15 16:16:39
        
        //formate date
		Date activityEndDate = null;
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			activityEndDate = dateFormat2.parse("2017-01-16");
			System.out.println(dateFormat2.format(activityEndDate));
			//compare 
			System.out.println(activityEndDate.after(date4));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		//////////////string compere
		String cs = "2017-01-14";
		System.out.println(cs.compareTo("2017-01-16") > 0);
		System.out.println(cs.compareTo("2017-01-13") > 0);
		System.out.println(cs.compareTo("2017-01-14") );
		
		Date now = new Date();
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
		System.out.println(dateFormat3.format(now));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();   
		try {
			ca.setTime(format.parse("2017-03-10"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ca set date
		ca.setTime(date4);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
        System.out.println("===============last:"+last);
        
        Date end = new Date();
        System.out.println("Time cost:" + (end.getTime() - begin.getTime()));
	}
	
	

}
