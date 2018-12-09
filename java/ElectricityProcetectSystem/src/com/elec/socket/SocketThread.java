package com.elec.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;

import com.elec.domain.ElecEntity;

class SocketThread extends Thread {
	Integer count = 0;
	private ServletContext servletContext;
	private ServerSocket serverSocket;
	private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	//站点信息
	private Map<Integer,ServerThread> points = new ConcurrentHashMap<>();
	//比对关系，配置信息meters.txt
	private Map<String,String> metersPairs = new HashMap<>();
	
	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.servletContext = servletContext;
		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		if (serverSocket == null) {
			try {
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//获取电表节点逻辑序列号 （ElectricityProcetectSystem/data/meters.txt）
		initElectircMeters();
		//for local test
//		pointmeters.add("2");
//		pointmeters.add("3");
		//启动电量比对线程
		ElecJudgeThread elecJudge = new ElecJudgeThread(metersPairs,points);
		cachedThreadPool.execute(elecJudge);
	}

	public void run() {
		System.out.println("Server SocketThread start:" + count);
		try {
			Integer count = 0;
			String meters;
			Integer pointCount = 0;
			String[] meterArray = null;
			List<Map<Integer,ServerThread> >lMeters = null;
			while (!this.isInterrupted()) {
				Socket socket = serverSocket.accept();
				count++;
				if (socket != null) {
					ServerThread clientThread = new ServerThread(socket);
					System.out.println("new commer:" + socket.getRemoteSocketAddress().toString());
					cachedThreadPool.execute(clientThread);
					//wait socket client register pacakage finished
					while (true) {
						if(clientThread.getRegisterStatus()) {
							points.put(clientThread.getRegisterSeq(), clientThread);
							break;
						} else {
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}

	public void closeServerSocket() {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
				// MyServer.destroyedTimer();
			}

		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}
	private void initElectircMeters() {
		
		File dataFile = new File(this.servletContext.getRealPath("/") + "data/meters.txt");
		StringBuilder result = new StringBuilder();
        List<ElecEntity> elecEntitys = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(dataFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	//获取站点信息liaocheng|1000,1001
            	String[] sa = s.split("\\|");
            	System.out.println("initElectircMeters, point:" + sa[0] + ",meters:" + sa[1]);
            	metersPairs.put(sa[0], sa[1]);
            }
            br.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	
}