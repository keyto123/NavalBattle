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
	
	public GameManager(PlayerBoard playerBoard, CpuBoard cpuBoard, BoatPanel boatPanel) {
		this.playerBoard = playerBoard;
		this.cpuBoard = cpuBoard;
		this.boatPanel = boatPanel;
	}

	public void setSelectedBoatType(BoatType boatType) {
		this.selectedBoat = boatType;
	}
	
	public BoatType getSelectedBoatType() {
		return selectedBoat;
	}
	
	
}
