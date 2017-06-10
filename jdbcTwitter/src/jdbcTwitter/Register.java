package jdbcTwitter;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;


public class Register extends JFrame {
	dbAccess db;
	String ID;
	String PW;
	String NAME;
	int BIRTH;
	TextField id;
	TextField pw;
	TextField Name;
	TextField birth;
	Container c;
	
	
	Register() {
		super("Register");
		getContentPane().setBackground(Color.white);
		setSize(300, 400);
		setting();
		setButton();
		setVisible(true);
		setResizable(false);
		db = new dbAccess();
	}

	void setting() {
	}

	void setButton() {
		getContentPane().setLayout(null);
		JLabel lname = new JLabel("name :", Label.RIGHT);
		lname.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lname.setBounds(53, 175, 45, 23);
		getContentPane().add(lname);
		Name = new TextField(18);
		Name.setBounds(104, 175, 150, 23);
		getContentPane().add(Name);
		JLabel lbirth = new JLabel("birth :", Label.RIGHT);
		lbirth.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lbirth.setBounds(53, 219, 40, 23);
		getContentPane().add(lbirth);
		birth = new TextField(18);
		birth.setBounds(104, 219, 150, 23);
		getContentPane().add(birth);
		JButton register = new JButton("register");
		register.setFont(new Font("Arial Black", Font.PLAIN, 13));
		register.setBounds(89, 270, 113, 33);
		getContentPane().add(register);
		register.setBackground(Color.white);
		pw = new TextField(18);
		pw.setBounds(104, 133, 150, 23);
		getContentPane().add(pw);
		pw.setEchoChar('*');
		JLabel lpw = new JLabel("PW :", Label.RIGHT);
		lpw.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lpw.setBounds(58, 133, 40, 23);
		getContentPane().add(lpw);
		id = new TextField(18);
		id.setBounds(104, 92, 150, 23);
		getContentPane().add(id);
		JLabel lid = new JLabel("ID :", Label.RIGHT);
		lid.setFont(new Font("Aharoni", Font.PLAIN, 13));
		lid.setBounds(67, 92, 26, 23);
		getContentPane().add(lid);
		
		JLabel lblWelecomeToOur = new JLabel("welecome to our twitter ");
		lblWelecomeToOur.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelecomeToOur.setFont(new Font("Eras Bold ITC", Font.PLAIN, 18));
		lblWelecomeToOur.setBackground(new Color(0, 0, 255));
		lblWelecomeToOur.setBounds(24, 10, 245, 76);
		getContentPane().add(lblWelecomeToOur);
		register.addActionListener(new EventHandler());
	}

	class EventHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			ID = id.getText();
			PW = pw.getText();
			BIRTH = Integer.parseInt(birth.getText());
			NAME = Name.getText();
			if(ID.length() == 0 || PW.length() == 0 || birth.getText().length() ==0 || NAME.length() == 0)
				JOptionPane.showMessageDialog(null, "type all blank");
			
			else if(ID.length() > 10)
				JOptionPane.showMessageDialog(null, "ID length doesn't over 10");
			else if(PW.length() > 10)
				JOptionPane.showMessageDialog(null, "pw length doesn't over 10");
			else if(NAME.length() > 10)
				JOptionPane.showMessageDialog(null, "name length doesn't over 10");
		
			if (db.checkUser(ID)) {
				JOptionPane.showMessageDialog(null, "already exists ID"); //이미 존재하는 ID입니다라고 메세지창
			} else {
				db.addUser(ID, NAME, BIRTH, PW);  // 등록되었습니다 메시지와 함께 다시 로그인창 생성
				JOptionPane.showMessageDialog(null,"register your id");
				dispose();
				new Login();
			}
		}
	}

}
