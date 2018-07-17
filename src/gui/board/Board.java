package gui.board;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Attack;
import game.AttackStatus;
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
		super(300, 300, null);
		this.title.setBounds(150 - name.length() * 8, 0, 100, 25);
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
		return buttons[x][y].hasBoat();
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
		return !(x < 0 || x >= buttons.length || y < 0 || y >= buttons.length || !buttons[x][y].isEnabled());
	}

	protected boolean checkValidPosition(Point point) {
		return this.checkValidPosition(point.x, point.y);
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
}
