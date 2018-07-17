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
	
	private Power power;

	public BoatType(BoatTypes type) {
		this.length = type.getLength();
		boatParts = new ImageIcon[length];
		boatDestroyedParts = new ImageIcon[length];
		
		quantity = (Util.BOATLENGTHLIMIT - length) + 1;

		this.power = type.getPower();
		
		fullBoat = Util.getIcon(type.getName()+ "/normal.png");
		destroyedBoat = Util.getIcon(type.getName() + "/destroyed.png");

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
	
	public Power getPower() {
		return power;
	}
}
