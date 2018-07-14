package game.boats;

import javax.swing.ImageIcon;

import game.Util;

public class BoatType {
	
	private ImageIcon fullBoat;
	private ImageIcon destroyedBoat;
	private ImageIcon boatParts[];
	private ImageIcon boatDestroyedParts[];
	
	private int length;
	public int quantity;

	public BoatType(String name, int length) {
		this.length = length;
		boatParts = new ImageIcon[length];
		boatDestroyedParts = new ImageIcon[length];
		quantity = 5 - length;

		fullBoat = Util.getIcon(name + "/normal.png");
		destroyedBoat = Util.getIcon(name + "/destroyed.png");

		boatParts = Util.getImageParts(fullBoat, length);
		boatDestroyedParts = Util.getImageParts(destroyedBoat, length);
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
