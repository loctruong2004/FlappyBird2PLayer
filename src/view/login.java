package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.action_login;
import model.DB;

public class login extends JPanel {
	private static final long serialVersionUID = 1L;
	public int broadWidth = 360;
	public int broadHeight = 640;
	public JTextField textfield;
	private JButton BTNsubmit;
	private DB db;
	private JButton BTNtop;
	private action_login listener;
	private Image backgroundImg;
	
	private JButton BTNPLayers;
	private JTextField textfield2;
	private JPanel form;
	private JPanel wrapname2;
	private JLabel name2;
	private JPanel wrapTextfield2;
	private JLabel name;

	public login(DB db) {
		this.db = db;
		this.backgroundImg = new ImageIcon(getClass().getResource("/Image/flappybirdbg.png")).getImage();
		this.showLogin();
	}

	public void showLogin() {
		setPreferredSize(new Dimension(broadWidth, broadHeight));
		setLayout(new GridLayout(5, 1));

		Font fontHeader = new Font("Arial", Font.BOLD, 22);
		Font fontBTN = new Font("Arial", Font.BOLD, 50);
		Font fontLabel = new Font("Arial", Font.BOLD, 40);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Image/flappybird.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(64, 48, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);

		// header
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setOpaque(false); // Make the panel transparent
		JPanel headerlb = new JPanel();
		JLabel contentHeader = new JLabel("Nhập thông tin người chơi");
		contentHeader.setFont(fontHeader);
		headerlb.add(contentHeader);
		headerlb.setOpaque(false);
		JPanel wrapimg = new JPanel();
		wrapimg.setOpaque(false); // Make the panel transparent
		wrapimg.add(new JLabel(icon));
		
		header.add(headerlb, BorderLayout.CENTER);
		header.add(wrapimg, BorderLayout.SOUTH);

		// form
		form = new JPanel();
		form.setLayout(new GridLayout(4, 1));
		form.setOpaque(false); // Make the panel transparent
		JPanel wrapname = new JPanel();
		wrapname.setOpaque(false); // Make the panel transparent
		name = new JLabel("Tên Người Chơi");
		name.setFont(fontHeader);
		wrapname.add(name);
		textfield = new JTextField();
		JPanel wrapTextfield = new JPanel();
		wrapTextfield.setOpaque(false); // Make the panel transparent
		textfield.setColumns(12);
		textfield.setFont(fontHeader);
		wrapTextfield.add(textfield);
		wrapname2 = new JPanel();
		wrapname2.setOpaque(false); // Make the panel transparent
		name2 = new JLabel("Tên Người Chơi 2");
		name2.setFont(fontHeader);
		wrapname2.add(name2);
		textfield2 = new JTextField();
		wrapTextfield2 = new JPanel();
		wrapTextfield2.setOpaque(false); // Make the panel transparent
		textfield2.setColumns(12);
		textfield2.setFont(fontHeader);
		wrapTextfield2.add(textfield2);
		form.add(wrapname);
		form.add(wrapTextfield);

		
		// footer
		JPanel footer = new JPanel();
		footer.setOpaque(false); // Make the panel transparent
		BTNsubmit = new JButton("Start");
		BTNsubmit.setFont(fontBTN);
		footer.add(BTNsubmit);

		// top
		JPanel top = new JPanel();
		top.setOpaque(false); // Make the panel transparent
		BTNtop = new JButton("Top Người Chơi");
		BTNtop.setFont(fontHeader);
		JPanel players = new JPanel();
		BTNPLayers = new JButton("chơi 2 người");
		players.setOpaque(false);
		BTNPLayers.setFont(fontHeader);

		players.add(BTNPLayers);
		top.add(BTNtop);

		this.add(header);
		this.add(form);
		this.add(footer);
		this.add(players);
		this.add(top);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
	}

	public void addLoginListener(action_login listener) {
		textfield.addKeyListener(listener);
		textfield.addActionListener(listener);
		BTNsubmit.addActionListener(listener);
		BTNtop.addActionListener(listener);
		BTNPLayers.addActionListener(listener);
	}

	public void removeActionListener() {
		textfield.removeKeyListener(listener);
		textfield.removeActionListener(listener);
		BTNsubmit.removeActionListener(listener);
		BTNtop.removeActionListener(listener);
	}

	public void replaceMode(boolean isPlayers) {
		if (isPlayers) {
			BTNPLayers.setText("chơi 1 người");
			form.add(wrapname2);
			form.add(wrapTextfield2);
			name.setText("Tên Người Chơi 1");
		} else {
			BTNPLayers.setText("chơi 2 người"); 
			form.remove(wrapname2);
			form.remove(wrapTextfield2);
			name.setText("Tên Người Chơi");
		}
		this.revalidate();
		this.repaint();
	}

	public boolean checked() {
		String text = textfield.getText().trim();
		db.setCurrentName(text);
		if (text == null || text.equals("") || !db.checkPerson())
			return false;
		return true;
	}

	public String getCurrName() {
		return textfield.getText().trim();
	}

	public void error() {
		Border redBorder = BorderFactory.createLineBorder(Color.RED);
		textfield.setBorder(redBorder);
	}
}
