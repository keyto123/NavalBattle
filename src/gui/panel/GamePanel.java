package gui.panel;

import javax.swing.JButton;

import gui.GameFrame;

public abstract class GamePanel extends BasePanel {

	protected JButton quit = new JButton("Quit");

	protected GamePanel(int width, int height, GameFrame frame) {
		super(width, height, frame);

		quit.setBounds(5, 550, 100, 25);
		quit.addActionListener(a -> System.exit(0));
		this.add(quit);
	}
}
