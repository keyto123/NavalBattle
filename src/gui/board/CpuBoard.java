package gui.board;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import game.Attack;
import game.AttackStatus;
import game.GameManager;
import game.Util;
import game.boats.BoatStorage;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public class CpuBoard extends Board {

	private Point firstHit = new Point(0, 0);
	private Point lastAttackPoint = new Point(0, 0);
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
		super("CPU", panel);
		this.setButtonsListener(listener);
		parentPanel = panel;
	}

	public void initBoats() {
		gm = parentPanel.getGm();
		int x = 0, y = 0;
		for (int i = 0; i < storage.getLength(); i++) {
			for (int j = 0; j < storage.getBoatType(i).quantity; j++) {
				do {
					x = rand.nextInt(Util.boardSize);
					y = rand.nextInt(Util.boardSize);
				} while (!this.setButtonBoat(buttons[x][y], storage.getBoatType(i), false));
			}
		}
	}

	private void buttonAction(ActionEvent e) {
		BoardButton b = (BoardButton) e.getSource();
		Point point = new Point(b.getPosX(), b.getPosY());
		
		super.receiveAttack(new Attack(point, gm.getPlayerPower()));
		gm.resetPlayerPower();

		if (checkFinish()) {
			gm.finishGame(Util.playerWin);
		} else {
			cpuAttack();
		}

	}

	private void cpuAttack() {

		if (useSmartAttack) {
			smartAttack();
		} else {
			randomAttack();
		}

	}

	private void randomAttack() {
		AttackStatus result = null;
		Attack attack = new Attack(0, 0, activePower);
		do {
			attack.point.x = rand.nextInt(10);
			attack.point.y = rand.nextInt(10);
			result = gm.cpuAttack(attack);
		} while (result == AttackStatus.INVALIDATTACK);

		if (result == AttackStatus.HIT) {
			useSmartAttack = true;
			firstHit.setLocation(attack.point);
			lastAttackPoint.setLocation(firstHit);
		}
	}

	private void smartAttack() {
		AttackStatus result;
		if (smartAttackFail == false) {
			Point nextAttackPoint = new Point(lastAttackPoint.x, lastAttackPoint.y + 1);

			Attack attack = new Attack(nextAttackPoint, activePower);
			result = gm.cpuAttack(attack);

			if (result == AttackStatus.INVALIDATTACK || result == AttackStatus.MISS) {
				smartAttackFail = true;
				lastAttackPoint.setLocation(firstHit);
			} else {
				lastAttackPoint.setLocation(nextAttackPoint);
			}

			if (result == AttackStatus.INVALIDATTACK) {
				smartAttack();
			}

		} else {
			Point nextAttackPoint = new Point(lastAttackPoint.x, lastAttackPoint.y - 1);
			Attack attack = new Attack(nextAttackPoint, activePower);
			result = gm.cpuAttack(attack);

			// Reset stuff since we gonna use random attack again
			if (result == AttackStatus.INVALIDATTACK || result == AttackStatus.MISS) {
				useSmartAttack = false;
				smartAttackFail = false;
			} else {
				lastAttackPoint.setLocation(nextAttackPoint);
			}

			// shouldn't waste a movement, so random attack
			if (result == AttackStatus.INVALIDATTACK) {
				randomAttack();
			}

		}
	}
}
