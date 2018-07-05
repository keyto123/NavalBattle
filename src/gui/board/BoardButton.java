package gui.board;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.boats.BoatType;

public class BoardButton extends JButton {

	private int posX;
	private int posY;
	private boolean hasBoat = false;
	private boolean isHead = false;
	private BoatType boatType = null;

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean hasBoat() {
		return hasBoat;
	}
	
	public void setHasBoat(boolean hasBoat) {
		this.hasBoat = hasBoat;
	}

	public BoatType getBoatType() {
		return boatType;
	}

	public void setBoatType(BoatType boatType) {
		this.boatType = boatType;
	}
	
	public boolean isHead() {
		return isHead;
	}

	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}

	public BoardButton(int x, int y, ImageIcon enabledIcon, ImageIcon disabledIcon, boolean boat) {
		this.posX = x;
		this.posY = y;
		this.setIcon(enabledIcon);
		this.setDisabledIcon(disabledIcon);
		this.hasBoat = boat;
		this.setBorder(null);
	}
}
