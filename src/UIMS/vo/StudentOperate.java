package UIMS.vo;

public interface StudentOperate {
	public int add(Person person);					// 添加
	public int delete(Person person);				// 删除
	public int update(Student from, Student to);	// 修改
	public Student findName(String toSearch);		// 精确查找
	public Student fuzzySearch(String toSearch);	// 模糊查找
	public int count();
}