/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-11
 * @version 4.2.0
 */

package UIMS.model;

import java.util.*;
import UIMS.vo.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class WorkerModel implements PersonOperate {
	
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Worker> workList = new ArrayList<Worker>();
	private Connection DBconn;
		
	static final String author = "1711640118";
	
	public WorkerModel(Connection DatabaseConnection, boolean createNewTable) {
		DBconn = DatabaseConnection;
		// PreparedStatement pstmt = null;
		if (createNewTable) {
			try {
				DBconn.prepareStatement("create table workerData(ID text, name text, age int, salary float, job text) character set utf8;").execute();
			} catch (SQLException e) {
				System.out.println("数据库错误。");
			}			
		}
	}
	public boolean add(Person person) {
		Worker work = (Worker)person;
		PreparedStatement pstmt = null;
		try {
			// 查重
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID from workerData where ID=?;");
			pstmt.setString(1, work.getID());
			ResultSet rset = pstmt.executeQuery();
			int rowCount = 0;
			while (rset.next()) { rowCount++; }
			if (rowCount == 0) {
				// 查重完毕，开始添加
				pstmt = (PreparedStatement) DBconn.prepareStatement("insert into workerData(ID,name,age,salary,job) values(?,?,?,?,?);");
				pstmt.setString(1, work.getID());
				pstmt.setString(2, work.getName());
				pstmt.setInt(3, work.getAge());
				pstmt.setFloat(4, (float)work.getSalary());
				pstmt.setString(5, work.getJob());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/** 根据 ID 查找，返回单个工人对象 */
	public Person findID(String ID) {
		PreparedStatement pstmt = null;
		try {
			// 检查工人数量
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID,name,age,salary,job from workerData where ID=?;");
			pstmt.setString(1, ID);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Worker work = new Worker(ID, rset.getString(2), rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue(), rset.getString(5) );
				return work;
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
	
	/** 返回是否存在这个 ID */
	public boolean hasID(String ID) {
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID from workerData where ID=?;");
			pstmt.setString(1, ID);
			ResultSet rset = pstmt.executeQuery();
			int rowCount = 0;
			while (rset.next()) { rowCount++; }
			if (rowCount >= 1) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	/** 根据姓名精确查找，返回找到的所有工人列表 */
	public ArrayList<Person> findName(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID,name,age,salary,job from workerData where name=?;");
			pstmt.setString(1, name);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Worker work = new Worker(rset.getString(1), name, rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue(), rset.getString(5) );
				returnList.add(work);
			}
		} catch (SQLException e) {
			return null;
		}		
		return returnList;
	}
	/** 模糊查找，找有没有这个人包含这个字符串，返回找到的所有工人列表 */
	public ArrayList<Person> fuzzySearch(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		PreparedStatement pstmt = null;
		try {
			// emmm... Here can NOT use the method as other functions do (pstmt.setString) because of some bug of this JDBC... Hopefully it can be fixed in the future version.
			// (Thus it's dangerous here
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID,name,age,salary,job from workerData where name like '%" + name + "%';");
			// pstmt.setString(1, name);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Worker work = new Worker(rset.getString(1), rset.getString(2), rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue(), rset.getString(5) );
				returnList.add(work);
			}
			// Because of the insecurity of the method above, it need to catch all exceptions.
		} catch (Exception e) {
			return returnList;
		}		
		return returnList;
	}
	
	/** 按工人对象删除工人，成功返回 true，失败返回 false */
	public boolean delete(Person person) {
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("delete from workerData where ID=?");
			pstmt.setString(1, person.getID());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/** 按工人 ID 删除工人，成功返回 true，失败返回 false */
	public boolean delete(String ID) {
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("delete from workerData where ID=?");
			pstmt.setString(1, ID);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/** 按工人对象修改工人，成功返回 true，失败返回 false */
	public boolean update(Person from, Person to) {
		Worker workTo = (Worker)to;
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("update workerData set ID=?,name=?,age=?,salary=?,job=? where id=? ");
			pstmt.setString(1, workTo.getID());
			pstmt.setString(2, workTo.getName());
			pstmt.setInt(3, workTo.getAge());
			pstmt.setFloat(4, (float)workTo.getSalary());
			pstmt.setString(5, workTo.getJob());
			pstmt.setString(6, from.getID());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/** 按工人 ID 修改工人，成功返回 true，失败返回 false */
	public boolean update(String from, Person to) {
		Worker workTo = (Worker)to;
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("update workerData set ID=?,name=?,age=?,salary=?,job=? where id=? ");
			pstmt.setString(1, workTo.getID());
			pstmt.setString(2, workTo.getName());
			pstmt.setInt(3, workTo.getAge());
			pstmt.setFloat(4, (float)workTo.getSalary());
			pstmt.setString(5, workTo.getJob());
			pstmt.setString(6, from);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/** 返回所有工人的列表 */
	public ArrayList<Worker> list() {		
		ArrayList<Worker> returnList = new ArrayList<Worker>(); 
		// PreparedStatement pstmt = null;
		try {
			ResultSet rset = (ResultSet) DBconn.prepareStatement("select id,name,age,salary,job from workerData").executeQuery();
			while (rset.next()) {
				Worker work = new Worker(rset.getString(1), rset.getString(2), rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue(), rset.getString(5) );
				returnList.add(work);
			}
		} catch (SQLException e) {
			return null;
		}		
		return returnList;
	}
	
	/** 返回工人数量 */
	public int count() {
		// PreparedStatement pstmt = null;
		try {
			ResultSet rset = (ResultSet) DBconn.prepareStatement("select * from workerData;").executeQuery();
			int rowCount = 0;
			while (rset.next()) { rowCount++; }
			return rowCount;
		} catch (SQLException e) {
			return 0;
		}
	}
	
}
