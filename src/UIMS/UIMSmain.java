/**
 * @author 计算机1701　叶文滔　1711640118
 * @date 2019-01-02
 * @version 1.0.0
 */

package UIMS;

import java.util.*;
import UIMS.vo.*;
import UIMS.model.*;
import UIMS.view.*;

public class UIMSmain {
	
	public static void main(String[] args) {
		
		StudentModel stuModel = new StudentModel();
		WorkerModel workModel = new WorkerModel();
		CLI view = new CLI(stuModel, workModel);
		// StoreController ctrl = new StoreControllerImpl(model,view);
		
	}
	
	
	// 工人管理部分
	/*
	static class WorkerInner implements WorkerOperate {
		
		static List<Worker> worker = new ArrayList<Worker>();
		static int pointer = 0;

		private static void WorkerManagement() {
			
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

		static final String author = "1711640118";

		public static void add(List<Worker> worker) {
											//						||    ||
			// 查找有没有重复 ID，如有，退回	//					   \  /  \  /
			boolean mustBeTrue = true;		//					    \/    \/
			while (mustBeTrue) {	//	<-	<-	<-	恭喜！！！发现了编译器 / IDE 有 bug！这里直接填 true 会报错噢～
											//					    /\    /\
											//					   /  \  /  \
											//						||    ||	编译器会以为这里是死循环，实际能跳出来
				boolean flag = true;	// flag 为真，则工人有重复
				String input1 = null;
				while (flag) {
					System.out.print("请输入工人 ID（输入 0 返回菜单）：");
					input1 = sc.next();
					if (input1.equals("0")) { return; }
					flag = false;
					for (Worker work : worker) {
						if (work.getID().equals(input1)) {
							flag = true;
							System.out.print("工人 ID 重复，重试");
						}
					}
				}
				System.out.print("请输入工人姓名：");
				String input2 = sc.next();
				System.out.print("请输入工人年龄：");
				int input3 = sc.nextInt();
				System.out.print("请输入工人成绩：");
				double input4 = sc.nextDouble();
				System.out.print("请输入工人工作：");
				String input5 = sc.next();
				// 定义新的 Worker
				worker.add( new Worker(input1, input2, input3, input4, input5) );
			}
			return;
		}
		
		public static void list(List<Worker> worker) {
			if (worker.size() != 0) {
				System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
				for (Worker work : worker) {
					work.printInfo();
				}
			} else {
				System.out.println("没有工人。");
			}
			return;
		}
		
		public static void find(List<Worker> worker) {
			if (worker.size() != 0) {
				System.out.print("要查找的工人姓名：");
				String input = sc.next();
				boolean flag = false;
				for (Worker work : worker) {
					if (work.getName().equals(input)) {
						System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
						work.printInfo();
						flag = true;
					}
				}
				if (!flag) { System.out.println("找不到该工人。"); }
			} else {
				System.out.println("没有工人。");
			}
			return;
		}
		
		public static void fuzzySearch(List<Worker> worker) {
			
		}
		
		public static void delete(List<Worker> worker) {		// returns pointer
			if (worker.size() != 0) {
				while (worker.size() >= 0) {
					boolean found = false;
					list(worker);
					System.out.print("请输入要删除的工人 ID（输入 0 返回菜单）：");
					String input = sc.next();
					if (input.equals("0")) { break; }
					for (Worker work : worker) {
						if ( work.getID().equals(input) ) {		// 就决定要删你了
							worker.remove(work);
							found = true;
							break;
						}
					}
					if (!found) {
						System.out.println("找不到这个工人.");
					}
				}
			} else {
				System.out.println("没有工人。");
			}
			return;
		}
		
		public static void update(List<Worker> worker) {
			if (worker.size() != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = true;
					list(worker);
					System.out.print("请输入要修改的工人 ID（输入 0 返回菜单）：");
					String input = sc.next();						// input：源 ID
					if (input.equals("0")) { return; }
					for (Worker work : worker) {
						if ( work.getID().equals(input) ) {			// 找到要修改的人
							String input1 = null;					// input1：目标 ID
							boolean flag = true;					// flag 为真则 ID 输入重复
							while (flag) {
								flag = false;
								System.out.print("请输入工人 ID：");
								input1 = sc.next();
								for (Worker workrep : worker) {
									if (workrep.getID().equals(input1) && !(input1.equals(input))) {	// 找到列表中已经有这个 ID 就不放行，除非这就是它自己
										System.out.print("工人 ID 重复，重试");
										flag = true;
										break;
									}
								}
							}
							System.out.print("请输入工人姓名：");
							String input2 = sc.next();
							System.out.print("请输入工人年龄：");
							int input3 = sc.nextInt();
							System.out.print("请输入工人成绩：");
							double input4 = sc.nextDouble();
							System.out.print("请输入工人工作：");
							String input5 = sc.next();
							work.setInfo(input1, input2, input3, input4, input5);
							repeat = true;
						}
					}
				}
			} else {
				System.out.println("没有工人。");
			}
			return;
		}
	}
	*/
}

