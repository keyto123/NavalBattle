package gui.board;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Configs;
import gui.BoardButton;

public abstract class Board extends JPanel {

	protected BoardButton buttons[][] = new BoardButton[Configs.boardSize][Configs.boardSize];

	protected ActionListener listener;
	private JLabel title = new JLabel();

	protected Board(String name, ActionListener listener) {
		this.title.setBounds(150 - name.length() * 8, 0, 100, 25);
		this.title.setText(name);
		this.add(this.title);

		this.setBackground(Color.GRAY);
		this.setSize(300, 300);

		this.listener = listener;
		this.initButtons();
	}

	protected void initButtons() {
		// playerButtons
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new BoardButton(i, j, null, null, false);
				buttons[i][j].setBounds(j * 27, 30 + i * 27, Configs.buttonWidth, Configs.buttonHeight);
				buttons[i][j].addActionListener(listener);
				this.add(buttons[i][j]);
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
