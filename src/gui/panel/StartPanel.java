package gui.panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.Util;
import gui.GameFrame;

@SuppressWarnings("serial")
public class StartPanel extends GamePanel {

	private JButton start = new JButton("Play");
	private JComboBox<Integer> boardSizeBox;
	private JComboBox<Integer> boatQuantityBox;

	private JLabel boardSizeLabel = new JLabel("Board size:");
	private JLabel boatQuantityLabel = new JLabel("Boat Quantity:");

	private int selectedBoardSize = Util.boardSize;
	private int selectedBoatQuantity = Util.boatLengthLimit;

	public StartPanel(GameFrame frame) {
		super(frame);
		start.setBounds(frame.getWidth() - 110, frame.getHeight() - 50, Util.commonButtonWidth, Util.buttonHeight);
		start.addActionListener(e -> start_buttonAction());
		this.add(start);
		
		int x = this.getWidth() / 2 - 25, y = this.getHeight() / 2 - 50;

		Integer boardSizeOptions[] = new Integer[] {
				6, 7, 8, 9, 10, 11, 12, 13, 14, 15
		};
		boardSizeBox = new JComboBox<Integer>(boardSizeOptions);
		
		boardSizeBox.addActionListener(e -> {
			selectedBoardSize = (int) boardSizeBox.getSelectedItem();
			selectedBoatQuantity = selectedBoardSize / 3 + 1;
		});
		
		boardSizeBox.setSelectedItem(Util.boardSize);
		
		boardSizeLabel.setBounds(x - 100, y, 100, 25);
		boardSizeBox.setBounds(x, y, 50, 25);

		Integer boatQuantityOptions[] = new Integer[] {
				2, 3, 4, 5, 6
		};
		boatQuantityBox = new JComboBox<Integer>(boatQuantityOptions);
		
		boatQuantityBox.addActionListener(e -> {
			selectedBoatQuantity = (int)boatQuantityBox.getSelectedItem() + 1;
		});
		
		boatQuantityBox.setSelectedItem(Util.boatLengthLimit - 1);
		
		boatQuantityLabel.setBounds(x - 123, y + 30, 120, 25);
		boatQuantityBox.setBounds(x, y + 30, 50, 25);
		
		this.add(boardSizeLabel);
		this.add(boardSizeBox);
		this.add(boatQuantityLabel);
		this.add(boatQuantityBox);
	}
	
	private boolean checkValidSelections() {
		switch(selectedBoardSize) {
		case 6:
		case 7:
		case 8:
			if(selectedBoatQuantity > 4) {
				return false;
			}
			break;
		}
		return true;
	}

	private void start_buttonAction() {
		if(!checkValidSelections()) {
			JOptionPane.showMessageDialog(this, "Please select a smaller amount of boats or increase board size");
			return;
		}
		Util.boardSize = selectedBoardSize;
		Util.boatLengthLimit = selectedBoatQuantity;
		frame.setVisible(false);
		frame.restart();
		frame.setVisible(true);
		frame.battleStarted();
	}
}
