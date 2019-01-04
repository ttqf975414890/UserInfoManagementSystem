package UIMS.vo;

public interface StudentOperate {
	public int add(Student student);				// 添加
	public int delete(Student student);				// 删除
	public int update(Student from, Student to);	// 修改
	public Student findName(String toSearch);			// 精确查找
	public Student fuzzySearch(String toSearch);	// 模糊查找
}