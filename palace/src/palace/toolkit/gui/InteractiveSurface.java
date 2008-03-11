package palace.toolkit.gui;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import palace.toolkit.visual.AbstractCompositeInteractive;
import palace.toolkit.visual.Interactive;


public class InteractiveSurface extends AbstractCompositeInteractive implements
		ComponentListener {

	private Canvas _canvas;

	public InteractiveSurface() {
		
		_form = new Rectangle2D.Double();
		_alignmentArbiter = new FreeAlignment();
	}

	public void componentResized(ComponentEvent componentEvent) {
		setWidth((int) ((Component) componentEvent.getSource()).getBounds()
				.getWidth());
		setHeight((int) ((Component) componentEvent.getSource()).getBounds()
				.getHeight());
		_canvas.repaint();
	}

	public void componentMoved(ComponentEvent componentEvent) {
	}

	public void componentShown(ComponentEvent componentEvent) {
	}

	public void componentHidden(ComponentEvent componentEvent) {
	}

	public void draw(Graphics2D g2d) {
		for (Interactive interactive : _elements)
			interactive.draw(g2d);
	}

	public void setCanvas(Canvas canvas) {
		_canvas = canvas;
		_canvas.addComponentListener(this);
	}

	public void update(Observable observable, Object object) {
		_canvas.repaint();
	}

}
