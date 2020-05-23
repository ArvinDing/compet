package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Game extends JFrame {

	public Game() {

		initUI();
	}

	private void initUI() {

		add(new Board());
		setSize(500, 500);
		setTitle("Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Game ex = new Game();
			ex.setVisible(true);
		});
	}

}
