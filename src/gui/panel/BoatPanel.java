package gui.panel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import game.boats.BoatButton;
import game.boats.BoatUtils;

public class BoatPanel extends BasePanel {

	private JLabel name = new JLabel("Boats");
	private BoatButton boat1 = new BoatButton(BoatUtils.boats[0]);
	private JLabel boat1Quantity = new JLabel();
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
		if(b.getBoatType().quantity > 0) {
			b.getBoatType().quantity--;
			parentPanel.getGm().setSelectedBoatType(b.getBoatType());
			boat1Quantity.setText(b.getBoatType().quantity + "");
		}
	};
	
	public void updateQuantities() {
		boat1Quantity.setText(boat1.getBoatType().quantity + "");
	}

	private void initButtons() {
		Rectangle r = new Rectangle(100, 25, boat1.getWidth(), boat1.getHeight());

		boat1.setBounds(r);
		boat1.setBorder(null);
		boat1.setBackground(Color.GRAY);
		boat1.addActionListener(listener);
		this.add(boat1);

		r = new Rectangle(r.x - 50, r.y, 45, 25);
		boat1Quantity.setBounds(r);
		boat1Quantity.setText(boat1.getBoatType().quantity + "");
		this.add(boat1Quantity);
	}
}
