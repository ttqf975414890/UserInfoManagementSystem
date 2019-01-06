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
	
	private StudentModel stuModel;
	private WorkerModel workModel;
	private FileOperate fileOperate;
	
	public CLI(StudentModel stuModel, WorkerModel workModel, FileOperate fileOperate) {
		this.stuModel = stuModel;
		this.workModel = workModel;
		this.fileOperate = fileOperate;
		// 读取文件
		ArrayList<Student> stuList = fileOperate.LoadStudentFrom("studentData.csv");
		if (stuList != null) {
			for (Student stu : stuList) {
				stuModel.add(stu);
			}
		}
		ArrayList<Worker> workList = fileOperate.LoadWorkerFrom("workerData.csv");
		if (workList != null) {
			for (Worker work : workList) {
				workModel.add(work);
			}
		}
		// 读取文件完毕
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
		
	private void listStudent() {
		System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
		if (stuModel.count() == 0) {
			System.out.println("列表中没有学生");
		} else {
			for (Student student : stuModel.list()) {
				System.out.println(String.format(" %8s | %8s | %6d | %6.1f ", student.getID(), student.getName(), student.getAge(), student.getScore()));					
			}
		}
	}
	private void StudentManagement() {
		
		while (true) {
			System.out.println();
			listStudent();
			System.out.println("\n学生信息管理\n"
							+ "　　1. 增加学生信息\n"
							+ "　　2. 删除学生信息\n"
							+ "　　3. 修改学生信息\n"
							+ "　　4. 查询学生信息\n"
							+ "　　5. 模糊查询学生信息\n"
							+ "　　6. 返回上一级菜单");
			System.out.print("请选择具体的操作：");
			int menuInput = sc.nextInt();
			switch (menuInput) {
			case 1: {	// 增加
				String inputID = null;
				while (true) {
					System.out.print("请输入学生 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					if (stuModel.hasID(inputID)) {
						System.out.print("学生 ID 重复，重试");
						continue;
					}
					System.out.print("请输入学生姓名：");
					String input2 = sc.next();
					System.out.print("请输入学生年龄：");
					int input3 = sc.nextInt();
					System.out.print("请输入学生成绩：");
					double input4 = sc.nextDouble();
					// 定义新的 Student
					stuModel.add( new Student(inputID, input2, input3, input4) );
				}
				fileOperate.SaveTo("studentData.csv", stuModel.SerializeToCsvString());
				break;
			}
			case 2: {	// 删除
				String inputID = null;
				while (true) {
					System.out.println();
					listStudent();
					System.out.print("\n请输入要删除的学生 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					Student toDelete = stuModel.findID(inputID);
					if (toDelete == null) {
						System.out.print("不存在此 ID，重试");
						continue;
					}
					stuModel.delete(toDelete);
					fileOperate.SaveTo("studentData.csv", stuModel.SerializeToCsvString());
				}
				break;
			}
			case 3: {	// 修改
				String inputID = null;
				while (true) {
					System.out.println();
					listStudent();
					System.out.print("\n请输入要修改的学生 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					Student toFind = stuModel.findID(inputID);
					if (toFind == null) {
						System.out.print("此学生不存在，重试");
						continue;						
					}
					while (true) {
						System.out.print("请输入新的学生 ID：");
						inputID = sc.next();
						if (inputID.equals("0")) {
							System.out.print("不能输入为 0 的 ID，重试");
							continue;
						} else { break; }
					}
					System.out.print("请输入学生姓名：");
					String input2 = sc.next();
					System.out.print("请输入学生年龄：");
					int input3 = sc.nextInt();
					System.out.print("请输入学生成绩：");
					double input4 = sc.nextDouble();
					// 定义新的 Student
					stuModel.update(toFind, new Student(inputID, input2, input3, input4) );
					fileOperate.SaveTo("studentData.csv", stuModel.SerializeToCsvString());
				}
				break;
			}
			case 4: {	// 查询（按姓名）
				String inputName = null;
				// boolean repeat = true;
				while (true) {
					System.out.print("请输入学生姓名（输入 0 返回菜单）：");
					inputName = sc.next();
					if (inputName.equals("0")) { break; }
					Student gotta = stuModel.findName(inputName);
					if (gotta == null) {
						System.out.print("此学生不存在，重试");
						continue;
					}
					System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
					System.out.println(String.format(" %8s | %8s | %6d | %6.1f \n", gotta.getID(), gotta.getName(), gotta.getAge(), gotta.getScore()));
				}
				break;
			}
			case 5: {	// 基础的模糊查询（按姓名）
				String inputName = null;
				// boolean repeat = true;
				while (true) {
					System.out.print("请输入学生姓名包含的字符（输入 0 返回菜单）：");
					inputName = sc.next();
					if (inputName.equals("0")) { break; }
					Student gotta = stuModel.fuzzySearch(inputName);
					if (gotta == null) {
						System.out.print("此学生不存在，重试");
						continue;
					}
					System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
					System.out.println(String.format(" %8s | %8s | %6d | %6.1f \n", gotta.getID(), gotta.getName(), gotta.getAge(), gotta.getScore()));
				}
				break;
			}
			case 6: { return; }
			default: {}
			}
		}
	}
	
	private void listWorker() {
		System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
		if (workModel.count() == 0) {
			System.out.println("列表中没有工人");
		} else {
			for (Worker worker : workModel.list()) {
				System.out.println(String.format(" %8s | %8s | %6d | %6.1f | %8s ", worker.getID(), worker.getName(), worker.getAge(), worker.getSalary(), worker.getJob()));					
			}
		}
	}
	private void WorkerManagement() {
		
		while (true) {
			System.out.println();
			listWorker();
			System.out.println("\n工人信息管理\n"
							+ "　　1. 增加工人信息\n"
							+ "　　2. 删除工人信息\n"
							+ "　　3. 修改工人信息\n"
							+ "　　4. 查询工人信息\n"
							+ "　　5. 模糊查询工人信息\n"
							+ "　　6. 返回上一级菜单");
			System.out.print("请选择具体的操作：");
			int menuInput = sc.nextInt();
			switch (menuInput) {
			case 1: {	// 增加
				String inputID = null;
				while (true) {
					System.out.print("请输入工人 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					if (workModel.hasID(inputID)) {
						System.out.print("工人 ID 重复，重试");
						continue;
					}
					System.out.print("请输入工人姓名：");
					String input2 = sc.next();
					System.out.print("请输入工人年龄：");
					int input3 = sc.nextInt();
					System.out.print("请输入工人工资：");
					double input4 = sc.nextDouble();
					System.out.print("请输入工人工作：");
					String input5 = sc.next();
					// 定义新的 Worker
					workModel.add( new Worker(inputID, input2, input3, input4, input5) );
					fileOperate.SaveTo("workerData.csv", workModel.SerializeToCsvString());
				}
				break;
			}
			case 2: {	// 删除
				String inputID = null;
				while (true) {
					System.out.println();
					listWorker();
					System.out.print("\n请输入要删除的工人 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					Worker toDelete = workModel.findID(inputID);
					if (toDelete == null) {
						System.out.print("不存在此 ID，重试");
						continue;
					}
					workModel.delete(toDelete);
				}
				break;
			}
			case 3: {	// 修改
				String inputID = null;
				while (true) {
					System.out.println();
					listWorker();
					System.out.print("\n请输入要修改的工人 ID（输入 0 返回菜单）：");
					inputID = sc.next();
					if (inputID.equals("0")) { break; }
					Worker toFind = workModel.findID(inputID);
					if (toFind == null) {
						System.out.print("此工人不存在，重试");
						continue;						
					}
					while (true) {
						System.out.print("请输入新的工人 ID：");
						inputID = sc.next();
						if (inputID.equals("0")) {
							System.out.print("不能输入为 0 的 ID，重试");
							continue;
						} else { break; }
					}
					System.out.print("请输入工人姓名：");
					String input2 = sc.next();
					System.out.print("请输入工人年龄：");
					int input3 = sc.nextInt();
					System.out.print("请输入工人工资：");
					double input4 = sc.nextDouble();
					System.out.print("请输入工人工作：");
					String input5 = sc.next();
					// 定义新的 Worker
					workModel.update(toFind, new Worker(inputID, input2, input3, input4, input5) );
				}
				break;
			}
			case 4: {	// 查询（按姓名）
				String inputName = null;
				// boolean repeat = true;
				while (true) {
					System.out.print("请输入工人姓名（输入 0 返回菜单）：");
					inputName = sc.next();
					if (inputName.equals("0")) { break; }
					Worker gotta = workModel.findName(inputName);
					if (gotta == null) {
						System.out.print("此工人不存在，重试");
						continue;
					}
					System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
					System.out.println(String.format(" %8s | %8s | %6d | %6.1f | %8s \n", gotta.getID(), gotta.getName(), gotta.getAge(), gotta.getSalary(), gotta.getJob()));
				}
				break;
			}
			case 5: {	// 查询（按姓名）
				String inputName = null;
				// boolean repeat = true;
				while (true) {
					System.out.print("请输入工人姓名（输入 0 返回菜单）：");
					inputName = sc.next();
					if (inputName.equals("0")) { break; }
					Worker gotta = workModel.fuzzySearch(inputName);
					if (gotta == null) {
						System.out.print("此工人不存在，重试");
						continue;
					}
					System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
					System.out.println(String.format(" %8s | %8s | %6d | %6.1f | %8s \n", gotta.getID(), gotta.getName(), gotta.getAge(), gotta.getSalary(), gotta.getJob()));
				}
				break;
			}
			case 6: { return; }
			default: {}
			}
		}
	}

}
