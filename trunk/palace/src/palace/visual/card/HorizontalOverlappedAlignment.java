package palace.visual.card;

import static palace.visual.card.VisualCard.WIDTH;

import java.util.ArrayList;

import visual.AlignmentArbiter;
import visual.CompositeInteractive;
import visual.Interactive;

public class HorizontalOverlappedAlignment implements AlignmentArbiter {
	
	public static final int SPACING = 16;

	public void alignElements(CompositeInteractive c, ArrayList<Interactive> a) {
		int cumulativeWidth = 0;
		for (Interactive interactive : a) {
			interactive
					.setX(c.getX() + c.getElementSpacing() + cumulativeWidth);
			cumulativeWidth += SPACING;
			interactive.setY(c.getY() + c.getElementSpacing());
		}
	}

	public void alignFrame(CompositeInteractive c, ArrayList<Interactive> a) {
		if (a.isEmpty()) {
			c.setWidth(c.getEmptyWidth() + 2 * c.getElementSpacing());
			c.setHeight(c.getEmptyHeight() + 2 * c.getElementSpacing());
		} else {
			c.setWidth(2 * c.getElementSpacing() + totalWidth(a));
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

	// pre: elements.size >= 1
	private int totalWidth(ArrayList<Interactive> elements) {
		return (elements.size() - 1) * SPACING + WIDTH;
	}

}
