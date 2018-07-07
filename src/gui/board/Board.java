package gui.board;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Util;
import game.boats.BoatType;
import gui.panel.BasePanel;

@SuppressWarnings("serial")
public abstract class Board extends BasePanel {
	
	protected BoardButton buttons[][] = new BoardButton[Util.boardSize][Util.boardSize];

	private JLabel title = new JLabel();

	protected Board(String name) {
		super(300, 300, null);
		this.title.setBounds(150 - name.length() * 8, 0, 100, 25);
		this.title.setText(name);
		this.add(this.title);

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
			if(player) {
				buttons[x][y + i].setIcon(parts[i]);
			}
			buttons[x][y + i].setDisabledIcon(destroyedParts[i]);
			buttons[x][y + i].setHasBoat(true);
		}
		b.setBoatType(bt);

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
}
