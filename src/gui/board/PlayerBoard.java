package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import game.boats.BoatType;
import gui.panel.BattlePanel;

public class PlayerBoard extends Board {
	
	private BattlePanel parentPanel;
	
	private ActionListener listener = e -> {
		buttonAction(e);
	};
	
	public PlayerBoard(BattlePanel panel) {
		super("PLAYER");
		this.setButtonsListener(listener);
		parentPanel = panel;
	}

	private void buttonAction(ActionEvent e) {
		BoatType bt = parentPanel.getGm().getSelectedBoatType();
		BoardButton b = (BoardButton) e.getSource();
		if(b.getIcon() == null && bt != null) {
			ImageIcon parts[] = bt.getIconParts();
			
			int x = b.getPosX(), y = b.getPosY();
			
			if(buttons[0].length - y < parts.length) {
				JOptionPane.showMessageDialog(this, "You fucking troll");
				return;
			}
			
			for(int i = 0; i < parts.length; i++) {
				if(buttons[x][y + 1].getIcon() != null) {
					JOptionPane.showMessageDialog(this, "Die man, stop trolling");
					return;
				}
			}
			
			for(int i = 0; i < parts.length; i++) {
				buttons[x][y + i].setIcon(parts[i]);
			}			
		} else {
			//b.setEnabled(false);			
		}
	}

}
