package gui.panel;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import game.GameManager;
import game.boats.BoatButton;
import game.boats.BoatUtils;
import gui.GameFrame;

public class BoatPanel extends BasePanel {

	private JLabel name = new JLabel("Boats");
	private BoatButton boat1 = new BoatButton(BoatUtils.boats[0]);
	private BattlePanel parentPanel;
	
	public BoatPanel(BattlePanel panel) {
		super(800, 200, null);
		
		parentPanel = panel;
		
		name.setBounds(350, 0, 100, 25);
		this.add(name);

		initButtons();

		this.setVisible(true);
	}

	private ActionListener listener = e -> {
		BoatButton b = (BoatButton) e.getSource();
		parentPanel.getGm().setSelectedBoatType(b.getBoatType());
	};

	private void initButtons() {
		boat1.setBounds(5, 5, boat1.getWidth(), boat1.getHeight());
		boat1.addActionListener(listener);
		this.add(boat1);
	}
}
