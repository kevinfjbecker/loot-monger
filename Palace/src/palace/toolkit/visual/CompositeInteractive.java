package palace.toolkit.visual;

import java.awt.Graphics2D;

public interface CompositeInteractive extends Interactive,
		Iterable<Interactive> {

	void addElement(Interactive interactive);
	
	void alignElements();

	void alignFrame();

	void drawForm(Graphics2D g2d);
	
	int getElementSpacing();

	int getEmptyHeight();

	int getEmptyWidth();

	Interactive getGrabbedElement();

	Interactive getSelectedElement();

	boolean isAnElementGrabbed();

	boolean isAnElementSelected();

	Interactive pick(int x, int y);

	void remove(Interactive interactive);
	
}
