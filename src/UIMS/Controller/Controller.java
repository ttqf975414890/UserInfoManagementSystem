/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-11
 * @version 4.2.0
 */

package UIMS.Controller;

public interface Controller {
	
	/** 处理添加人员的动作，返回真为成功 */
	public boolean handleAddStudentInfo(String ID, String name, int age, double score);
	public boolean handleAddWorkerInfo(String ID, String name, int age, double salary, String job);
	
	/** 处理删除人员的动作，返回真为成功 */
	public boolean handleDeleteStudentInfo(String ID);
	public boolean handleDeleteWorkerInfo(String ID);
	
	/** 处理更新人员的动作，返回真为成功 */
	public boolean handleUpdateStudentInfo(String fromID, String toID, String toName, int toAge, double toScore);
	public boolean handleUpdateWorkerInfo(String fromID, String toID, String toName, int toAge, double toSalary, String toJob);
	
	/** 处理列出所有人员的动作，返回真为成功 */
	public void handleGetAllStudentInfo();
	public void handleGetAllWorkerInfo();
	
	/** 处理列出已过滤人员的动作
	 *  @param filter 0: 按 ID
	 *                1: 按姓名（精确）
	 *                2: 按姓名（模糊）
	 */
	public void handleGetFilteredStudentInfo(int filter, String filterContent);
	public void handleGetFilteredWorkerInfo(int filter, String filterContent);
	
	/** 向视图层返回所有人员的数量 */
	public int getStudentCount();
	public int getWorkerCount();
	/** 向视图层返回该 ID 是否存在 */
	public boolean hasStudent(String ID);
	public boolean hasWorker(String ID);
		
}
