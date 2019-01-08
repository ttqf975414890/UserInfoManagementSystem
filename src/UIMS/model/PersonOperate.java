package UIMS.model;

import java.util.ArrayList;
import UIMS.vo.Person;

public interface PersonOperate {
	public int add(Person person);							// 添加
	public int delete(Person person);						// 删除
	public int update(Person from, Person to);				// 修改
	public Person findID(String ID);						// 按 ID 查找
	public ArrayList<Person> findName(String toSearch);		// 按姓名精确查找
	public ArrayList<Person> fuzzySearch(String toSearch);	// 按姓名模糊查找
	public String SerializeToCsvString();
	public int count();
}