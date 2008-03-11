package palace.visual.card;

import java.awt.geom.Rectangle2D;

import palace.toolkit.visual.AbstractCompositeInteractive;
import palace.toolkit.visual.AlignmentArbiter;
import palace.toolkit.visual.HorizontalAlignment;
import palace.toolkit.visual.VerticalAlignment;


public class VisualPlayArea extends AbstractCompositeInteractive {

	public static VisualPlayArea getHorizontallyAlligned() {
		return new VisualPlayArea(new HorizontalAlignment());
	}

	public static VisualPlayArea getVerticallyAlligned() {
		return new VisualPlayArea(new VerticalAlignment());
	}

	protected VisualPlayArea(AlignmentArbiter alignmentArbiter) {

		_form = new Rectangle2D.Double();

		_alignmentArbiter = alignmentArbiter;

		alignFrame();

	}

	public void clear() {
		_elements.clear();
		alignFrame();
	}

}
