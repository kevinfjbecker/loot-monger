package palace.event;

import static palace.event.PalaceEvent.PLAY_MULTIPLE;

import java.awt.event.ActionEvent;

import palace.MouseInterpreter;
import palace.logical.card.LogicalCard;
import palace.toolkit.visual.VisualTarget;
import palace.visual.card.VisualCard;

public class PlayMultipleEvent extends ActionEvent {

	private static final long serialVersionUID = 1L;

	private LogicalCard[] _cards;

	private VisualTarget _target;

	public PlayMultipleEvent(MouseInterpreter mouseInteractionHub,
			VisualCard[] cards, VisualTarget target) {
		super(mouseInteractionHub, PLAY_MULTIPLE, "playMultiple");
		_cards = new LogicalCard[cards.length];
		for (int i = 0; i < cards.length; i++)
			_cards[i] = cards[i].getLogicalCard();
		_target = target;
	}

	public LogicalCard[] getCardsPlayed() {
		return _cards;
	}

	public VisualTarget getTarget() {
		return _target;
	}

}
