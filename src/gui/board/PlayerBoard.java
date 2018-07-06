package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import game.GameManager;
import game.boats.BoatType;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
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
		GameManager gm = parentPanel.getGm();
		BoatType bt = gm.getSelectedBoatType();
		BoardButton b = (BoardButton) e.getSource();

		if (b.hasBoat() == false) {

			if (!this.setButtonBoat(b, bt) && bt != null) {
				JOptionPane.showMessageDialog(this, "Invalid position");
				bt.quantity++;
			}
			
		} else {
			while (!b.isHead()) {
				b = buttons[b.getPosX()][b.getPosY() - 1];
			}
			removeButtonBoat(b);
			b.getBoatType().quantity++;

			if (bt != null) {
				bt.quantity++;
			}
		}
		gm.updateQuantities();
		gm.setSelectedBoatType(null);
	}

}
