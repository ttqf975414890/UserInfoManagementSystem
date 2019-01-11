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

public class StudentModel implements PersonOperate {
	
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Student> stuList = new ArrayList<Student>();
	private Connection DBconn;
		
	static final String author = "1711640118";
	
	public StudentModel(Connection DatabaseConnection, boolean createNewTable) {
		DBconn = DatabaseConnection;
		// PreparedStatement pstmt = null;
		if (createNewTable) {
			try {
				DBconn.prepareStatement("create table studentData(ID text, name text, age int, score float) character set utf8;").execute();
			} catch (SQLException e) {
				System.out.println("数据库错误。");
			}			
		}
	}
	public boolean add(Person person) {
		Student stu = (Student)person;
		PreparedStatement pstmt = null;
		try {
			// 查重
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID from studentData where ID=?;");
			pstmt.setString(1, stu.getID());
			ResultSet rset = pstmt.executeQuery();
			int rowCount = 0;
			while (rset.next()) { rowCount++; }
			if (rowCount == 0) {
				// 查重完毕，开始添加
				pstmt = (PreparedStatement) DBconn.prepareStatement("insert into studentData(ID,name,age,score) values(?,?,?,?);");
				pstmt.setString(1, stu.getID());
				pstmt.setString(2, stu.getName());
				pstmt.setInt(3, stu.getAge());
				pstmt.setFloat(4, (float)stu.getScore());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/** 根据 ID 查找，返回单个学生对象 */
	public Person findID(String ID) {
		PreparedStatement pstmt = null;
		try {
			// 检查学生数量
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID,name,age,score from studentData where ID=?;");
			pstmt.setString(1, ID);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Student stu = new Student(ID, rset.getString(2), rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue() );
				return stu;
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
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID from studentData where ID=?;");
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
	
	/** 根据姓名精确查找，返回找到的所有学生列表 */
	public ArrayList<Person> findName(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID,name,age,score from studentData where name=?;");
			pstmt.setString(1, name);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Student stu = new Student(rset.getString(1), name, rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue() );
				returnList.add(stu);
			}
		} catch (SQLException e) {
			return null;
		}		
		return returnList;
	}
	/** 模糊查找，找有没有这个人包含这个字符串，返回找到的所有学生列表 */
	public ArrayList<Person> fuzzySearch(String name) {
		ArrayList<Person> returnList = new ArrayList<Person>(); 
		PreparedStatement pstmt = null;
		try {
			// emmm... Here can NOT use the method as other functions do (pstmt.setString) because of some bug of this JDBC... Hopefully it can be fixed in the future version.
			// (Thus it's dangerous here
			pstmt = (PreparedStatement) DBconn.prepareStatement("select ID,name,age,score from studentData where name like '%" + name + "%';");
			// pstmt.setString(1, name);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Student stu = new Student(rset.getString(1), rset.getString(2), rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue() );
				returnList.add(stu);
			}
			// Because of the insecurity of the method above, it need to catch all exceptions.
		} catch (Exception e) {
			return returnList;
		}		
		return returnList;
	}
	
	/** 按学生对象删除学生，成功返回 true，失败返回 false */
	public boolean delete(Person person) {
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("delete from studentData where ID=?");
			pstmt.setString(1, person.getID());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/** 按学生 ID 删除学生，成功返回 true，失败返回 false */
	public boolean delete(String ID) {
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("delete from studentData where ID=?");
			pstmt.setString(1, ID);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/** 按学生对象修改学生，成功返回 true，失败返回 false */
	public boolean update(Person from, Person to) {
		Student stuTo = (Student)to;
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("update studentData set ID=?,name=?,age=?,score=? where id=? ");
			pstmt.setString(1, stuTo.getID());
			pstmt.setString(2, stuTo.getName());
			pstmt.setInt(3, stuTo.getAge());
			pstmt.setFloat(4, (float)stuTo.getScore());
			pstmt.setString(5, from.getID());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/** 按学生 ID 修改学生，成功返回 true，失败返回 false */
	public boolean update(String from, Person to) {
		Student stuTo = (Student)to;
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) DBconn.prepareStatement("update studentData set ID=?,name=?,age=?,score=? where id=? ");
			pstmt.setString(1, stuTo.getID());
			pstmt.setString(2, stuTo.getName());
			pstmt.setInt(3, stuTo.getAge());
			pstmt.setFloat(4, (float)stuTo.getScore());
			pstmt.setString(5, from);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/** 返回所有学生的列表 */
	public ArrayList<Student> list() {		
		ArrayList<Student> returnList = new ArrayList<Student>(); 
		// PreparedStatement pstmt = null;
		try {
			ResultSet rset = (ResultSet) DBconn.prepareStatement("select id,name,age,score from studentData").executeQuery();
			while (rset.next()) {
				Student stu = new Student(rset.getString(1), rset.getString(2), rset.getInt(3), (new BigDecimal(String.valueOf(rset.getFloat(4)))).doubleValue() );
				returnList.add(stu);
			}
		} catch (SQLException e) {
			return null;
		}		
		return returnList;
	}
	
	/** 返回学生数量 */
	public int count() {
		// PreparedStatement pstmt = null;
		try {
			ResultSet rset = (ResultSet) DBconn.prepareStatement("select * from studentData;").executeQuery();
			int rowCount = 0;
			while (rset.next()) { rowCount++; }
			return rowCount;
		} catch (SQLException e) {
			return 0;
		}
	}
	
}
