package palace.toolkit.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import palace.toolkit.logical.State;
import palace.toolkit.visual.Interactive;



public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private InteractiveSurface _interactiveSurface;

	private MouseHub _mouseInteractionHub;

	public Canvas(MouseHub hub, InteractiveSurface surface) {

		setBackground(Color.WHITE);

		_interactiveSurface = surface;
		surface.setCanvas(this);

		if (hub != null) {
			_mouseInteractionHub = hub;
			addMouseListener(_mouseInteractionHub);
			addMouseMotionListener(_mouseInteractionHub);
			_mouseInteractionHub.setInteractiveSurface(_interactiveSurface);
			_mouseInteractionHub.setCanvas(this);
		}
	}

	public void addElement(Interactive interactive) {
		_interactiveSurface.addElement(interactive);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		_interactiveSurface.draw(g2d);
	}

	public void setGameState(State state) {
		_mouseInteractionHub.setGameState(state);
	}
}
