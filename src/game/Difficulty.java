package game;

public enum Difficulty {
	EASY(1), MEDIUM(2), HARD(3);

	private int difficulty;

	public int getDifficulty() {
		return difficulty;
	}

	private Difficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
