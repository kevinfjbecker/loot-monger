package turn;

import listener.GameEventNotifier;
import listener.GameObserver;
import player.Player;

public abstract class APhase implements IPhase {

	private CTurn _turn;

	private GameEventNotifier _gameEventNotifier;

	public APhase(CTurn turn) {
		this._turn = turn;
		_gameEventNotifier = new GameEventNotifier();
		_gameEventNotifier.addListener(turn.getGameObserver());
	}

	public final void play(Player player) {
		notifyStartOfPhase();
		resolveAction(player);
		notifyEndOfPhase();
	}

	protected abstract void notifyStartOfPhase();

	protected abstract void resolveAction(Player player);

	protected abstract void notifyEndOfPhase();

	public void setGameObserver(GameObserver gameObserver) {
		_gameEventNotifier.addListener(gameObserver);
	}

	public GameEventNotifier getGameEventNotifier() {
		return (_gameEventNotifier);
	}

	public CTurn getTurn() {
		return (_turn);
	}
}
