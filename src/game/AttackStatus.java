package game;

public enum AttackStatus {
	INVALIDATTACK(-1), MISS(0), HIT(1);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	private AttackStatus(int value) {
		this.value = value;
	}
}
