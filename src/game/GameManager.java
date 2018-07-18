package game;

import java.awt.Point;

import javax.swing.JOptionPane;

import game.boats.BoatType;
import game.boats.Power;
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

	public BoatType getSelectedBoatType() {
		return selectedBoat;
	}

	public void startGame() {
		gameStarted = true;
		cpuBoard.initBoats();
	}

	public void finishGame(boolean winner) {
		if (Util.playerWin == winner) {
			JOptionPane.showMessageDialog(playerBoard, "You win!");
		} else {
			JOptionPane.showMessageDialog(cpuBoard, "You Lose!");
		}
		gameStarted = false;
		cpuBoard.disableButtons();
		playerBoard.disableButtons();
	}

	public void updateQuantities() {
		boatPanel.updateQuantities();
	}

	public boolean hasGameStarted() {
		return gameStarted;
	}

	public AttackStatus cpuAttack(Attack attack) {
		return playerBoard.receiveCpuAttack(attack);
	}
	
	public Power getPlayerPower() {
		return playerBoard.getActivePower();
	}
	
	public Point cpuSmartAttackPoint() {
		return playerBoard.possibleSmartAttackPoint();
	}
	
	public boolean cpuPossibleDiagonalMovement(Point p) {
		return playerBoard.checkPossibleDiagonalAttack(p);
	}

	public void resetCpuPower() {
		cpuBoard.resetPower();
	}
	
	public void resetPlayerPower() {
		playerBoard.resetPower();
	}
}
