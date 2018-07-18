package gui.panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.Difficulty;
import game.Util;
import gui.GameFrame;

@SuppressWarnings("serial")
public class StartPanel extends GamePanel {

	private JButton start = new JButton("Play");

	private JComboBox<Integer> boardSizeBox;
	private JComboBox<Integer> boatQuantityBox;
	private JComboBox<Difficulty> difficultyLevelBox;

	private JLabel boardSizeLabel = new JLabel("Board size:");
	private JLabel boatQuantityLabel = new JLabel("Boat Quantity:");
	private JLabel difficultyLevelLabel = new JLabel("Difficulty:");

	private int selectedBoardSize = Util.boardSize;
	private int selectedBoatQuantity = Util.boatLengthLimit;
	private Difficulty selectedDifficulty = Util.gameDifficulty;

	public StartPanel(GameFrame frame) {
		super(frame);
		start.setBounds(frame.getWidth() - 110, frame.getHeight() - 50, Util.commonButtonWidth, Util.buttonHeight);
		start.addActionListener(e -> start_buttonAction());
		this.add(start);

		// initBoardSizeOption(x, y);
		// initBoatQuantityOption(x, y);

		initAllOptions();
		initBounds();
		initListeners();
		addAll();
	}

	private void initAllOptions() {
		Integer sizes[] = new Integer[] {
				6, 7, 8, 9, 10, 11, 12, 13, 14, 15
		};
		boardSizeBox = new JComboBox<Integer>(sizes);
		boardSizeBox.setSelectedItem(Util.boardSize);

		Integer quantities[] = new Integer[] {
				2, 3, 4, 5, 6
		};
		boatQuantityBox = new JComboBox<Integer>(quantities);
		boatQuantityBox.setSelectedItem(Util.boatLengthLimit - 1);

		difficultyLevelBox = new JComboBox<Difficulty>(Difficulty.values());
		difficultyLevelBox.setSelectedItem(Util.gameDifficulty);
	}

	private void initBounds() {
		int x = this.getWidth() / 2 - 25, y = this.getHeight() / 2 - 50;

		int boxWidth = 100, boxHeight = 25;

		boardSizeLabel.setBounds(x - 100, y, 100, boxHeight);
		boardSizeBox.setBounds(x, y, boxWidth, boxHeight);

		boatQuantityLabel.setBounds(x - 123, y + 30, 123, boxHeight);
		boatQuantityBox.setBounds(x, y + 30, boxWidth, boxHeight);

		difficultyLevelLabel.setBounds(x - 87, y + 60, 87, boxHeight);
		difficultyLevelBox.setBounds(x, y + 60, boxWidth, boxHeight);
	}

	private void initListeners() {
		boardSizeBox.addActionListener(e -> {
			selectedBoardSize = (int) boardSizeBox.getSelectedItem();
		});

		boatQuantityBox.addActionListener(e -> {
			selectedBoatQuantity = (int) boatQuantityBox.getSelectedItem() + 1;
		});

		difficultyLevelBox.addActionListener(e -> {
			selectedDifficulty = (Difficulty) difficultyLevelBox.getSelectedItem();
		});
	}

	private void addAll() {
		this.add(boardSizeLabel);
		this.add(boardSizeBox);

		this.add(boatQuantityLabel);
		this.add(boatQuantityBox);

		this.add(difficultyLevelLabel);
		this.add(difficultyLevelBox);
	}

	private boolean checkValidSelections() {
		switch (selectedBoardSize) {
		case 6:
		case 7:
		case 8:
			if (selectedBoatQuantity > 4) {
				return false;
			}
			break;
		}
		return true;
	}

	private void start_buttonAction() {
		if (!checkValidSelections()) {
			JOptionPane.showMessageDialog(this, "Please select a smaller amount of boats or increase board size");
			return;
		}
		Util.boardSize = selectedBoardSize;
		Util.boatLengthLimit = selectedBoatQuantity;
		Util.gameDifficulty = selectedDifficulty;
		frame.setVisible(false);
		frame.restart();
		frame.setVisible(true);
		frame.battleStarted();
	}
}
