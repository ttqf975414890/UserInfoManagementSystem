/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-11
 * @version 4.2.0
 */

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
	public double getSalary() { return this.salary; }
	public String getJob() { return this.job; }
}