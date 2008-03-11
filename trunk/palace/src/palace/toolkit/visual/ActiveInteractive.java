package palace.toolkit.visual;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.Observer;

import palace.toolkit.visitor.Visitor;


public class ActiveInteractive implements Active, Interactive {

	private Active _active;
	
	private Interactive _interactive;

	public ActiveInteractive(Interactive interactive, Active active){
		_interactive = interactive;
		_active = active;
	}

	public void accept(Visitor<Interactive> v) {
		_interactive.accept(v);		
	}

	public void addObserver(Observer observer){
		_interactive.addObserver(observer);
	}
	
	public boolean contains(int x, int y) {
		return _interactive.contains(x,y);
	}

	public void draw(Graphics2D g2d) {
		_interactive.draw(g2d);
	}

	public ActionEvent getActionEvent() {
		return _active.getActionEvent();
	}

	public int getCenterX() {
		return _interactive.getCenterX();
	}

	public int getCenterY() {
		return _interactive.getCenterY();
	}

	public int getHeight() {
		return _interactive.getHeight();
	}

	public CompositeInteractive getParent() {
		return _interactive.getParent();
	}

	public int getWidth() {
		return _interactive.getWidth();
	}

	public int getX() {
		return _interactive.getX();
	}

	public int getY() {
		return _interactive.getY();
	}

	public void grab(int x, int y) {
		_interactive.grab(x,y);
	}

	public boolean isGrabbed() {
		return _interactive.isGrabbed();
	}

	public boolean isSelected() {
		return _interactive.isSelected();
	}

	public boolean isTargeted() {
		return _interactive.isTargeted();
	}

	public void select(int x, int y) {
		_interactive.select(x,y);
	}

	public void setCenterX(int x) {
		_interactive.setCenterX(x);
	}

	public void setCenterY(int y) {
		_interactive.setCenterY(y);
	}

	public void setHeight(int h) {
		_interactive.setHeight(h);
	}

	public void setParent(CompositeInteractive parent) {
		_interactive.setParent(parent);
	}

	public void setWidth(int w) {
		_interactive.setWidth(w);
	}

	public void setX(int x) {
		_interactive.setX(x);
	}

	public void setY(int y) {
		_interactive.setY(y);
	}

	public void target(int x, int y) {
		_interactive.target(x, y);
	}

	public void ungrab() {
		_interactive.ungrab();
	}

	public void unselect() {
		_interactive.unselect();
	}

	public void untarget() {
		_interactive.untarget();
	}
	
}
