package palace.toolkit.visual;

import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;

import palace.toolkit.visitor.Visitor;


public class AbstractInteractive extends Observable implements Interactive {

	protected RectangularShape _form;

	private int _h;

	private boolean _isGrabbed;

	private boolean _isSelected;

	private boolean _isTargeted;

	private CompositeInteractive _parent;

	private int _w;

	private int _x;

	private int _y;

	public void accept(Visitor<Interactive> v) {
		v.visit(this);
	}

	public boolean contains(int x, int y) {
		return _form.contains(x, y);
	}

	public void draw(Graphics2D g2d) {
		g2d.draw(_form);
	}

	public int getCenterX() {
		return (int) _form.getCenterX();
	}

	public int getCenterY() {
		return (int) _form.getCenterY();
	}

	public int getHeight() {
		return (int) _form.getHeight();
	}

	public CompositeInteractive getParent() {
		return _parent;
	}

	public int getWidth() {
		return (int) _form.getWidth();
	}

	public int getX() {
		return (int) _form.getX();
	}

	public int getY() {
		return (int) _form.getY();
	}

	public void grab(int x, int y) {
		if (_form.contains(x, y))
			_isGrabbed = true;
	}

	public boolean isGrabbed() {
		return _isGrabbed;
	}

	public boolean isSelected() {
		return _isSelected;
	}

	public boolean isTargeted() {
		return _isTargeted;
	}

	protected void resetFrame() {
		_form.setFrame(_x, _y, _w, _h);
	}

	public void select(int x, int y) {
		if (_form.contains(x, y))
			_isSelected = true;
	}

	public void setCenterX(int x) {
		_x = x - _w / 2;
		resetFrame();
	}

	public void setCenterY(int y) {
		_y = y - _h / 2;
		resetFrame();
	}

	public void setHeight(int h) {
		_h = h;
		resetFrame();
	}

	public void setParent(CompositeInteractive parent) {
		_parent = parent;
	}

	public void setWidth(int w) {
		_w = w;
		resetFrame();
	}

	public void setX(int x) {
		_x = x;
		resetFrame();
	}

	public void setY(int y) {
		_y = y;
		resetFrame();
	}

	public void target(int x, int y) {
		if (_form.contains(x, y))
			_isTargeted = true;
	}

	public void ungrab() {
		_isGrabbed = false;
	}

	public void unselect() {
		_isSelected = false;
	}

	public void untarget() {
		_isTargeted = false;
	}

}
