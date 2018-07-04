package gui.panel;

import javax.swing.JButton;

import game.Util;
import game.boats.BoatType;
import gui.GameFrame;
import gui.board.Board;
import gui.board.CpuBoard;
import gui.board.PlayerBoard;

public class BattlePanel extends GamePanel {

	private JButton finish = new JButton("Finish");

	private Board cpuPanel = new CpuBoard(frame);
	private Board playerPanel = new PlayerBoard(frame);
	private BoatPanel boatPanel = new BoatPanel(frame);
	
	public BattlePanel(GameFrame frame) {
		super(800, 600, frame);
		this.setBackgroundImage(null);
		
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
		playerPanel.setBounds(Util.minButtonX + 20, Util.minButtonY, playerPanel.getWidth(),
				playerPanel.getHeight());
		this.add(playerPanel);

		cpuPanel.setBounds(Util.minButtonX + playerPanel.getWidth() + 25, Util.minButtonY, cpuPanel.getWidth(),
				cpuPanel.getHeight());
		this.add(cpuPanel);
		
		boatPanel.setBounds(Util.minButtonX, playerPanel.getHeight() + 5 + Util.minButtonY, boatPanel.getWidth(), boatPanel.getHeight());
		this.add(boatPanel);
	}

	private void finish_buttonAction() {
		frame.battleFinished();
	}
	
	public void setBoat(BoatType boat, int posx, int posy, boolean player) {
		if(player) {
			
		} else {
			
		}
	}

}
