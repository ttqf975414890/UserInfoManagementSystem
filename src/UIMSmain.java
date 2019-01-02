/**
 * @author �����1701��Ҷ���ϡ�1711640118
 * @date 2019-01-03
 * @version 1.0.1
 */
import java.util.*;

public class UIMSmain {

	static Scanner sc = new Scanner(System.in);
	static final String byebye = ""
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n"
			+ "������������������������������������������������\n";
	
	public static void main(String[] args) {
		
		boolean flag = true;		// flag Ϊ��ʱ��ʾ�˵�
		while (true) {
			if (flag) {
			System.out.println("\nѧУ��Ϣ�������\n"
							+ "����1. ѧ����Ϣ����\n"
							+ "����2. ������Ϣ����\n"
							+ "����3. �˳�ϵͳ");
			}
			System.out.print("��ѡ����Ҫ�Ĳ�����");
			int input = sc.nextInt();
			switch (input) {
			case 1: { StudentInner.StudentManagement(); break; }
			case 2: { WorkerInner.WorkerManagement(); break; }
			case 3: { System.out.println(byebye); System.exit(0); }
			default: { flag = false; }
			}			
		}
				
	}
	
	// ѧ��������
	static class StudentInner {
		
		static Student[] student = new Student[10];
		static int pointer = 0;

		private static void StudentManagement() {
			
			boolean flag = true;		// flag Ϊ��ʱ��ʾ�˵�
			while (true) {
				if (flag) {
					System.out.println("\nѧ����Ϣ����\n"
									+ "����1. ����ѧ����Ϣ\n"
									+ "����2. �г�ȫ��ѧ����Ϣ\n"
									+ "����3. ��ѯѧ����Ϣ\n"
									+ "����4. ɾ��ѧ����Ϣ\n"
									+ "����5. �޸�ѧ����Ϣ\n"
									+ "����6. ������һ���˵�");
				}
				System.out.print("��ѡ�����Ĳ�����");
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
			if (pointer > 9) { System.out.println("�б������� 10 �ˣ��ռ�������"); return pointer; }
			// ������û���ظ� ID�����У��˻�
			while (pointer < 10) {
				boolean flag = true;		// flag Ϊ�棬��ѧ�����ظ�
				String input1 = null;
				while (flag) {
					System.out.print("������ѧ�� ID������ 0 ���ز˵�����");
					input1 = sc.next();
					if (input1.equals("0")) { return pointer; }
					flag = false;
					for (int i = 0; i < pointer; i++) {
						if (student[i].getID().equals(input1)) {
							flag = true;
							System.out.print("ѧ�� ID �ظ�������");
						}
					}
				}
				System.out.print("������ѧ��������");
				String input2 = sc.next();
				System.out.print("������ѧ�����䣺");
				int input3 = sc.nextInt();
				System.out.print("������ѧ���ɼ���");
				double input4 = sc.nextDouble();
				// �����µ� Student
				student[pointer] = new Student(input1, input2, input3, input4);
				pointer++;
			}
			return pointer;
		}
		
		static void listStudent(Student[] student, int pointer) {
			if (pointer != 0) {
				System.out.println("----ID----|---����---|--����--|--�ɼ�--");
				for (int i = 0; i < pointer; i++) {
					student[i].printInfo();
				}				
			} else {
				System.out.println("û��ѧ����");
			}
		}
		
		static void findStudent(Student[] student, int pointer) {
			if (pointer != 0) {
				System.out.print("Ҫ���ҵ�ѧ��������");
				String input = sc.next();
				boolean flag = false;
				for (int i = 0; i < pointer; i++) {
					if ( student[i].getName().equals(input) ) {
						System.out.println("----ID----|---����---|--����--|--�ɼ�--");
						student[i].printInfo();
						flag = true;
					}
				}
				if (!flag) { System.out.println("�Ҳ�����ѧ����"); }
			} else {
				System.out.println("û��ѧ����");
			}
		}
		
		static int deleteStudent(Student[] student, int pointer) {		// returns pointer
			if (pointer != 0) {
				while (pointer >= 0) {
					boolean found = false;
					listStudent(student, pointer);
					System.out.print("������Ҫɾ����ѧ�� ID������ 0 ���ز˵�����");
					String input = sc.next();
					if (input.equals("0")) { break; }
					for (int i = 0; i < pointer; i++) {
						if ( student[i].getID().equals(input) ) {		// �;���Ҫɾ����
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
						System.out.println("�Ҳ������ѧ��.");
					}
				}
			} else {
				System.out.println("û��ѧ����");
			}
			return pointer;
		}
		
		static void modifyStudent(Student[] student, int pointer) {
			if (pointer != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = false;											// �ٶ��Ҳ�������ȡ���������ҵ�����ظ�
					listStudent(student, pointer);
					System.out.print("������Ҫ�޸ĵ�ѧ�� ID������ 0 ���ز˵�����");
					String input = sc.next();
					if (input.equals("0")) { return; }
					for (int i = 0; i < pointer; i++) {
						if ( student[i].getID().equals(input) ) {			// �ҵ�Ҫ�޸ĵ���
							String input1 = null;
							boolean flag = true;
							while (flag) {
								flag = false;
								System.out.print("������ѧ�� ID��");
								input1 = sc.next();
								for (int j = 0; j < pointer; j++) {
									if (student[j].getID().equals(input1) && !(input1.equals(input))) {	// �ҵ��б����Ѿ������ ID �Ͳ����У�������������Լ�
										System.out.print("ѧ�� ID �ظ�������");
										flag = true;
										break;
									}
								}
							}
							System.out.print("������ѧ��������");
							String input2 = sc.next();
							System.out.print("������ѧ�����䣺");
							int input3 = sc.nextInt();
							System.out.print("������ѧ���ɼ���");
							double input4 = sc.nextDouble();
							student[i].setInfo(input1, input2, input3, input4);
							repeat = true;
						}
					}
				}
			} else {
				System.out.println("û��ѧ����");
			}
		}		
	}
	
