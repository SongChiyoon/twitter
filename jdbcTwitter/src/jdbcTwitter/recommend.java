package jdbcTwitter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class recommend extends JFrame {

	private JPanel contentPane;
	private JTextField tid;
	String id;
	board board;
	dbAccess db;
	public recommend(dbAccess DB, String ID) {
		super("Recommend");
		setVisible(true);
		setResizable(false);
		setBounds(100, 100, 404, 335);
		this.id = ID;
		this.db = DB;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea followerList = new JTextArea();
		followerList.setText(db.Recommend(id));
		followerList.setEditable(false);
		
		JScrollPane p = new JScrollPane(followerList);
		
		p.setBounds(12, 71, 372, 227);
		contentPane.add(p);
		
		tid = new JTextField();
		tid.setBounds(12, 20, 238, 29);
		contentPane.add(tid);
		tid.setColumns(10);
		
		JButton btnFollow = new JButton("Follow");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(db.checkfollower(id, tid.getText())){
					JOptionPane.showMessageDialog(null, "already follow");
				}
				else if(false){
					//있는 아이디인지 아닌지 check
				}
				else{
					db.addfollow(id, tid.getText());
					JOptionPane.showMessageDialog(null, "follow success !");
					followerList.setText(db.Recommend(id));
				}
				
			}
		});
		btnFollow.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnFollow.setBackground(new Color(255, 250, 250));
		btnFollow.setBounds(273, 20, 99, 27);
		contentPane.add(btnFollow);
	}

}
