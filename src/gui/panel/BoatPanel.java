package gui.panel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import game.boats.BoatButton;
import game.boats.BoatType;
import game.boats.BoatUtils;

@SuppressWarnings("serial")
public class BoatPanel extends BasePanel {

	private JLabel name = new JLabel("Boats");
	private BoatButton boats[];
	private JLabel boatQuantities[];
	private BattlePanel parentPanel;

	public BoatPanel(BattlePanel panel) {
		super(800, 200, null);

		parentPanel = panel;

		name.setBounds(350, 0, 100, 25);
		this.add(name);

		boats = new BoatButton[BoatUtils.boats.length];
		boatQuantities = new JLabel[BoatUtils.boats.length];

		initButtons();

		this.setVisible(true);
	}

	private ActionListener listener = e -> {
		BoatButton b = (BoatButton) e.getSource();
		BoatType bt = b.getBoatType();
		if (bt.quantity > 0) {
			bt.quantity--;
			parentPanel.getGm().setSelectedBoatType(bt);
			updateQuantities();
		}
	};

	public void updateQuantities() {
		for (int i = 0; i < boats.length; i++) {
			BoatType bt = boats[i].getBoatType();
			boatQuantities[i].setText(bt.quantity + "");
		}
	}

	private void initButtons() {
		Rectangle r = new Rectangle();

		// Init Boats and Labels
		for (int i = 0; i < boats.length; i++) {
			boats[i] = new BoatButton(BoatUtils.boats[i]);
			boatQuantities[i] = new JLabel();
		}

		for (int i = 0; i < boats.length; i++) {
			for (int j = 0; j < 2 && i < boats.length; j++, i++) {
				BoatType bt = boats[i].getBoatType();
				r = new Rectangle(100 + (i / 2 * 100), 25 + (j * 30), boats[i].getWidth(), boats[i].getHeight());
				boats[i].setBounds(r);
				boats[i].setBorder(null);
				boats[i].setBackground(Color.GRAY);
				boats[i].addActionListener(listener);
				this.add(boats[i]);
				
				r = new Rectangle(r.x - 50, r.y, 45, 25);
				boatQuantities[i].setBounds(r);
				boatQuantities[i].setText(bt.quantity + "");
				this.add(boatQuantities[i]);
			}
		}

	}
}
