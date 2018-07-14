package game;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Util {

	private Util() {}

	public static final int minButtonX = 5;
	public static final int minButtonY = 40;
	public static final int buttonWidth = 25;
	public static final int buttonHeight = 25;
	public static final int boardSize = 10;
	
	public static final int INVALIDATTACK = -1;
	public static final int MISS = 0;
	public static final int HIT = 1;
	
	public static final ImageIcon waterIcon = Util.getIcon("water.jpg");
	public static final ImageIcon disabledWaterIcon = Util.getIcon("disabledWater.jpg");

	public static ImageIcon getIcon(String name) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(Util.class.getResource("/" + name)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}
}
