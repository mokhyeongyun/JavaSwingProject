package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MemberUI extends MouseAdapter {
	MainUI main;
	JScrollPane pane;
	JPanel center_panel,list_panel;
	
	
	String[] colNames = {"아이디", "비밀번호", "이름" ,"핸드폰번호","직급"};
	DefaultTableModel model = new DefaultTableModel(colNames,0) {
		@Override
		public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	JTable table = new JTable(model);
	JTableHeader header = table.getTableHeader();
	
	Object[] row = new Object[5];
	Object[] click_select;

	
	public MemberUI() {}
	public MemberUI(MainUI main) {
		this.main=main;
		init();
	}
	
	
	public void init() {
		main.jf.setTitle("화장품 재고관리 프로그램 - 회원관리");
		main.switch_panel(MainUI.MEMBER);
		list_panel = new JPanel();
		createJTableData();
		
		main.member_panel.setLayout(new BorderLayout());
		
		pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(450,200));
		pane.getViewport().setBackground(main.cl);
		header.setBackground(main.cl);
		header.setFont(new Font("굴림체", Font.BOLD,12));
		list_panel.add(pane);
		
		
		main.member_panel.add(list_panel, BorderLayout.NORTH);
		main.member_panel.setBackground(Color.white);
		list_panel.setBackground(Color.white);
		
		main.jf.add(main.member_panel, BorderLayout.CENTER);
		main.jf.repaint();
		main.jf.setVisible(true);
		
		table.addMouseListener(this);
	}
	
	public void createJTableData() {
		MemberDao dao = new MemberDao();
		ArrayList<MemberVO> list = dao.getList();
		table.getColumn("아이디").setPreferredWidth(200);
		table.getColumn("비밀번호").setPreferredWidth(150);
		table.getColumn("이름").setPreferredWidth(120);
		table.getColumn("핸드폰번호").setPreferredWidth(200);
		table.getColumn("직급").setPreferredWidth(100);
		
		for(MemberVO vo : list) {
			row[0] = vo.getId();
			row[1] = vo.getPass();
			row[2] = vo.getName();
			row[3] = vo.getPnum();
			row[4] = vo.getRank();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
	}
	
	public void changeJTableData() {
		model.setRowCount(0);
		
		MemberDao dao = new MemberDao();
		ArrayList<MemberVO> list = dao.getList();
		for(MemberVO vo : list) {
			row[0] = vo.getId();
			row[1] = vo.getPass();
			row[2] = vo.getName();
			row[3] = vo.getPnum();
			row[4] = vo.getRank();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		click_select = new Object[table.getColumnCount()];
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		Object obj = e.getSource();
		if(e.getClickCount()>1) {
		if(obj == table) {
			for(int i=0;i<table.getColumnCount();i++) {
				Object ob = table.getModel().getValueAt(row, i);
				click_select[i]=ob;
			}
			new MemberUpdateUI(click_select,this);
		}
		}//if
		
	}
	
	
	
}
















