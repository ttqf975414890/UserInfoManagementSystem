/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-10
 * @version 4.1.1
 */

package UIMS.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import UIMS.Controller.Controller;
import UIMS.model.StudentModel;
import UIMS.view.TableCellListener;

public class GUI {
	
	private Controller ctrl;
	private int maxLine = 0;
	// 学生面板
	private JFrame stuFrame;
	private Container stuContent;
	private BorderLayout stuLayout = new BorderLayout();
	private String[] stuTableHeaders = {"ID", "姓名", "年龄", "成绩"};
	private JTable stuTable;
	private JScrollPane stuTablePane;
	private DefaultTableModel stuTableModel;
	private DefaultTableCellRenderer stuTableRenderer = new DefaultTableCellRenderer();// 设置表格对齐模式
	private JRadioButton stuFindRadio1 = new JRadioButton("按 ID", true);
	private JRadioButton stuFindRadio2 = new JRadioButton("按姓名");
	private JRadioButton stuFindRadio3 = new JRadioButton("按姓名（模糊）");
	private JLabel stuFindLabel = new JLabel("查找：　");
	private JTextField stuFindText = new JTextField();
	private GridLayout _1x5GridLayout = new GridLayout(1, 5);
	private GridLayout _2x1GridLayout = new GridLayout(2, 1);	// 2 行 1 列
	private Container stuFind = new Container();
	private JLabel stuFrameStatus;
	private Container stuBottomBar = new Container();

	/** 构造方法 */
	public GUI() {
		buildDisplayStudent();
		// buildDisplayWorker();
	}
	
