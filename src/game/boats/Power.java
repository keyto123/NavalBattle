package game.boats;

public enum Power {
	NONE(0), CROSS(1), SQUARE(2);
	
	private int power;
	
	public int getPower() {
		return power;
	}

	private Power(int power) {
		this.power = power;
	}
}
