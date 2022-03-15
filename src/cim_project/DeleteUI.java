package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteUI implements ActionListener {
	MainUI main;

	JPanel label_panel,tf_panel, btn_panel, search_panel;
	JButton btn_delete, btn_search;
	ArrayList<JTextField> tf_list;
	String[] namelist = {"품번","제품명","브랜드","구분","가격","수량"};
	JTextField tf_search;
	JComboBox<String> menulist;
	
	public DeleteUI() {}
	public DeleteUI(MainUI main) {
		this.main = main;
		init();
	}
	
	public void init() {
		main.jf.setTitle("화장품 재고관리 프로그램 - 삭제");
		main.switch_panel(MainUI.DELETE);
		
		
		main.delete_panel.setLayout(new BorderLayout());
		tf_list = new ArrayList<JTextField>();
		btn_panel = new JPanel();
		tf_panel = new JPanel(new GridLayout(namelist.length,1));
		label_panel = new JPanel(new GridLayout(namelist.length,1));
		btn_delete = new JButton("삭제하기");
		btn_delete.setFont(Commons.getFont());
		btn_panel.add(btn_delete);
		btn_delete.setBackground(Color.white);
		
		
		search_panel = new JPanel();
		btn_search = new JButton("검 색");
		btn_search.setFont(Commons.getFont());
		tf_search = new JTextField(20);
		btn_search.setBackground(Color.white);
		
		JLabel search_label = new JLabel("삭제할 제품번호");
		search_label.setFont(Commons.getFont());
		search_panel.add(search_label);
		search_panel.add(tf_search);
		search_panel.add(btn_search);
		
		for(String name : namelist) {
			JPanel l_panel = new JPanel();
			JPanel t_panel = new JPanel();
			JLabel label = new JLabel("                     "+name);
			label.setFont(Commons.getFont());
			l_panel.add(label);
			JTextField tf = new JTextField(12);
			t_panel.add(tf);
			
			tf_list.add(tf);
			label_panel.add(l_panel);
			tf_panel.add(t_panel);
			l_panel.setBackground(Color.white);
			t_panel.setBackground(Color.white);
		}
		for(int i=0; i<tf_list.size();i++) {
		tf_list.get(i).setEditable(false);
		}
		
		main.delete_panel.add(search_panel, BorderLayout.NORTH);
		main.delete_panel.add(label_panel, BorderLayout.WEST);
		main.delete_panel.add(new JLabel("                                   "), BorderLayout.EAST);

		main.delete_panel.add(tf_panel, BorderLayout.CENTER);
		main.delete_panel.add(btn_panel, BorderLayout.SOUTH);
		search_panel.setBackground(Color.white);
		btn_panel.setBackground(Color.white);
		tf_panel.setBackground(Color.white);
		label_panel.setBackground(Color.white);
		main.delete_panel.setBackground(Color.white);
		
		main.jf.add(main.delete_panel, BorderLayout.CENTER);
		main.jf.repaint();
		main.jf.setVisible(true);
		
		btn_delete.addActionListener(this);
		btn_search.addActionListener(this);
		tf_search.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==btn_delete) {
			if(tf_list.get(0).getText().equals("")){
				JOptionPane.showMessageDialog(null, "품번을 입력해주세요");
			}else {
			int in = JOptionPane.showConfirmDialog(null,"정말로 삭제 하시겠습니까?","",0);
				if(in==0) {
					delete_proc();
				}
			}
		}else if(obj == btn_search || obj==tf_search) {
			if(validationCheck()) {
			search_proc();
			tf_search.setText("");
			}
		}
		
	}
	public boolean validationCheck(){
		boolean result = false;
			if(tf_search.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "품번을 입력해주세요");
				tf_search.requestFocus();
			}else {
				result=true;
		}
		return result;
	}
	
	public void search_proc() {
		CimDao dao = new CimDao();
		CimVO vo = new CimVO();
		String tfs = tf_search.getText();
		vo = dao.getList(tfs);
		if(vo.getCno() != null) {
			tf_list.get(0).setText(vo.getCno());
			tf_list.get(1).setText(vo.getCname());
			tf_list.get(2).setText(vo.getCbrand());
			tf_list.get(3).setText(vo.getCkinds());
			tf_list.get(4).setText(vo.getCprice());
			tf_list.get(5).setText(String.valueOf(vo.getCquantity()));
		}else {
			JOptionPane.showMessageDialog(null, "존재하지 않는 번호 입니다.");
		}
		
	}
	
	public void delete_proc() {
		CimDao dao = new CimDao();
		CimVO vo = new CimVO();
		vo.setCno(tf_list.get(0).getText());
		int result = dao.getDelete(vo);
		if(result==1) {
			JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
			for(int i=0; i<tf_list.size();i++) {
				tf_list.get(i).setText("");
			}
		}
		
	}
}














