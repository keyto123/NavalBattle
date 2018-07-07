package gui.board;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.Util;
import game.boats.BoatType;

@SuppressWarnings("serial")
public class BoardButton extends JButton {
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = Util.waterIcon.getImage();
		g.drawImage(img, 0, 0, Util.buttonWidth, Util.buttonHeight, null);
		if(this.isEnabled()) {
			//g.drawImage(this.getIcon()., 0, 0, Util.buttonWidth, Util.buttonHeight, null);
			this.getIcon().paintIcon(this, g, 0, 0);
		} else {
			this.getDisabledIcon().paintIcon(this, g, 0, 0);
		}
	}
	
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
		this.setContentAreaFilled(false);
		this.setBorder(null);
	}
}
