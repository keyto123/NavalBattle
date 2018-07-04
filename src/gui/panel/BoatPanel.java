package gui.panel;

import javax.swing.JLabel;

import game.boats.BoatButton;
import game.boats.BoatUtils;
import gui.GameFrame;

public class BoatPanel extends BasePanel {

	private JLabel name = new JLabel("Boats");

	public BoatPanel(GameFrame frame) {
		super(800, 200, frame);
		name.setBounds(350, 0, 100, 25);
		this.add(name);

		initButtons();

		this.setVisible(true);
	}

	private void initButtons() {
		BoatButton boat1 = new BoatButton(BoatUtils.boats[0]);
		boat1.setBounds(5, 5, boat1.getWidth(), boat1.getHeight());
		this.add(boat1);
	}
}
