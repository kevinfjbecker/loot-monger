package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.Rule;

public class TakeAnotherTurn extends Action {

	 private Rule _myRule;

	public class TriggerTurnRule extends Rule {

		public TriggerTurnRule(FluxxGame game) {
			super(game);
		}

		@Override
		public void addToList(ArrayList<Rule> list) {
			_list = list;
			list.add(this);
		}

		@Override
		protected void attach(Rule rule) {
		}

		public void detach() {
			_list.remove(this);
		}

		@Override
		public void follow(Player player) {
			detach();
			_game.playTurn(player);
			clear();
		}

		@Override
		protected boolean matchesType(Rule rule) {
			return rule instanceof TriggerTurnRule;
		}

	}

	public TakeAnotherTurn(String name, FluxxGame game) {
		super(name, game);
		 _myRule = new TriggerTurnRule(game);
	}

	@Override
	protected void doWhateverItSays() {
		 _myRule.addToList(_game.getTurn());
	}

}
