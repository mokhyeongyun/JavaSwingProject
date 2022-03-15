package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinUI extends JFrame implements WindowListener, ActionListener{
	JPanel label_panel, tf_panel, btn_panel;
	JButton btn_join, btn_reset, btn_check,btn_back;
	JTextField tf_id,tf_name,tf_pnum,tf_rank;
	JPasswordField tf_pass, tf_passcheck;
	JComboBox<String> box_rank;
	
	JPanel p1 = new JPanel();
	
	public JoinUI() {
		init();
	}
	
	public void init() {
		setTitle("ȭ��ǰ ������ ���α׷� - ȸ������");
		String[] namelist = {"���̵�","��й�ȣ","��й�ȣ Ȯ��","�̸�","�޴���","����"};
		label_panel = new JPanel(new GridLayout(7,1));
		tf_panel = new JPanel(new GridLayout(7,1));
		btn_panel = new JPanel();
		
		btn_join = new JButton("�����ϱ�");
		btn_reset = new JButton("�ٽþ���");
		btn_check = new JButton("�ߺ�Ȯ��");
		btn_back = new JButton("���ư���");
		btn_join.setFont(Commons.getFont());
		btn_reset.setFont(Commons.getFont());
		btn_check.setFont(Commons.getFont());
		btn_back.setFont(Commons.getFont());
		btn_join.setBackground(Color.white);
		btn_reset.setBackground(Color.white);
		btn_check.setBackground(Color.white);
		btn_back.setBackground(Color.white);
		
		btn_join.addActionListener(this);
		btn_reset.addActionListener(this);
		btn_check.addActionListener(this);
		btn_back.addActionListener(this);
		
		btn_panel.add(btn_join);
		btn_panel.add(btn_reset);
		btn_panel.add(btn_back);
		
		String[] ranklist = {"����","����","�˹�"};
		box_rank = new JComboBox<String>(ranklist);
		box_rank.setFont(Commons.getFont());
		box_rank.setPreferredSize(new Dimension(112,23));
		
		tf_id = new JTextField(10);
		tf_pass = new JPasswordField(10);
		tf_passcheck = new JPasswordField(10);
		tf_name = new JTextField(10);
		tf_pnum = new JTextField(10);
		
		for(String name : namelist) {
			JPanel l_panel = new JPanel();
			JPanel t_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			if(name.equals("���̵�")){
				l_panel.add(new JLabel("             "+name));
				t_panel.add(tf_id);
				t_panel.add(btn_check);
			}else if(name.equals("��й�ȣ")){
				l_panel.add(new JLabel("             "+name));
				t_panel.add(tf_pass);
			}else if(name.equals("��й�ȣ Ȯ��")){
				l_panel.add(new JLabel("             "+name));
				t_panel.add(tf_passcheck);
			}else if(name.equals("�̸�")){
				l_panel.add(new JLabel("             "+name));
				t_panel.add(tf_name);
			}else if(name.equals("�޴���")){
				l_panel.add(new JLabel("             "+name));
				t_panel.add(tf_pnum);
			}else if(name.equals("����")){
				l_panel.add(new JLabel("             "+name));
				t_panel.add(box_rank);
			}
			label_panel.add(l_panel);
			label_panel.setFont(Commons.getFont());
			tf_panel.add(t_panel);
			l_panel.setBackground(Color.white);
			t_panel.setBackground(Color.white);
		}
		
		ImageIcon img = new ImageIcon("image/join2.PNG");
		JLabel join_img = new JLabel(img);
		
		add(p1);
		p1.setLayout(new BorderLayout(40,10));
		
		
		p1.add(join_img, BorderLayout.NORTH);
		p1.add(label_panel, BorderLayout.WEST);
		p1.add(tf_panel, BorderLayout.CENTER);
		p1.add(btn_panel, BorderLayout.SOUTH);
		
		
		label_panel.setBackground(Color.white);
		tf_panel.setBackground(Color.white);
		btn_panel.setBackground(Color.white);
		p1.setBackground(Color.white);
		
		
		setSize(420,460);
		setVisible(true);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn_join || obj == tf_rank) {
			join_proc();
		}else if(obj == btn_reset) {
			tf_id.setText("");
			tf_pass.setText("");
			tf_passcheck.setText("");
			tf_name.setText("");
			tf_pnum.setText("");
//			tf_rank.setText("");
			tf_id.requestFocus();
		
		}else if(obj == btn_check) {
			check_proc();
			
		}else if(obj == btn_back) {
			
			setVisible(false);	
		}
	}
	
	public void check_proc() {
		int result = 0;
		String id = tf_id.getText();
		if(id.equals("")) {
			JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���");
			tf_id.requestFocus();
		}else {
			MemberDao dao = new MemberDao();
			result = dao.getCheck(id);
			
			if(result==1) {
				JOptionPane.showMessageDialog(null, "�̹� ������� ���̵��Դϴ�.\n���� �Է����ּ���.");
				tf_id.setText("");
				tf_id.requestFocus();

			}else {
				JOptionPane.showMessageDialog(null, "��밡���� ���̵� �Դϴ�.");
				btn_check.setBackground(Color.LIGHT_GRAY);
				tf_pass.requestFocus();
			}
		}
	}
	
	public void join_proc() {
		if(validationCheck()) {
			MemberDao dao = new MemberDao();
			MemberVO vo = new MemberVO();
			vo.setId(tf_id.getText());
			vo.setPass(tf_pass.getText());
			vo.setName(tf_name.getText());
			vo.setPnum(tf_pnum.getText());
			vo.setRank(box_rank.getSelectedItem().toString());
			int result = dao.getJoin(vo);
			if(result == 1) {
				JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����ϼ̽��ϴ�.");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����ϼ̽��ϴ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				tf_id.setText("");
				tf_pass.setText("");
				tf_passcheck.setText("");
				tf_name.setText("");
				tf_pnum.setText("");
				tf_rank.setText("");
				tf_id.requestFocus();
			}
		}
	}

		public boolean validationCheck() {
			boolean result = false;
			
			if(tf_id.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
				tf_id.requestFocus();
			}else if(tf_pass.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���");
				tf_pass.requestFocus();
			}else if(tf_passcheck.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���");
				tf_passcheck.requestFocus();
			}else if(tf_name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���");
				tf_name.requestFocus();
			}else if(tf_pnum.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�ڵ��� ��ȣ�� �Է����ּ���");
				tf_pnum.requestFocus();
			
			}else	{
				if(tf_pass.getText().equals(tf_passcheck.getText())) {
					result = true;
				}else {
				JOptionPane.showMessageDialog(null,"��й�ȣ�� ��ġ���� �ʽ��ϴ�.","", JOptionPane.ERROR_MESSAGE);
				}
			}
			return result;
		}
		
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
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










