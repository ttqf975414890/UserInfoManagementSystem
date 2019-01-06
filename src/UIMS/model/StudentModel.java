package UIMS.model;

import java.util.*;

// import UIMS.UIMSmain.StudentInner;
import UIMS.vo.*;

// 学生管理部分
public class StudentModel implements StudentOperate {
	
	static Scanner sc = new Scanner(System.in);
	static List<Student> stuList = new ArrayList<Student>();
		
	static final String author = "1711640118";

	public int add(Person person) {
		// 查重
		for (Student stu : stuList) {
			if (stu.getID().equals(person.getID())) {
				return 0;
			}
		}
		stuList.add((Student)person);
		return 1;
	}
	
	// 根据 ID 查找
	public Student findID(String ID) {
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if (ID.equals(stu.getID())) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着
	}
	// 根据姓名查找
	public Student findName(String name) {
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if (name.equals(stu.getName())) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着
	}
	
	public boolean hasID(String ID) {
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if (ID.equals(stu.getID())) {
					return true;
				}
			}
		}
		return false;		// 没有学生，或者找遍了都没找着
	}
	
	// 模糊查找，找有没有这个人包含这个字符串
	public Student fuzzySearch(String name) {
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if ( stu.getName().contains(name) ) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着		
	}
	
	public int delete(Person person) {
		if (stuList.size() != 0) {
			int index = stuList.indexOf(person);
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
	public int delete(String ID) {
		if (stuList.size() != 0 && hasID(ID)) {
			for (Student stu : stuList) {
				if ( ID.equals(stu.getID()) ) {		// 就决定要删你了
					stuList.remove(stu);
					return 1;
				}
			}
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
	public int update(String from, Student to) {
		if (stuList.size() != 0) {
			for (int i = 0; i < stuList.size(); i++) {
				if ( from.equals(stuList.get(i).getID()) ) {
					stuList.remove(i);
					stuList.add(i, to);
					return 1;
				}
			}
		}
		return 0;
	}

	// 将来取消它
	public List<Student> list() {
		return stuList;
	}
	
	public int count() {
		return stuList.size();
	}
}
