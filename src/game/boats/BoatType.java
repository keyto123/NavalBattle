package game.boats;

import javax.swing.ImageIcon;

import game.Util;

public class BoatType {
	
	private ImageIcon fullBoat;
	private ImageIcon boatParts[];
	private ImageIcon boatDestroyedParts[];
	
	private int length;
	public int quantity;

	public BoatType(String name, int length) {
		this.length = length;
		boatParts = new ImageIcon[length];
		boatDestroyedParts = new ImageIcon[length];
		quantity = 5 - length;

		fullBoat = Util.getIcon(name + ".png");

		for (int i = 1; i <= length; i++) {
			boatParts[i - 1] = Util.getIcon(name + "/" + i + ".png");
			boatDestroyedParts[i - 1] = Util.getIcon(name + "/boat_explosion/" + i + ".png");
		}
	}

	public ImageIcon getIcon() {
		return fullBoat;
	}

	public ImageIcon[] getIconParts() {
		return boatParts;
	}
	
	public ImageIcon[] getIconDestroyedParts() {
		return boatDestroyedParts;
	}

	public int getLength() {
		return length;
	}
}
