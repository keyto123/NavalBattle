package gui.board;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import game.GameManager;
import game.Util;
import game.boats.BoatStorage;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public class CpuBoard extends Board {

	private BattlePanel parentPanel;

	private Point firstHit = null;
	private Point lastAttackPoint = null;
	private boolean useSmartAttack = false;
	private boolean smartAttackFail = false;

	private Random rand = new Random();
	private BoatStorage storage = new BoatStorage();
	private GameManager gm;

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
		gm = parentPanel.getGm();
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

		if (checkFinish()) {
			gm.finishGame(false);
		} else {
			cpuAttack();
		}

	}

	// TODO: use logic
	private void cpuAttack() {

		if (useSmartAttack) {
			smartAttack();
		} else {
			randomAttack();
		}

	}

	private void randomAttack() {
		int x, y, result;
		do {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			result = gm.cpuAttack(x, y);
		} while (result == Util.INVALIDATTACK);

		if (result == Util.HIT) {
			useSmartAttack = true;
			firstHit = new Point(x, y);
			lastAttackPoint = new Point(firstHit);
		}
	}

	private void smartAttack() {
		int result;
		if (smartAttackFail == false) {
			lastAttackPoint.setLocation(lastAttackPoint.x, lastAttackPoint.y + 1);
			result = gm.cpuAttack(lastAttackPoint.x, lastAttackPoint.y);

			if (result == Util.INVALIDATTACK || result == Util.MISS) {
				smartAttackFail = true;
				lastAttackPoint.setLocation(firstHit.x, firstHit.y);
			}
			
			if (result == Util.INVALIDATTACK) {
				smartAttack();
			}
			
		} else {
			lastAttackPoint.setLocation(lastAttackPoint.x, lastAttackPoint.y - 1);
			result = gm.cpuAttack(lastAttackPoint.x, lastAttackPoint.y);

			// Reset stuff since we gonna use random attack again
			if (result == Util.INVALIDATTACK || result == Util.MISS) {
				useSmartAttack = false;
				firstHit = null;
				lastAttackPoint = null;
				smartAttackFail = false;
			}

			// shouldn't waste a movement, so random attack
			if (result == Util.INVALIDATTACK) {
				randomAttack();
			}

		}
	}
}
