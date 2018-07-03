package gui.panel;

import javax.swing.JButton;

import game.Configs;
import gui.GameFrame;
import gui.board.Board;
import gui.board.CpuBoard;
import gui.board.PlayerBoard;

public class BattlePanel extends GamePanel {

	private JButton finish = new JButton("Finish");

	private Board cpuPanel = new CpuBoard();
	private Board playerPanel = new PlayerBoard();

	public BattlePanel(GameFrame frame) {
		super(800, 600, frame);

		initButtons();
		initLabels();
		initPanels();
	}

	private void initButtons() {
		// Finish
		finish.setBounds(650, 550, 100, 25);
		finish.addActionListener(e -> finish_buttonAction());
		this.add(finish);
	}

	private void initLabels() {

	}

	private void initPanels() {
		playerPanel.setBounds(Configs.minButtonX + 20, Configs.minButtonY, playerPanel.getWidth(),
				playerPanel.getHeight());
		this.add(playerPanel);

		cpuPanel.setBounds(Configs.minButtonX + playerPanel.getWidth() + 25, Configs.minButtonY, cpuPanel.getWidth(),
				cpuPanel.getHeight());
		this.add(cpuPanel);
	}

	private void finish_buttonAction() {
		frame.battleFinished();
	}

}
