import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

public class TestReadFile {
	/*
	 * split file by splitNum,a splitNum rows one file
	 * 
	 * @param strOrigFileName
	 * 
	 * @param splitNum
	 * 
	 * @filterBlackUser
	 * 
	 * @return after splitting file list
	 */
	public static List<String> splitFile(String strOrigFileName, int splitNum, boolean filterBlackUserList) {
		List<String> splitFileList = new ArrayList<String>();
		int iRowNum = 0;
		int iTotalNum = 0;
		int iBlackNum = 0;
		BufferedReader br = null;
		String blackFileName = strOrigFileName + "_black";
		OutputStreamWriter oswBlack = null;

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(strOrigFileName), "UTF-8"));
			Integer resultFileSeq = 1;
			String splitFileName = strOrigFileName + "_" + resultFileSeq++;

			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(splitFileName, false), "UTF-8");
			oswBlack = new OutputStreamWriter(new FileOutputStream(blackFileName, false), "UTF-8");

			while (true) {
				String line = br.readLine();
				if (line != null) {
					++iTotalNum;
					// black user
					if (filterBlackUserList && isBlackUser(line)) {
						++iBlackNum;
						oswBlack.write(line);
						oswBlack.write('\n');
						continue;
					}
					osw.write(line);
					osw.write('\n');
					++iRowNum;
					if (iRowNum >= splitNum) {
						// add before file name
						splitFileList.add(splitFileName);
						try {
							iRowNum = 0;
							osw.flush();
							osw.close();
							// create new file
							splitFileName = strOrigFileName + "_" + resultFileSeq++;
							osw = new OutputStreamWriter(new FileOutputStream(splitFileName, false), "UTF-8");
						} catch (Exception e) {
							e.printStackTrace();
							br.close();
						}
					}

				} else { // readline end of
					System.out.println(
							"[OrderCenter] finished read file,fileNum=" + iTotalNum + " blackNum=" + iBlackNum);

					// last file
					if (iRowNum > 0) {
						splitFileList.add(splitFileName);
						iRowNum = 0;
						osw.flush();
						osw.close();
					} else {
						// delete empty file
						deleteFile(splitFileName);
					}
					br.close();
					if (iBlackNum > 0) {
						splitFileList.add(blackFileName);
					} else {
						// delete black user file
						deleteFile(blackFileName);
					}
					// close black user file
					oswBlack.flush();
					oswBlack.close();
					break;
				}
			} // --- while ---

		} catch (Exception e) {
			e.printStackTrace();
			// read file exception
			System.out.println("[OrderCenter] read file exception.");
		}

		return splitFileList;
	}

	private static boolean isBlackUser(String row) {
		int idex = 0;
		int times = 0;
		String tmp = row;
//		String phone = null;
		while ((idex = tmp.indexOf("|")) != -1) {
			times++;
			tmp = tmp.substring(idex + 1);
			// find phone_number field
			if (times == 6) {
				String phone = tmp.substring(0, tmp.indexOf("|"));
				System.out.println("phone=" + phone);
				break;
			}

		}

		// judge black user

		return true;

	}

	/*
	 * delete file
	 */
	private static void deleteFile(String fileName) {
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

	public static void main(String[] args) {
		List<String> fileList = TestReadFile.splitFile("/Users/yanjunshen/Downloads/order_test.csv", 7777, false);
		for (String file : fileList) {
			System.out.println("file=" + file);

		}
	}
}
