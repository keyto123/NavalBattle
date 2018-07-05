package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;

import game.boats.BoatUtils;
import gui.panel.BattlePanel;

public class CpuBoard extends Board {

	private BattlePanel mainPanel;

	private Random rand = new Random();

	private ActionListener listener = e -> {
		buttonAction(e);
	};

	public CpuBoard(BattlePanel panel) {
		super("CPU");
		this.setButtonsListener(listener);
		this.initBoats();
		mainPanel = panel;
		// this.disableButtons();
	}

	private void initBoats() {
		// not the real functionality
		int x = 0, y = 0;
		for (int i = 0; i < 2; i++) {
			do {
				x = rand.nextInt(10);
				y = rand.nextInt(9);
			} while (!this.setButtonBoat(buttons[x][y], BoatUtils.boats[0]));
		}
	}

	private void buttonAction(ActionEvent e) {
		BoardButton b = (BoardButton) e.getSource();
		if (b.hasBoat()) {
			JOptionPane.showMessageDialog(this, "Very good, little kid");
		}
		b.setEnabled(false);
	}
}
