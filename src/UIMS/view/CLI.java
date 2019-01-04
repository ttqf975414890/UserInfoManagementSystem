package UIMS.view;

import java.util.*;
import UIMS.vo.*;
import UIMS.model.*;

public class CLI {
	
	static Scanner sc = new Scanner(System.in);
	static final String byebye = ""
			+ "□□□□□□□□□□□□□□□□□□□□□□□□\n"
			+ "□□■■■■□□□□□□□□□□□□□□□□■□\n"
			+ "□■□□□■□□□□□□□□□□□□□□□□■□\n"
			+ "□■□□□■□□□□□□□□□□□□□□□□■□\n"
			+ "□■□□□■□□□□□□□□□□□□□□□□■□\n"
			+ "□■□□■■□□■□□□■□□□■■■■□□■□\n"
			+ "□■■■■■□□■□□□■□□■□□□■□□■□\n"
			+ "□■□□□■■□■□□□■□■■□■■□□□■□\n"
			+ "□■□□□□■□■□□□■□■■■□□□■□■□\n"
			+ "□■□□□■■□■□□■■□□■□□□■□□□□\n"
			+ "□■■■■■□□■■■■□□□□■■■■□□■□\n"
			+ "□□□□□□□□□□■□□□□□□□□□□□□□\n"
			+ "□□□□□□□□□□■□□□□□□□□□□□□□\n"
			+ "□□□□□□□□□□■□□□□□□□□□□□□□\n"
			+ "□□□□□□□□□□□□□□□□□□□□□□□□\n";
	
	private StudentModel stuModel = new StudentModel();
	private WorkerModel workModel = new WorkerModel();
	
	public CLI() {
		boolean flag = true;		// flag 为真时显示菜单
		while (true) {
			if (flag) {
			System.out.println("\n学校信息管理程序\n"
							+ "　　1. 学生信息管理\n"
							+ "　　2. 工人信息管理\n"
							+ "　　3. 退出系统");
			}
			System.out.print("请选择所要的操作：");
			int input = sc.nextInt();
			switch (input) {
			case 1: { StudentManagement(); break; }
			case 2: { WorkerManagement(); break; }
			case 3: { System.out.println(byebye); System.exit(0); }
			default: { flag = false; }
			}			
		}
	}
	
	private void StudentManagement() {
		
	}
	
	static List<Worker> worker = new ArrayList<Worker>();
	
	private void WorkerManagement() {
		boolean showMenu = true;		// flag 为真时显示菜单
		while (true) {
			if (showMenu) {
				System.out.println("\n工人信息管理\n"
								+ "　　1. 增加工人信息\n"
								+ "　　2. 查询工人信息\n"
								+ "　　4. 修改工人信息\n"
								+ "　　3. 删除工人信息\n"
								+ "　　5. 返回上一级菜单");
			}
			System.out.print("请选择具体的操作：");
			int menuInput = sc.nextInt();
			switch (menuInput) {
			case 1: {													// 增加
				String inputID = null;
				boolean dumplicated = true;
				while (dumplicated) {
					System.out.print("请输入工人 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					if (workModel.findID(inputID))
						
						
						
						
						// TODO
						
						
						
				}
				
				

					boolean mustBeTrue = true;		//					    \/    \/
					while (mustBeTrue) {	//	<-	<-	<-	恭喜！！！发现了编译器 / IDE 有 bug！这里直接填 true 会报错噢～
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
					break;
			}
			case 2: {
				WorkerInner.list(worker); break;
			}
			case 3: {
				WorkerInner.find(worker); break;
			}
			case 4: {
				WorkerInner.delete(worker); break;
			}
			case 5: {
				WorkerInner.update(worker); break;
			}
			case 6: { return; }
			default: { showMenu = false; }
			}			
		}
	}

}
