package jdbcTwitter;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jdbcTwitter.Login;
import jdbcTwitter.Register;
import jdbcTwitter.dbAccess;
import jdbcTwitter.Login.EventHandler;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Login {
	JFrame login;
	Container c;
	String ID;
	String PW;
	TextField id;
	TextField pw;
	dbAccess db;
	Login() {

		login = new JFrame("log in");
		login.setSize(400, 291);
		db = new dbAccess();
		c = login.getContentPane();
		c.setBackground(Color.white);

		text();
		setButton();
		login.setVisible(true);
		login.setResizable(false);
		login.setLocation(100, 100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void text() {
		JLabel lid = new JLabel("ID :", SwingConstants.RIGHT);
		lid.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lid.setBounds(101, 128, 38, 23);
		JLabel lpw = new JLabel("PW :", Label.RIGHT);
		lpw.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lpw.setBounds(110, 166, 29, 23);
		id = new TextField(18);
		id.setBounds(159, 128, 150, 23);
		pw = new TextField(18);
		pw.setBounds(159, 166, 150, 23);
		pw.setEchoChar('*');
		login.getContentPane().setLayout(null);
		c.add(lid);
		c.add(id);
		c.add(lpw);
		c.add(pw);
	}

	void setButton() {
		JButton log_in = new JButton("log in" );
		log_in.setFont(new Font("Bodoni MT", Font.PLAIN, 12));
		log_in.setLocation(90, 211);
		JButton register = new JButton("register");
		register.setFont(new Font("Bodoni MT", Font.PLAIN, 12));
		register.setLocation(210, 211);
		log_in.setSize(99, 25);
		log_in.setBackground(Color.white);
		log_in.addActionListener(new EventHandler());
		c.add(log_in);
		register.setSize(99, 25);
		register.setBackground(Color.white);
		register.addActionListener(new EventHandler());
		c.add(register);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\\uC1A1\uCE58\uC724\\workspace\\test\\twitt.PNG"));
		lblNewLabel.setBounds(133, 37, 130, 81);
		login.getContentPane().add(lblNewLabel);
	}

	class EventHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("log in")) {
				ID = id.getText();
				PW = pw.getText();
				System.out.println(ID +" "+PW);
				if(db.checkUser(ID, PW))
				{	
					login.dispose();
					JOptionPane.showMessageDialog(null, "welcome !");
					board frame = new board(ID);
					frame.setVisible(true);
					
	
					
				}
				
				else{
					JOptionPane.showMessageDialog(null, "ID or PW is not correct !");
					
				}
				
			}
			else{
				login.dispose();
				new Register();
			}
		}

	}

	public static void main(String[] args) {
		new Login();
	}
}
