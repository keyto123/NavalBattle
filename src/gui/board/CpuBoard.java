package gui.board;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import game.GameManager;
import game.battle.Attack;
import game.boats.BoatStorage;
import game.cpu.AttackFunctions;
import game.util.Configs;
import gui.panel.BattlePanel;

@SuppressWarnings("serial")
public class CpuBoard extends Board {

	private Random rand = new Random();
	private BoatStorage storage = new BoatStorage();
	private GameManager gm;
	private AttackFunctions attackFunctions;

	private ActionListener listener = e -> {
		if (parentPanel.getGm().hasGameStarted()) {
			buttonAction(e);
		}
	};

	public CpuBoard(BattlePanel panel) {
		super("CPU", panel);
		this.setButtonsListener(listener);
		parentPanel = panel;
	}

	public void initBoats() {
		gm = parentPanel.getGm();
		attackFunctions = new AttackFunctions(gm);
		int x = 0, y = 0;
		for (int i = 0; i < storage.getLength(); i++) {
			for (int j = 0; j < storage.getBoatType(i).quantity; j++) {
				do {
					x = rand.nextInt(Configs.boardSize);
					y = rand.nextInt(Configs.boardSize);
				} while (!this.setButtonBoat(buttons[x][y], storage.getBoatType(i), false));
			}
		}
	}

	private void buttonAction(ActionEvent e) {
		BoardButton b = (BoardButton) e.getSource();
		Point point = new Point(b.getPosX(), b.getPosY());

		super.receiveAttack(new Attack(point, gm.getPlayerPower()));
		gm.resetPlayerPower();

		if (checkFinish()) {
			gm.finishGame(Configs.playerWinFlag);
		} else {
			attackFunctions.cpuAttack(activePower);
		}

	}
}
