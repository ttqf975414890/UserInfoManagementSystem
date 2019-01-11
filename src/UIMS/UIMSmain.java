/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-11
 * @version 4.2.0
 */

package UIMS;

import UIMS.Controller.ControllerImpl;
import UIMS.model.*;
import UIMS.view.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class UIMSmain {
	
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static String DBURL = "jdbc:mysql://localhost:3306/";
	private static String DBUSER = "root";
	private static String DBPASSWORD = "admin";

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(DBDRIVER);
			while (true) {
				System.out.print("请输入 MySQL 数据库地址:端口，默认值为：localhost:3306。使用默认值请输入 0，退出请直接关闭：");
				String input = sc.next();
				if (!input.equals("0") ) {
					DBURL = "jdbc:mysql://" + input;
				}
				System.out.print("请输入 MySQL 数据库用户名，默认值为：root。使用默认请输入 0：");
				input = sc.next();
				if (!input.equals("0") ) {
					DBUSER = input;
				}
				System.out.print("请输入 MySQL 数据库用户名，默认值为：admin。使用默认请输入 0：");
				input = sc.next();
				if (!input.equals("0") ) {
					DBPASSWORD = input;
				}
				try {
					Connection DBTempConn = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
					boolean createNewTable = false;
					try {	// 这一块用于探测是不是已经创建好数据库了，没有的话就创建
						DBTempConn.prepareStatement("use UIMS;").execute();
					} catch (SQLException err) {
						createNewTable = true;
						DBTempConn.prepareStatement("create database UIMS;").execute();
					}
					StudentModel stuModel = new StudentModel(DriverManager.getConnection(DBURL + "uims", DBUSER, DBPASSWORD), createNewTable);
					WorkerModel workModel = new WorkerModel(DriverManager.getConnection(DBURL + "uims", DBUSER, DBPASSWORD), createNewTable);
					System.out.println("数据库连接成功。");
					StuGUI stuGUI = new StuGUI();
					WorkGUI workGUI = new WorkGUI();
					new ControllerImpl(stuModel, workModel, stuGUI, workGUI);
					break;
				} catch (SQLException err) {
					System.out.println("数据库连接失败。");
					System.out.println(err.getMessage());
					if (err.getMessage().contains("Communications link failure")) {
						System.out.println("数据库连接失败。请检查 IP 和端口。");
					} else if (err.getMessage().contains("Access denied")) {
						System.out.println("用户名或密码错误，请重试。");
					} else {
						System.out.println("失败。错误信息：" + err.getMessage());
					}
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("调用 JDBC/MySQL 接口失败。");;
		}
		sc.close();
	}
	
}
