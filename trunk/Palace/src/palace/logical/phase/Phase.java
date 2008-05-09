package palace.logical.phase;

import java.awt.event.ActionListener;

import palace.logical.card.GameState;

public interface Phase extends ActionListener {

	void setGameState(GameState gameState);

}
