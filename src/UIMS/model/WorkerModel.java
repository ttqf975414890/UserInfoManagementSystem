package UIMS.model;

import java.util.*;

// import UIMS.UIMSmain.WorkerInner;
import UIMS.vo.*;

// 工人管理部分
public class WorkerModel implements WorkerOperate {
	
	static Scanner sc = new Scanner(System.in);
	static List<Worker> workList = new ArrayList<Worker>();
		
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
	
	// 根据 ID 查找
	public Worker findID(String ID) {
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if (ID.equals(work.getID())) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着
	}
	// 根据姓名查找
	public Worker findName(String name) {
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if (name.equals(work.getName())) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着
	}
	
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
	
	// 模糊查找，找有没有这个人包含这个字符串
	public Worker fuzzySearch(String name) {
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if ( work.getName().contains(name) ) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着		
	}
	
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
	
	public int update(Worker from, Worker to) {
		if (workList.size() != 0) {
			for (int i = 0; i < workList.size(); i++) {
				if ( from.getID().equals(workList.get(i).getID()) ) {
					workList.remove(i);
					workList.add(i, to);
					return 1;
				}
			}
		}
		return 0;
	}
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

	// 将来取消它
	public List<Worker> list() {
		return workList;
	}
	
	public int count() {
		return workList.size();
	}
}
