package palace.toolkit.visual;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import palace.toolkit.visitor.Visitor;


public class AbstractCompositeInteractive extends AbstractInteractive implements
		CompositeInteractive, Observer {

	protected AlignmentArbiter _alignmentArbiter;

	protected ArrayList<Interactive> _elements = new ArrayList<Interactive>();

	public void accept(Visitor<Interactive> v) {
		v.visit(this);
		for(Interactive interactive : _elements)
			interactive.accept(v);
	}

	public void addElement(Interactive interactive) {
		_elements.add(interactive);
		interactive.addObserver(this);
		interactive.setParent(this);
		alignElements();
		alignFrame();
	}

	public void alignElements() {
		_alignmentArbiter.alignElements(this, _elements);
	}

	public void alignFrame() {
		_alignmentArbiter.alignFrame(this, _elements);
		if (getParent() != null) {
			getParent().alignElements();
			getParent().alignFrame();
		}
	}

	public void draw(Graphics2D g2d) {
		drawForm(g2d);
		for (Interactive interactive : _elements)
			interactive.draw(g2d);
	}

	public void drawForm(Graphics2D g2d){
		g2d.draw(_form);
	}
	
	public int getElementSpacing() {
		// TODO waaaagh, magic numbers!
		return 3;
	}

	public int getEmptyHeight() {
		// TODO waaaagh, magic numbers!
		return 97;
	}

	public int getEmptyWidth() {
		// TODO waaaagh, magic numbers!
		return 73;
	}

	public Interactive getGrabbedElement() {
		for (Interactive interactive : _elements) {
			if (interactive instanceof CompositeInteractive)
				if (((CompositeInteractive) interactive).isAnElementGrabbed())
					return ((CompositeInteractive) interactive)
							.getGrabbedElement();
			if (interactive.isGrabbed())
				return interactive;
		}
		return null;
	}

	public Interactive getSelectedElement() {
		for (Interactive interactive : _elements) {
			if (interactive instanceof CompositeInteractive)
				if (((CompositeInteractive) interactive).isAnElementSelected())
					return ((CompositeInteractive) interactive)
							.getSelectedElement();
			if (interactive.isSelected())
				return interactive;
		}
		return null;
	}

	public void grab(int x, int y) {
		if (!contains(x, y))
			return;
		for (Interactive interactive : _elements)
			if (interactive.contains(x, y)) {
				interactive.grab(x, y);
				return;
			}
		super.grab(x, y);
	}

	public boolean isAnElementGrabbed() {
		for (Interactive interactive : _elements) {
			if (interactive instanceof CompositeInteractive)
				if (((CompositeInteractive) interactive).isAnElementGrabbed())
					return true;
			if (interactive.isGrabbed())
				return true;
		}
		return false;
	}

	public boolean isAnElementSelected() {
		for (Interactive interactive : _elements) {
			if (interactive instanceof CompositeInteractive)
				if (((CompositeInteractive) interactive).isAnElementSelected())
					return true;
			if (interactive.isSelected())
				return true;
		}
		return false;
	}

	public Iterator<Interactive> iterator() {
		return _elements.iterator();
	}

	public Interactive pick(int x, int y) {
		if (!contains(x, y))
			return null;
		for (Interactive interactive : _elements)
			if (interactive.contains(x, y))
				if (interactive instanceof CompositeInteractive)
					return ((CompositeInteractive) interactive).pick(x, y);
				else
					return interactive;
		return this;
	}

	public void remove(Interactive interactive) {

		_elements.remove(interactive);

		alignElements();
		alignFrame();
	}

	protected void resetFrame() {
		super.resetFrame();
		alignElements();
	}

	public void select(int x, int y) {
		if (!contains(x, y))
			return;
		if (pick(x, y) == this) {
			if (isSelected())
				unselect();
			else {
				if (isAnElementSelected())
					unselect();
				super.select(x, y);
			}
			return;
		}
		if (isAnElementSelected() || isSelected())
			if (pick(x, y) == getSelectedElement())
				unselect();
			else {
				unselect();
				pick(x, y).select(x, y);
			}
		else
			pick(x, y).select(x, y);
	}

	public void target(int x, int y) {

		if (!contains(x, y))
			return;

		if (pick(x, y) == this) {
			super.target(x, y);
			return;
		}

		pick(x, y).target(x, y);
	}

	public void ungrab() {
		super.ungrab();
		for (Interactive interactive : _elements)
			interactive.ungrab();
	}

	public void unselect() {

		super.unselect();

		if (isAnElementSelected())
			getSelectedElement().unselect();
	}

	public void update(Observable observable, Object object) {
		setChanged();
		notifyObservers();
	}

}
