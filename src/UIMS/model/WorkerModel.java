package UIMS.model;

import java.util.*;

// import UIMS.UIMSmain.WorkerInner;
import UIMS.vo.*;

// 工人管理部分
public class WorkerModel implements WorkerOperate {
	
	static Scanner sc = new Scanner(System.in);
	static List<Worker> workList = new ArrayList<Worker>();
	public WorkerModel() {
		
	}
	
	// TODO 迁移这部分到 View 层
	/*
	private void WorkerManagement() {
		
		boolean flag = true;		// flag 为真时显示菜单
		while (true) {
			if (flag) {
				System.out.println("\n工人信息管理\n"
								+ "　　1. 增加工人信息\n"
								+ "　　2. 列出全部工人信息\n"
								+ "　　3. 查询工人信息\n"
								+ "　　4. 删除工人信息\n"
								+ "　　5. 修改工人信息\n"
								+ "　　6. 返回上一级菜单");
			}
			System.out.print("请选择具体的操作：");
			int input = sc.nextInt();
			switch (input) {
			case 1: { WorkerInner.add(worker); break; }
			case 2: { WorkerInner.list(worker); break; }
			case 3: { WorkerInner.find(worker); break; }
			case 4: { WorkerInner.delete(worker); break; }
			case 5: { WorkerInner.update(worker); break; }
			case 6: { return; }
			default: { flag = false; }
			}			
		}
	}
	*/
	
	static final String author = "1711640118";

	public int add(Worker worker) {
		
		// 查重
		for (Worker work : workList) {
			if (work.getID().equals(worker.getID())) {
				return 0;
			}
		}
		workList.add(worker);
		return 1;
		
	}
	
	/*	list 不在 model 层做
	public void list(List<Worker> worker) {
		if (workList.size() != 0) {
			// System.out.println("----ID----|---姓名---|--年龄--|--工资--");
			// for (Worker work : workList) {
			//	work.printInfo();
			// }
		} else {
			// System.out.println("没有工人。");
		}
		return;
	}
	*/
	
	// 根据 ID 查找
	public Worker findID(String toSearch) {
		if (workList.size() != 0) {
			/*	toSearch 类型不符
			int index = workList.indexOf(toSearch);
			if (index != -1) {
				return workList.get(index);
			}
			*/
			for (Worker work : workList) {
				if (toSearch.equals(work.getID())) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着
	}
	
	// 根据姓名查找
	public Worker findName(String toSearch) {
		if (workList.size() != 0) {
			/*	toSearch 类型不符
			int index = workList.indexOf(toSearch);
			if (index != -1) {
				return workList.get(index);
			}
			*/
			for (Worker work : workList) {
				if (toSearch.equals(work.getName())) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着
	}
	
	// 模糊查找，找有没有这个人包含这个字符串
	public Worker fuzzySearch(String toSearch) {
		if (workList.size() != 0) {
			for (Worker work : workList) {
				if ( work.getName().contains(toSearch) ) {
					return work;
				}
			}
		}
		return null;		// 没有工人，或者找遍了都没找着		
	}
	
	public int delete(Worker worker) {		// returns pointer
		if (workList.size() != 0) {
			int index = workList.indexOf(worker);
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

}
