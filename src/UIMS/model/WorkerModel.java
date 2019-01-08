package UIMS.model;

import java.util.*;
import UIMS.view.View;
import UIMS.vo.*;

//工人管理部分
public class WorkerModel implements PersonOperate {
	
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Worker> workList = new ArrayList<Worker>();
	static View view;
		
	static final String author = "1711640118";

	public int add(Person person) {
		// 查重
		for (Worker work : workList) {
			if (work.getID().equals(person.getID())) {
				return 0;
			}
		}
		workList.add((Worker)person);
		return 1;
	}
	
	/** 根据 ID 查找，返回单个工人对象 */
	public Person findID(String ID) {
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if (ID.equals(work.getID())) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着
	}
	
	/** 返回是否存在这个 ID */
	public boolean hasID(String ID) {
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if (ID.equals(work.getID())) {
					return true;
				}
			}
		}
		return false;		// 没有工人，或者找遍了都没找着
	}
	
	/** 根据姓名精确查找，返回找到的所有工人列表 */
	public ArrayList<Person> findName(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if ( work.getName().equals(name) ) {
					returnList.add(work);
				}
			}
		}
		return returnList;
	}
	/** 模糊查找，找有没有这个人包含这个字符串，返回找到的所有工人列表 */
	public ArrayList<Person> fuzzySearch(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if ( work.getName().contains(name) ) {
					returnList.add(work);
				}
			}
		}
		return returnList;
	}
	
	/** 按工人对象删除工人，成功返回 1，失败返回 0 */
	public int delete(Person person) {
		if (workList.size() != 0) {
			int index = workList.indexOf(person);
			workList.remove(index);
			/*	不需要手动遍历
			for (Worker work : workList) {
				if ( work.getID().equals(worker.getID()) ) {		// 就决定要删你了
					workList.remove(work);
					return 1;
				}
			}
			*/
			return 1;
		}
		return 0;
	}
	/** 按工人 ID 删除工人，成功返回 1，失败返回 0 */
	public int delete(String ID) {
		if (workList.size() != 0 && hasID(ID)) {
			for (Worker work : workList) {
				if ( ID.equals(work.getID()) ) {		// 就决定要删你了
					workList.remove(work);
					return 1;
				}
			}
		}
		return 0;
	}
	
	/** 按工人对象修改工人，成功返回 1，失败返回 0 */
	public int update(Person from, Person to) {
		if (workList.size() != 0) {
			for (int i = 0; i < workList.size(); i++) {
				if ( from.getID().equals(workList.get(i).getID()) ) {
					workList.remove(i);
					workList.add(i, (Worker)to);
					return 1;
				}
			}
		}
		return 0;
	}
	/** 按工人 ID 修改工人，成功返回 1，失败返回 0 */
	public int update(String from, Worker to) {
		if (workList.size() != 0) {
			for (int i = 0; i < workList.size(); i++) {
				if ( from.equals(workList.get(i).getID()) ) {
					workList.remove(i);
					workList.add(i, to);
					return 1;
				}
			}
		}
		return 0;
	}

	/** 返回所有工人的列表 */
	public ArrayList<Worker> list() {
		return workList;
	}
	
	/** 返回工人数量 */
	public int count() {
		return workList.size();
	}
	
	/** 返回序列化为 csv String 的工人数据，返回格式为每行内容："ID,name,age,salary,job" */
	public String SerializeToCsvString() {	// returns "ID,name,age,salary,job" in each line
		String toReturn = "";
		for (Worker work : workList) {
			toReturn += work.getID() + "," + work.getName() + "," + work.getAge() + "," + work.getSalary() + "," + work.getJob() + "\n";
		}
		return toReturn;
	}
	
}
