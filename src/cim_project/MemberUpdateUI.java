package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberUpdateUI implements ActionListener {
	JFrame jf;
	Object[] click_select;
	JPanel  center_panel,label_panel,tf_panel, btn_panel;
	JButton btn_update, btn_exit;
	JComboBox<String> box_rank;
	ArrayList<JTextField> tf_list;
	MemberUI memberui;

	
	public MemberUpdateUI(){}
	public MemberUpdateUI(Object[] click_select,MemberUI memberui) {
		this.click_select = click_select;
		this.memberui=memberui;
		init();
	}
	
	public void init() {
		jf = new JFrame();
		jf.setLayout(new BorderLayout());
		label_panel = new JPanel(new GridLayout(5,1));
		tf_panel = new JPanel(new GridLayout(5,1));
		tf_list = new ArrayList<JTextField>();

		//버튼
		btn_panel = new JPanel();
		btn_update = new JButton("수정완료");
		btn_exit = new JButton("나가기");
		btn_update.setFont(Commons.getFont());
		btn_exit.setFont(Commons.getFont());
		btn_update.setBackground(Color.white);
		btn_exit.setBackground(Color.white);
		btn_panel.add(btn_update);
		btn_panel.add(btn_exit);
		
		String[] ranklist = {"점장","직원","알바"};
		box_rank = new JComboBox<String>(ranklist);
		
		String[] namelist = {"아이디","비밀번호","이름","핸드폰번호","직급"};
		for(String name : namelist) {
			JPanel l_panel = new JPanel();
			JPanel t_panel = new JPanel();
			l_panel.add(new JLabel(name));
			JTextField tf = new JTextField(10);
			t_panel.add(tf);
			label_panel.add(l_panel);
			if(name.equals("직급")) {
				tf_panel.add(box_rank);
			}else {
			tf_panel.add(tf);
			}
			tf_list.add(tf);
			l_panel.setBackground(Color.white);
			t_panel.setBackground(Color.white);
		}
		tf_list.get(0).setEditable(false);
		center_panel = new JPanel();
		center_panel.add(label_panel);
		center_panel.add(tf_panel);
		select_proc();
		
		jf.add(center_panel,BorderLayout.CENTER);
		jf.add(btn_panel, BorderLayout.SOUTH);
		jf.setSize(260,240);
		jf.setVisible(true);
		jf.getContentPane().setBackground(Color.white);
		center_panel.setBackground(Color.white);
		btn_panel.setBackground(Color.white);
		label_panel.setBackground(Color.white);
		tf_panel.setBackground(Color.white);
		
		btn_update.addActionListener(this);
		btn_exit.addActionListener(this);
		
	}
	
	public void select_proc() {
		
		for(int i=0;i<click_select.length;i++) {
			if(i==click_select.length-1) {
				box_rank.setSelectedItem(click_select[i]);
			}else {
				tf_list.get(i).setText((String) click_select[i]);
			}
		
		}
	}
	
	
	public void update_proc() {
		MemberVO vo = new MemberVO();
		MemberDao dao = new MemberDao();
		vo.setId(tf_list.get(0).getText());
		vo.setPass(tf_list.get(1).getText());
		vo.setName(tf_list.get(2).getText());
		vo.setPnum(tf_list.get(3).getText());
		vo.setRank(box_rank.getSelectedItem().toString());
		
		int result = dao.getUpdate2(vo);
		if(result == 1) {
			JOptionPane.showMessageDialog(null, "회원 정보를 수정하였습니다.");
			jf.dispose();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj == btn_update) {
			update_proc();
			memberui.changeJTableData();
			
		}else if(obj == btn_exit) {
			jf.dispose();

		}
		
	}

}







