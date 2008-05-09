package action.handler;

import java.util.ArrayList;

import action.Action;

public class History {

	private ArrayList<Action> _actions;

	private int _position;

	public History() {
		_actions = new ArrayList<Action>();
		_position = -1;
	}

	public void add(Action action) {
		_actions.add(action);
		_position++;
	}

	public void commit() {
		if (_position < _actions.size() - 1)
			_actions.subList(_position + 1, _actions.size()).clear();
	}

	public void rollBack(int i) {
		if (_actions.isEmpty())
			return;
		for (; i > 0 && _position >= 0; i--)
			_actions.get(_position--).undo();
	}

	public void rollForward(int i) {
		if (_actions.isEmpty() || _position >= _actions.size() - 1)
			return;
		for (; i > 0 && _position < _actions.size() - 1; i--)
			_actions.get(++_position).execute();
	}

	public String toString() {
		String s = "history: " + _actions.toString() + "\n";
		s += "          "; // offsets "history: ["
		for (int k = 0; k < _position && _position > -1; k++) {
			for (int i = 0; i < _actions.get(k).toString().length(); i++)
				s += " ";
			s += "  "; // offsets ", " between list entries
		}
		s += "^";
		return s;
	}

}
