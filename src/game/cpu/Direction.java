package game.cpu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum Direction {

	N(-1, 0), NE(-1, 1), E(0, 1), SE(1, 1), S(1, 0), SW(1, -1), W(0, -1), NW(-1, -1);

	public static final List<Direction> values = Collections.unmodifiableList(Arrays.asList(values()));

	public final int x, y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static final int diagonalIndex(int i) {
		return (i * 2) + 1;
	}
}
