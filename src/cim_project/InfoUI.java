package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class InfoUI implements ActionListener {
	MainUI main;
	

	JPanel label_panel,tf_panel, btn_panel;
	JButton btn_update, btn_open, btn_check;
	ArrayList<JTextField> tf_list;
	String[] namelist = {"아이디","비밀번호","이름","휴대폰"};
	JPasswordField tf_pass_check;
	JComboBox<String> info_box;
	
	public InfoUI() {}
	public InfoUI(MainUI main) {
		this.main = main;
		pass_check();
	}

	public void pass_check() {
		main.jf.setTitle("화장품 재고관리 프로그램 - 내 정보");
		main.switch_panel(MainUI.INFO);
		
		main.info_panel.setLayout(new BorderLayout(0,70));

		JPanel ppanel = new JPanel();
		JLabel plabel = new JLabel("비밀번호 확인");
		plabel.setFont(Commons.getFont());
		tf_pass_check=new JPasswordField(10);
		btn_check = new JButton("Enter");
		btn_check.setFont(Commons.getFont());
		btn_check.setBackground(Color.white);
		
		ppanel.add(plabel);
		ppanel.add(tf_pass_check);
		ppanel.add(btn_check);
		
		main.info_panel.add(new JLabel("                 "),BorderLayout.NORTH);
		main.info_panel.add(ppanel,BorderLayout.CENTER);
		main.info_panel.setBackground(Color.white);
		ppanel.setBackground(Color.white);

		main.jf.add(main.info_panel,BorderLayout.CENTER);
		main.jf.repaint();
		main.jf.setVisible(true);
		btn_check.addActionListener(this);
		tf_pass_check.addActionListener(this);
		
		
	} 
	
	public void init() {
		main.jf.setTitle("화장품 재고관리 프로그램 - 내 정보");
		main.switch_panel(MainUI.INFO);
		
		main.info_panel.setLayout(new BorderLayout());
		tf_list = new ArrayList<JTextField>();
		btn_panel = new JPanel();
		tf_panel = new JPanel(new GridLayout(namelist.length,1));
		label_panel = new JPanel(new GridLayout(namelist.length,1));
		btn_open = new JButton("수정하기");
		btn_update = new JButton("저장하기");
		btn_open.setFont(Commons.getFont());
		btn_update.setFont(Commons.getFont());
		btn_open.setBackground(Color.white);
		btn_update.setBackground(Color.white);
		btn_panel.add(btn_open);
		btn_panel.add(btn_update);
		
		for(int i =0;i<namelist.length;i++) {
			JPanel l_panel = new JPanel();
			JPanel t_panel = new JPanel();
			JLabel label = new JLabel("                     "+namelist[i]);
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
		
		createInfo();
		
		
		main.info_panel.add(label_panel, BorderLayout.WEST);
		main.info_panel.add(new JLabel("                                   "), BorderLayout.EAST);
		main.info_panel.add(new JLabel("   "), BorderLayout.NORTH);
		main.info_panel.add(tf_panel, BorderLayout.CENTER);
		main.info_panel.add(btn_panel, BorderLayout.SOUTH);
		label_panel.setBackground(Color.white);
		tf_panel.setBackground(Color.white);
		btn_panel.setBackground(Color.white);
		
		
		main.jf.add(main.info_panel,BorderLayout.CENTER);
		main.jf.repaint();
		main.jf.setVisible(true);
		btn_update.addActionListener(this);
		btn_open.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==btn_update) {
			int in = JOptionPane.showConfirmDialog(null,"내 정보를 수정 하시겠습니까?","",0);
				if(in==0) {
					update_proc();
				}
		}else if(obj==btn_open) {
			tf_list.get(1).setEditable(true);
			tf_list.get(2).setEditable(true);
			tf_list.get(3).setEditable(true);
		}else if(obj ==tf_pass_check || obj == btn_check) {
			pass_proc();
		}
	
	}
	
	public void createInfo() {
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		tf_list.get(0).setText(main.id);
		tf_list.get(0).setEditable(false);
		vo = dao.getInfo(main.id);
		tf_list.get(1).setText(vo.getPass());
		tf_list.get(1).setEditable(false);
		tf_list.get(2).setText(vo.getName());
		tf_list.get(2).setEditable(false);
		tf_list.get(3).setText(vo.getPnum());
		tf_list.get(3).setEditable(false);
		
	}
	
	public void update_proc() {
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		
		vo.setId(tf_list.get(0).getText());
		vo.setPass(tf_list.get(1).getText());
		vo.setName(tf_list.get(2).getText());
		vo.setPnum(tf_list.get(3).getText());
//		vo.setRank(info_box.getSelectedItem().toString());
		int result = dao.getUpdate(vo);
		if(result ==1) {
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
			tf_list.get(0).setEditable(false);
			tf_list.get(1).setEditable(false);
			tf_list.get(2).setEditable(false);
			tf_list.get(3).setEditable(false);
//			info_box.setEditable(false);
		}else {
			JOptionPane.showMessageDialog(null, "정보수정에 실패하였습니다.");

		}
	}
	
	public void pass_proc() {
		MemberDao dao = new MemberDao();
		
		int check =dao.getLogin(main.id,tf_pass_check.getText());
		if(check==1) {
			 JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.");

			init();
		}else {
			 JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.","비밀번호 오류",JOptionPane.ERROR_MESSAGE);
			 tf_pass_check.setText("");
			 tf_pass_check.requestFocus();
		}
	}
	
	
}


















