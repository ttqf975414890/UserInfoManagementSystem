package UIMS.Controller;

import UIMS.vo.*;

public interface Controller {
	
	/** 处理根据 ID 查询学生的动作 */
	public void handleGetStudentInfo(String id);
	
	/** 处理添加学生的动作 */
	public void handleAddStudentInfo(Student student);
	
	/** 处理删除学生的动作 */
	public void handleDeleteStudentInfo(Student student);
	
	/** 处理更新学生的动作 */
	public void handleUpdateStudentInfo(Student from, Student to);
	
	/** 处理列出所有学生的动作 */
	public void handleGetAllStudentInfo();
	
}
