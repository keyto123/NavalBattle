package game.boats;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoatButton extends JButton {
	
	BoatType boatType;
	
	public BoatButton(BoatType boat) {
		this.boatType = boat;
		
		ImageIcon icon = boat.getIcon();
		this.setIcon(icon);
		
		int height = icon.getIconHeight();
		
		this.setSize(25 * boat.getLength(), height);
	}
}
