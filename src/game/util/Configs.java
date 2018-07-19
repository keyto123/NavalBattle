package game.util;

import game.Difficulty;

public final class Configs {

	private Configs() {}

	public static final int minButtonX = 5;
	public static final int minButtonY = 40;
	public static final int commonButtonWidth = 100;
	public static final int buttonWidth = 25;
	public static final int buttonHeight = 25;

	public static Difficulty gameDifficulty = Difficulty.EASY;

	// You might not want to use less than 6
	public static int boardSize = 6;

	public static final boolean playerWinFlag = true;
	public static final boolean cpuWinFlag = false;

	// Actual max size of a boat
	public static int boatLengthLimit = 3;

}
