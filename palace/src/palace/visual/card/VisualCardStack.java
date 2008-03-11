package palace.visual.card;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import palace.logical.card.LogicalCard;
import palace.logical.card.LogicalCardStack;

import visitor.Visitor;
import visual.AbstractCompositeInteractive;
import visual.Interactive;
import visual.VisualTarget;

public class VisualCardStack extends AbstractCompositeInteractive implements
		VisualTarget {

	private LogicalCardStack _logicalCardStack;

	public VisualCardStack(LogicalCardStack logicalCardStack) {

		_logicalCardStack = logicalCardStack;

		_form = new Rectangle2D.Double();

		_alignmentArbiter = new DiagonalAlignment();

		alignFrame();

	}

	public void accept(Visitor<Interactive> v) {
		v.visit(this);
		for (int i = _elements.size() - 1; i >= 0; i--)
			v.visit(_elements.get(i));

	}

	public void addElement(Interactive interactive) {
		_elements.add(0, interactive);
		interactive.addObserver(this);
		interactive.setParent(this);
		alignElements();
		alignFrame();
	}

	public void draw(Graphics2D g2d) {
		g2d.draw(_form);
		for (int i = _elements.size() - 1; i >= 0; i--)
			_elements.get(i).draw(g2d);
	}

	public LogicalCardStack getLogicalCardStack() {
		return _logicalCardStack;
	}

	public Interactive pick(int x, int y) {
		if (!contains(x, y))
			return null;
		if (!_elements.isEmpty() && _elements.get(0).contains(x, y))
			return _elements.get(0);
		return this;
	}

	public void grab(int x, int y) {
		if (!contains(x, y))
			return;
		if (!_elements.isEmpty() && _elements.get(0).contains(x, y))
			_elements.get(0).grab(x, y);
	}

	public void select(int x, int y) {
		if (!contains(x, y))
			return;
		if (_elements.get(0).contains(x, y))
			_elements.get(0).select(x, y);
	}

	public void target(int x, int y) {
		if (!contains(x, y))
			return;
		if (!_elements.isEmpty() && _elements.get(0).contains(x, y))
			_elements.get(0).target(x, y);
	}

	public void update(Observable logicalCardStack, Object logicalCard) {
		addElement(new VisualCard((LogicalCard) logicalCard));
	}
}