	// ���˹�����
	static class WorkerInner {
		
		static Worker[] worker = new Worker[10];
		static int pointer = 0;
		
		private static void WorkerManagement() {
			
			boolean flag = true;		// flag Ϊ��ʱ��ʾ�˵�
			while (true) {
				if (flag) {
					System.out.println("\n������Ϣ����\n"
									+ "����1. ���ӹ�����Ϣ\n"
									+ "����2. �г�ȫ��������Ϣ\n"
									+ "����3. ��ѯ������Ϣ\n"
									+ "����4. ɾ��������Ϣ\n"
									+ "����5. �޸Ĺ�����Ϣ\n"
									+ "����6. ������һ���˵�");
				}
				System.out.print("��ѡ�����Ĳ�����");
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
			if (pointer > 9) { System.out.println("�б������� 10 �ˣ��ռ�������"); return pointer; }
			// ������û���ظ� ID�����У��˻�
			while (pointer < 10) {
				boolean flag = true;		// flag Ϊ�棬�������ظ�
				String input1 = null;
				while (flag) {
					System.out.print("�����빤�� ID������ 0 ���ز˵�����");
					input1 = sc.next();
					if (input1.equals("0")) { return pointer; }
					flag = false;
					for (int i = 0; i < pointer; i++) {
						if (worker[i].getID().equals(input1)) {
							flag = true;
							System.out.print("���� ID �ظ�������");
						}
					}
				}
				System.out.print("�����빤��������");
				String input2 = sc.next();
				System.out.print("�����빤�����䣺");
				int input3 = sc.nextInt();
				System.out.print("�����빤�˹��ʣ�");
				double input4 = sc.nextDouble();
				System.out.print("�����빤�˹�����");
				String input5 = sc.next();
				// �����µ� Worker
				worker[pointer] = new Worker(input1, input2, input3, input4, input5);
				pointer++;
			}
			return pointer;
		}
		
		static void listWorker(Worker[] worker, int pointer) {
			if (pointer != 0) {
				System.out.println("----ID----|---����---|--����--|--����--|---����---");
				for (int i = 0; i < pointer; i++) {
					worker[i].printInfo();
				}				
			} else {
				System.out.println("û�й��ˡ�");
			}
		}
		
		static void findWorker(Worker[] worker, int pointer) {
			if (pointer != 0) {
				System.out.print("Ҫ���ҵĹ���������");
				String input = sc.next();
				boolean flag = false;
				for (int i = 0; i < pointer; i++) {
					if ( worker[i].getName().equals(input) ) {
						System.out.println("----ID----|---����---|--����--|--����--|---����---");
						worker[i].printInfo();
						flag = true;
					}
				}
				if (!flag) { System.out.println("�Ҳ����ù��ˡ�"); }
			} else {
				System.out.println("û�й��ˡ�");
			}
		}
		
		static int deleteWorker(Worker[] worker, int pointer) {		// returns pointer
			if (pointer != 0) {
				while (pointer >= 0) {
					boolean found = false;
					listWorker(worker, pointer);
					System.out.print("������Ҫɾ���Ĺ��� ID������ 0 ���ز˵�����");
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
						System.out.println("�Ҳ���������ˡ�");
					}
				}
			} else {
				System.out.println("û�й��ˡ�");
			}
			return pointer;
		}
		
		static void modifyWorker(Worker[] worker, int pointer) {
			if (pointer != 0) {
				boolean repeat = true;
				while (repeat) {
					repeat = false;											// �ٶ��Ҳ�������ȡ���������ҵ�����ظ�
					listWorker(worker, pointer);
					System.out.print("������Ҫ�޸ĵĹ��� ID������ 0 ���ز˵�����");
					String input = sc.next();
					if (input.equals("0")) { return; }
					for (int i = 0; i < pointer; i++) {
						if ( worker[i].getID().equals(input) ) {			// �ҵ�Ҫ�޸ĵ���
							String input1 = null;
							boolean flag = true;
							while (flag) {
								flag = false;
								System.out.print("�����빤�� ID��");
								input1 = sc.next();
								for (int j = 0; j < pointer; j++) {
									if (worker[j].getID().equals(input1) && !(input1.equals(input))) {	// �ҵ��б����Ѿ������ ID �Ͳ����У�������������Լ�
										System.out.print("���� ID �ظ�������");
										flag = true;
										break;
									}
								}
							}
							System.out.print("�����빤��������");
							String input2 = sc.next();
							System.out.print("�����빤�����䣺");
							int input3 = sc.nextInt();
							System.out.print("�����빤�˹��ʣ�");
							double input4 = sc.nextDouble();
							System.out.print("�����빤�˹�����");
							String input5 = sc.next();
							worker[i].setInfo(input1, input2, input3, input4, input5);
							repeat = true;
						}
					}
				}
			} else {
				System.out.println("û�й��ˡ�");
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
