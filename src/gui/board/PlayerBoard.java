package gui.board;

import java.awt.event.ActionEvent;

import gui.BoardButton;
import gui.Communicable;
import gui.GameFrame;
import gui.panel.BattlePanel;

public class PlayerBoard extends Board {
	
	public PlayerBoard(GameFrame frame) {
		super("PLAYER", e -> buttonAction(e), frame);
	}

	private static void buttonAction(ActionEvent e) {
		System.out.println("Hai from player");
		BoardButton b = (BoardButton) e.getSource();
		b.setEnabled(false);
	}

}
