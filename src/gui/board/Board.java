package gui.board;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Attack;
import game.AttackStatus;
import game.Difficulty;
import game.Util;
import game.boats.BoatType;
import game.boats.Power;
import gui.panel.BasePanel;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public abstract class Board extends BasePanel {

	protected BoardButton buttons[][] = new BoardButton[Util.boardSize][Util.boardSize];
	protected Power activePower = Power.NONE;
	protected BattlePanel parentPanel;

	private JLabel title = new JLabel();

	protected Board(String name, BattlePanel parentPanel) {
		super(Util.boardSize * 30, Util.boardSize * 30 + 30, null);
		this.title.setBounds(0, 0, 100, 25);
		this.title.setText(name);
		this.add(this.title);
		this.parentPanel = parentPanel;

		this.initButtons();
	}

	protected void initButtons() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new BoardButton(i, j, null, null, false);
				buttons[i][j].setBounds(j * 27, 30 + i * 27, Util.buttonWidth, Util.buttonHeight);
				buttons[i][j].setBorder(null);
				buttons[i][j].setIcon(Util.waterIcon);
				buttons[i][j].setDisabledIcon(Util.disabledWaterIcon);
				buttons[i][j].setBackground(Color.BLUE);
				this.add(buttons[i][j]);
			}
		}
	}

	protected void setButtonsListener(ActionListener listener) {
		for (BoardButton[] row : buttons) {
			for (BoardButton col : row) {
				col.addActionListener(listener);
			}
		}
	}

	public void enableButtons() {
		for (BoardButton[] row : buttons) {
			for (BoardButton col : row) {
				col.setEnabled(true);
			}
		}
	}

	public void disableButtons() {
		for (BoardButton[] row : buttons) {
			for (BoardButton col : row) {
				col.setEnabled(false);
			}
		}
	}

	public boolean hasBoat(int x, int y) {
		if (!checkValidPosition(x, y)) {
			return false;
		}
		return buttons[x][y].hasBoat();
	}

	public boolean hasDisabledBoat(int x, int y) {
		if (hasBoat(x, y)) {
			return !buttons[x][y].isEnabled();
		}
		return false;
	}
	
	public boolean hasDisabledBoat(Point p) {
		return hasDisabledBoat(p.x, p.y);
	}

	private boolean hasEnabledBoat(int x, int y) {
		if (hasBoat(x, y)) {
			return buttons[x][y].isEnabled();
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean hasEnabledBoat(Point p) {
		return hasEnabledBoat(p.x, p.y);
	}

	protected boolean setButtonBoat(BoardButton b, BoatType bt, boolean player) {
		if (bt == null) {
			return false;
		}

		if (!checkValidBoatPosition(b, bt)) {
			return false;
		}

		ImageIcon parts[] = bt.getIconParts();
		ImageIcon destroyedParts[] = bt.getIconDestroyedParts();
		int x = b.getPosX(), y = b.getPosY();

		buttons[x][y].setHead(true);
		for (int i = 0; i < parts.length; i++) {
			if (player) {
				buttons[x][y + i].setIcon(parts[i]);
			}
			buttons[x][y + i].setDisabledIcon(destroyedParts[i]);
			buttons[x][y + i].setHasBoat(true);
			buttons[x][y + i].setBoatType(bt);
		}

		return true;
	}

	protected boolean removeButtonBoat(BoardButton b) {
		BoatType bt = b.getBoatType();
		if (bt == null) {
			return false;
		} else {
			b.setHead(false);
			int x = b.getPosX(), y = b.getPosY();
			for (int i = 0; i < bt.getLength(); i++) {
				buttons[x][y + i].setIcon(Util.waterIcon);
				buttons[x][y + i].setDisabledIcon(null);
				buttons[x][y + i].setHasBoat(false);
			}
			return true;
		}
	}

	protected boolean checkValidBoatPosition(BoardButton b, BoatType bt) {
		ImageIcon parts[] = bt.getIconParts();
		int x = b.getPosX(), y = b.getPosY();

		if (buttons[0].length - y < parts.length) {
			return false;
		}

		for (int i = 0; i < parts.length; i++) {
			if (buttons[x][y + i].hasBoat() == true) {
				return false;
			}
		}
		return true;
	}

	protected boolean checkFinish() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				if (buttons[i][j].isEnabled() && buttons[i][j].hasBoat()) {
					return false;
				}
			}
		}
		return true;
	}
	
	protected boolean checkValidPosition(int x, int y) {
		return !(x < 0 || x >= Util.boardSize || y < 0 || y >= Util.boardSize);
	}

	protected boolean checkValidAttackPosition(int x, int y) {
		return checkValidPosition(x, y) && buttons[x][y].isEnabled();
	}

	protected boolean checkValidPosition(Point point) {
		return this.checkValidAttackPosition(point.x, point.y);
	}

	protected Point getBoatHead(Point point) {
		return getBoatHead(point.x, point.y);
	}

	protected Point getBoatHead(int x, int y) {
		if (!buttons[x][y].hasBoat()) {
			return null;
		}
		while (!buttons[x][y].isHead()) {
			y--;
		}
		return new Point(x, y);
	}

	protected boolean checkExplodedBoat(Point point) {
		Point head = getBoatHead(point);
		if (head == null) {
			return false;
		}

		int length = buttons[head.x][head.y].getBoatType().getLength();

		for (int i = 0; i < length; i++) {
			if (buttons[head.x][head.y + i].isEnabled()) {
				return false;
			}
		}
		return true;
	}

	protected AttackStatus receiveAttack(Attack attack) {
		if (!checkValidPosition(attack.point)) {
			return AttackStatus.INVALIDATTACK;
		}

		int x = attack.point.x, y = attack.point.y;
		buttons[x][y].setEnabled(false);
		useActivePower(attack);

		if (checkExplodedBoat(attack.point)) {
			activePower = buttons[x][y].getBoatType().getPower();
		}

		if (buttons[x][y].hasBoat()) {
			return AttackStatus.HIT;
		} else {
			return AttackStatus.MISS;
		}
	}

	protected void useActivePower(Attack attack) {
		switch (attack.power) {
		case NONE:
			return;

		case CROSS:
			useCrossPower(attack.point);
			break;

		case SQUARE:
			useSquarePower(attack.point);
			break;
		}
	}

	protected void useCrossPower(Point point) {

		for (int i = -1; i < 2; i++) {
			receiveAttack(new Attack(point.x + i, point.y + i));
			receiveAttack(new Attack(point.x - i, point.y + i));
		}
	}

	protected void useSquarePower(Point point) {
		for (int i = point.x - 1; i < point.x + 2; i++) {
			for (int j = point.y - 1; j < point.y + 2; j++) {
				receiveAttack(new Attack(i, j));
			}
		}
	}

	public void resetPower() {
		this.activePower = Power.NONE;
	}

	public Point possibleSmartAttackPoint() {
		Point p = new Point(0, 0);
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				p.setLocation(i, j);

				if (Util.gameDifficulty == Difficulty.VERYHARD) {
					if (checkPossibleSmartAttackPoint2(p)) {
						return p;
					}
					continue;
				}

				if (checkPossibleSmartAttackPoint(p)) {
					return p;
				}
			}
		}
		return null;
	}

	public boolean checkPossibleDiagonalAttack(Point p) {
		Point possibles = new Point(0, 0);
		for (int i = -1; i < 2; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				possibles.setLocation(p.x + i, p.y + j);
				if (checkPossibleAttackPoint(possibles)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkPossibleAttackPoint(Point p) {
		if (p.x < 0 || p.x >= Util.boardSize) {
			return false;
		}

		if (p.y < 0 || p.y >= Util.boardSize) {
			return false;
		}

		if (!buttons[p.x][p.y].isEnabled()) {
			return false;
		}

		return true;
	}

	private boolean checkPossibleSmartAttackPoint(Point point) {

		if (hasDisabledBoat(point)) {

			if (checkValidAttackPosition(point.x, point.y - 1)) {
				return true;
			}

			if (checkValidAttackPosition(point.x, point.y + 1)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkPossibleSmartAttackPoint2(Point point) {

		if (hasDisabledBoat(point)) {

			if (hasEnabledBoat(point.x, point.y - 1)) {
				return true;
			}

			if (hasEnabledBoat(point.x, point.y + 1)) {
				return true;
			}
		}

		return false;
	}
}
