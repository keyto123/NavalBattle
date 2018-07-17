package gui.panel;

import javax.swing.JButton;

import game.GameManager;
import game.Util;
import gui.GameFrame;
import gui.board.CpuBoard;
import gui.board.PlayerBoard;

@SuppressWarnings("serial")
public class BattlePanel extends GamePanel {

	private JButton finish = new JButton("Finish");
	private JButton startGame = new JButton("Start");

	private PlayerBoard playerPanel = new PlayerBoard(this);
	private CpuBoard cpuPanel = new CpuBoard(this);
	private BoatPanel boatPanel = new BoatPanel(this);
	private GameManager gm = new GameManager(playerPanel, cpuPanel, boatPanel);

	public GameManager getGm() {
		return gm;
	}

	public BattlePanel(GameFrame frame) {
		super(frame);
		this.setBackgroundImage(null);

		initButtons();
		initLabels();
		initPanels();
	}

	private void initButtons() {
		// Finish
		finish.setBounds(this.getWidth() - 110, this.getHeight() - 50, Util.commonButtonWidth, Util.buttonHeight);
		finish.addActionListener(e -> finish_buttonAction());
		this.add(finish);

		// Start
		startGame.setBounds(this.getWidth() - 220, this.getHeight() - 50, Util.commonButtonWidth, Util.buttonHeight);
		startGame.addActionListener(e -> {
			gm.startGame();
			startGame.setEnabled(false);
		});
		this.add(startGame);
	}

	private void initLabels() {

	}

	private void initPanels() {
		playerPanel.setBounds(Util.minButtonX + 20, 10, playerPanel.getWidth(), playerPanel.getHeight());
		this.add(playerPanel);

		cpuPanel.setBounds(Util.minButtonX + playerPanel.getWidth() + 25, 10, cpuPanel.getWidth(),
				cpuPanel.getHeight());
		this.add(cpuPanel);

		boatPanel.setBounds(Util.minButtonX, playerPanel.getHeight() + 5, boatPanel.getWidth(),
				boatPanel.getHeight());
		this.add(boatPanel);
	}

	private void finish_buttonAction() {
		frame.battleFinished();
	}

}
