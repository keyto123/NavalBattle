package game;

import javax.swing.JOptionPane;

import game.boats.BoatType;
import gui.board.CpuBoard;
import gui.board.PlayerBoard;
import gui.panel.BoatPanel;

public final class GameManager {

	private BoatPanel boatPanel;
	private PlayerBoard playerBoard;
	private CpuBoard cpuBoard;
	private BoatType selectedBoat;
	private boolean gameStarted = false;
	
	public GameManager(PlayerBoard playerBoard, CpuBoard cpuBoard, BoatPanel boatPanel) {
		this.playerBoard = playerBoard;
		this.cpuBoard = cpuBoard;
		this.boatPanel = boatPanel;
	}

	public void setSelectedBoatType(BoatType boatType) {
		this.selectedBoat = boatType;
	}
	
	public void startGame() {
		gameStarted = true;
		cpuBoard.initBoats();
	}
	
	public void finishGame(boolean player) {
		if(!player) {
			JOptionPane.showMessageDialog(playerBoard, "You win!");
		} else {
			JOptionPane.showMessageDialog(cpuBoard, "You Lose!");
		}
		gameStarted = false;
		cpuBoard.disableButtons();
		playerBoard.disableButtons();
	}
	
	public BoatType getSelectedBoatType() {
		return selectedBoat;
	}
	
	public void updateQuantities() {
		boatPanel.updateQuantities();
	}
	
	public boolean hasGameStarted() {
		return gameStarted;
	}
	
	public int cpuAttack(int x, int y) {
		return playerBoard.receiveAttack(x, y);
	}
	
	public void setAttackSuccess() {
		
	}
}
