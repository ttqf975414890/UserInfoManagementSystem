package UIMS.Controller;

import java.util.ArrayList;

import UIMS.model.*;
import UIMS.view.*;
import UIMS.vo.Person;
import UIMS.vo.Student;

public class ControllerImpl implements Controller {

	private StudentModel stuModel;
	private GUI gui;
	private FileOperate fileOP = new FileOperate();
	
	/** 构造方法  */
	public ControllerImpl(StudentModel StuModel, GUI gui) {
		stuModel = StuModel;
		this.gui = gui;
		this.gui.addStudentInfoListener(this); 	// 向视图注册控制器自身
		// 从文件读取数据
		ArrayList<Student> stuList = fileOP.LoadStudentFrom("studentData.csv");
		if (stuList != null) {
			for (Student stu : stuList) {
				stuModel.add(stu);
				gui.handleAddStudent(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
			}
			gui.setStudentStatusBar("已从文件读取学生信息。当前学生数：" + stuModel.count() + " 个。");
		}
	}
	public boolean handleAddStudentInfo(String ID, String name, int age, double score) {
		stuModel.add( new Student(ID, name, age, score) );
		return fileOP.SaveTo("studentData.csv", stuModel.SerializeToCsvString());
	}
	public boolean handleDeleteStudentInfo(String ID) {
		stuModel.delete(ID);
		return fileOP.SaveTo("studentData.csv", stuModel.SerializeToCsvString());
	}
	public boolean handleUpdateStudentInfo(String fromID, String toID, String toName, int toAge, double toScore) {
		stuModel.update(fromID, new Student(toID, toName, toAge, toScore) );
		return fileOP.SaveTo("studentData.csv", stuModel.SerializeToCsvString());
	}
	public void handleGetAllStudentInfo() {
		ArrayList<Student> stuList = stuModel.list();
		for (Student stu : stuList) {
			gui.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
		}
	}
	public void handleGetFilteredStudentInfo(int filter, String filterContent) {	// filter：0 为按 ID 获取，1 为按姓名获取，2 为按姓名模糊获取
		switch (filter) {
		case 0: {
			Student stu = (Student)stuModel.findID(filterContent);
			if (stu != null) {
				gui.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
				gui.setStudentStatusBar("找到 ID: " + filterContent + " 的学生共 1 个。");
			} else {
				gui.setStudentStatusBar("找不到 ID: " + filterContent + " 的学生。");
			}
			break;
		}
		case 1: {
			ArrayList<Person> psList = stuModel.findName(filterContent);
			int sum = 0;
			for (Person ps : psList) {
				Student stu = (Student)ps;
				gui.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
				sum++;
			}
			if (sum != 0) {
				gui.setStudentStatusBar("找到姓名: " + filterContent + " 的学生共 " + sum + " 个。");
			} else {
				gui.setStudentStatusBar("找不到姓名: " + filterContent + " 的学生。");
			}
			break;
		}
		case 2: {
			ArrayList<Person> psList = stuModel.fuzzySearch(filterContent);
			int sum = 0;
			for (Person ps : psList) {
				Student stu = (Student)ps;
				gui.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
				sum++;
			}
			if (sum != 0) {
				gui.setStudentStatusBar("找到姓名匹配内容: " + filterContent + " 的学生共 " + sum + " 个。");
			} else {
				gui.setStudentStatusBar("找不到姓名匹配内容: " + filterContent + " 的学生。");
			}
			break;
		}
		}
	}
	
}
