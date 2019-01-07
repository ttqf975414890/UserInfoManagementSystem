package UIMS.Controller;

import UIMS.model.*;
import UIMS.view.*;
import UIMS.vo.Student;

public class ControllerImpl implements Controller {

	private StudentModel stuModel;
	private View view;
	/** 构造方法  */
	public ControllerImpl(StudentModel StuModel, View view) {
		stuModel = StuModel;
		this.view = view;
		this.view.addStudentInfoListener(this); 	// 向视图注册控制器自身
	}
	public void handleGetStudentInfo(String ID) {
		Student stu = null;
		stu = stuModel.findID(ID);
		view.showDisplay(stu);		
	}
	public void handleAddStudentInfo(Student student) {
		stuModel.add(student);
	}
	public void handleDeleteStudentInfo(Student student) {
		stuModel.delete(student);
	}
	public void handleUpdateStudentInfo(Student from, Student to) {
		stuModel.update(from, to);
	}
	public void handleGetAllStudentInfo() {
		// TODO 列出怎么搞
		stuModel.list();
	}

}
