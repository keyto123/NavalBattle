package gui.board;

import java.awt.event.ActionEvent;

import gui.BoardButton;
import gui.GameFrame;
import gui.panel.BattlePanel;

public class CpuBoard extends Board {

	BattlePanel mainPanel;
	
	public CpuBoard(GameFrame frame) {
		super("CPU", e -> buttonAction(e), frame);
	}
	
	private static void buttonAction(ActionEvent e) {
		System.out.println("Hai from cpu");
		BoardButton b = (BoardButton)e.getSource();
		b.setEnabled(false);
	}
}
