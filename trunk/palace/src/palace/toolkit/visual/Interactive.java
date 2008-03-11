package palace.toolkit.visual;

import java.util.Observer;

import palace.toolkit.visitor.Visitable;


public interface Interactive extends Drawable, Geometric,
		Visitable<Interactive> {

	void addObserver(Observer observer);
	
	public void grab(int x, int y);

	public boolean contains(int x, int y);

	public CompositeInteractive getParent();

	boolean isSelected();

	boolean isGrabbed();
	
	boolean isTargeted();

	void select(int x, int y);

	void setParent(CompositeInteractive parent);

	void target(int x, int y);

	void ungrab();

	void unselect();

	void untarget();
}