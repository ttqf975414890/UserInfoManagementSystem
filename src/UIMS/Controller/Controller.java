package UIMS.Controller;

public interface Controller {
	
	/** 处理添加学生的动作，返回真为成功 */
	public boolean handleAddStudentInfo(String ID, String name, int age, double score);
	
	/** 处理删除学生的动作，返回真为成功 */
	public boolean handleDeleteStudentInfo(String ID);
	
	/** 处理更新学生的动作，返回真为成功 */
	public boolean handleUpdateStudentInfo(String fromID, String toID, String toName, int toAge, double toScore);
	
	/** 处理列出所有学生的动作，返回真为成功 */
	public void handleGetAllStudentInfo();
	
	/** 处理列出已过滤学生的动作
	 *  @param filter 0: 按 ID
	 *                1: 按姓名（精确）
	 *                2: 按姓名（模糊）
	 */
	public void handleGetFilteredStudentInfo(int filter, String filterContent);
}
