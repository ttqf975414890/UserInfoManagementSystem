package UIMS.model;

import java.io.*;
import UIMS.vo.*;
import java.util.ArrayList;

public class FileOperate {
	public int SaveTo(String fileName, String content) {
		try {
			FileWriter writer = new FileWriter(fileName, false);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			return 0;
		}
		return 1;
	}
	public ArrayList<Student> LoadStudentFrom(String fileName) {
		ArrayList<Student> stuList = new ArrayList<Student>();
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr); 
			String readOut;
			while ((readOut = br.readLine()) != null) {
				String[] studentInfo = readOut.split(",");
				Student stu = new Student(studentInfo[0], studentInfo[1], Integer.parseInt(studentInfo[2]), Double.parseDouble(studentInfo[3]));
				stuList.add(stu);
			}
			br.close();
		} catch (Exception e) {
			return null;
		}
		return stuList;
	}
	public ArrayList<Worker> LoadWorkerFrom(String fileName) {
		ArrayList<Worker> workList = new ArrayList<Worker>();
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr); 
			String readOut;
			while ((readOut = br.readLine()) != null) {
				String[] workerInfo = readOut.split(",");
				Worker work = new Worker(workerInfo[0], workerInfo[1], Integer.parseInt(workerInfo[2]), Double.parseDouble(workerInfo[3]), workerInfo[4]);
				workList.add(work);
			}
			br.close();
		} catch (Exception e) {
			return null;
		}
		return workList;
	}
}
