package UIMS.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import UIMS.view.TableCellListener;

public class GUI {
	
	private int maxLine = 0;
	// 学生面板
	private JFrame stuFrame;
	private Container stuContent;
	private BorderLayout stuLayout = new BorderLayout();
	private String[] stuTableHeaders = {"ID", "姓名", "年龄", "成绩"};
	private JTable stuTable;
	private JScrollPane stuTablePane;
	private DefaultTableModel stuTableModel;
	private JLabel stuFindLabel;
	private JTextField stuFindText;
	private GridLayout _2x1GridLayout = new GridLayout(2, 1);	// 2 行 1 列
	private GridLayout _1x2GridLayout = new GridLayout(1, 2);
	private GridLayout _1x3GridLayout = new GridLayout(1, 3);
	private Container stuFindInput = new Container();
	private Container stuFindOption = new Container();
	private Container stuFind = new Container();
	private JRadioButton stuFindRadio1 = new JRadioButton("按 ID", true);
	private JRadioButton stuFindRadio2 = new JRadioButton("按姓名");
	private JRadioButton stuFindRadio3 = new JRadioButton("按姓名（模糊）");

	/** 构造方法 */
	public GUI() {
		buildDisplayStudent();
		buildDisplayWorker();
	}
	
	/** 创建学生面板 */
	void buildDisplayStudent() {
		stuFrame = new JFrame("学生信息管理系统");
		stuContent = stuFrame.getContentPane();
		stuContent.setLayout(stuLayout);
		// 设置 table 样式
		stuTableModel = new DefaultTableModel(stuTableHeaders, 0);
		stuTableModel.addRow(new Object[]{"双击并填写信息以增加学生"});
		stuTable = new JTable(stuTableModel);
		stuTablePane = new JScrollPane(stuTable);
		stuTable.setRowHeight(32);
		stuTable.getTableHeader().setFont(new Font("微软雅黑", 0, 18));
		stuTable.setFont(new Font("微软雅黑", 0, 14));
		DefaultTableCellRenderer stuTableRenderer = new DefaultTableCellRenderer();	// 设置表格对齐模式
		stuTableRenderer.setHorizontalAlignment(JLabel.CENTER);
		for(int i = 0; i< stuTable.getColumnCount(); i++) {
			stuTable.getColumn(stuTable.getColumnName(i)).setCellRenderer(stuTableRenderer);
		}
		/* 传统的表格变化监听器，已使用 TableCellListener 代替
		// TableChangeListener tableChangeListener = new TableChangeListener();
		// stuTable.getModel().addTableModelListener(tableChangeListener);
		*/
		TableCellListener tcl = new TableCellListener(stuTable, tableAction);
		// 设置查找器样式
		stuFindLabel = new JLabel(" 查找：");			// 设置查找器上层
		stuFindLabel.setFont(new Font("微软雅黑", 0, 18));
		stuFindText = new JTextField();
		stuFindText.setFont(new Font("微软雅黑", 0, 16));
		stuFindInput.add(stuFindLabel);
		stuFindInput.add(stuFindText);
		stuFindInput.setLayout(_1x2GridLayout);
		stuFindOption.add(stuFindRadio1);			// 设置查找器下层
		stuFindOption.add(stuFindRadio2);
		stuFindOption.add(stuFindRadio3);
		stuFindRadio1.setFont(new Font("微软雅黑", 0, 18));
		stuFindRadio2.setFont(new Font("微软雅黑", 0, 18));
		stuFindRadio3.setFont(new Font("微软雅黑", 0, 18));
		stuFindOption.setLayout(_1x3GridLayout);
		StuRadioChangeListener stuRadioChangeListener = new StuRadioChangeListener();
		stuFindRadio1.addActionListener(stuRadioChangeListener);
		stuFindRadio2.addActionListener(stuRadioChangeListener);
		stuFindRadio3.addActionListener(stuRadioChangeListener);
		stuFind.setLayout(_2x1GridLayout);			// 设置查找器整体
		stuFind.add(stuFindInput);
		stuFind.add(stuFindOption);
		stuContent.add(stuTablePane, BorderLayout.CENTER);
		stuContent.add(stuFind, BorderLayout.SOUTH);
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
			stuTable.setValueAt((Object)"233", 1, 1);
		}
	}
	/** 处理表格编辑事件 */
	Action tableAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;	// 不加这条会有警告
		public void actionPerformed(ActionEvent e)
		{
			TableCellListener tcl = (TableCellListener)e.getSource();
			System.out.printf("cell changed%n");
			System.out.println("Row   : " + tcl.getRow());
			System.out.println("Column: " + tcl.getColumn());
			System.out.println("Old   : " + tcl.getOldValue());
			System.out.println("New   : " + tcl.getNewValue());
			
			/**
			 * 
			 * Start from here!!
			 * 
			 */
		}
	};
	
	/** 显示单个客户面板 custPan */
	public void refreshCustPane(Customer cust){
		showCard("customer");
		if(cust==null || cust.getId()==-1){
			idTf.setText(null);
			nameTf.setText(null);
			addrTf.setText(null);
			ageTf.setText(null);
			return;
		}
		idTf.setText(new Long(cust.getId()).toString());
		nameTf.setText(cust.getName().trim());
		addrTf.setText(cust.getAddr().trim());
		ageTf.setText(new Integer(cust.getAge()).toString());
	}
	/** 显示所有客户面板 allCustPan */
	public void refreshAllCustPan(Set custs){
		showCard("allcustomers");
		String newData[][];
		newData=new String[custs.size()][4];
		Iterator it=custs.iterator();
		int i=0;
		while(it.hasNext()){
			Customer cust=it.next();
			newData[i][0]=new Long(cust.getId()).toString();
			newData[i][1]=cust.getName();
			newData[i][2]=cust.getAddr();
			newData[i][3]=new Integer(cust.getAge()).toString();
			i++;
		}
		tableModel.setDataVector(newData,tableHeaders);
	}

	
	void buildDisplayWorker() {

	}
}



/*
 *  This class listens for changes made to the data in the table via the
 *  TableCellEditor. When editing is started, the value of the cell is saved
 *  When editing is stopped the new value is saved. When the oold and new
 *  values are different, then the provided Action is invoked.
 *
 *  The source of the Action is a TableCellListener instance.
 */