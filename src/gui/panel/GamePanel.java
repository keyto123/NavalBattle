package gui.panel;

import javax.swing.JButton;

import gui.GameFrame;

@SuppressWarnings("serial")
public abstract class GamePanel extends BasePanel {

	protected JButton quit = new JButton("Quit");

	protected GamePanel(GameFrame frame) {
		super(frame.getWidth(), frame.getHeight(), frame);

		quit.setBounds(10, frame.getHeight() - 50, 100, 25);
		quit.addActionListener(a -> System.exit(0));
		this.add(quit);
	}
}
