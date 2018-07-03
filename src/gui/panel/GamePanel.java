package gui.panel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.GameFrame;

public abstract class GamePanel extends JPanel {

	protected JButton quit = new JButton("Quit");
	protected GameFrame frame;

	protected GamePanel(int width, int height, GameFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		this.setSize(width, height);
		this.setBackground(Color.GRAY);

		quit.setBounds(5, 550, 100, 25);
		quit.addActionListener(a -> System.exit(0));
		this.add(quit);
	}
}
