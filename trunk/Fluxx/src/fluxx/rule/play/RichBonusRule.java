package fluxx.rule.play;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.DecoratingRule;
import fluxx.rule.Rule;

public class RichBonusRule extends DecoratingRule implements PlayRule {

	public RichBonusRule(FluxxGame game) {
		super(game);
	}

	@Override
	public void follow(Player player) {
		
		_next.follow(player);
		
		if(!player.getCardsInHand().isEmpty())
			return;
		
		boolean playerHasMostKeepers = true;
		for (Player otherPlayer : _game.getPlayers())
			if (otherPlayer != player
					&& otherPlayer.getKeepersInPlay().size() >= player
							.getKeepersInPlay().size())
				playerHasMostKeepers = false;

		if (playerHasMostKeepers) {
			if (player.chooseYesOrNo()) {
				player.playCard();
			}
		}

	}

	@Override
	protected boolean matchesType(Rule rule) {
		return rule instanceof PlayRule;
	}

	public int numberToBePlayed() {
		return ((PlayRule) _next).numberToBePlayed();
	}

}
