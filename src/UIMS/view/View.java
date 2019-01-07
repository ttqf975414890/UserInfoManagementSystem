package UIMS.view;

import UIMS.Controller.*;
import UIMS.vo.*;

public interface View {
	
	/** 注册处理用户动作的监听器，即 StoreController 控制器 */
	public void addStudentInfoListener(Controller controller);
	
	/** 在图形界面上显示数据，参数 display 表示待显示的数据 */
	public void showDisplay(Object display);
	
	/** 当模型层修改了数据库中某个客户的信息时，同步刷新视图层
	的图形界面 */
	public void handleStudentChange(Student student);
	
}
