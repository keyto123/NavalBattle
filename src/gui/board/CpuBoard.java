package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;

import gui.panel.BattlePanel;

public class CpuBoard extends Board {

	private BattlePanel mainPanel;
	
	private Random rand = new Random();
	
	private ActionListener listener = e -> {
		buttonAction(e);
	};
	
	public CpuBoard(BattlePanel panel) {
		super("CPU");
		this.setButtonsListener(listener);
		this.initBoats();
		mainPanel = panel;
		this.disableButtons();
	}
	
	private void initBoats() {
		// not the real functionality
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++) {
				buttons[i][j].setHasBoat(rand.nextBoolean());
			}
		}
	}
	
	private void buttonAction(ActionEvent e) {
		BoardButton b = (BoardButton)e.getSource();
		if(b.hasBoat()) {
			JOptionPane.showMessageDialog(this, "Very good, little kid");
		}
		b.setEnabled(false);
	}
}
