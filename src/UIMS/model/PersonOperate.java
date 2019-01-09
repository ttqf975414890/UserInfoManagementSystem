/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-10
 * @version 4.1.0
 */

package UIMS.model;

import java.util.ArrayList;
import UIMS.vo.Person;

public interface PersonOperate {
	public boolean add(Person person);							// 添加
	public boolean delete(Person person);						// 删除
	public boolean update(Person from, Person to);				// 修改
	public Person findID(String ID);						// 按 ID 查找
	public ArrayList<Person> findName(String toSearch);		// 按姓名精确查找
	public ArrayList<Person> fuzzySearch(String toSearch);	// 按姓名模糊查找
	public int count();
}