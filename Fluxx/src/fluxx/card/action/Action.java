package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;

public abstract class Action extends Card {

	public Action(String name, FluxxGame game) {
		super(name, game);
	}
	
	protected abstract void doWhateverItSays();

	public final void play() {
		doWhateverItSays();
		discard();
	}

}
