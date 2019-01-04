package UIMS.vo;

public interface WorkerOperate {
	public int add(Worker worker);					// 添加
	public int delete(Worker worker);				// 删除
	public int update(Worker from, Worker to);		// 修改
	public Worker findName(String toSearch);			// 精确查找
	public Worker fuzzySearch(String toSearch);		// 模糊查找
}