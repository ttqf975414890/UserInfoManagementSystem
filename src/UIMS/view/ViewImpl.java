package UIMS.view;

import UIMS.view.GUI;
import UIMS.vo.Student;
import UIMS.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import UIMS.Controller.*;

public class ViewImpl implements View {
	
	private GUI gui;
	private StudentModel stuModel;
	private Controller ctrl;
	private Object display;
	
	/** 构造方法 */
	public ViewImpl(StudentModel studentModel) {
		stuModel = studentModel;
		// stuModel.addChangeListener(this);
		gui = new GUI();
		// TODO 向图形界面注册监听器
	}
	
	/** 注册 Controller */
	public void addStudentInfoListener(Controller controller) {
		ctrl = controller;
	}
	
	/** 在图形界面上展示参数 display 指定的数据 */
	public void showDisplay(Object display){
		// if(!(display instanceof Exception))this.display=display;
		this.display = display;
		if (display instanceof Student) {
			gui.refreshCustPane((Student)display);
		}
		if (display instanceof ArrayList<?>){
			gui.refreshAllCustPan((ArrayList<Student>)display);
		}
	}

	/** 刷新界面上的客户信息*/
	/*
	public void handleStudentChange(Student student) {
		if (display instanceof Student) {
			gui.refreshCustPane((Student)display);
		}
		if (display instanceof ArrayList<?>){
			gui.refreshAllCustPan((ArrayList<Student>)display);
		}
	}
	*/
	
	/** 刷新界面上的客户信息*/
	public void handleCustomerChange(Customer cust)throws StoreException{
		long cIdOnPan=-1;
		try{
			if(display instanceof Set){
				gui.refreshAllCustPan(storemodel.getAllCustomers());
				return;
			}
			if(display instanceof Customer){
				cIdOnPan=gui.getCustIdOnCustPan();
				if(cIdOnPan!=cust.getId())return;
				gui.refreshCustPane(cust);
			}
		}catch(Exception e){
			System.out.println("StoreViewImpl processCustomer "+e);
		}
	}
		/** 监听图形界面上【查询客户】按钮的 ActionEvent 的监听器 */
	transient ActionListener custGetHandler = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			StoreController sc;
			long custId;
			custId = gui.getCustIdOnCustPan();
			for(int i=0;i<storecontrollers.size();i++){
				sc=storeControllers.get(i);
				sc.handleGetCustomerGesture(custId);
			}
		}
	};
	
}
