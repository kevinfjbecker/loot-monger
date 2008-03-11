package palace.event;


import java.awt.event.ActionEvent;

import palace.MouseInterpreter;
import palace.logical.card.LogicalCard;
import palace.visual.card.VisualCard;

import visual.VisualTarget;

public class PlayEvent extends ActionEvent {

	private static final long serialVersionUID = 1L;

	private LogicalCard _card;

	private VisualTarget _target;

	public PlayEvent(MouseInterpreter mouseInteractionHub, VisualCard card,
			VisualTarget target) {
		super(mouseInteractionHub, PalaceEvent.PLAY, "play");
		_card = card.getLogicalCard();
		_target = target;
	}

	public LogicalCard getCardPlayed() {
		return _card;
	}

	public VisualTarget getTarget() {
		return _target;
	}

}
