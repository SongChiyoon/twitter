package jdbcTwitter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class edit extends JFrame {

	private JPanel contentPane;
	private TextField pw;
	private JButton btnEdit;
	private dbAccess db;
	public edit(String id,dbAccess DB) {
		super("Edit");
		setVisible(true);
		setResizable(false);
		setBounds(100, 100, 350, 131);
		this.db = DB;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pw = new TextField();
		pw.setBounds(93, 26, 197, 21);
		pw.setEchoChar('*');
		contentPane.add(pw);
		pw.setColumns(10);
		
		JLabel lpw = new JLabel("PW");
		lpw.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lpw.setHorizontalAlignment(SwingConstants.RIGHT);
		lpw.setBounds(12, 26, 57, 21);
		contentPane.add(lpw);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pw.getText().length() > 10){
					JOptionPane.showMessageDialog(null, "pw doesn't exceed 10 ");
				}
				else{
					db.pwEdit(id, pw.getText());
					JOptionPane.showMessageDialog(null, "edit success !");
					
				}
			}
		});
		btnEdit.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnEdit.setBackground(new Color(248, 248, 255));
		btnEdit.setBounds(193, 57, 99, 25);
		contentPane.add(btnEdit);
	}
}
