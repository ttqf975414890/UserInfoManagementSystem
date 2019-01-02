/**
 * @author 计算机1701　叶文滔　1711640118
 * @date 2019-01-03
 * @version 1.0.1
 */
import java.util.*;

public class UIMSmain {

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
	
	public static void main(String[] args) {
		
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
			case 1: { StudentInner.StudentManagement(); break; }
			case 2: { WorkerInner.WorkerManagement(); break; }
			case 3: { System.out.println(byebye); System.exit(0); }
			default: { flag = false; }
			}			
		}
				
	}
	
	// 学生管理部分
	static class StudentInner {
		
		static Student[] student = new Student[10];
		static int pointer = 0;

		private static void StudentManagement() {
			
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
				case 1: { pointer = StudentInner.addStudent(student, pointer); break; }
				case 2: { StudentInner.listStudent(student, pointer); break; }
				case 3: { StudentInner.findStudent(student, pointer); break; }
				case 4: { pointer = StudentInner.deleteStudent(student, pointer); break; }
				case 5: { StudentInner.modifyStudent(student, pointer); break; }
				case 6: { return; }
				default: { flag = false; }
				}			
			}
		}

		static final String author = "1711640118";

		static int addStudent(Student[] student, int pointer) {		// returns pointer
			if (pointer > 9) { System.out.println("列表数据限 10 人，空间已满。"); return pointer; }
			// 查找有没有重复 ID，如有，退回
			while (pointer < 10) {
				boolean flag = true;		// flag 为真，则学生有重复
				String input1 = null;
				while (flag) {
					System.out.print("请输入学生 ID（输入 0 返回菜单）：");
					input1 = sc.next();
					if (input1.equals("0")) { return pointer; }
					flag = false;
					for (int i = 0; i < pointer; i++) {
						if (student[i].getID().equals(input1)) {
							flag = true;
							System.out.print("学生 ID 重复，重试");
						}
					}
				}
				System.out.print("请输入学生姓名：");
				String input2 = sc.next();
				System.out.print("请输入学生年龄：");
				int input3 = sc.nextInt();
				System.out.print("请输入学生成绩：");
				double input4 = sc.nextDouble();
				// 定义新的 Student
				student[pointer] = new Student(input1, input2, input3, input4);
				pointer++;
			}
			return pointer;
		}
		
		static void listStudent(Student[] student, int pointer) {
			if (pointer != 0) {
				System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
				for (int i = 0; i < pointer; i++) {
					student[i].printInfo();
				}				
			} else {
				System.out.println("没有学生。");
			}
		}
		
		static void findStudent(Student[] student, int pointer) {
			if (pointer != 0) {
				System.out.print("要查找的学生姓名：");
				String input = sc.next();
				boolean flag = false;
				for (int i = 0; i < pointer; i++) {
					if ( student[i].getName().equals(input) ) {
						System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
						student[i].printInfo();
						flag = true;
					}
				}
				if (!flag) { System.out.println("找不到该学生。"); }
			} else {
				System.out.println("没有学生。");
			}
		}
		
		static int deleteStudent(Student[] student, int pointer) {		// returns pointer
			if (pointer != 0) {
				while (pointer >= 0) {
					boolean found = false;
					listStudent(student, pointer);
					System.out.print("请输入要删除的学生 ID（输入 0 返回菜单）：");
					String input = sc.next();
					if (input.equals("0")) { break; }
					for (int i = 0; i < pointer; i++) {
						if ( student[i].getID().equals(input) ) {		// 就决定要删你了
							int j;
							for (j = i; j < pointer - 1; j++) {
								student[j] = student[j+1];
							}
							student[j] = null;
							found = true;
							break;
						}
					}
					if (found) {
						pointer--;
					} else {
						System.out.println("找不到这个学生.");
					}
				}
			} else {
				System.out.println("没有学生。");
			}
			return pointer;
		}
		
		static void modifyStudent(Student[] student, int pointer) {
			if (pointer != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = false;											// 假定找不到或者取消操作，找到后才重复
					listStudent(student, pointer);
					System.out.print("请输入要修改的学生 ID（输入 0 返回菜单）：");
					String input = sc.next();
					if (input.equals("0")) { return; }
					for (int i = 0; i < pointer; i++) {
						if ( student[i].getID().equals(input) ) {			// 找到要修改的人
							String input1 = null;
							boolean flag = true;
							while (flag) {
								flag = false;
								System.out.print("请输入学生 ID：");
								input1 = sc.next();
								for (int j = 0; j < pointer; j++) {
									if (student[j].getID().equals(input1) && !(input1.equals(input))) {	// 找到列表中已经有这个 ID 就不放行，除非这就是它自己
										System.out.print("学生 ID 重复，重试");
										flag = true;
										break;
									}
								}
							}
							System.out.print("请输入学生姓名：");
							String input2 = sc.next();
							System.out.print("请输入学生年龄：");
							int input3 = sc.nextInt();
							System.out.print("请输入学生成绩：");
							double input4 = sc.nextDouble();
							student[i].setInfo(input1, input2, input3, input4);
							repeat = true;
						}
					}
				}
			} else {
				System.out.println("没有学生。");
			}
		}		
	}
	
	// 工人管理部分
	static class WorkerInner {
		
		static Worker[] worker = new Worker[10];
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
				case 1: { pointer = WorkerInner.addWorker(worker, pointer); break; }
				case 2: { WorkerInner.listWorker(worker, pointer); break; }
				case 3: { WorkerInner.findWorker(worker, pointer); break; }
				case 4: { pointer = WorkerInner.deleteWorker(worker, pointer); break; }
				case 5: { WorkerInner.modifyWorker(worker, pointer); break; }
				case 6: { return; }
				default: { flag = false; }
				}			
			}
		}

		static final String author = "1711640118";

		static int addWorker(Worker[] worker, int pointer) {		// returns pointer
			if (pointer > 9) { System.out.println("列表数据限 10 人，空间已满。"); return pointer; }
			// 查找有没有重复 ID，如有，退回
			while (pointer < 10) {
				boolean flag = true;		// flag 为真，则工人有重复
				String input1 = null;
				while (flag) {
					System.out.print("请输入工人 ID（输入 0 返回菜单）：");
					input1 = sc.next();
					if (input1.equals("0")) { return pointer; }
					flag = false;
					for (int i = 0; i < pointer; i++) {
						if (worker[i].getID().equals(input1)) {
							flag = true;
							System.out.print("工人 ID 重复，重试");
						}
					}
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
				worker[pointer] = new Worker(input1, input2, input3, input4, input5);
				pointer++;
			}
			return pointer;
		}
		
		static void listWorker(Worker[] worker, int pointer) {
			if (pointer != 0) {
				System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
				for (int i = 0; i < pointer; i++) {
					worker[i].printInfo();
				}				
			} else {
				System.out.println("没有工人。");
			}
		}
		
		static void findWorker(Worker[] worker, int pointer) {
			if (pointer != 0) {
				System.out.print("要查找的工人姓名：");
				String input = sc.next();
				boolean flag = false;
				for (int i = 0; i < pointer; i++) {
					if ( worker[i].getName().equals(input) ) {
						System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
						worker[i].printInfo();
						flag = true;
					}
				}
				if (!flag) { System.out.println("找不到该工人。"); }
			} else {
				System.out.println("没有工人。");
			}
		}
		
		static int deleteWorker(Worker[] worker, int pointer) {		// returns pointer
			if (pointer != 0) {
				while (pointer >= 0) {
					boolean found = false;
					listWorker(worker, pointer);
					System.out.print("请输入要删除的工人 ID（输入 0 返回菜单）：");
					String input = sc.next();
					if (input.equals("0")) { break; }
					for (int i = 0; i < pointer; i++) {
						if ( worker[i].getID().equals(input) ) {
							int j;
							for (j = i; j < pointer - 1; j++) {
								worker[j] = worker[j+1];
							}
							worker[j] = null;
							found = true;
							break;
						}
					}
					if (found) {
						pointer--;
					} else {
						System.out.println("找不到这个工人。");
					}
				}
			} else {
				System.out.println("没有工人。");
			}
			return pointer;
		}
		
		static void modifyWorker(Worker[] worker, int pointer) {
			if (pointer != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = false;											// 假定找不到或者取消操作，找到后才重复
					listWorker(worker, pointer);
					System.out.print("请输入要修改的工人 ID（输入 0 返回菜单）：");
					String input = sc.next();
					if (input.equals("0")) { return; }
					for (int i = 0; i < pointer; i++) {
						if ( worker[i].getID().equals(input) ) {			// 找到要修改的人
							String input1 = null;
							boolean flag = true;
							while (flag) {
								flag = false;
								System.out.print("请输入工人 ID：");
								input1 = sc.next();
								for (int j = 0; j < pointer; j++) {
									if (worker[j].getID().equals(input1) && !(input1.equals(input))) {	// 找到列表中已经有这个 ID 就不放行，除非这就是它自己
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
							System.out.print("请输入工人工资：");
							double input4 = sc.nextDouble();
							System.out.print("请输入工人工作：");
							String input5 = sc.next();
							worker[i].setInfo(input1, input2, input3, input4, input5);
							repeat = true;
						}
					}
				}
			} else {
				System.out.println("没有工人。");
			}
		}		
	}

}

abstract class Person {
	String ID, name;
	int age;
	static final String author = "1711640118";
}

class Student extends Person {
	private double score;
	public Student(String ID, String name, int age, double score) {
		setInfo(ID, name, age, score);
	}
	public void setInfo(String ID, String name, int age, double score) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.score = score;		
	}
	String getID() { return this.ID; }
	String getName() { return this.name; }
	int getAge() { return this.age; }
	double getScore() { return this.score; }
	void printInfo() { System.out.println( String.format(" %8s | %8s | %6d | %6.1f ", this.ID, this.name, this.age, this.score) ); }
}

class Worker extends Person {
	private double salary;
	private String job;
	public Worker(String ID, String name, int age, double salary, String job) {
		setInfo(ID, name, age, salary, job);
	}
	public void setInfo(String ID, String name, int age, double salary, String job) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.job = job;
	}
	String getID() { return this.ID; }
	String getName() { return this.name; }
	int getAge() { return this.age; }
	double getSalary() { return this.salary; }
	String getJob() { return this.job; }
	void printInfo() { System.out.println( String.format(" %8s | %8s | %6d | %6.1f | %8s ", this.ID, this.name, this.age, this.salary, this.job) ); }
}
