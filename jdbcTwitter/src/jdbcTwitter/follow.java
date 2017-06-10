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

public class follow extends JFrame {

	private JPanel contentPane;
	private JTextField tid;
	board board;
	dbAccess db;
	String id;
	public follow(board b, dbAccess DB,String ID) {
		super("follower");
		setVisible(true);
		setResizable(false);
		this.board = b;
		this.db = DB;
		this.id = ID;
		setBounds(100, 100, 404, 372);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea followerList = new JTextArea();
		followerList.setText(db.follow(id));
		followerList.setEditable(false);
		
		JScrollPane p = new JScrollPane(followerList);
		p.setBounds(12, 108, 372, 227);
		contentPane.add(p);
		
		tid = new JTextField();
		tid.setBounds(12, 27, 238, 29);
		contentPane.add(tid);
		tid.setColumns(10);
		
		JButton go = new JButton("go");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(db.checkfollower(id, tid.getText())){
					JOptionPane.showMessageDialog(null, "gogo !");
					board.owner = tid.getText();
				//	board.setBoard(tid.getText());
					board.newboard(tid.getText(), db.IndividualBoard(tid.getText()));
				}
				
				else{
					JOptionPane.showMessageDialog(null, "not correct id");
				}
			}
		});
		go.setFont(new Font("Arial Black", Font.PLAIN, 14));
		go.setBackground(new Color(255, 250, 250));
		go.setBounds(262, 10, 99, 27);
		contentPane.add(go);
		
		JButton unfollow = new JButton("unfollow");
		unfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(db.checkfollower(id, tid.getText())){
					db.disconnect(id, tid.getText());
					followerList.setText(db.follow(id));
					JOptionPane.showMessageDialog(null, "delete !");
				}
				else{
					
					JOptionPane.showMessageDialog(null, "check id");
					
				}
			}
		});
		unfollow.setFont(new Font("Arial Black", Font.PLAIN, 14));
		unfollow.setBackground(new Color(255, 250, 250));
		unfollow.setBounds(262, 47, 99, 27);
		contentPane.add(unfollow);
	}

}
