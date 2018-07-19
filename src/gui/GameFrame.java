package gui;

import javax.swing.JFrame;

import game.util.Configs;
import gui.panel.BattlePanel;
import gui.panel.StartPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private StartPanel startPanel;
	private BattlePanel battlePanel;

	public GameFrame() {
		this.setTitle("Naval Battle");
		this.initSize();

		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initPanel();

		this.setResizable(false);
		this.setVisible(true);
	}

	private void initSize() {
		this.setSize(25 + Configs.boardSize * 60, 170 + Configs.boardSize * 30);
		this.setLocationRelativeTo(null);
	}

	private void initPanel() {
		if (startPanel != null) {
			this.remove(startPanel);
		}
		startPanel = new StartPanel(this);
		startPanel.setVisible(true);
		this.add(startPanel);
	}

	public void restart() {
		this.initSize();
		this.initPanel();
		this.repaint();
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
