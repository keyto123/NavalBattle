package game.battle;

public enum AttackStatus {
	INVALIDATTACK(-1), MISS(0), HIT(1);
	
	public final int value;
	private AttackStatus(int value) {
		this.value = value;
	}
}
