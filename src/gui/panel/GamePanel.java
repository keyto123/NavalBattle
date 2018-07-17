package gui.panel;

import javax.swing.JButton;

import game.Util;
import gui.GameFrame;

@SuppressWarnings("serial")
public abstract class GamePanel extends BasePanel {

	protected JButton quit = new JButton("Quit");

	protected GamePanel(GameFrame frame) {
		super(frame.getWidth(), frame.getHeight(), frame);

		quit.setBounds(10, frame.getHeight() - 50, Util.commonButtonWidth, Util.buttonHeight);
		quit.addActionListener(a -> System.exit(0));
		this.add(quit);
	}
}
