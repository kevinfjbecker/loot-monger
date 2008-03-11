package palace.visual.card;

import java.util.Observable;

import palace.logical.card.LogicalCard;

import visual.Interactive;

public class VisualHand extends VisualPlayArea {

	public VisualHand() {
		super(new HorizontalOverlappedAlignment());
	}

	public void changeCardPosition(Interactive card) {
		if (!_elements.contains(card))
			return;
		if (elementToRightOf(card) == card)
			return;
		if (elementToRightOf(card) == null) {
			_elements.remove(card);
			_elements.add(card);
			alignElements();
			return;
		}
		_elements.remove(card);
		_elements.add(_elements.indexOf(elementToRightOf(card)), card);
		alignElements();
	}

	private Interactive elementToRightOf(Interactive element) {
		for (Interactive nextElement : _elements)
			if (nextElement.getCenterX() > element.getCenterX())
				return nextElement;
		return null;
	}

	public void grab(int x, int y) {
		if (pick(x, y) == this)
			super.grab(x, y);
		else
			pick(x, y).grab(x, y);
	}

	public Interactive pick(int x, int y) {
		if (!contains(x, y))
			return null;
		for (int i = _elements.size() - 1; i >= 0; i--)
			if (_elements.get(i).contains(x, y))
				return _elements.get(i);
		return this;
	}

	public void select(int x, int y) {
		if (pick(x, y) == this)
			super.select(x, y);
		else
			pick(x, y).select(x, y);
	}

	public void target(int x, int y) {
		if (pick(x, y) == this)
			super.target(x, y);
		else
			pick(x, y).target(x, y);
	}

	public void update(Observable logicalHand, Object logicalCard) {
		VisualCard visualCard = new VisualCard((LogicalCard) logicalCard);
		visualCard.setFaceUp();
		addElement(visualCard);
	}

}
