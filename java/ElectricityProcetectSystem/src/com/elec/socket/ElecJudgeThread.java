package com.elec.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.elec.domain.ElecEntity;
import com.elec.utils.CompareObject;
import com.elec.utils.DateTimeManager;
import com.elec.utils.FileManager;

/*
 * 服务器线程处理类
 */
public class ElecJudgeThread implements Runnable {
	// 站点信息
	private Map<Integer, ServerThread> points;
	// 比对信息
	private Map<String, String> pairs;
	// 跳闸信息
	private List<ServerThread> offMeters = new ArrayList<ServerThread>();
	private List<String> offIds = new ArrayList<String>();

	public ElecJudgeThread(Map<String, String> pairs, Map<Integer, ServerThread> points) {
		this.points = points;
		this.pairs = pairs;
	}

	public void run() {
		while (true) {
			
			try {
				Thread.sleep(1000 * 10 );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("ElecJudgeThread running,threads number:" + points.size());
			if (points.size() < 2)
				continue;
			
			
			//判断points的线程socket状态
			Iterator<Map.Entry<Integer, ServerThread>> iterThr = points.entrySet().iterator();
			while (iterThr.hasNext()) {
				Map.Entry<Integer, ServerThread> thr = iterThr.next();
				if (thr.getValue().socketStatus == 1) {
					System.out.println("id:" + thr.getKey() + " socket closed,thread will be remove.");
					
					iterThr.remove();	
				}
			}
			
			for (Map.Entry<String, String> entry : pairs.entrySet()) {
				String point = entry.getKey();
				String[] meters = entry.getValue().split(",");
				if (meters.length != 2) {
					System.out.println("[ElecJudgeThread]Point info config err:" + point);
					continue;
				}
				//得到电表数据比对
				ServerThread meterHandle = points.get(Integer.parseInt(meters[0]));
				ServerThread meterHandle2 = points.get(Integer.parseInt(meters[1]));
				if( meterHandle == null || meterHandle2 == null) {
					System.out.println("[ElecJudgeThread]Point:" + point + " no client connect, id:" + Arrays.toString(meters));
					continue;
				}
				
				
				List<ElecEntity> meterValue = new ArrayList<ElecEntity>();
				List<String> values = meterHandle.getElecData();
				List<String> values2 = meterHandle2.getElecData();
				if (values.isEmpty() || values2.isEmpty()) 
					continue;
				
				ElecEntity aMeter = new ElecEntity(values.get(0), values.get(1), values.get(2), values.get(3),
					Integer.toString(meterHandle.getRegisterSeq()));
				ElecEntity bMeter = new ElecEntity(values2.get(0), values2.get(1), values2.get(2), values2.get(3),
						Integer.toString(meterHandle2.getRegisterSeq()));
			
				
				System.out.println("[ElecJudgeThread] Compare elec data,point:[" + point + "]begin.******");
				// http://blog.csdn.net/qq_27093465/article/details/62453581
				try {
					Map<String, String> result = CompareObject.compare(aMeter, bMeter);
					if (result.isEmpty()) {
						System.out.println("[ElecJudgeThread] two object equals");
						continue;
					}
					//2安培跳闸
					Integer aV = (int)Float.parseFloat(aMeter.getaValue());
					Integer aV2 = (int)Float.parseFloat(bMeter.getaValue());
					Integer bV = (int)Float.parseFloat(aMeter.getbValue());
					Integer bV2 = (int)Float.parseFloat(bMeter.getbValue());
					Integer cV = (int)Float.parseFloat(aMeter.getcValue());
					Integer cV2 = (int)Float.parseFloat(bMeter.getcValue());
					if ( Math.abs(aV - aV2) >= 1 ||  
					     Math.abs(bV - bV2) >= 1 ||  Math.abs(cV - cV2) >= 1) {
						//已经发送过跳闸，不再发送
						if(offIds.contains(aMeter.getId())) {
							System.out.println("[ElecJudgeThread] point:" + point + ",Id:" + aMeter.getId() + ",已经发送过跳闸不再发送.");
							continue;
						}
						boolean send = false;
						for (Map.Entry<String, String> en : result.entrySet()) {
							System.out.println("[ElecJudgeThread] meter number:" + aMeter.getId() + "," + bMeter.getId() + "," + en.getKey()
								+ " no equal.values:" + aMeter + "|" + bMeter);
							if (send)
								break;
							// to steal_detail.txt steal.txt
							toStealFile(point,aMeter, bMeter, en.getKey());
							// 跳闸A端
							System.out.println("[ElecJudgeThread] meter number:" + aMeter.getId() + " 跳闸。");
							// 跳闸
							meterHandle.sendData(2);
							meterHandle.setOffTime(System.currentTimeMillis());
							offMeters.add(meterHandle);
						
							// 记录一下跳闸的id
							offIds.add(aMeter.getId());
							send = true;
						}//end for 循环对象比对结果
					} //end 如果差值是大于2安倍
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("[ElecJudgeThread] Compare elec data,point:" + point + "end.******");
			}

			
			//轮询跳闸信息，发送复位指令，如果跳闸超过30分钟
			System.out.println("[ElecJudgeThread] Deal reset before:" + offIds);
			Iterator<ServerThread> iter = offMeters.iterator();
			while (iter.hasNext()) {
				long currentTime = System.currentTimeMillis();
				ServerThread handle = iter.next();
				if (currentTime - handle.getOffTime() >= 30 * 60 * 1000) {
					System.out.println("[ElecJudgeThread] 发送复位指令,id:"+ handle.getRegisterSeq());
					//复位
					handle.sendData(3);
					
					//去掉offIds的id
					System.out.println("[ElecJudgeThread] handle id:" + handle.getRegisterSeq());
					offIds.remove(handle.getRegisterSeq().toString());
					//去掉offMeters
					iter.remove();
				}
			}
			System.out.println("[ElecJudgeThread] Deal reset after:" + offIds);
		
			
			
		}
	}

	public void toStealFile(String point,ElecEntity a, ElecEntity b, String field) {
		// to steal_detail.txt steal.txt
		String dir = "/usr/local/apache-tomcat-8.5.28/webapps/ElectricityProcetectSystem/data/";
		StringBuilder sb = new StringBuilder();
		sb.append(a.getT());
		sb.append("|");
		sb.append(a.getaValue());
		sb.append("|");
		sb.append(a.getbValue());
		sb.append("|");
		sb.append(a.getcValue());
		sb.append(";");
		sb.append(b.getT());
		sb.append("|");
		sb.append(b.getaValue());
		sb.append("|");
		sb.append(b.getbValue());
		sb.append("|");
		sb.append(b.getcValue());
		sb.append("\n");
		FileManager.toFile(dir + "steal_detail_" + point + ".txt", sb.toString());
		FileManager.toFile(dir + "steal.txt", "基站:" + point + ",表:" + a.getId() + "," + field + ",存在偷电行为。已经跳闸" + "\n");

	}
}
