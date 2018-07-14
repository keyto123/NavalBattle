package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import game.GameManager;
import game.Util;
import game.boats.BoatType;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public class PlayerBoard extends Board {

	private BattlePanel parentPanel;

	private ActionListener listener = e -> {
		if (!parentPanel.getGm().hasGameStarted()) {
			buttonAction(e);
		}
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
			if (bt == null) {
				return;
			}
			if (!this.setButtonBoat(b, bt, true) && bt != null) {
				JOptionPane.showMessageDialog(this, "Invalid position");
			} else {
				bt.quantity--;
			}
			if (bt.quantity == 0) {
				gm.setSelectedBoatType(null);
			}
		} else {
			while (!b.isHead()) {
				b = buttons[b.getPosX()][b.getPosY() - 1];
			}
			removeButtonBoat(b);
			b.getBoatType().quantity++;
			gm.setSelectedBoatType(b.getBoatType());
		}
		gm.updateQuantities();
	}

	public int receiveAttack(int x, int y) {
		if(!checkValidPosition(x, y)) {
			return Util.INVALIDATTACK;
		}
		if (x >= buttons.length || y >= buttons.length || !buttons[x][y].isEnabled()) {
			return Util.INVALIDATTACK;
		} else {
			buttons[x][y].setEnabled(false);
			if (checkFinish()) {
				parentPanel.getGm().finishGame(true);
			}
			if(buttons[x][y].hasBoat()) {
				return Util.HIT;
			}
			return Util.MISS;
		}
	}
	
	private boolean checkValidPosition(int x, int y) {
		return !(x < 0 || x >= buttons.length || y < 0 || y >= buttons.length || !buttons[x][y].isEnabled());
	}

}
