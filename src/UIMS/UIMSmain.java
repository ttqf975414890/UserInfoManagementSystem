/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-06
 * @version 3.0.0
 */

package UIMS;

import UIMS.model.*;
import UIMS.view.*;


public class UIMSmain {
	
	public static void main(String[] args) {
		
		StudentModel stuModel = new StudentModel();
		WorkerModel workModel = new WorkerModel();
		FileOperate fileOperate = new FileOperate();
		CLI view = new CLI(stuModel, workModel, fileOperate);
		
	}
	
}
