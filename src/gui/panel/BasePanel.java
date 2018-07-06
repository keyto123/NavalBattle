package gui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import gui.GameFrame;

@SuppressWarnings("serial")
public abstract class BasePanel extends JPanel {
	
	private Image backgroundImage;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, null);
	}
	
	protected GameFrame frame;
	
	protected BasePanel(int width, int height, GameFrame frame) {
		this.frame = frame;
		this.setSize(width, height);
		this.setLayout(null);
		this.setBackground(Color.GRAY);
	}
	
	protected void setBackgroundImage(Image image) {
		backgroundImage = image;
	}
}
