package gui.board;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardButton extends JButton {

	private int posX;
	private int posY;
	private boolean boat;

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean hasBoat() {
		return boat;
	}

	public BoardButton(int x, int y, ImageIcon enabledIcon, ImageIcon disabledIcon, boolean boat) {
		this.posX = x;
		this.posY = y;
		this.setIcon(enabledIcon);
		this.setDisabledIcon(disabledIcon);
		this.boat = boat;
		this.setBorder(null);
	}
}
