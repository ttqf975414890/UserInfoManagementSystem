/**
 * @author 计算机1701　叶文滔　1711640118
 * @date 2019-01-02
 * @version 2.1.0
 */

package UIMS;

import java.util.*;
import UIMS.vo.*;
import UIMS.model.*;
import UIMS.view.*;
// import org.jawin.*;
// import org.jawin.FuncPtr;
// import org.jawin.ReturnFlags;
// import com.jacob.activeX.ActiveXComponent;
// import com.jacob.com.Dispatch;


public class UIMSmain {
	
	// private static ActiveXComponent printController = null;
	// private static Dispatch printObj = null;

	public static void main(String[] args) {
		
		/* JNI 过于复杂 🖕
		 */
		
		/* jacob 要求提供 com 组件，kernel32 是一个标准 dll，不是 com 组件
		try{
			printController = new ActiveXComponent("kernel32.dll");
			printObj = (Dispatch)(printController.getObject());
		} catch (Exception e) {
			printObj = new Dispatch();
			System.out.println(e.getMessage() + "读取终端控制DLL失败");
		}
		Dispatch.call(printObj, "Beep", 1000, 1000);
		*/
		
		/* jawin 不支持 64 位的 JDK 🖕
		try {
				FuncPtr msgBox = new FuncPtr("USER32.DLL", "MessageBoxW");
				msgBox.invoke_I(0, "Hello From a DLL", "From Jawin", 0, ReturnFlags.CHECK_NONE);
		} catch (Exception e) {
			
		}
		*/
		
		/* JNative 进行 int 转 DWORD 时发生错误，不开源，无法修复 🖕 
		// Kernel32.Beep(new DWORD(1), new DWORD(1));
		*/
		
		StudentModel stuModel = new StudentModel();
		WorkerModel workModel = new WorkerModel();
		CLI view = new CLI(stuModel, workModel);
		// StoreController ctrl = new StoreControllerImpl(model,view);
		
	}
	
}

