package gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.BoardButton;

public class CpuBoard extends Board {

	public CpuBoard() {
		super("CPU", e -> buttonAction(e));
	}
	
	private static void buttonAction(ActionEvent e) {
		System.out.println("Hai from cpu");
		BoardButton b = (BoardButton)e.getSource();
		b.setEnabled(false);
	}
}
