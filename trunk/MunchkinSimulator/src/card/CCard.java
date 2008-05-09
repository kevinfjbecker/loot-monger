package card;

import listener.GameEventNotifier;

public abstract class CCard {

	protected GameEventNotifier gameEventNotifier = new GameEventNotifier();

	public abstract void discard();

	public GameEventNotifier getGameEventNotifier() {
		return(gameEventNotifier);
	}

}
