package Thinking;

import java.util.Date;

public class TestStatic {
	static {
		int x = 5;
	}
	static int x,y;
	public static void main (String[] args) {
		x--;
		myMethod();
		System.out.println(x + y++ + x);
		
		
		int j = 0;
		for (int i = 0; i < 100; i++) {
			System.out.println("before j=" + j);
			j = j++;
			System.out.println("after j=" + j);
		}
		System.out.println(j);
		System.out.println("系统时间:" + new Date());
		
		
	}
	public static void myMethod() {
		y = x++ + ++x;
	}
}