	/** 创建学生面板 */
	void buildDisplayStudent() {
		stuFrame = new JFrame("学生信息管理系统");
		stuContent = stuFrame.getContentPane();
		stuContent.setLayout(stuLayout);
		// 设置 table 样式
		stuTableModel = new DefaultTableModel(stuTableHeaders, 0);
		stuTableModel.addRow(new Object[]{"", "", "", ""});
		stuTable = new JTable(stuTableModel);
		stuTablePane = new JScrollPane(stuTable);
		stuTable.setRowHeight(32);
		stuTable.getTableHeader().setFont(new Font("微软雅黑", 0, 18));
		stuTable.setFont(new Font("微软雅黑", 0, 14));
		stuTableRenderer.setHorizontalAlignment(JLabel.CENTER);		// 设置表格对齐模式
		for(int i = 0; i< stuTable.getColumnCount(); i++) {
			stuTable.getColumn(stuTable.getColumnName(i)).setCellRenderer(stuTableRenderer);
		}
		/* 传统的表格变化监听器，已使用 TableCellListener 代替
		// TableChangeListener tableChangeListener = new TableChangeListener();
		// stuTable.getModel().addTableModelListener(tableChangeListener);
		*/
		new TableCellListener(stuTable, tableAction);
		// tableKeyListener tablePressListener = new KeyListener();
		stuTable.addKeyListener(new tableKeyListener());
		// 设置查找器样式
		stuFind.add(stuFindRadio1);
		stuFind.add(stuFindRadio2);
		stuFind.add(stuFindRadio3);
		StuRadioChangeListener stuRadioChangeListener = new StuRadioChangeListener();
		stuFindRadio1.addActionListener(stuRadioChangeListener);
		stuFindRadio2.addActionListener(stuRadioChangeListener);
		stuFindRadio3.addActionListener(stuRadioChangeListener);
		stuFindRadio1.setFont(new Font("微软雅黑", 0, 18));
		stuFindRadio2.setFont(new Font("微软雅黑", 0, 18));
		stuFindRadio3.setFont(new Font("微软雅黑", 0, 18));
		stuFindLabel.setFont(new Font("微软雅黑", 0, 18));
		stuFindLabel.setHorizontalAlignment(JLabel.RIGHT);
		stuFindText.setFont(new Font("微软雅黑", 0, 16));
		stuFindText.setToolTipText("在此处输入要查找的内容，清空此框以显示全部数据");
		stuFindText.getDocument().addDocumentListener(new findChangeListener());
		stuFind.add(stuFindLabel);
		stuFind.add(stuFindText);
		stuFind.setLayout(_1x5GridLayout);
		stuFrameStatus = new JLabel(" 在表格的最后一行空白行双击以创建新学生。");
		stuFrameStatus.setFont(new Font("微软雅黑", 0, 16));
		stuBottomBar.setLayout(_2x1GridLayout);			// 设置查找器整体
		stuBottomBar.add(stuFind);
		stuBottomBar.add(stuFrameStatus);
		stuContent.add(stuTablePane, BorderLayout.CENTER);
		stuContent.add(stuBottomBar, BorderLayout.SOUTH);
		stuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 设置窗体
		stuFrame.pack();
		stuFrame.setSize(960, 540);
		stuFrame.setVisible(true);
	}
	/** 处理 radioButton 改变事件 */
	class StuRadioChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stuFindRadio1.setSelected(false);
			stuFindRadio2.setSelected(false);
			stuFindRadio3.setSelected(false);
			switch (e.getActionCommand()) {
			case "按 ID": { stuFindRadio1.setSelected(true); break; }
			case "按姓名": { stuFindRadio2.setSelected(true); break; }
			case "按姓名（模糊）": { stuFindRadio3.setSelected(true); break; }
			}
			new findChangeListener().changed();
		}
	}
	/** 处理表格编辑事件 */
	Action tableAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;	// 不加这条会有警告
		public void actionPerformed(ActionEvent e)
		{
			try {
				TableCellListener tcl = (TableCellListener)e.getSource();
				if (tcl.getOldValue().equals(tcl.getNewValue())) { return; }			// 如果啥都没改，就啥也不干
				/*
				System.out.printf("cell changed%n");
				System.out.print("		Row   : " + tcl.getRow());
				System.out.print("		Column: " + tcl.getColumn());
				System.out.print("		Old   : " + tcl.getOldValue());
				System.out.print("		New   : " + tcl.getNewValue());
				System.out.println("");
				 */		
				Object rowData1 = stuTable.getValueAt(tcl.getRow(), 0);
				Object rowData2 = stuTable.getValueAt(tcl.getRow(), 1);
				Object rowData3 = stuTable.getValueAt(tcl.getRow(), 2);
				Object rowData4 = stuTable.getValueAt(tcl.getRow(), 3);
				if ( !rowData1.equals("") &&				// 四列都有数据
					 !rowData2.equals("") &&
					 !rowData3.equals("") &&
					 !rowData4.equals("") ) {
					if (tcl.getRow() == maxLine) {				// 若是在新一行编辑
						if ( isInteger(rowData3.toString()) &&			// 而且数据合法
							 isNumeric(rowData4.toString())) {
							if ( !ctrl.hasID(rowData1.toString()) ) {	// 而且 ID 不重复
								// DefaultTableModel tableModel = (DefaultTableModel)stuTable.getModel();
								stuTableModel.addRow(new Object[]{"", "", "", ""});
								maxLine++;
								if ( !ctrl.handleAddStudentInfo( rowData1.toString(), rowData2.toString(), Integer.parseInt(rowData3.toString()), Double.parseDouble(rowData4.toString()) ) ) throw new FileWritingException();	// 先处理 GUI 再操作数据是为了防止文件操作错误
								setStudentStatusBar("学生信息添加成功。当前学生数：" + (maxLine) + "。");
							} else {
								setStudentStatusBar("新学生 ID 与已有重复，请修改。");
							}
						} else {
							setStudentStatusBar("新学生数据不合法，“年龄”只能是整数，“成绩”只能是数字。");
						}
					} else {									// 若是编辑已有行
						if ( isInteger(rowData3.toString()) &&			// 而且数据合法
							 isNumeric(rowData4.toString())) {
							if (tcl.getColumn() == 0) {						// 如果它在改 ID
								if ( !ctrl.hasID(rowData1.toString()) ) {	// 而且新 ID 不是是已有的
									if ( !ctrl.handleUpdateStudentInfo( tcl.getOldValue().toString(), rowData1.toString(), rowData2.toString(), Integer.parseInt(rowData3.toString()), Double.parseDouble(rowData4.toString()) ) ) throw new FileWritingException();
									setStudentStatusBar("已将 ID 为 " + tcl.getOldValue().toString() + " 的学生的 ID 更改为 " + rowData1.toString() + "。");
								} else {
									setStudentStatusBar("新学生 ID 与已有重复，请修改。");
								}
							} else {										// 如果它在改 ID 以外的信息
								if ( !ctrl.handleUpdateStudentInfo( rowData1.toString(), rowData1.toString(), rowData2.toString(), Integer.parseInt(rowData3.toString()), Double.parseDouble(rowData4.toString()) ) ) throw new FileWritingException();;
								setStudentStatusBar("已更新 ID 为 " + rowData1.toString() + " 的学生的信息。");
							}
						} else {
							setStudentStatusBar("学生数据不合法，“年龄”只能是整数，“成绩”只能是数字。");
						}
					}
					return;
				}
				setStudentStatusBar();
			} catch (FileWritingException err) {
				// 刷新列表
				stuTableModel = new DefaultTableModel(stuTableHeaders, 0);
				stuTable.setModel(stuTableModel);
				ctrl.handleGetAllStudentInfo();
				stuTableModel.addRow(new Object[]{"", "", "", ""});
				setStudentStatusBar("学生数据写入失败。请检查 MySQL 服务是否正常或信息中是否包含无法识别的字符（如 emoji）。");
			}
		}
	};
	private class FileWritingException extends Exception {
		private static final long serialVersionUID = 1L;
		public FileWritingException() {
			super();
		}
	}

	/** 处理删除功能 */
	private class tableKeyListener implements KeyListener {
		
		public void keyTyped(KeyEvent e) {
			if ((int)e.getKeyChar() == 127) {		// 按 Del 键
				// 需要延迟执行，否则 stopCellEditing() 会引发线程安全问题
				Timer stopEditTimer = new Timer(1, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// System.out.println("stop edit");
							stuTable.getCellEditor().stopCellEditing();
							if (stuTable.getSelectedRow() != maxLine) {	// 不是最后一行
								if (stuTable.getSelectedRow() != -1) {		// 排除未选中的情况
									// System.out.println("remove line");
									String toRemove = stuTable.getValueAt(stuTable.getSelectedRow(), 0).toString();
									stuTableModel.removeRow(stuTable.getSelectedRow());
									maxLine--;
									if (!ctrl.handleDeleteStudentInfo(toRemove)) throw new FileWritingException();	// 先处理 GUI 再操作数据是为了防止文件操作错误
									setStudentStatusBar("已移除 ID: " + toRemove + " 的学生的信息。当前学生数：" + (maxLine) + "。");
								}
							} else {
								setStudentStatusBar("不能对新增行进行删除操作。", true);
							}
						} catch (FileWritingException err) {
							// 刷新列表
							stuTableModel = new DefaultTableModel(stuTableHeaders, 0);
							stuTable.setModel(stuTableModel);
							ctrl.handleGetAllStudentInfo();
							stuTableModel.addRow(new Object[]{"", "", "", ""});
							setStudentStatusBar("学生数据写入失败。请检查 MySQL 服务是否正常。");
						}
					}
				});
				stopEditTimer.setRepeats(false);
				stopEditTimer.start();
			}
		}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		
	}
	/** 处理查找功能 */
	private class findChangeListener implements DocumentListener {

		public void insertUpdate(DocumentEvent e) {
			changed();
		}
		public void removeUpdate(DocumentEvent e) {
			changed();
		}
		public void changedUpdate(DocumentEvent e) {
			changed();
		}
		private void changed() {
			String findTextContent = stuFindText.getText();
			stuTableModel = new DefaultTableModel(stuTableHeaders, 0);
			stuTable.setModel(stuTableModel);
			if (!findTextContent.equals("")) {				// 有内容，开始查找
				if (stuFindRadio1.isSelected()) {			// 按 ID
					ctrl.handleGetFilteredStudentInfo(0, findTextContent);
				} else if (stuFindRadio2.isSelected()) {
					ctrl.handleGetFilteredStudentInfo(1, findTextContent);
				} else if (stuFindRadio3.isSelected()) {
					ctrl.handleGetFilteredStudentInfo(2, findTextContent);
				}
			} else {
				ctrl.handleGetAllStudentInfo();
				stuTableModel.addRow(new Object[]{"", "", "", ""});
				setStudentStatusBar();
			}
		}
	}
	/** 设置状态栏文字 */
	public void setStudentStatusBar(String text, boolean appendBefore) {
		stuFrameStatus.setText(" " + text + "在表格的最后一行空白行双击以创建新学生。在一行删按 Del 键以删除学生信息。");
	}
	public void setStudentStatusBar(String text) {
		stuFrameStatus.setText(" " + text);
	}
	public void setStudentStatusBar() {
		stuFrameStatus.setText(" 在表格的最后一行空白行双击以创建新学生。在一行删按 Del 键以删除学生信息。");
	}
	
	/** 注册 Controller */
	public void addStudentInfoListener(Controller controller) {
		ctrl = controller;
	}
	
	/** 由 Controller 调用一次性列出学生列表的响应 */
	public void handleStudentList(String ID, String Name, int age, double score) {
		DefaultTableModel tableModel = (DefaultTableModel)stuTable.getModel();
		tableModel.addRow(new Object[]{ID, Name, age, score});
		if (stuTable.getRowCount() == 1) {					// 表格居中，由于 JTable 优化不佳，这里需要多做一步操作
			for(int i = 0; i< stuTable.getColumnCount(); i++) {
				stuTable.getColumn(stuTable.getColumnName(i)).setCellRenderer(stuTableRenderer);
			}			
		}
	}
	/** 由 Controller 调用的非用户操作添加学生（如从文件读取）的响应 */
	public void handleAddStudent(String ID, String Name, int age, double score) {
		stuTableModel.removeRow(maxLine);					// 先把空白行删掉
		handleStudentList(ID, Name, age, score);
		maxLine++;
		stuTableModel.addRow(new Object[]{"", "", "", ""});	// 再把空白行补回来
		if (stuTable.getRowCount() == 1) {					// 表格居中，由于 JTable 优化不佳，这里需要多做一步操作
			for(int i = 0; i< stuTable.getColumnCount(); i++) {
				stuTable.getColumn(stuTable.getColumnName(i)).setCellRenderer(stuTableRenderer);
			}			
		}
	}
	
	
	/** 判断数据是否合法。由于这里代码很少，就不新开包了 */
	public static boolean isNumeric(String str)
	{
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static boolean isInteger(String str)
	{
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

