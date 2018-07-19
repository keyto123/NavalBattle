package game.boats;

import javax.swing.ImageIcon;

import game.util.Configs;
import game.util.Icons;

public class BoatType {

	private ImageIcon fullBoat;
	private ImageIcon destroyedBoat;
	private ImageIcon boatParts[];
	private ImageIcon boatDestroyedParts[];

	private int length;
	public int quantity;

	private Power power;

	public BoatType(BoatTypes type) {
		this.length = type.getLength();
		boatParts = new ImageIcon[length];
		boatDestroyedParts = new ImageIcon[length];

		quantity = (Configs.boatLengthLimit - length) + 1;

		this.power = type.getPower();

		fullBoat = Icons.getIcon(type.getName() + "/normal.png");
		destroyedBoat = Icons.getIcon(type.getName() + "/destroyed.png");

		boatParts = Icons.getImageParts(fullBoat, length);
		boatDestroyedParts = Icons.getImageParts(destroyedBoat, length);
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

	public Power getPower() {
		return power;
	}
}
