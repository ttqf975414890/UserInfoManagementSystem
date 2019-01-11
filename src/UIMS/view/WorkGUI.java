/**
 * @author 计算机 1701　叶文滔　1711640118
 * @date 2019-01-11
 * @version 4.2.0
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
import UIMS.view.TableCellListener;

public class WorkGUI {
	
	private Controller ctrl;
	private int maxLine = 0;
	// 工人面板
	private JFrame workFrame;
	private Container workContent;
	private BorderLayout workLayout = new BorderLayout();
	private String[] workTableHeaders = {"ID", "姓名", "年龄", "工资", "工作"};
	private JTable workTable;
	private JScrollPane workTablePane;
	private DefaultTableModel workTableModel;
	private DefaultTableCellRenderer workTableRenderer = new DefaultTableCellRenderer();// 设置表格对齐模式
	private JRadioButton workFindRadio1 = new JRadioButton("按 ID", true);
	private JRadioButton workFindRadio2 = new JRadioButton("按姓名");
	private JRadioButton workFindRadio3 = new JRadioButton("按姓名（模糊）");
	private JLabel workFindLabel = new JLabel("查找：　");
	private JTextField workFindText = new JTextField();
	private GridLayout _1x5GridLayout = new GridLayout(1, 5);
	private GridLayout _2x1GridLayout = new GridLayout(2, 1);	// 2 行 1 列
	private Container workFind = new Container();
	private JLabel workFrameStatus;
	private Container workBottomBar = new Container();

	/** 构造方法 */
	public WorkGUI() {
		buildDisplayWorker();
		// buildDisplayWorker();
	}
	
	/** 创建工人面板 */
	void buildDisplayWorker() {
		workFrame = new JFrame("工人信息管理系统");
		workContent = workFrame.getContentPane();
		workContent.setLayout(workLayout);
		// 设置 table 样式
		workTableModel = new DefaultTableModel(workTableHeaders, 0);
		workTableModel.addRow(new Object[]{"", "", "", ""});
		workTable = new JTable(workTableModel);
		workTablePane = new JScrollPane(workTable);
		workTable.setRowHeight(32);
		workTable.getTableHeader().setFont(new Font("微软雅黑", 0, 18));
		workTable.setFont(new Font("微软雅黑", 0, 14));
		workTableRenderer.setHorizontalAlignment(JLabel.CENTER);		// 设置表格对齐模式
		for(int i = 0; i< workTable.getColumnCount(); i++) {
			workTable.getColumn(workTable.getColumnName(i)).setCellRenderer(workTableRenderer);
		}
		/* 传统的表格变化监听器，已使用 TableCellListener 代替
		// TableChangeListener tableChangeListener = new TableChangeListener();
		// workTable.getModel().addTableModelListener(tableChangeListener);
		*/
		new TableCellListener(workTable, tableAction);
		// tableKeyListener tablePressListener = new KeyListener();
		workTable.addKeyListener(new tableKeyListener());
		// 设置查找器样式
		workFind.add(workFindRadio1);
		workFind.add(workFindRadio2);
		workFind.add(workFindRadio3);
		WorkRadioChangeListener workRadioChangeListener = new WorkRadioChangeListener();
		workFindRadio1.addActionListener(workRadioChangeListener);
		workFindRadio2.addActionListener(workRadioChangeListener);
		workFindRadio3.addActionListener(workRadioChangeListener);
		workFindRadio1.setFont(new Font("微软雅黑", 0, 18));
		workFindRadio2.setFont(new Font("微软雅黑", 0, 18));
		workFindRadio3.setFont(new Font("微软雅黑", 0, 18));
		workFindLabel.setFont(new Font("微软雅黑", 0, 18));
		workFindLabel.setHorizontalAlignment(JLabel.RIGHT);
		workFindText.setFont(new Font("微软雅黑", 0, 16));
		workFindText.setToolTipText("在此处输入要查找的内容，清空此框以显示全部数据");
		workFindText.getDocument().addDocumentListener(new findChangeListener());
		workFind.add(workFindLabel);
		workFind.add(workFindText);
		workFind.setLayout(_1x5GridLayout);
		workFrameStatus = new JLabel(" 在表格的最后一行空白行双击以创建新工人。");
		workFrameStatus.setFont(new Font("微软雅黑", 0, 16));
		workBottomBar.setLayout(_2x1GridLayout);			// 设置查找器整体
		workBottomBar.add(workFind);
		workBottomBar.add(workFrameStatus);
		workContent.add(workTablePane, BorderLayout.CENTER);
		workContent.add(workBottomBar, BorderLayout.SOUTH);
		workFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 设置窗体
		workFrame.pack();
		workFrame.setSize(960, 540);
		workFrame.setVisible(true);
	}
	/** 处理 radioButton 改变事件 */
	class WorkRadioChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			workFindRadio1.setSelected(false);
			workFindRadio2.setSelected(false);
			workFindRadio3.setSelected(false);
			switch (e.getActionCommand()) {
			case "按 ID": { workFindRadio1.setSelected(true); break; }
			case "按姓名": { workFindRadio2.setSelected(true); break; }
			case "按姓名（模糊）": { workFindRadio3.setSelected(true); break; }
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
				Object rowData1 = workTable.getValueAt(tcl.getRow(), 0);
				Object rowData2 = workTable.getValueAt(tcl.getRow(), 1);
				Object rowData3 = workTable.getValueAt(tcl.getRow(), 2);
				Object rowData4 = workTable.getValueAt(tcl.getRow(), 3);
				Object rowData5 = workTable.getValueAt(tcl.getRow(), 4);
				if ( !rowData1.equals("") &&				// 五列都有数据
					 !rowData2.equals("") &&
					 !rowData3.equals("") &&
					 !rowData4.equals("") &&
					 !rowData5.equals("") ) {
					if (tcl.getRow() == maxLine) {				// 若是在新一行编辑
						if ( isInteger(rowData3.toString()) &&			// 而且数据合法
							 isNumeric(rowData4.toString())) {
							if ( !ctrl.hasWorker(rowData1.toString()) ) {	// 而且 ID 不重复
								// DefaultTableModel tableModel = (DefaultTableModel)workTable.getModel();
								workTableModel.addRow(new Object[]{"", "", "", "", ""});
								maxLine++;
								if ( !ctrl.handleAddWorkerInfo( rowData1.toString(), rowData2.toString(), Integer.parseInt(rowData3.toString()), Double.parseDouble(rowData4.toString()), rowData5.toString() ) ) throw new DataWritingException();	// 先处理 GUI 再操作数据是为了防止数据库操作错误
								setWorkerStatusBar("工人信息添加成功。当前工人数：" + (maxLine) + "。");
							} else {
								setWorkerStatusBar("新工人 ID 与已有重复，请修改。");
							}
						} else {
							setWorkerStatusBar("新工人数据不合法，“年龄”只能是整数，“工资”只能是数字。");
						}
					} else {									// 若是编辑已有行
						if ( isInteger(rowData3.toString()) &&			// 而且数据合法
							 isNumeric(rowData4.toString())) {
							if (tcl.getColumn() == 0) {						// 如果它在改 ID
								if ( !ctrl.hasWorker(rowData1.toString()) ) {	// 而且新 ID 不是是已有的
									if ( !ctrl.handleUpdateWorkerInfo( tcl.getOldValue().toString(), rowData1.toString(), rowData2.toString(), Integer.parseInt(rowData3.toString()), Double.parseDouble(rowData4.toString()), rowData5.toString() ) ) throw new DataWritingException();
									setWorkerStatusBar("已将 ID 为 " + tcl.getOldValue().toString() + " 的工人的 ID 更改为 " + rowData1.toString() + "。");
								} else {
									setWorkerStatusBar("新工人 ID 与已有重复，请修改。");
								}
							} else {										// 如果它在改 ID 以外的信息
								if ( !ctrl.handleUpdateWorkerInfo( rowData1.toString(), rowData1.toString(), rowData2.toString(), Integer.parseInt(rowData3.toString()), Double.parseDouble(rowData4.toString()), rowData5.toString() ) ) throw new DataWritingException();;
								setWorkerStatusBar("已更新 ID 为 " + rowData1.toString() + " 的工人的信息。");
							}
						} else {
							setWorkerStatusBar("工人数据不合法，“年龄”只能是整数，“工资”只能是数字。");
						}
					}
					return;
				}
				setWorkerStatusBar();
			} catch (DataWritingException err) {
				// 刷新列表
				workTableModel = new DefaultTableModel(workTableHeaders, 0);
				workTable.setModel(workTableModel);
				ctrl.handleGetAllWorkerInfo();
				workTableModel.addRow(new Object[]{"", "", "", "", ""});
				setWorkerStatusBar("工人数据写入失败。请检查 MySQL 服务是否正常或信息中是否包含无法识别的字符（如 emoji）。");
			}
		}
	};
	private class DataWritingException extends Exception {
		private static final long serialVersionUID = 1L;
		public DataWritingException() {
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
							workTable.getCellEditor().stopCellEditing();
							if (workTable.getSelectedRow() != maxLine) {	// 不是最后一行
								if (workTable.getSelectedRow() != -1) {		// 排除未选中的情况
									// System.out.println("remove line");
									String toRemove = workTable.getValueAt(workTable.getSelectedRow(), 0).toString();
									workTableModel.removeRow(workTable.getSelectedRow());
									maxLine--;
									if (!ctrl.handleDeleteWorkerInfo(toRemove)) throw new DataWritingException();	// 先处理 GUI 再操作数据是为了防止文件操作错误
									setWorkerStatusBar("已移除 ID: " + toRemove + " 的工人的信息。当前工人数：" + (maxLine) + "。");
								}
							} else {
								setWorkerStatusBar("不能对新增行进行删除操作。", true);
							}
						} catch (DataWritingException err) {
							// 刷新列表
							workTableModel = new DefaultTableModel(workTableHeaders, 0);
							workTable.setModel(workTableModel);
							ctrl.handleGetAllWorkerInfo();
							workTableModel.addRow(new Object[]{"", "", "", "", ""});
							setWorkerStatusBar("工人数据写入失败。请检查 MySQL 服务是否正常。");
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
			String findTextContent = workFindText.getText();
			workTableModel = new DefaultTableModel(workTableHeaders, 0);
			workTable.setModel(workTableModel);
			if (!findTextContent.equals("")) {				// 有内容，开始查找
				if (workFindRadio1.isSelected()) {			// 按 ID
					ctrl.handleGetFilteredWorkerInfo(0, findTextContent);
				} else if (workFindRadio2.isSelected()) {
					ctrl.handleGetFilteredWorkerInfo(1, findTextContent);
				} else if (workFindRadio3.isSelected()) {
					ctrl.handleGetFilteredWorkerInfo(2, findTextContent);
				}
			} else {
				ctrl.handleGetAllWorkerInfo();
				workTableModel.addRow(new Object[]{"", "", "", "", ""});
				setWorkerStatusBar();
			}
		}
	}
	/** 设置状态栏文字 */
	public void setWorkerStatusBar(String text, boolean appendBefore) {
		workFrameStatus.setText(" " + text + "在表格的最后一行空白行双击以创建新工人。在一行删按 Del 键以删除工人信息。");
	}
	public void setWorkerStatusBar(String text) {
		workFrameStatus.setText(" " + text);
	}
	public void setWorkerStatusBar() {
		workFrameStatus.setText(" 在表格的最后一行空白行双击以创建新工人。在一行删按 Del 键以删除工人信息。");
	}
	
	/** 注册 Controller */
	public void addWorkerInfoListener(Controller controller) {
		ctrl = controller;
	}
	
	/** 由 Controller 调用一次性列出工人列表的响应 */
	public void handleWorkerList(String ID, String Name, int age, double salary, String job) {
		DefaultTableModel tableModel = (DefaultTableModel)workTable.getModel();
		tableModel.addRow(new Object[]{ID, Name, age, salary, job});
		if (workTable.getRowCount() == 1) {					// 表格居中，由于 JTable 优化不佳，这里需要多做一步操作
			for(int i = 0; i< workTable.getColumnCount(); i++) {
				workTable.getColumn(workTable.getColumnName(i)).setCellRenderer(workTableRenderer);
			}
		}
	}
	/** 由 Controller 调用的非用户操作添加工人（如从文件读取）的响应 */
	public void handleAddWorker(String ID, String Name, int age, double salary, String job) {
		workTableModel.removeRow(maxLine);					// 先把空白行删掉
		handleWorkerList(ID, Name, age, salary, job);
		maxLine++;
		workTableModel.addRow(new Object[]{"", "", "", "", ""});	// 再把空白行补回来
		if (workTable.getRowCount() == 1) {					// 表格居中，由于 JTable 优化不佳，这里需要多做一步操作
			for(int i = 0; i< workTable.getColumnCount(); i++) {
				workTable.getColumn(workTable.getColumnName(i)).setCellRenderer(workTableRenderer);
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
