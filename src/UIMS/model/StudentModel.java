package UIMS.model;

import java.util.*;
import UIMS.vo.*;

// 学生管理部分
public class StudentModel implements PersonOperate {
	
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Student> stuList = new ArrayList<Student>();
		
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
	
	/** 根据 ID 查找，返回单个学生对象 */
	public Person findID(String ID) {
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if (ID.equals(stu.getID())) {
					return stu;
				}
			}
		}
		return null;		// 没有学生，或者找遍了都没找着
	}
	
	/** 返回是否存在这个 ID */
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
	
	/** 根据姓名精确查找，返回找到的所有学生列表 */
	public ArrayList<Person> findName(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if ( stu.getName().equals(name) ) {
					returnList.add(stu);
				}
			}
		}
		return returnList;
	}
	/** 模糊查找，找有没有这个人包含这个字符串，返回找到的所有学生列表 */
	public ArrayList<Person> fuzzySearch(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		if (stuList.size() != 0) {
			for (Student stu : stuList) {
				if ( stu.getName().contains(name) ) {
					returnList.add(stu);
				}
			}
		}
		return returnList;
	}
	
	/** 按学生对象删除学生，成功返回 1，失败返回 0 */
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
	/** 按学生 ID 删除学生，成功返回 1，失败返回 0 */
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
	
	/** 按学生对象修改学生，成功返回 1，失败返回 0 */
	public int update(Person from, Person to) {
		if (stuList.size() != 0) {
			for (int i = 0; i < stuList.size(); i++) {
				if ( from.getID().equals(stuList.get(i).getID()) ) {
					stuList.remove(i);
					stuList.add(i, (Student)to);
					return 1;
				}
			}
		}
		return 0;
	}
	/** 按学生 ID 修改学生，成功返回 1，失败返回 0 */
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

	/** 返回所有学生的列表 */
	public ArrayList<Student> list() {
		return stuList;
	}
	
	/** 返回学生数量 */
	public int count() {
		return stuList.size();
	}
	
	/** 返回序列化为 csv String 的学生数据，返回格式为每行内容："ID,name,age,score" */
	public String SerializeToCsvString() {
		String toReturn = "";
		for (Student stu : stuList) {
			toReturn += stu.getID() + "," + stu.getName() + "," + stu.getAge() + "," + stu.getScore() + "\n";
		}
		return toReturn;
	}
	
}
