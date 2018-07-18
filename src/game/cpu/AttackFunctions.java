package game.cpu;

import java.awt.Point;
import java.util.Random;

import game.Attack;
import game.AttackStatus;
import game.Difficulty;
import game.GameManager;
import game.Util;
import game.boats.Power;

public class AttackFunctions {

	private Point firstHit = new Point(0, 0);
	private Point lastAttackPoint = new Point(0, 0);
	private boolean useSmartAttack = false;
	private boolean smartAttackFail = false;
	private boolean firstAttack = true;
	private GameManager gm;
	private Power activePower;
	private Random rand = new Random();

	public AttackFunctions(GameManager gm) {
		this.gm = gm;
	}

	public void cpuAttack(Power power) {
		this.activePower = power;

		switch (Util.gameDifficulty) {
		case EASY:
			randomAttack();
			break;

		case MEDIUM:
			if (useSmartAttack) {
				smartAttack();
			} else {
				randomAttack();
			}
			break;

		case HARD:
			if (useSmartAttack) {
				smartAttack();
			} else {
				Point point = gm.cpuSmartAttackPoint();
				if (point == null) {
					randomAttack();
				} else {
					smartAttack2(point);
				}
			}
			break;

		case VERYHARD:
			if (useSmartAttack) {
				smartAttack();
			} else {
				Point point = gm.cpuSmartAttackPoint();
				if (point == null) {
					guidedAttack();
				} else {
					smartAttack2(point);
				}
			}
			break;
		}
		firstAttack = false;
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
		}
		lastAttackPoint.setLocation(attack.point);
	}

	private void guidedAttack() {
		int direction;
		Diagonal diagonal;
		Attack attack;
		AttackStatus status;
		Point nextAttackPoint = new Point(0, 0);

		if (firstAttack || !gm.cpuPossibleDiagonalMovement(lastAttackPoint)) {
			randomAttack();
			return;
		}

		do {
			direction = rand.nextInt(4);
			diagonal = Diagonal.values[direction];
			int nextX = lastAttackPoint.x + diagonal.x;
			int nextY = lastAttackPoint.y + diagonal.y;
			nextAttackPoint.setLocation(nextX, nextY);
			attack = new Attack(nextAttackPoint, activePower);
		} while ((status = gm.cpuAttack(attack)) == AttackStatus.INVALIDATTACK);

		switch (status) {
		case INVALIDATTACK:
			break;

		case HIT:
			useSmartAttack = true;
			firstHit.setLocation(attack.point);
		case MISS:
			lastAttackPoint.setLocation(attack.point);
			break;
		}
	}

	private void smartAttack() {
		AttackStatus result;
		if (smartAttackFail == false) {
			Point nextAttackPoint = new Point(lastAttackPoint.x, lastAttackPoint.y + 1);
			Attack attack = new Attack(nextAttackPoint, activePower);

			if (Util.gameDifficulty == Difficulty.VERYHARD && !gm.cpuAttackBoat(attack.point)) {
				smartAttackFail = true;
				lastAttackPoint.setLocation(firstHit);
				smartAttack();
				return;
			}

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

			if (Util.gameDifficulty == Difficulty.VERYHARD && !gm.cpuAttackBoat(attack.point)) {
				useSmartAttack = false;
				smartAttackFail = false;
				cpuAttack(activePower);
				return;
			}

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
				cpuAttack(activePower);
			}

		}
	}

	private void smartAttack2(Point point) {
		firstHit.setLocation(point);
		lastAttackPoint.setLocation(point);
		useSmartAttack = true;
		smartAttack();
	}
}
