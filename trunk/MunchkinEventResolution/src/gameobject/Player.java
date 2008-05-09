package gameobject;

import static strategy.munchkin.StrategyTypes.ResolveCombat;
import event.DiscardRequired;
import event.Event;

import java.util.ArrayList;

import strategy.AStrategyDecorator;
import strategy.CStrategyContext;
import strategy.munchkin.GeneralResolveCombatStrategy;
import strategy.munchkin.IResolveCombat;
import action.Acknowledge;
import action.Action;
import action.Discard;

public class Player {

	private ArrayList<Buff> _buffs;

	private CharacterClass _characterClass;

	private ArrayList<Card> _hand;

	private int _level;

	private CStrategyContext _strategyContext;

	public Player() {
		_buffs = new ArrayList<Buff>();
		_level = 1;
		_hand = new ArrayList<Card>();
		_strategyContext = new CStrategyContext();
		_strategyContext.putStrategy(new GeneralResolveCombatStrategy(
				ResolveCombat));
	}

	public void acceptStrategyDecorator(AStrategyDecorator strategyDecorator) {
		strategyDecorator.overrideStrategyInContext(_strategyContext);
	}

	public void addBuff(Buff buff) {
		_buffs.add(buff);
	}

	public void addToHand(Card card) {
		_hand.add(card);
	}

	public Action chooseAction(ArrayList<Action> actions) {
		if (actions.isEmpty())
			return (new Acknowledge());
		return actions.get(0);
	}

	public void discard(Card card) {
		_hand.remove(card);
	}

	private int getBuffBonus() {
		int buffBonus = 0;
		for (Buff buff : _buffs)
			buffBonus += buff.getBonus();
		return buffBonus;
	}

	public int getCombatLevel(Monster _monster) {
		return _level + getBuffBonus();
	}

	private void getDiscardOptions(ArrayList<Action> options) {
		for (Card card : _hand)
			options.add(new Discard(this, card));
	}

	public ArrayList<Action> getOptions(Event event) {
		ArrayList<Action> options = new ArrayList<Action>();
		if (event instanceof DiscardRequired)
			getDiscardOptions(options);
		options.addAll(_characterClass.getOptions(event));
		return options;
	}

	public IResolveCombat getResolveCombatStrategy() {
		return (IResolveCombat) _strategyContext.getStrategy(ResolveCombat);
	}

	public boolean hasCards() {
		return !_hand.isEmpty();
	}

	public void removeBuff(Buff buff) {
		_buffs.remove(buff);
	}

	public Action respond(Event event) {
		return chooseAction(getOptions(event));
	}

	public void setCharacterClass(CharacterClass characterClass) {
		_characterClass = characterClass;
		_characterClass.setPlayer(this);
	}

	public String toString() {
		String s;
		s = "Player {\n";
		s += "\tLevel: " + _level + "(+" + getBuffBonus() + ")\n";
		s += "\tClass: " + _characterClass + "\n";
		s += "\tBuffs: " + _buffs + "\n";
		s += "\tHand: " + _hand + "\n";
		s += "}";
		return s;
	}
}