package game.boats;

public enum BoatTypes {
	
	SMALLBOAT(2, "boat1", Power.NONE), MEDIUMBOAT(3, "boat2", Power.CROSS), LARGEBOAT(4, "boat3", Power.SQUARE);
	
	private int length;
	private String name;
	private Power power;
	
	public int getLength() {
		return length;
	}
	
	public String getName() {
		return name;
	}
	
	public Power getPower() {
		return power;
	}
	
	private BoatTypes(int length, String name, Power power) {
		this.length = length;
		this.name = name;
		this.power = power;
	}
}
