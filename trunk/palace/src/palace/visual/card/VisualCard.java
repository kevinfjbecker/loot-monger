package palace.visual.card;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import palace.logical.card.LogicalCard;

import visual.AbstractInteractive;

public class VisualCard extends AbstractInteractive {

	public static final int HEIGHT = 97;

	public static final int RADIUS = 5;

	public static final int WIDTH = 73;

	private BufferedImage _cardFace;

	private BufferedImage _cardBack;

	private boolean _isFaceUp;

	private LogicalCard _logicalCard;

	protected VisualCard(LogicalCard logicalCard) {
		_logicalCard = logicalCard;
		_logicalCard.setVisualCard(this);
		_form = new RoundRectangle2D.Double(0, 0, 0, 0, RADIUS, RADIUS);
		setHeight(HEIGHT);
		setWidth(WIDTH);
		_cardBack = CardImageManager.getCardBack();
		_cardFace = CardImageManager.getCardFace(this);
	}

	public void draw(Graphics2D g2d) {

		if (_isFaceUp)
			g2d.drawImage((Image) _cardFace, getX(), getY(), getWidth(),
					getHeight(), null);
		else
			g2d.drawImage((Image) _cardBack, getX(), getY(), getWidth(),
					getHeight(), null);

		if (isTargeted() || isSelected()) {
			Color previousColor = g2d.getColor();
			g2d.setColor(Color.GREEN);
			g2d.draw(_form);
			g2d.setColor(previousColor);
		}

		untarget();
	}

	public LogicalCard getLogicalCard() {
		return _logicalCard;
	}

	public void setFaceDown() {
		_isFaceUp = false;
	}

	public void setFaceUp() {
		_isFaceUp = true;
	}

	public String toString() {
		return _logicalCard.toString();
	}

}
