package UIMS.vo;

public class Worker extends Person {
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
	public String getID() { return this.ID; }
	public String getName() { return this.name; }
	public int getAge() { return this.age; }
	public double getSalary() { return this.salary; }
	public String getJob() { return this.job; }
	public void printInfo() {
		System.out.println( String.format(" %8s | %8s | %6d | %6.1f | %8s ", this.ID, this.name, this.age, this.salary, this.job) );
	}
}