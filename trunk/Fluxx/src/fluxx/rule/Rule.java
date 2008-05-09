package fluxx.rule;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.player.Player;

public abstract class Rule {

	protected FluxxGame _game;

	protected ArrayList<Rule> _list;

	protected Rule _next;

	protected Rule _previous;

	public Rule(FluxxGame game) {
		_game = game;
	}

	public void addToList(ArrayList<Rule> list) {
		_list = list;
		attach(getMatchingRule(list));
	}

	protected abstract void attach(Rule rule);

	public void clear() {
		_list = null;
		_next = null;
		_previous = null;
	}

	public void detach() {
		if (_next == null)
			if (_previous == null) {
				if (_list != null && _list.contains(this))
					_list.remove(this);
			} else
				_previous._next = null;
		else if (_previous == null) {
			_list.add(_list.indexOf(this), _next);
			_list.remove(this);
			_next._previous = null;
		} else {
			_previous._next = _next;
			_next._previous = _previous;
		}

		clear();
	}

	protected Rule descendToBasicRule(Rule rule) {
		for (; !(rule instanceof BasicRule); rule = rule._next)
			;
		return rule;
	}

	public abstract void follow(Player player);

	private Rule getMatchingRule(ArrayList<Rule> list) {
		for (Rule rule : list)
			if (matchesType(rule))
				return rule;
		return null;
	}

	protected void insertInFrontOf(Rule rule) {
		rule._previous._next = this;
		_previous = rule._previous;
	}

	protected void linkForwardTo(Rule rule) {
		_next = rule;
		rule._previous = this;
	}

	protected abstract boolean matchesType(Rule rule);

	protected void replaceTopLevelConnection(Rule rule) {
		_list.add(_list.indexOf(rule), this);
		_list.remove(rule);
	}

	protected void swapOutPrevious(Rule rule) {
		if (rule._previous._previous == null)
			replaceTopLevelConnection(rule._previous);
		else {
			rule._previous._previous._next = this;
			_previous = rule._previous._previous;
		}
		rule._previous.clear();
	}

}
