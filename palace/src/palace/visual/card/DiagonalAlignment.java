package palace.visual.card;

import java.util.ArrayList;

import visual.AlignmentArbiter;
import visual.CompositeInteractive;
import visual.Interactive;
import static palace.visual.card.VisualCard.*;

public class DiagonalAlignment implements AlignmentArbiter {

	private static int OFFSET = 4;

	public void alignElements(CompositeInteractive c, ArrayList<Interactive> e) {
		int cumulativeElementOffset = 0;
		for (Interactive interactive : e) {

			interactive.setX(c.getX() + (e.indexOf(interactive) + 1)
					* c.getElementSpacing() + cumulativeElementOffset);

			interactive.setY(c.getY() + (e.indexOf(interactive) + 1)
					* c.getElementSpacing() + cumulativeElementOffset);

			cumulativeElementOffset += OFFSET;
		}
	}

	public void alignFrame(CompositeInteractive c, ArrayList<Interactive> e) {

		if (e.isEmpty()) {
			c.setWidth(c.getEmptyWidth() + 2 * c.getElementSpacing());
			c.setHeight(c.getEmptyHeight() + 2 * c.getElementSpacing());
		} else {
			c.setWidth(WIDTH + OFFSET * (e.size() - 1) + (e.size() + 1)
					* c.getElementSpacing());
			c.setHeight(HEIGHT + OFFSET * (e.size() - 1) + (e.size() + 1)
					* c.getElementSpacing());
		}
	}
}
