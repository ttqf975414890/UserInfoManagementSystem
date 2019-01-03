/**
 * @author 计算机1701　叶文滔　1711640118
 * @date 2019-01-02
 * @version 1.0.0
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
		
		static List<Student> student = new ArrayList<Student>();
		// static Student[] student = new Student[10];
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
				case 1: { student = StudentInner.addStudent(student); break; }
				case 2: { student = StudentInner.listStudent(student); break; }
				case 3: { StudentInner.findStudent(student); break; }
				case 4: { StudentInner.deleteStudent(student); break; }
				case 5: { StudentInner.modifyStudent(student); break; }
				case 6: { return; }
				default: { flag = false; }
				}			
			}
		}

		static final String author = "1711640118";

		static List<Student> addStudent(List<Student> student) {
											//						||    ||
			// 查找有没有重复 ID，如有，退回	//					   \  /  \  /
			boolean mustBeTrue = true;		//					    \/    \/
			while (mustBeTrue) {	//	<-	<-	<-	恭喜！！！发现了编译器 / IDE 有 bug！这里直接填 true 会报错噢～
											//					    /\    /\
											//					   /  \  /  \
											//						||    ||	编译器会以为这里是死循环，实际能跳出来
				boolean flag = true;	// flag 为真，则学生有重复
				String input1 = null;
				while (flag) {
					System.out.print("请输入学生 ID（输入 0 返回菜单）：");
					input1 = sc.next();
					if (input1.equals("0")) { return student; }
					flag = false;
					for (Student stu : student) {
						if (stu.getID().equals(input1)) {
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
				student.add( new Student(input1, input2, input3, input4) );
			}
			return student;
		}
		
		static List<Student> listStudent(List<Student> student) {
			if (student.size() != 0) {
				System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
				for (Student stu : student) {
					stu.printInfo();
				}
			} else {
				System.out.println("没有学生。");
			}
			return student;
		}
		
		static List<Student> findStudent(List<Student> student) {
			if (student.size() != 0) {
				System.out.print("要查找的学生姓名：");
				String input = sc.next();
				boolean flag = false;
				for (Student stu : student) {
					if (stu.getName().equals(input)) {
						System.out.println("----ID----|---姓名---|--年龄--|--成绩--");
						stu.printInfo();
						flag = true;
					}
				}
				if (!flag) { System.out.println("找不到该学生。"); }
			} else {
				System.out.println("没有学生。");
			}
			return student;
		}
		
		static List<Student> deleteStudent(List<Student> student) {		// returns pointer
			if (student.size() != 0) {
				while (student.size() >= 0) {
					boolean found = false;
					listStudent(student);
					System.out.print("请输入要删除的学生 ID（输入 0 返回菜单）：");
					String input = sc.next();
					if (input.equals("0")) { break; }
					for (Student stu : student) {
						if ( stu.getID().equals(input) ) {		// 就决定要删你了
							student.remove(stu);
							found = true;
							break;
						}
					}
					if (!found) {
						System.out.println("找不到这个学生.");
					}
				}
			} else {
				System.out.println("没有学生。");
			}
			return student;
		}
		
		static List<Student> modifyStudent(List<Student> student) {
			if (student.size() != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = true;
					listStudent(student);
					System.out.print("请输入要修改的学生 ID（输入 0 返回菜单）：");
					String input = sc.next();						// input：源 ID
					if (input.equals("0")) { return student; }
					for (Student stu : student) {
						if ( stu.getID().equals(input) ) {			// 找到要修改的人
							String input1 = null;					// input1：目标 ID
							boolean flag = true;					// flag 为真则 ID 输入重复
							while (flag) {
								flag = false;
								System.out.print("请输入学生 ID：");
								input1 = sc.next();
								for (Student sturep : student) {
									if (sturep.getID().equals(input1) && !(input1.equals(input))) {	// 找到列表中已经有这个 ID 就不放行，除非这就是它自己
//									if (sturep.getID().equals(input1) && !(stu.getID().equals(input))) {	// 找到列表中已经有这个 ID 就不放行，除非这就是它自己
										// TODO 找它自己还可以优化
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
							stu.setInfo(input1, input2, input3, input4);
							repeat = true;
						}
					}
				}
			} else {
				System.out.println("没有学生。");
			}
			return student;
		}
	}
	
	// 工人管理部分
	static class WorkerInner {
		
		static List<Worker> worker = new ArrayList<Worker>();
		// static Worker[] worker = new Worker[10];
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
				case 1: { worker = WorkerInner.addWorker(worker); break; }
				case 2: { worker = WorkerInner.listWorker(worker); break; }
				case 3: { WorkerInner.findWorker(worker); break; }
				case 4: { WorkerInner.deleteWorker(worker); break; }
				case 5: { WorkerInner.modifyWorker(worker); break; }
				case 6: { return; }
				default: { flag = false; }
				}			
			}
		}

		static final String author = "1711640118";

		static List<Worker> addWorker(List<Worker> worker) {
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
					if (input1.equals("0")) { return worker; }
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
			return worker;
		}
		
		static List<Worker> listWorker(List<Worker> worker) {
			if (worker.size() != 0) {
				System.out.println("----ID----|---姓名---|--年龄--|--工资--|---工作---");
				for (Worker work : worker) {
					work.printInfo();
				}
			} else {
				System.out.println("没有工人。");
			}
			return worker;
		}
		
		static List<Worker> findWorker(List<Worker> worker) {
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
			return worker;
		}
		
		static List<Worker> deleteWorker(List<Worker> worker) {		// returns pointer
			if (worker.size() != 0) {
				while (worker.size() >= 0) {
					boolean found = false;
					listWorker(worker);
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
			return worker;
		}
		
		static List<Worker> modifyWorker(List<Worker> worker) {
			if (worker.size() != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = true;
					listWorker(worker);
					System.out.print("请输入要修改的工人 ID（输入 0 返回菜单）：");
					String input = sc.next();						// input：源 ID
					if (input.equals("0")) { return worker; }
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
			return worker;
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
