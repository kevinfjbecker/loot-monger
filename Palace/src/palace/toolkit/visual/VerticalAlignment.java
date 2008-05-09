package palace.toolkit.visual;

import java.util.ArrayList;


public class VerticalAlignment implements AlignmentArbiter {

	public void alignElements(CompositeInteractive c, ArrayList<Interactive> e) {
		int cumulativeHeight = 0;
		for (Interactive interactive : e) {
			interactive.setX(c.getX() + c.getElementSpacing());
			interactive.setY(c.getY() + (e.indexOf(interactive) + 1)
					* c.getElementSpacing() + cumulativeHeight);
			cumulativeHeight += interactive.getHeight();
		}
	}

	public void alignFrame(CompositeInteractive c, ArrayList<Interactive> e) {
		if (e.isEmpty()) {
			c.setWidth(c.getEmptyWidth() + 2 * c.getElementSpacing());
			c.setHeight(c.getEmptyHeight() + 2 * c.getElementSpacing());
		} else {
			c.setWidth(maximalWidth(e) + 2 * c.getElementSpacing());
			c
					.setHeight((e.size() + 1) * c.getElementSpacing()
							+ totalHeight(e));
		}
	}

	private int maximalWidth(ArrayList<Interactive> elements) {
		int maximalElementWidth = 0;
		for (Interactive interactive : elements)
			if (interactive.getWidth() > maximalElementWidth)
				maximalElementWidth = interactive.getWidth();
		return maximalElementWidth;
	}

	private int totalHeight(ArrayList<Interactive> e) {
		int cumulativeElementHeight = 0;
		for (Interactive interactive : e)
			cumulativeElementHeight += interactive.getHeight();
		return cumulativeElementHeight;
	}

}
