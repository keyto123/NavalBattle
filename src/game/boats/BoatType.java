package game.boats;

import javax.swing.ImageIcon;

import game.Util;

public abstract class BoatType {
	private ImageIcon fullBoat;
	private ImageIcon boatParts[];
	private int length;
	
	public BoatType(String name, int length) {
		this.length = length;
		boatParts = new ImageIcon[length];
		
		fullBoat = Util.getIcon(name + ".png");
		
		for(int i = 1; i <= length; i++) {
			boatParts[i - 1] = Util.getIcon(name + "/" + i + ".png");
		}
	}
	
	public ImageIcon getIcon() {
		return fullBoat;
	}
	
	public ImageIcon[] getIconParts() {
		return boatParts;
	}
	
	public int getLength() {
		return length;
	}
}
