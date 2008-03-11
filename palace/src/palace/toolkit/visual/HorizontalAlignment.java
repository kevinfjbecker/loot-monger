package palace.toolkit.visual;

import java.util.ArrayList;

public class HorizontalAlignment implements AlignmentArbiter {

	public void alignElements(CompositeInteractive c, ArrayList<Interactive> a) {
		int cumulativeWidth = 0;
		for (Interactive interactive : a) {
			interactive.setX(c.getX() + (a.indexOf(interactive) + 1)
					* c.getElementSpacing() + cumulativeWidth);
			cumulativeWidth += interactive.getWidth();
			interactive.setY(c.getY() + c.getElementSpacing());
		}
	}

	public void alignFrame(CompositeInteractive c, ArrayList<Interactive> a) {

		if (a.isEmpty()) {
			c.setWidth(c.getEmptyWidth() + 2 * c.getElementSpacing());
			c.setHeight(c.getEmptyHeight() + 2 * c.getElementSpacing());
		} else {
			c.setWidth((a.size() + 1) * c.getElementSpacing() + totalWidth(a));
			c.setHeight(maximalHeight(a) + 2 * c.getElementSpacing());
		}
	}

	private int maximalHeight(ArrayList<Interactive> elements) {
		int maximalElementHeight = 0;
		for (Interactive interactive : elements)
			if (interactive.getHeight() > maximalElementHeight)
				maximalElementHeight = interactive.getHeight();
		return maximalElementHeight;
	}

	private int totalWidth(ArrayList<Interactive> e) {
		int cumulativeElementWidth = 0;
		for (Interactive interactive : e)
			cumulativeElementWidth += interactive.getWidth();
		return cumulativeElementWidth;
	}

}
