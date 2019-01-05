package Thinking;

import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.Timer;

/**
 * 
 * ClassName: AnoymousInnerClassTest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年3月24日 下午4:31:05 <br/> 
 * 
 * @author yanjunshen 
 * @version  
 * @since JDK 1.8
 */
public class AnoymousInnerClassTest {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock();
		clock.start(1000, true);
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}
	/**
	 * A clock that prints the time in regular intervals.
	 * ClassName: TalkingClock <br/> 
	 * Function: TODO ADD FUNCTION. <br/> 
	 * Reason: TODO ADD REASON(可选). <br/> 
	 * date: 2018年3月24日 下午4:32:03 <br/> 
	 * 
	 * @author yanjunshen 
	 * @version AnoymousInnerClassTest 
	 * @since JDK 1.8
	 */
	class TalkingClock {
		public void start(int interval,final boolean beep) {
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Date now = new Date();
					System.out.println("At the tone,the time is "  + now);
					if(beep) 
						Toolkit.getDefaultToolkit().beep();
				}
			};
			Timer t = new Timer(interval,listener);
			t.start();
		}
	}

