package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
	
	public static final boolean PlayerWin = true;
	public static final boolean CpuWin = false;
	
	// actual max size of a boat
	public static final int BOATLENGTHLIMIT = 4;

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

	public static ImageIcon[] getImageParts(ImageIcon src, int quantity) {
		Image img = src.getImage();
		ImageIcon parts[] = new ImageIcon[quantity];

		BufferedImage dest = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = dest.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		for (int i = 0; i < quantity; i++) {
			parts[i] = new ImageIcon(dest.getSubimage(i * Util.buttonWidth, 0, Util.buttonWidth, Util.buttonHeight));
		}

		return parts;
	}
}
