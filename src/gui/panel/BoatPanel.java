package gui.panel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import game.GameManager;
import game.boats.BoatButton;
import game.boats.BoatStorage;
import game.boats.BoatType;

@SuppressWarnings("serial")
public class BoatPanel extends BasePanel {

	private JLabel name = new JLabel("Boats");
	private BoatButton boats[];
	private JLabel boatQuantities[];
	private BattlePanel parentPanel;
	private BoatStorage storage = new BoatStorage();

	public BoatPanel(BattlePanel panel) {
		super(panel.getWidth(), 100, null);

		parentPanel = panel;

		int namePosX = this.getWidth() / 2 - 30;
		name.setBounds(namePosX, 0, 100, 25);
		this.add(name);

		boats = new BoatButton[storage.getLength()];
		boatQuantities = new JLabel[storage.getLength()];

		initButtons();

		this.setVisible(true);
	}

	private ActionListener listener = e -> {
		GameManager gm = parentPanel.getGm();

		if (!gm.hasGameStarted()) {
			boatButton_Function(e);
		}
	};

	private void boatButton_Function(ActionEvent e) {
		BoatButton b = (BoatButton) e.getSource();
		BoatType bt = b.getBoatType();
		if (bt.quantity > 0) {
			parentPanel.getGm().setSelectedBoatType(bt);
			updateQuantities();
		}
	}

	public void updateQuantities() {
		for (int i = 0; i < boats.length; i++) {
			BoatType bt = boats[i].getBoatType();
			boatQuantities[i].setText(bt.quantity + "");
		}
	}

	private void initButtons() {

		// Init Boats and Labels
		for (int i = 0; i < boats.length; i++) {
			boats[i] = new BoatButton(storage.getBoatType(i));
			boatQuantities[i] = new JLabel();
		}

		boatButtonsInit2();
	}

	@SuppressWarnings("unused")
	private void boatButtonsInit() {
		Rectangle r = new Rectangle();
		for (int i = 0; i < boats.length;) {
			for (int j = 0; j < 2 && i < boats.length; j++, i++) {
				BoatType bt = boats[i].getBoatType();
				r = new Rectangle(100 + (i / 2 * 100), 25 + (j * 30), boats[i].getWidth(), boats[i].getHeight());

				boats[i].setBounds(r);
				boats[i].setBorder(null);
				boats[i].setBackground(Color.GRAY);
				boats[i].addActionListener(listener);
				this.add(boats[i]);

				r = new Rectangle(r.x - 15, r.y, 45, 25);
				boatQuantities[i].setBounds(r);
				boatQuantities[i].setText(bt.quantity + "");
				this.add(boatQuantities[i]);
			}
		}
	}

	private void boatButtonsInit2() {
		Rectangle r = new Rectangle();
		for (int i = 0; i < boats.length; i++) {
			BoatType bt = boats[i].getBoatType();

			r = new Rectangle(
					25 + i * 25 + (boats[i].getWidth() * 5 / 11) * i, 25,
					boats[i].getWidth(), boats[i].getHeight()
			);
			boats[i].setBounds(r);
			boats[i].setBorder(null);
			boats[i].setBackground(Color.GRAY);
			boats[i].addActionListener(listener);
			this.add(boats[i]);

			r = new Rectangle(r.x + (boats[i].getWidth() / 2), r.y + 25, 30, 25);
			boatQuantities[i].setBounds(r);
			boatQuantities[i].setText(bt.quantity + "");
			this.add(boatQuantities[i]);

		}
	}
}
