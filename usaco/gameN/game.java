package gameN;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class game extends JApplet implements ActionListener {


	boolean singleB = false;
	JButton single, credits, multi;
	JLabel label, status;

	public void init() {
		getContentPane().setBackground(Color.BLACK);
		JFrame frame = new JFrame();
		// more initialization code here	

		label = new JLabel("Tic Tac Toe!");
		label.setFont(new Font("Serif", Font.BOLD, 34));
		label.setForeground(Color.GREEN);
		add(label);

		single = new JButton("SinglePlayer");
		credits = new JButton("Credits");
		multi = new JButton("Multiplayer");

		single.addActionListener(this);
		multi.addActionListener(this);
		credits.addActionListener(this);

		multi.setForeground(Color.GREEN);
		single.setForeground(Color.GREEN);
		credits.setForeground(Color.GREEN);

		multi.setBackground(Color.BLACK);
		single.setBackground(Color.BLACK);
		credits.setBackground(Color.BLACK);

		add(credits);
		add(multi);
		add(single);
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (singleB) {

			single.setVisible(false);
			multi.setVisible(false);
			credits.setVisible(false);

			single.setEnabled(false);
			multi.setEnabled(false);
			credits.setEnabled(false);

			Dimension d = getSize();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, d.width, d.height);

			add(status, BorderLayout.SOUTH);
			status.setVisible(true);

			singleB = false;
		} else {
			multi.setLocation(850, 400);
			multi.setSize(400, 40);
			single.setLocation(850, 300);
			single.setSize(400, 40);
			credits.setLocation(850, 500);
			credits.setSize(400, 40);
			label.setLocation(950, 100);
			label.setSize(400, 50);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == single) {

			singleB = true;
		} else if (e.getSource() == credits) {
			System.out.println("Button 2 was pressed");

		}
		this.repaint();
	}

	

}