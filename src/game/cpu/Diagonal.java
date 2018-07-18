package game.cpu;

enum Diagonal {

	SE(1, 1), NE(-1, 1), SW(1, -1), NW(-1, -1);

	static final Diagonal values[] = values();

	public final int x, y;

	private Diagonal(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
