package com.elec.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileManager {
	public static void toFile(String fileName, String rec) {
		/// usr/local/apache-tomcat-8.5.28/webapps/ElectricityProcetectSystem/data/result.txt
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		try {
			outSTr = new FileOutputStream(new File(fileName), true);
			Buff = new BufferedOutputStream(outSTr);
			Buff.write(rec.getBytes());

			Buff.flush();
			Buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	/*
	 * delete file
	 */
	public static void deleteFile(String fileName) {
		// delete file

		try {
			File deleteFile = new File(fileName);
			if (deleteFile.delete()) {
				System.out.println(deleteFile.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
