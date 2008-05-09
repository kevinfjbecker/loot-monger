package action.handler;

import action.Action;

public class Executor {

	private History _history;

	public void execute(Action action) {
		action.execute();
		_history.add(action);
	}

	public void setHistory(History history) {
		_history = history;
	}

}
