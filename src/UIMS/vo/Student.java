/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-10
 * @version 4.1.0
 */

package UIMS.vo;

public class Student extends Person {
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
	public double getScore() { return this.score; }
	public void printInfo() {
		System.out.println( String.format(" %8s | %8s | %6d | %6.1f ", this.ID, this.name, this.age, this.score) );
	}
}