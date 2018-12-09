
class SingleTon {
	
    	private static SingleTon singleTon = new SingleTon();
    	
    	public static int count1;
    	public static int count2 = 0;

    	private SingleTon() {
    		System.out.println("Constructor...");
    		count1++;
    		count2++;
    	}

    	public static SingleTon getInstance() {
    		return singleTon;
    	}
    }

public class testThread {
	
	
	public static void main(String args[]) {

        Thread t = new Thread() {

            public void run() {
                pong();
            }
        };

        t.run();//pongping
        //t.start();//pingpong
        System.out.println("ping");
        
        
        SingleTon singleTon = SingleTon.getInstance();
		System.out.println("count1=" + singleTon.count1);
		System.out.println("count2=" + singleTon.count2);

    }

    static void pong() {

        System.out.println("pong");

    }
    
    
    

    
}
