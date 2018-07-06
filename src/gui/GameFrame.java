package gui;

import javax.swing.JFrame;

import gui.panel.BattlePanel;
import gui.panel.StartPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private StartPanel startPanel;
	private BattlePanel battlePanel;

	public GameFrame() {
		this.setTitle("Naval Battle");
		this.setSize(800, 600);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startPanel = new StartPanel(this);
		startPanel.setVisible(true);
		this.add(startPanel);

		this.setResizable(false);
		this.setVisible(true);
	}

	public void battleFinished() {
		this.remove(battlePanel);
		this.battlePanel = null;
		this.add(startPanel);
		this.repaint();
	}

	public void battleStarted() {
		this.remove(startPanel);
		this.battlePanel = new BattlePanel(this);
		this.add(battlePanel);
		this.repaint();
	}
}
