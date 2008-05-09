package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.rule.Rule;

public abstract class NewRule extends Card {

	protected Rule _myRule;

	public NewRule(String name, FluxxGame game) {
		super(name, game);
	}

	protected void addToNewRulesList() {
		for (int i = 0; i < _game.getNewRules().size(); i++)
			if (matchesType(_game.getNewRules().get(i)))
				_game.getNewRules().remove(i).discard();
		_game.getNewRules().add(this);
		_myRule.addToList(_game.getTurn());
	}

	public void discard(){
		super.discard();
		_myRule.detach();
	}
	
	protected abstract boolean matchesType(NewRule newRule);
	
	public void play() {
		addToNewRulesList();
	}

}
