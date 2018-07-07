package gui.board;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;

import game.boats.BoatStorage;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public class CpuBoard extends Board {

	private BattlePanel parentPanel;

	private Point firstHit;
	private boolean lastAttackSuccess = false;

	private Random rand = new Random();
	private BoatStorage storage = new BoatStorage();

	private ActionListener listener = e -> {
		if (parentPanel.getGm().hasGameStarted()) {
			buttonAction(e);
		}
	};

	public CpuBoard(BattlePanel panel) {
		super("CPU");
		this.setButtonsListener(listener);
		parentPanel = panel;
	}

	public void initBoats() {
		int x = 0, y = 0;
		for (int i = 0; i < storage.getLength(); i++) {
			for (int j = 0; j < storage.getBoatType(i).quantity; j++) {
				do {
					x = rand.nextInt(10);
					y = rand.nextInt(10);
				} while (!this.setButtonBoat(buttons[x][y], storage.getBoatType(i), false));
			}
		}
	}

	private void buttonAction(ActionEvent e) {
		BoardButton b = (BoardButton) e.getSource();
		b.setEnabled(false);

		if(checkFinish()) {
			parentPanel.getGm().finishGame(false);
		} else {
			cpuAttack();
		}
		

	}

	// TODO: use logic
	private void cpuAttack() {
		int x, y;
		do {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
		} while (parentPanel.getGm().cpuAttack(x, y));
	}

	public void setAttackSuccess() {

	}
}
