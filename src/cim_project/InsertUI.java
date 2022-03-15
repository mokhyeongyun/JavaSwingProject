package cim_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class InsertUI implements ActionListener{

	MainUI main;
	JPanel  label_panel,tf_panel, btn_panel;
	JButton btn_insert, btn_reset;
	ArrayList<JTextField> tf_list;
	String[] namelist = {"품번","제품명","브랜드","구분","가격","수량"};
	TitledBorder tb = new TitledBorder(new LineBorder(Color.magenta));
	
	
	public InsertUI() {}
	public InsertUI(MainUI main) {
		this.main=main;
		init();
	}
	
	public void init() {
		main.jf.setTitle("화장품 재고관리 프로그램 - 수정");
		main.switch_panel(MainUI.INSERT);
		
		main.insert_panel.setLayout(new BorderLayout());
		tf_list = new ArrayList<JTextField>();
		btn_panel = new JPanel();
		tf_panel = new JPanel(new GridLayout(namelist.length,1));
		label_panel = new JPanel(new GridLayout(namelist.length,1));
		
		btn_insert = new JButton("등 록");
		btn_reset = new JButton("다시쓰기");
		btn_insert.setFont(Commons.getFont());
		btn_reset.setFont(Commons.getFont());
		btn_insert.setBackground(Color.white);
		btn_reset.setBackground(Color.white);
		btn_panel.add(btn_insert);
		btn_panel.add(btn_reset);
		
		for(String name : namelist) {
			JPanel l_panel = new JPanel();
			JPanel t_panel = new JPanel();
			JLabel label = new JLabel("                 "+name);
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
		
		
		main.insert_panel.add(new JLabel("            "), BorderLayout.NORTH);
		main.insert_panel.add(new JLabel("                         "), BorderLayout.EAST);
		main.insert_panel.add(label_panel, BorderLayout.WEST);
		main.insert_panel.add(tf_panel, BorderLayout.CENTER);
		main.insert_panel.add(btn_panel, BorderLayout.SOUTH);
		main.insert_panel.setBackground(Color.white);
		label_panel.setBackground(Color.white);
		tf_panel.setBackground(Color.white);
		btn_panel.setBackground(Color.white);
//		main.insert_panel.setBorder(tb);
		
		main.jf.add(main.insert_panel, BorderLayout.CENTER);
		main.jf.repaint();
		main.jf.setVisible(true);
		
		btn_insert.addActionListener(this);
		btn_reset.addActionListener(this);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==btn_insert) {
			if(validationCheck()) {
			int in = JOptionPane.showConfirmDialog(null,"정말로 등록 하시겠습니까?","",0);
				if(in==0) {
					insert_proc();
				}
			}
			//-----------------11-15
		}else if(obj==btn_reset) {
			for(JTextField jf : tf_list) {
				jf.setText("");
			}
			tf_list.get(0).requestFocus();
		}
	}
	
	public boolean validationCheck(){
		boolean result = false;
		String[] clist = {"품번","제품명","브랜드","구분","가격","수량"};
		
		for(int i = 0;i<clist.length;i++) {
			JTextField tf = tf_list.get(i);
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, clist[i]+"를 입력해주세요");
				tf.requestFocus();
				i = clist.length;
			}else if(i==(clist.length-1)){
				result = true;
			}
		}
		return result;
	}
	
	
	public void insert_proc() {
		CimDao dao = new CimDao();
		CimVO vo = new CimVO();
		vo.setCno(tf_list.get(0).getText());
		vo.setCname(tf_list.get(1).getText());
		vo.setCbrand(tf_list.get(2).getText());
		vo.setCkinds(tf_list.get(3).getText());
		vo.setCprice(tf_list.get(4).getText());
		vo.setCquantity(Integer.parseInt(tf_list.get(5).getText()));
		
		int result = dao.getInsert(vo);
		
		if(result != 0) {
			JOptionPane.showMessageDialog(null, "등록에 성공하였습니다");
			for(JTextField tf : tf_list) {
				tf.setText("");
			}
		}else {
			JOptionPane.showMessageDialog(null, "등록에 실패하였습니다.");
		}
	}
		
	}
	
	











