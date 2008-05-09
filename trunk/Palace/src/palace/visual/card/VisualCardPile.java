package palace.visual.card;

import static palace.event.PalaceEvent.EAT_PILE;
import static palace.visual.card.VisualCard.HEIGHT;
import static palace.visual.card.VisualCard.RADIUS;
import static palace.visual.card.VisualCard.WIDTH;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;
import java.util.Observer;

import palace.logical.card.LogicalCardPile;
import palace.toolkit.visual.AbstractInteractive;
import palace.toolkit.visual.Active;
import palace.toolkit.visual.VisualTarget;


public class VisualCardPile extends AbstractInteractive implements Observer,
		VisualTarget, Active {

	private LogicalCardPile _logicalCardPile;

	private VisualCard _topCard;

	public VisualCardPile(LogicalCardPile logicalCardPile) {
		_logicalCardPile = logicalCardPile;
		_form = new RoundRectangle2D.Double(0, 0, 0, 0, RADIUS, RADIUS);

		// TODO waaaagh, magic numbers!
		setHeight(HEIGHT + 6);
		// TODO waaaagh, magic numbers!
		setWidth(WIDTH + 6);
	}

	public void draw(Graphics2D g2d) {

		if (_topCard != null) {
			for (int i = 3; i >= 0; i--)
				g2d.draw(new RoundRectangle2D.Double(getX() + 2 * i, getY() + 2
						* i, WIDTH, HEIGHT, RADIUS, RADIUS));

			// TODO: these should only really be set then the pile is moved.
			_topCard.setX(getX());
			_topCard.setY(getY());

			_topCard.draw(g2d);
		} else {
			g2d.draw(_form);
		}
	}

	public ActionEvent getActionEvent() {
		return new ActionEvent(this, EAT_PILE, "eat pile");
	}

	public LogicalCardPile getLogicalCardPile() {
		return _logicalCardPile;
	}

	public void update(Observable observable, Object object) {
		if (_logicalCardPile.getTop() != null)
			_topCard = new VisualCard(_logicalCardPile.getTop());
		else
			_topCard = null;
	}

}
