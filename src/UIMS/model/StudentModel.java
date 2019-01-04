package UIMS.model;

import java.util.*;

// import UIMS.UIMSmain.StudentInner;
import UIMS.vo.*;

// 学生管理部分
public class StudentModel implements StudentOperate {
	
	static Scanner sc = new Scanner(System.in);
	static List<Student> stuList = new ArrayList<Student>();
	public StudentModel() {
		
	}
	
	// TODO 迁移这部分到 View 层
	/*
	private void StudentManagement() {
		
		boolean flag = true;		// flag 为真时显示菜单
		while (true) {
			if (flag) {
				System.out.println("\n学生信息管理\n"
								+ "　　1. 增加学生信息\n"
								+ "　　2. 列出全部学生信息\n"
								+ "　　3. 查询学生信息\n"
								+ "　　4. 删除学生信息\n"
								+ "　　5. 修改学生信息\n"
								+ "　　6. 返回上一级菜单");
			}
			System.out.print("请选择具体的操作：");
			int input = sc.nextInt();
			switch (input) {
			case 1: { StudentInner.add(student); break; }
			case 2: { StudentInner.list(student); break; }
			case 3: { StudentInner.find(student); break; }
			case 4: { StudentInner.delete(student); break; }
			case 5: { StudentInner.update(student); break; }
			case 6: { return; }
			default: { flag = false; }
			}			
		}
	}
	*/
	
	static final String author = "1711640118";

	public int add(Student student) {
		
		// 查重
		for (Student stu : stuList) {
			if (stu.getID().equals(student.getID())) {
				return 0;
			}
		}
		stuList.add(student);
		return 1;
		
	}
	
	/*	list 不在 model 层做
	public void list(List<Student> student) {
		if (stuList.size() != 0) {
			// System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
			// for (Student stu : stuList) {
			//	stu.printInfo();
			// }
		} else {
			// System.out.println("没有学生。");
		}
		return;
	}
	*/
	
	// 根据 ID 查找
	public Student findID(String toSearch) {
		if (stuList.size() != 0) {
			/*	toSearch 类型不符
			int index = stuList.indexOf(toSearch);
			if (index != -1) {
				return stuList.get(index);
			}
			*/
			for (Student stu : stuList) {
				if (toSearch.equals(stu.getID())) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着
	}
	
	// 根据姓名查找
	public Student findName(String toSearch) {
		if (stuList.size() != 0) {
			/*	toSearch 类型不符
			int index = stuList.indexOf(toSearch);
			if (index != -1) {
				return stuList.get(index);
			}
			*/
			for (Student stu : stuList) {
				if (toSearch.equals(stu.getName())) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着
	}
	
	// 模糊查找，找有没有这个人包含这个字符串
	public Student fuzzySearch(String toSearch) {
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if ( stu.getName().contains(toSearch) ) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着		
	}
	
	public int delete(Student student) {		// returns pointer
		if (stuList.size() != 0) {
			int index = stuList.indexOf(student);
			stuList.remove(index);
			/*	不需要手动遍历
			for (Student stu : stuList) {
				if ( stu.getID().equals(student.getID()) ) {		// 就决定要删你了
					stuList.remove(stu);
					return 1;
				}
			}
			*/
			return 1;
		}
		return 0;
	}
	
	public int update(Student from, Student to) {
		if (stuList.size() != 0) {
			for (int i = 0; i < stuList.size(); i++) {
				if ( from.getID().equals(stuList.get(i).getID()) ) {
					stuList.remove(i);
					stuList.add(i, to);
					return 1;
				}
			}
		}
		return 0;
	}

}
