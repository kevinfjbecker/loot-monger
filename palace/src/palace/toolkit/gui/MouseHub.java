package palace.toolkit.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import palace.toolkit.logical.State;
import palace.toolkit.visual.CompositeInteractive;
import palace.toolkit.visual.Interactive;
import palace.toolkit.visual.VisualTarget;


public class MouseHub implements MouseListener, MouseMotionListener {

	protected State _state;

	protected Canvas _canvas;

	protected InteractiveSurface _surface;

	private int _xOffset;

	private int _yOffset;

	protected VisualTarget getTarget(int x, int y) {
		for (Interactive interactive : traverseAll())
			if (interactive.contains(x, y))
				if (interactive instanceof VisualTarget)
					return (VisualTarget) interactive;
		return null;
	}

	protected void calculateOffset(MouseEvent e) {
		if (_surface.isAnElementGrabbed()) {
			_xOffset = _surface.getGrabbedElement().getX()
					- e.getX();
			_yOffset = _surface.getGrabbedElement().getY()
					- e.getY();
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void setCanvas(Canvas canvas) {
		_canvas = canvas;
	}

	public void setGameState(State State) {
		_state = State;
	}

	public void setInteractiveSurface(InteractiveSurface interactiveSurface) {
		_surface = interactiveSurface;
	}

	private ArrayList<Interactive> traverseAll() {
		ArrayList<Interactive> traversal = new ArrayList<Interactive>();
		for (Interactive interactive : _surface) {
			traversal.add(interactive);
			if (interactive instanceof CompositeInteractive)
				traverseAllRecursiveCall(traversal,
						((CompositeInteractive) interactive));
		}
		return traversal;
	}

	private void traverseAllRecursiveCall(ArrayList<Interactive> traversal,
			CompositeInteractive compositeInteractive) {
		for (Interactive interactive : compositeInteractive) {
			traversal.add(interactive);
			if (interactive instanceof CompositeInteractive)
				traverseAllRecursiveCall(traversal,
						((CompositeInteractive) interactive));
		}
	}

	protected void updateLocation(MouseEvent e) {
		if (_surface.isAnElementGrabbed()) {
			_surface.getGrabbedElement().setX(_xOffset + e.getX());
			_surface.getGrabbedElement().setY(_yOffset + e.getY());
		}
	}

}
