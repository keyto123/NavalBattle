package gui.board;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import game.Util;
import gui.GameFrame;
import gui.panel.BasePanel;

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
				buttons[i][j].setBackground(Color.BLUE);
				this.add(buttons[i][j]);
			}
		}
	}
	
	protected void setButtonsListener(ActionListener listener) {
		for(BoardButton[] row : buttons) {
			for(BoardButton col : row) {
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
}
