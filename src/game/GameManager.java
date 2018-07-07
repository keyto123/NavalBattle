package game;

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
	
	public BoatType getSelectedBoatType() {
		return selectedBoat;
	}
	
	public void updateQuantities() {
		boatPanel.updateQuantities();
	}
	
	public boolean hasGameStarted() {
		return gameStarted;
	}
	
	public boolean cpuAttack(int x, int y) {
		return playerBoard.receiveAttack(x, y);
	}
	
	public void setAttackSuccess() {
		
	}
}
