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

		if (bt == null) {
			return;
		}

		if (b.hasBoat() == false) {
			if(checkValidBoatPosition(b, bt)) {
				this.setButtonBoard(b, bt);
			}
		} else {
			BoatType curBoat = b.getBoatType();
			int length = curBoat.getLength();

			int x = b.getPosX(), y = b.getPosY();

			for (int i = 0; i < length; i++) {
				buttons[x][y + i].setHasBoat(false);
				buttons[x][y + i].setIcon(null);
				buttons[x][y + i].setDisabledIcon(null);
			}

			parentPanel.getGm().setSelectedBoatType(b.getBoatType());
		}
	}
	
	private void setButtonBoard(BoardButton b, BoatType bt) {
		ImageIcon parts[] = bt.getIconParts();
		int x = b.getPosX(), y = b.getPosY();
		
		for (int i = 0; i < parts.length; i++) {
			buttons[x][y + i].setIcon(parts[i]);
			buttons[x][y + i].setDisabledIcon(null);
			buttons[x][y + i].setHasBoat(true);
		}
		b.setBoatType(bt);
	}
	
	private boolean checkValidBoatPosition(BoardButton b, BoatType bt) {
		ImageIcon parts[] = bt.getIconParts();
		int x = b.getPosX(), y = b.getPosY();
		
		if (buttons[0].length - y < parts.length) {
			JOptionPane.showMessageDialog(this, "You fucking troll");
			return false;
		}

		for (int i = 0; i < parts.length; i++) {
			if (buttons[x][y + 1].getIcon() != null) {
				JOptionPane.showMessageDialog(this, "Die man, stop trolling");
				return false;
			}
		}
		return true;
	}

}
