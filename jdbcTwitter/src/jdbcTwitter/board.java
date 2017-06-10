package jdbcTwitter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JSlider;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;

public class board extends JFrame {
	private JTextField POST;
	String ID;
	String owner;
	JTextArea board;
	board me;
	dbAccess db;
	JLabel OWNER;

	public board(String id) {
		super("BOARD");
		setResizable(false);
		this.ID = id;
		me = this;
		db = new dbAccess();
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 483);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 13));
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("menu");
		mnNewMenu.setFont(new Font("Arial Black", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem Follower = new JMenuItem("My Follower");
		Follower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new follow(me,db,ID);
			}
		});
		Follower.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		mnNewMenu.add(Follower);
		
		JMenuItem recommend = new JMenuItem("Recommend");
		recommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new recommend(db,ID);
			}
		});
		recommend.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		mnNewMenu.add(recommend);
		
		JMenuItem myBoard = new JMenuItem("My board");
		myBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newboard(ID, db.IndividualBoard(ID));
				owner = ID;
			}
		});
		mnNewMenu.add(myBoard);
		myBoard.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		JMenuItem Search = new JMenuItem("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search frame = new Search(ID, me,db);
				frame.setVisible(true);
			}
		});
		Search.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		mnNewMenu.add(Search);
		
		JMenuItem Edit = new JMenuItem("PW Edit");
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new edit(id,db);
			}
		});
		Edit.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		mnNewMenu.add(Edit);
		getContentPane().setLayout(null);
		
		JLabel myID = new JLabel(ID);
		myID.setHorizontalAlignment(SwingConstants.CENTER);
		myID.setFont(new Font("Arial Black", Font.PLAIN, 13));
		myID.setBackground(new Color(85, 107, 47));
		myID.setBounds(12, 14, 72, 22);
		getContentPane().add(myID);
		setBoard(ID);
		
		
	}
	public void setBoard(String Owner){
		this.owner = Owner;
		System.out.println(owner);
		OWNER = new JLabel();
		OWNER.setText(owner);
		OWNER.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 20));
		OWNER.setHorizontalAlignment(SwingConstants.CENTER);
		OWNER.setBackground(new Color(173, 255, 47));
		OWNER.setBounds(245, 10, 204, 31);
		getContentPane().add(OWNER);
		
		POST = new JTextField();
		POST.setBounds(116, 51, 491, 77);
		getContentPane().add(POST);
		POST.setColumns(10);
		JButton postB = new JButton("post");
		postB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(POST.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "input message");
				}
				else if(POST.getText().length() > 150){
					JOptionPane.showMessageDialog(null, "message's length doesn't exceeds 150 character");
				}
				else{					
					JOptionPane.showMessageDialog(null, "post");
					db.addPost(owner, ID, POST.getText());
					newboard(owner,db.IndividualBoard(owner));
					POST.setText("");
				}
			}
		});
		postB.setFont(new Font("Arial Black", Font.PLAIN, 12));
		postB.setBackground(new Color(255, 255, 255));
		postB.setBounds(508, 129, 99, 25);
		getContentPane().add(postB);
		board = new JTextArea();
		board.setEditable(false);
		board.setText(db.IndividualBoard(ID));
		JScrollPane p = new JScrollPane(board);
		p.setBounds(119, 172, 488, 245);
		getContentPane().add(p);
		
	}
	public void newboard(String id, String post){
		OWNER.setText(id);
		board.setText(post);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
