package gui.board;

import java.awt.event.ActionEvent;

import gui.BoardButton;

public class PlayerBoard extends Board {

	public PlayerBoard() {
		super("PLAYER", e -> buttonAction(e));
	}

	private static void buttonAction(ActionEvent e) {
		System.out.println("Hai from player");
		BoardButton b = (BoardButton) e.getSource();
		b.setEnabled(false);
	}
}
