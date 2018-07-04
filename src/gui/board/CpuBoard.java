package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.panel.BattlePanel;

public class CpuBoard extends Board {

	private BattlePanel mainPanel;
	
	private ActionListener listener = e -> {
		buttonAction(e);
	};
	
	public CpuBoard(BattlePanel panel) {
		super("CPU");
		this.setButtonsListener(listener);
		mainPanel = panel;
	}
	
	private static void buttonAction(ActionEvent e) {
		System.out.println("Hai from cpu");
		BoardButton b = (BoardButton)e.getSource();
		b.setEnabled(false);
	}
}
