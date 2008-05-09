package palace;


import java.awt.Graphics2D;
import java.util.ArrayList;

import palace.toolkit.gui.InteractiveSurface;
import palace.toolkit.visitor.Visitor;
import palace.toolkit.visual.CompositeInteractive;
import palace.toolkit.visual.Interactive;
import palace.visual.card.VisualCard;

public class MultiSelectSurface extends InteractiveSurface {

	private class DrawingVisitor implements Visitor<Interactive> {

		private Graphics2D _g2d;

		public void setGraphics2D(Graphics2D g2d) {
			_g2d = g2d;
		}

		public void visit(Interactive t) {
			if (_dragging && (t == _grabbed || _selected.contains(t)))
				return;
			if (t instanceof CompositeInteractive) {
				((CompositeInteractive) t).drawForm(_g2d);
			} else {
				t.draw(_g2d);
			}
		}
	}

	private boolean _dragging;

	private DrawingVisitor _drawingVisitor;

	private Interactive _grabbed;

	private ArrayList<VisualCard> _selected;

	public MultiSelectSurface() {
		super();
		_drawingVisitor = new DrawingVisitor();
		_selected = new ArrayList<VisualCard>();
	}

	public void alignElements() {
		for (Interactive interactive : _elements)
			if (interactive instanceof CompositeInteractive)
				((CompositeInteractive) interactive).alignElements();
	}

	public void draw(Graphics2D g2d) {

		_drawingVisitor.setGraphics2D(g2d);
		for (Interactive interactive : _elements)
			interactive.accept(_drawingVisitor);
		if (_dragging) {
			for (int i = _selected.size() - 1; i >= 0; i--)
				if (_selected.get(i) != _grabbed)
					_selected.get(i).draw(g2d);
			if (_grabbed != null) {
				_grabbed.draw(g2d);
			}
		}
	}

	public Interactive getGrabbedElement() {
		return _grabbed;
	}

	public ArrayList<VisualCard> getSelectedElements() {
		return _selected;
	}

	public void grab(int x, int y) {
		if (pick(x, y) != this)
			_grabbed = pick(x, y);
	}

	public boolean isAnElementGrabbed() {
		return _grabbed != null;
	}

	public void select(int x, int y) {
		Interactive i = pick(x, y);
		if (i == this || !(i instanceof VisualCard))
			return;
		if (_selected.contains(i)) {
			i.unselect();
			_selected.remove(i);
		} else {
			i.select(x, y);
			_selected.add((VisualCard) i);
		}
	}

	public void setDragging(boolean b) {
		_dragging = b;
	}

	public void ungrab() {
		_grabbed = null;
	}

}
