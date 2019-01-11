/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-11
 * @version 4.2.0
 */

package UIMS.Controller;

import java.util.ArrayList;

import UIMS.model.*;
import UIMS.view.*;
import UIMS.vo.Person;
import UIMS.vo.Student;
import UIMS.vo.Worker;

public class ControllerImpl implements Controller {

	private StudentModel stuModel;
	private WorkerModel workModel;
	private StuGUI stuGUI;
	private WorkGUI workGUI;
	
	/** 构造方法  */
	public ControllerImpl(StudentModel StuModel, WorkerModel WorkModel, StuGUI stuGUI, WorkGUI workGUI) {
		stuModel = StuModel;
		workModel = WorkModel;
		this.stuGUI = stuGUI;
		this.workGUI = workGUI;
		this.stuGUI.addStudentInfoListener(this); 	// 向视图注册控制器自身
		this.workGUI.addWorkerInfoListener(this);
		// 从数据库读取数据
		ArrayList<Student> stuList = stuModel.list();
		ArrayList<Worker> workList = workModel.list();
		for (Student stu : stuList) {
			stuGUI.handleAddStudent(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
		}
		stuGUI.setStudentStatusBar("已从数据库读取学生信息。当前学生数：" + getStudentCount() + " 个。");		
		for (Worker work : workList) {
			workGUI.handleAddWorker(work.getID(), work.getName(), work.getAge(), work.getSalary(), work.getJob());
		}
		workGUI.setWorkerStatusBar("已从数据库读取工人信息。当前学生数：" + getWorkerCount() + " 个。");		
	}
	public boolean handleAddStudentInfo(String ID, String name, int age, double score) {
		return (boolean)stuModel.add( new Student(ID, name, age, score) );
	}
	public boolean handleAddWorkerInfo(String ID, String name, int age, double salary, String job) {
		return (boolean)workModel.add( new Worker(ID, name, age, salary, job) );
	}
	public boolean handleDeleteStudentInfo(String ID) {
		return stuModel.delete(ID);
	}
	public boolean handleDeleteWorkerInfo(String ID) {
		return workModel.delete(ID);
	}
	public boolean handleUpdateStudentInfo(String fromID, String toID, String toName, int toAge, double toScore) {
		return stuModel.update(fromID, new Student(toID, toName, toAge, toScore) );
	}
	public boolean handleUpdateWorkerInfo(String fromID, String toID, String toName, int toAge, double toSalary, String toJob) {
		return workModel.update(fromID, new Worker(toID, toName, toAge, toSalary, toJob) );
	}
	public void handleGetAllStudentInfo() {
		ArrayList<Student> stuList = stuModel.list();
		for (Student stu : stuList) {
			stuGUI.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
		}
	}
	public void handleGetAllWorkerInfo() {
		ArrayList<Worker> workList = workModel.list();
		for (Worker work : workList) {
			workGUI.handleWorkerList(work.getID(), work.getName(), work.getAge(), work.getSalary(), work.getJob());
		}
	}
	public void handleGetFilteredStudentInfo(int filter, String filterContent) {	// filter：0 为按 ID 获取，1 为按姓名获取，2 为按姓名模糊获取
		switch (filter) {
		case 0: {
			Student stu = (Student)stuModel.findID(filterContent);
			if (stu != null) {
				stuGUI.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
				stuGUI.setStudentStatusBar("找到 ID: " + filterContent + " 的学生共 1 个。");
			} else {
				stuGUI.setStudentStatusBar("找不到 ID: " + filterContent + " 的学生。");
			}
			break;
		}
		case 1: {
			ArrayList<Person> psList = stuModel.findName(filterContent);
			int sum = 0;
			for (Person ps : psList) {
				Student stu = (Student)ps;
				stuGUI.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
				sum++;
			}
			if (sum != 0) {
				stuGUI.setStudentStatusBar("找到姓名: " + filterContent + " 的学生共 " + sum + " 个。");
			} else {
				stuGUI.setStudentStatusBar("找不到姓名: " + filterContent + " 的学生。");
			}
			break;
		}
		case 2: {
			ArrayList<Person> psList = stuModel.fuzzySearch(filterContent);
			int sum = 0;
			for (Person ps : psList) {
				Student stu = (Student)ps;
				stuGUI.handleStudentList(stu.getID(), stu.getName(), stu.getAge(), stu.getScore());
				sum++;
			}
			if (sum != 0) {
				stuGUI.setStudentStatusBar("找到姓名匹配内容: " + filterContent + " 的学生共 " + sum + " 个。");
			} else {
				stuGUI.setStudentStatusBar("找不到姓名匹配内容: " + filterContent + " 的学生。");
			}
			break;
		}
		}
	}
	public void handleGetFilteredWorkerInfo(int filter, String filterContent) {	// filter：0 为按 ID 获取，1 为按姓名获取，2 为按姓名模糊获取
		switch (filter) {
		case 0: {
			Worker work = (Worker)workModel.findID(filterContent);
			if (work != null) {
				workGUI.handleWorkerList(work.getID(), work.getName(), work.getAge(), work.getSalary(), work.getJob());
				workGUI.setWorkerStatusBar("找到 ID: " + filterContent + " 的工人共 1 个。");
			} else {
				workGUI.setWorkerStatusBar("找不到 ID: " + filterContent + " 的工人。");
			}
			break;
		}
		case 1: {
			ArrayList<Person> psList = workModel.findName(filterContent);
			int sum = 0;
			for (Person ps : psList) {
				Worker work = (Worker)ps;
				workGUI.handleWorkerList(work.getID(), work.getName(), work.getAge(), work.getSalary(), work.getJob());
				sum++;
			}
			if (sum != 0) {
				workGUI.setWorkerStatusBar("找到姓名: " + filterContent + " 的工人共 " + sum + " 个。");
			} else {
				workGUI.setWorkerStatusBar("找不到姓名: " + filterContent + " 的工人。");
			}
			break;
		}
		case 2: {
			ArrayList<Person> psList = workModel.fuzzySearch(filterContent);
			int sum = 0;
			for (Person ps : psList) {
				Worker work = (Worker)ps;
				workGUI.handleWorkerList(work.getID(), work.getName(), work.getAge(), work.getSalary(), work.getJob());
				sum++;
			}
			if (sum != 0) {
				workGUI.setWorkerStatusBar("找到姓名匹配内容: " + filterContent + " 的工人共 " + sum + " 个。");
			} else {
				workGUI.setWorkerStatusBar("找不到姓名匹配内容: " + filterContent + " 的工人。");
			}
			break;
		}
		}
	}
	public int getStudentCount() {
		return stuModel.count();
	}
	public int getWorkerCount() {
		return workModel.count();
	}
	public boolean hasStudent(String ID) {
		return stuModel.hasID(ID);
	}
	public boolean hasWorker(String ID) {
		return workModel.hasID(ID);
	}
}
