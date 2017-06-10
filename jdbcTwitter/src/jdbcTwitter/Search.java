package jdbcTwitter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private board board;
	private boolean ok;
	String id;
	dbAccess db;
	public Search(String myid,board b,dbAccess DB) {
		super("Search");
		setVisible(true);
		setResizable(false);
		this.board = b;
		this.db = DB;
		this.id = myid;
		setBounds(100, 100, 448, 192);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ID = new JTextField();
		ID.setBounds(81, 18, 151, 29);
		contentPane.add(ID);
		ID.setColumns(10);
		
		JLabel lid = new JLabel("ID");
		lid.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lid.setHorizontalAlignment(SwingConstants.RIGHT);
		lid.setBounds(12, 17, 57, 29);
		contentPane.add(lid);
		
		JTextArea inform = new JTextArea();
		inform.setBounds(22, 57, 407, 61);
		inform.setEditable(false);
		contentPane.add(inform);
		
		JButton search = new JButton("Search");
		search.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(db.checkUser(ID.getText())){
					ok = true;
					inform.setText(db.individualInform(ID.getText()));
				}
				 else{
					 JOptionPane.showMessageDialog(null, "check id");
					 ok = false;
				 }
				
			}
		});
		search.setBackground(new Color(248, 248, 255));
		search.setBounds(244, 19, 99, 25);
		contentPane.add(search);
		
		JButton follow = new JButton("Follow");
		follow.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		follow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ok == true){
					if(db.checkfollower(id, ID.getText())){
					JOptionPane.showMessageDialog(null, "already follow");
					}
					else{
					JOptionPane.showMessageDialog(null, "follow success");
					db.addfollow(id, ID.getText());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "click search first");
				}

			}
		});
		follow.setBackground(new Color(248, 248, 255));
		follow.setBounds(330, 128, 99, 25);
		contentPane.add(follow);
		
	
		
		JButton go = new JButton("GO");
		go.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ok == true){
					JOptionPane.showMessageDialog(null, "gogo !");
					b.owner = ID.getText();
					//	board.setBoard(tid.getText());
						board.newboard(ID.getText(), db.IndividualBoard(ID.getText()));

					
				}
				else{
					JOptionPane.showMessageDialog(null, "click search first");
				}
			}
		});
		go.setBackground(new Color(248, 248, 255));
		go.setBounds(216, 128, 99, 25);
		contentPane.add(go);
	}

}
