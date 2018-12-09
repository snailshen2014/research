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
import java.util.List;

import com.elec.utils.DateTimeManager;
import com.elec.utils.FileManager;

/*
 * 服务器线程处理类
 */
public class ServerThread implements Runnable {
	// 和本线程相关的Socket
	Socket socket = null;
	InputStream is = null;
	OutputStream os = null;
	Integer hostSeq = null;
	public volatile int socketStatus = 0;
	long offTime ;
	boolean isRegister;
	//最后数据
	List<String> lastElec = new ArrayList<String>();
	public synchronized List<String> getElecData () {
		return lastElec;
	}
	public synchronized void saveElecData (String t,String a,String b,String c) {
		lastElec.clear();
		lastElec.add(t);
		lastElec.add(a);
		lastElec.add(b);
		lastElec.add(c);
	
	}
	public synchronized void setOffTime (long t) {
		this.offTime = t;
	}
	public synchronized long getOffTime () {
		return this.offTime ;
	}
	
	public synchronized boolean getRegisterStatus () {
		return this.isRegister ;
	}
	
	public synchronized void setRegisterStatus (boolean b) {
		isRegister = b;
	}
	
	public synchronized Integer getRegisterSeq () {
		return this.hostSeq;
	}
	public synchronized void setRegisterSeq (Integer seq) {
		 this.hostSeq = seq;
	}
	public ServerThread(Socket socket) {
		this.socket = socket;
		this.hostSeq = 0;
		offTime = 0l;
		isRegister = false;
	}

	// 线程执行的操作，响应客户端的请求
	public void run() {
		try {
			// 获取输入流，并读取客户端信息
			is = socket.getInputStream();
			os = socket.getOutputStream();
			while (true) {
				if (socket.isClosed()) {
					System.out.println(socket.getRemoteSocketAddress() + "Closed.");
					socketStatus = 1;//1 closed
					break;
				}
				if (this.socketStatus == 1) {
					System.out.println(socket.getRemoteSocketAddress() + "Closed.");
					break;
				}
				if(this.isRegister) {
					//send 读数
					sendData(1);
					//sleep
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int size = 0;
				size = is.available();
				if (size == 0) {
					System.out.println(socket.getRemoteSocketAddress() + " no data to read.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				System.out.println("Received data size=" + size);
				byte[] data = new byte[size];
				is.read(data);
				
				String strPackage = bytesToHexString(data);
				System.out.println("Received data   package:" + strPackage );
				
				String heartbeat = "7777772e7573722e636e";
				strPackage = strPackage.replace(heartbeat, "");
				
				//注册包ZC1000-ZC9999
				if (!isRegister && strPackage.length() == 6) {
					setRegisterSeq(Integer.parseInt(strPackage.substring(2,6)));
					setRegisterStatus(true);
					System.out.println(socket.getRemoteSocketAddress() +
										" already register ,seq:" + this.hostSeq);
					continue;
					
				}
				if (strPackage.length() < 16 )
					continue;
				
				//04:读数，05：跳闸，复位
				String funcCode = strPackage.substring(2, 4);
				if (funcCode.equals("04")) {
					strPackage = strPackage.substring(0,34);
				} else if (funcCode.equals("05")) {
					strPackage = strPackage.substring(0,16);
				}
				else { //dirty data 
					System.out.println(socket.getRemoteSocketAddress() + " dirty data:" + strPackage);
					continue;
				}
				
				if (funcCode.equals("04")) //save elect data
					doBusiness(strPackage);
				else {
					System.out.println("Received client responsed data:" + strPackage);
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				socket.close();
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public void doBusiness(String s) {
		System.out.println("Received client data:" + s);
		//01 04 0c 0000 0000 0000 0000 02                      00                         66 00  bfaf
        //         a     b   c    漏电  除1，10，100（03,02,01)  继电器状态（00正常，否则异常） 备用    crc
		//读数返回
		String aValue = s.substring(6,10);
		String bValue = s.substring(10,14);
		String cValue = s.substring(14,18);
		String xiaoshu = s.substring(22,24);
		String status = s.substring(24,26);
		String crc = s.substring(30,s.length());
		float av,bv,cv;
		//保存数量
		av = Integer.parseInt(aValue, 16);
		bv = Integer.parseInt(bValue, 16);
		cv = Integer.parseInt(cValue, 16);
		if (xiaoshu.equals("02")) {
			av /= 10;
			bv /= 10;
			cv /= 10;
			
		} else if (xiaoshu.equals("01")) {
			av /= 100;
			bv /= 100;
			cv /= 100;
		} else {
			
		}
		System.out.println("Device:" + socket.getRemoteSocketAddress() + ",seq=" + this.hostSeq +
						",avalue:" + av + ",bvalue:" + bv + ",cvalue:" + cv);
		
		//to file
		String currTime = DateTimeManager.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		String rec = currTime + "|" + av + "|" +  bv + "|" + cv + "\n";
		String elecFile = "/usr/local/apache-tomcat-8.5.28/webapps/ElectricityProcetectSystem/data/";
		elecFile += this.hostSeq.toString();
		elecFile += ".txt";
		FileManager.toFile(elecFile,rec);
		
		//save elec data
		saveElecData(currTime,Float.toString(av),Float.toString(bv),Float.toString(cv));
		
	}

	public synchronized void sendData(int type) {
//		String hostSeq = String.format("%02x", this.hostSeq);
		String hostSeq = "01";
		String readData =  hostSeq ;
		String command ="" ;
		switch ( type) {
		case 1: //读数
			command = "0400000006";
			break;
		case 2: //跳闸
			command = "050000FF01";
			break;
		case 3://复位
			command = "0500000000";
			break;
		default:
			System.out.println("error operator command.");
			break;
		}
		readData += command;
		//CRC
		int crc = CRC16.calcCrc16(hexStringToByteArray(readData));
		String scrc = String.format("%04x", crc);
		
		System.out.println("CRC code=" + String.format("%04x", crc));
		readData += scrc.substring(2, scrc.length());
		readData += scrc.substring(0,2);
		// 读数
		try {
			os.write(hexStringToByteArray(readData));
			os.flush();
			System.out.println(socket.getRemoteSocketAddress() + "Command type:" + type + ", package:" + readData + " to client.");
		} catch (IOException e) {
			System.out.println(socket.getRemoteSocketAddress() + "Command type:" + type + ", package:" + readData + " to client Error.");
			// TODO Auto-generated catch block
			e.printStackTrace();
			socketStatus = 1;//1 closed
		}

	}

	/*
	 * 16进制字符串转字节数组
	 */
	public byte[] hexStringToByteArray(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		// toUpperCase将字符串中的所有字符转换为大写
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		// toCharArray将此字符串转换为一个新的字符数组。
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}
	//charToByte返回在指定字符的第一个发生的字符串中的索引，即返回匹配字符  
    private  byte charToByte(char c) {  
        return (byte) "0123456789ABCDEF".indexOf(c);  
    }  
   
}
