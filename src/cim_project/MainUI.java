package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class MainUI implements ActionListener, WindowListener{
	JFrame jf;
	JPanel west_panel, center_panel, list_panel, search_panel, logout_panel
			, insert_panel, update_panel, delete_panel,info_panel,title_panel, member_panel;
	JButton insert_button,update_button,delete_button,info_button,exit_button
			, logout_button, search_button, back_button ,all_button, member_button;
	JScrollPane pane;
	JTextField tf_search;
	JComboBox<String> menulist;
	JLabel title_img, title_label;
	String id;
	Color cl = new Color(232,232,244);
	Color cltable = new Color(90,156,161);
	Color cllogout = new Color(255,227,227);
	Color clback = new Color(227,241,255);
	
	
	String[] colNames = {"ǰ��", "��ǰ��", "�귣��" ,"����", "����", "����", "������¥"};
	DefaultTableModel model = new DefaultTableModel(colNames,0) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	JTable table = new JTable(model);
	JTableHeader header = table.getTableHeader();
	
	Object[] row = new Object[7];
	
	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;
	public static final int INFO = 4;
	public static final int BACK = 5;
	public static final int MEMBER =6;
	
	public MainUI() {
		init();
	}
	
	public MainUI(String id) {
		this.id = id;
		init();
	}

	
	public void init() {
		jf = new JFrame();
		jf.setTitle("ȭ��ǰ ������ ���α׷�");
		
		jf.setFont(Commons.getFont());
		center_panel = new JPanel(new BorderLayout());
		west_panel = new JPanel(new GridLayout(6,1,0,3));
		list_panel = new JPanel();
		search_panel = new JPanel();
		insert_panel = new JPanel();
		update_panel = new JPanel();
		delete_panel = new JPanel();
		info_panel = new JPanel();
		member_panel = new JPanel(); ////////////
		title_panel = new JPanel();
		
		all_button = new JButton("��ü ����Ʈ ����");
		insert_button = new JButton("��ǰ ���");
		update_button = new JButton("��ǰ ����");
		delete_button = new JButton("��ǰ ����");
		info_button = new JButton("�� ����");
		member_button = new JButton("ȸ������");      //////////
		exit_button = new JButton("�� ��");
		
//		all_button.setBorderPainted(false);
		insert_button.setBorderPainted(false);
		update_button.setBorderPainted(false);
		delete_button.setBorderPainted(false);
		info_button.setBorderPainted(false);
		member_button.setBorderPainted(false);
		exit_button.setBorderPainted(false);
		
		all_button.setFont(Commons.getFont());
		insert_button.setFont(Commons.getFont());
		update_button.setFont(Commons.getFont());
		delete_button.setFont(Commons.getFont());
		info_button.setFont(Commons.getFont());
		exit_button.setFont(Commons.getFont());
		member_button.setFont(Commons.getFont());
		
		all_button.setBackground(Color.white);
		insert_button.setBackground(Color.white);
		update_button.setBackground(Color.white);
		delete_button.setBackground(Color.white);
		info_button.setBackground(Color.white);
		exit_button.setBackground(Color.white);
		member_button.setBackground(Color.white);
		
		
		west_panel.add(insert_button);
		west_panel.add(update_button);
		west_panel.add(delete_button);
		west_panel.add(info_button);
		west_panel.add(member_button);                 ///////////////
		west_panel.add(exit_button);
		
		createJTableData();

		tf_search = new JTextField(15);
		search_button = new JButton("�˻�");
		search_button.setFont(Commons.getFont());
		search_button.setBackground(Color.white);
		String[] mlist = {"ǰ��","��ǰ��","�귣��","����"};
		menulist = new JComboBox<String>(mlist);
		
		search_panel.add(menulist);
		search_panel.add(tf_search);
		search_panel.add(search_button);
		search_panel.add(all_button);
		
		pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(450,200));
		pane.getViewport().setBackground(cl);
		header.setBackground(cl);
		header.setFont(new Font("����ü", Font.BOLD,12));
		
		list_panel.add(pane);
		center_panel.add(list_panel, BorderLayout.NORTH);
		center_panel.add(search_panel, BorderLayout.SOUTH);
		list_panel.setBackground(Color.white);
		search_panel.setBackground(Color.white);
		
		logout_button = new JButton("�α׾ƿ�");
		back_button = new JButton("��ü ��� ����Ʈ ����");
		logout_button.setFont(Commons.getFont());
		back_button.setFont(Commons.getFont());
		
		logout_button.setBackground(cllogout);
		back_button.setBackground(clback);
		
		logout_button.setBorderPainted(false);
		back_button.setBorderPainted(false);
		
		logout_panel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		logout_panel.add(back_button);
		logout_panel.add(new JLabel("                                  "));
		logout_panel.add(logout_button);
		
		ImageIcon img = new ImageIcon("image/select2.PNG");
		title_img = new JLabel(img);
		title_label = new JLabel("**["+id+"] �� ȯ���մϴ�**");
		title_label.setForeground(Color.blue);
		title_label.setFont(new Font("�������", Font.BOLD, 15));
		title_panel.add(title_img);
		title_panel.add(new JLabel("           "));
		title_panel.add(title_label);        ///////////////////
		
		jf.add(title_panel, BorderLayout.NORTH);
		jf.add(west_panel,BorderLayout.WEST);
		jf.add(center_panel,BorderLayout.CENTER);
		jf.add(logout_panel,BorderLayout.SOUTH);
		title_panel.setBackground(Color.white);
		west_panel.setBackground(Color.white);
		center_panel.setBackground(Color.white);
		logout_panel.setBackground(Color.white);
		jf.getContentPane().setBackground(Color.white);
		
		
		
		jf.setSize(600,430);
		jf.setVisible(true);
		
		all_button.addActionListener(this);
		insert_button.addActionListener(this);
		update_button.addActionListener(this);
		delete_button.addActionListener(this);
		info_button.addActionListener(this);
		exit_button.addActionListener(this);
		logout_button.addActionListener(this);
		search_button.addActionListener(this);
		tf_search.addActionListener(this);
		back_button.addActionListener(this);
		member_button.addActionListener(this);          /////
		jf.addWindowListener(this);
		
	}
	
	public void createJTableData() {
		CimDao dao = new CimDao();
		ArrayList<CimVO> list = dao.getList();
		table.getColumn("��ǰ��").setPreferredWidth(380);
		table.getColumn("�귣��").setPreferredWidth(150);
		table.getColumn("����").setPreferredWidth(120);
		table.getColumn("ǰ��").setPreferredWidth(100);
		table.getColumn("����").setPreferredWidth(100);
		table.getColumn("������¥").setPreferredWidth(150);
		table.getColumn("����").setPreferredWidth(50);
		for(CimVO vo : list) {
			row[0] = vo.getCno();
			row[1] = vo.getCname();
			row[2] = vo.getCbrand();
			row[3] = vo.getCkinds();
			row[4] = vo.getCprice();
			row[5] = vo.getCquantity();
			row[6] = vo.getCdate();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
		table.setModel(model);
	}

	public void search() {
		CimDao dao=new CimDao();
		ArrayList<CimVO> search_list = new ArrayList<CimVO>();
		
		String item = menulist.getSelectedItem().toString();
		
			if(validationCheck(item)) {
				search_list = dao.getSearch(item,tf_search.getText());
				model.setRowCount(0);
				for(CimVO vo : search_list) {
					row[0] = vo.getCno();
					row[1] = vo.getCname();
					row[2] = vo.getCbrand();
					row[3] = vo.getCkinds();
					row[4] = vo.getCprice();
					row[5] = vo.getCquantity();
					row[6] = vo.getCdate();
					
					model.addRow(row);
				}
				
				model.fireTableDataChanged();
				tf_search.setText("");
			
		}
	}
	
	
	public boolean validationCheck(String item) {
		boolean result = false;

		if (tf_search.getText().equals("")) {			
			JOptionPane.showMessageDialog(null, item+"��(��) �Է����ּ���");
			tf_search.requestFocus();
		} else {
			result = true;
		}

		return result;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==insert_button) {
			new InsertUI(this);
		}else if(obj==update_button) {
			new UpdateUI(this);
		}else if(obj==delete_button) {
			new DeleteUI(this);
		}else if(obj==info_button) {
			new InfoUI(this);
		}else if(obj==member_button) {  ///////////////////
			if(id.equals("������")) {
			new MemberUI(this);
			}else {
				JOptionPane.showMessageDialog(null, "������ �����ϴ�.");///
			}
			
		}else if(obj==exit_button) {
			int exit = JOptionPane.showConfirmDialog(null, "������ ���� �Ͻðڽ��ϱ�?","",0);
			if(exit ==0) {
				System.exit(0);
			}
		}else if(obj==search_button || obj==tf_search) {
			search();
		}else if(obj == all_button) {
			CimDao dao=new CimDao();
			ArrayList<CimVO> search_list = new ArrayList<CimVO>();
			
			String item = menulist.getSelectedItem().toString();
			tf_search.setText("");
			search_list = dao.getSearch(item);
			
			model.setRowCount(0);
			for(CimVO vo : search_list) {
				row[0] = vo.getCno();
				row[1] = vo.getCname();
				row[2] = vo.getCbrand();
				row[3] = vo.getCkinds();
				row[4] = vo.getCprice();
				row[5] = vo.getCquantity();
				row[6] = vo.getCdate();
				
				model.addRow(row);
			}
			model.fireTableDataChanged();
		
		}else if(obj==logout_button) {
			int result = JOptionPane.showConfirmDialog(null, "���� �α׾ƿ� �Ͻðڽ��ϱ�?","",0);
			if(result==0) {
				jf.setVisible(false);
				new LoginUI();
			}
		}else if(obj == back_button) {
			switch_panel(MainUI.BACK);
			CimDao dao=new CimDao();
			ArrayList<CimVO> search_list = new ArrayList<CimVO>();
			
			String item = menulist.getSelectedItem().toString();
			tf_search.setText("");
			search_list = dao.getSearch(item);
			
			model.setRowCount(0);
			for(CimVO vo : search_list) {
				row[0] = vo.getCno();
				row[1] = vo.getCname();
				row[2] = vo.getCbrand();
				row[3] = vo.getCkinds();
				row[4] = vo.getCprice();
				row[5] = vo.getCquantity();
				row[6] = vo.getCdate();
				
				model.addRow(row);
			}
			model.fireTableDataChanged();
		}
		
	}
	
	public void switch_panel(int menu) {
		
		insert_panel.setVisible(false);
		update_panel.setVisible(false);
		delete_panel.setVisible(false);
		info_panel.setVisible(false);
		west_panel.setVisible(false);
		logout_panel.setVisible(false);
		center_panel.setVisible(false);
		member_panel.setVisible(false); 
		title_panel.removeAll();
//		title_panel.add(title_label);

		
		switch(menu) {
		case INSERT :  
			title_panel.add(new JLabel(new ImageIcon("image/insert2.PNG")));
//			title_panel.add(title_label);
			title_panel.add(new JLabel("           "));
			title_panel.add(title_label);
			insert_panel.removeAll();
			insert_panel.setVisible(true);
			west_panel.setVisible(true);
			logout_panel.setVisible(true);
			break;
		case UPDATE :
			title_panel.add(new JLabel(new ImageIcon("image/update2.PNG")));
//			title_panel.add(title_label);
			title_panel.add(new JLabel("           "));
			title_panel.add(title_label);
			update_panel.removeAll();
			update_panel.setVisible(true);
			west_panel.setVisible(true);
			logout_panel.setVisible(true);
			break;
		case DELETE :
			title_panel.add(new JLabel(new ImageIcon("image/delete2.PNG")));
//			title_panel.add(title_label);
			title_panel.add(new JLabel("           "));
			title_panel.add(title_label);
			delete_panel.removeAll();
			delete_panel.setVisible(true);
			west_panel.setVisible(true);
			logout_panel.setVisible(true);
			break;
		case INFO : 
			title_panel.add(new JLabel(new ImageIcon("image/info2.PNG")));
//			title_panel.add(title_label);
			title_panel.add(new JLabel("           "));
			title_panel.add(title_label);
			info_panel.removeAll();
			info_panel.setVisible(true);
			west_panel.setVisible(true);
			logout_panel.setVisible(true);
			break;
		case BACK :
			title_panel.add(new JLabel(new ImageIcon("image/select2.PNG")));
//			title_panel.add(title_label);
			title_panel.add(new JLabel("           "));
			title_panel.add(title_label);
			center_panel.setVisible(true);
			west_panel.setVisible(true);
			logout_panel.setVisible(true);
			break;
		case MEMBER :  ////////////////
			title_panel.add(new JLabel(new ImageIcon("image/member.png")));
//			title_panel.add(title_label);
			title_panel.add(new JLabel("           "));
			title_panel.add(title_label);
			member_panel.removeAll();
			member_panel.setVisible(true);
			west_panel.setVisible(true);
			logout_panel.setVisible(true);
			break;
		}
	}
	
	
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
		
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}


	
	
}

