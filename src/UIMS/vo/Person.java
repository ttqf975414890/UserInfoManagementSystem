/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-10
 * @version 4.1.0
 */

package UIMS.vo;

public abstract class Person {
	String ID, name;
	int age;
	static final String author = "1711640118";
	public String getID() { return this.ID; }
	public String getName() { return this.name; }
	public int getAge() { return this.age; }
}