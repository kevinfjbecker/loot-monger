package fluxx.card;

import fluxx.FluxxGame;
import fluxx.player.Player;

public abstract class Card implements Playable {

	protected FluxxGame _game;

	protected String _name;

	protected Player _player;

	public Card(String name, FluxxGame game) {
		_name = name;
		_game = game;
	}

	public void discard() {
		_game.getDiscardPile().add(this);
		_player = null;
	}

	public String getName() {
		return _name;
	}

	public Player getPlayer() {
		return _player;
	}

	public void setPlayer(Player player) {
		_player = player;
	}

	public String toString() {
		return _name;
	}

}
