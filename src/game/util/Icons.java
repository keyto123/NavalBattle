package game.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @Reference 
 * 		-	<a href="https://stackoverflow.com/questions/9864267/loading-image-resource">Image from resource</a><br>
 * 		-	<a href="https://stackoverflow.com/questions/2386064/how-do-i-crop-an-image-in-java">Image Cropping</a><br>
 */

public final class Icons {
	
	private Icons() {}
	
	public static final ImageIcon waterIcon = getIcon("water.jpg");
	public static final ImageIcon disabledWaterIcon = getIcon("disabledWater.jpg");
	
	public static ImageIcon getIcon(String name) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(Icons.class.getResource("/" + name)));
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
			parts[i] = new ImageIcon(dest.getSubimage(i * Configs.buttonWidth, 0, Configs.buttonWidth, Configs.buttonHeight));
		}

		return parts;
	}
	
}
