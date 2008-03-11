package palace.visual.card;

import static palace.event.PalaceEvent.DRAW;
import static palace.visual.card.VisualCard.HEIGHT;
import static palace.visual.card.VisualCard.RADIUS;
import static palace.visual.card.VisualCard.WIDTH;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import palace.logical.card.LogicalDeck;
import palace.toolkit.visual.AbstractInteractive;
import palace.toolkit.visual.Active;


public class VisualDeck extends AbstractInteractive implements Active {

	private BufferedImage _cardBack;

	private LogicalDeck _logicalDeck;

	public VisualDeck(LogicalDeck logicalDeck) {
		_logicalDeck = logicalDeck;
		_logicalDeck.setVisualDeck(this);
		_form = new RoundRectangle2D.Double(0, 0, 0, 0, RADIUS, RADIUS);

		_cardBack = CardImageManager.getCardBack();
		
		// TODO waaaagh, magic numbers!
		setHeight(HEIGHT + 6);
		// TODO waaaagh, magic numbers!
		setWidth(WIDTH + 6);
	}

	public void draw(Graphics2D g2d) {
		if(!_logicalDeck.isEmpty()){
		for (int i = 3; i >= 0; i--)
			g2d.draw(new RoundRectangle2D.Double(getX() + 2 * i,
					getY() + 2 * i, WIDTH, HEIGHT, RADIUS, RADIUS));
		
		g2d.drawImage((Image) _cardBack, getX(), getY(), WIDTH,
				HEIGHT, null);
		}else{
			g2d.draw(_form);
		}
	}
	
	public ActionEvent getActionEvent() {
		return new ActionEvent(this, DRAW, "draw");
	}

}
