package gui.board;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.Attack;
import game.AttackStatus;
import game.GameManager;
import game.Util;
import game.boats.BoatType;
import game.boats.Power;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public class PlayerBoard extends Board {

	private JLabel powerLabel = new JLabel("Power: NONE");

	private ActionListener listener = e -> {
		if (!parentPanel.getGm().hasGameStarted()) {
			buttonAction(e);
		}
	};

	public PlayerBoard(BattlePanel panel) {
		super("PLAYER", panel);
		this.setButtonsListener(listener);
		parentPanel = panel;

		powerLabel.setBounds(60, 0, 200, 25);

		this.add(powerLabel);
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

			Point head = getBoatHead(b.getPosX(), b.getPosY());
			b = buttons[head.x][head.y];

			removeButtonBoat(b);
			b.getBoatType().quantity++;
			gm.setSelectedBoatType(b.getBoatType());
		}
		gm.updateQuantities();
	}

	public AttackStatus receiveCpuAttack(Attack attack) {
		AttackStatus as = super.receiveAttack(attack);
		if (as == AttackStatus.INVALIDATTACK) {
			return as;
		}

		parentPanel.getGm().resetCpuPower();

		updatePowerLabel();

		if (checkFinish()) {
			parentPanel.getGm().finishGame(Util.cpuWin);
		}

		return as;
	}

	public Power getActivePower() {
		return activePower;
	}

	private void updatePowerLabel() {
		powerLabel.setText("Power: " + activePower.name());
	}

}
