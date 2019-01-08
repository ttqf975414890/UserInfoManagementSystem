package UIMS.model;

import java.io.*;
import UIMS.vo.*;
import java.util.ArrayList;

public class FileOperate {
	public boolean SaveTo(String fileName, String content) {
		try {
			FileWriter writer = new FileWriter(fileName, false);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
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
}
