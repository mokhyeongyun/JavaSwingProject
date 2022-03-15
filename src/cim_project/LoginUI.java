package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI extends JFrame implements WindowListener, ActionListener{
	JPanel label_panel, tf_panel, btn_panel, e_panel;
	JButton btn_login, btn_join;
	JTextField tf_id;
	JPasswordField tf_pass;
	Color cl = new Color(183,208,212);
	
	
	public LoginUI() {
		init();
	}

	public void init() {
		setTitle("화장품 재고관리 프로그램 - 로그인");
		String[] namelist = {"ID","PASSWORD"};
		label_panel = new JPanel(new GridLayout(3,1));
		tf_panel = new JPanel(new GridLayout(3,1));
		btn_panel = new JPanel();
		e_panel = new JPanel();
		btn_login = new JButton("로그인");
		btn_join = new JButton("회원가입");
		btn_login.setFont(Commons.getFont());
		btn_join.setFont(Commons.getFont());
		btn_login.setBackground(Color.white);
		btn_join.setBackground(Color.white);
		JPanel login_panel = new JPanel();
		JPanel join_panel = new JPanel();
		login_panel.add(btn_login);
		join_panel.add(btn_join);
		btn_panel.add(login_panel);
		btn_panel.add(join_panel);
		
		
		for(String name : namelist) {
			JPanel l_panel = new JPanel();
			JPanel t_panel = new JPanel();
			
			if(name.equals("ID")) {
				label_panel.add(l_panel.add(new JLabel("                  "+name+"     ")));
				tf_id = new JTextField(10);
				tf_panel.add(t_panel.add(tf_id));
			}else if(name.equals("PASSWORD")) {
				label_panel.add(l_panel.add(new JLabel("                  "+name+"     ")));
				tf_pass = new JPasswordField(10);
				tf_panel.add(t_panel.add(tf_pass));
			}
		}
		label_panel.setFont(Commons.getFont());
		
		ImageIcon img = new ImageIcon("image/login2.PNG");
		JLabel login_img = new JLabel(img);
		JLabel east_label =new JLabel("                ");
		
		login_panel.setBackground(Color.white);
		join_panel.setBackground(Color.white);
		btn_panel.setBackground(Color.white);
		label_panel.setBackground(Color.white);
		tf_panel.setBackground(Color.white);
		getContentPane().setBackground(Color.white);
		
		
		
		add(login_img, BorderLayout.NORTH);
		add(btn_panel, BorderLayout.SOUTH);
		add(label_panel, BorderLayout.WEST);
		add(tf_panel, BorderLayout.CENTER);
		add(east_label, BorderLayout.EAST);
		setSize(350,270);
		setVisible(true);
		
		
		addWindowListener(this);
		btn_login.addActionListener(this);
		tf_pass.addActionListener(this);
		btn_join.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = new Object();
		obj=e.getSource();
		if(obj==btn_login || obj==tf_pass) {
			login_proc();
		}else if(obj==btn_join) {
			new JoinUI();
		}
		
	}
// --- 로그인 proc 시작 11-10
	public void login_proc() {
		String id = tf_id.getText();
		String pass = tf_pass.getText();
		
		if(validationCheck()) {
			MemberDao dao = new MemberDao();
			MemberVO vo = new MemberVO();
			
			vo.setId(id);
			vo.setPass(pass);
			int result = dao.getLogin(vo);
			if(result==0) {
				JOptionPane.showMessageDialog(null, "등록되지 않은 아이디 이거나\n잘못된 비밀번호 입니다.");
				tf_id.setText("");
				tf_pass.setText("");
				tf_id.requestFocus();
			}else {
				JOptionPane.showMessageDialog(null, "로그인 성공");
				setVisible(false);
				new MainUI(id);
			}
			
		}
		
	}
	
	
	
	public boolean validationCheck() {
		boolean result= false;
		if(tf_id.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력 해 주세요");
			tf_id.requestFocus();
		}else if(tf_pass.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력 해 주세요");
			tf_pass.requestFocus();
		}else {
			result = true;
		}
		return result;
	}
	
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
