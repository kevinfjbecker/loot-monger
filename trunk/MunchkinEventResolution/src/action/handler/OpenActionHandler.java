package action.handler;

import action.OpenAction;

public class OpenActionHandler {

	private Executor _executor;

	public void handle(OpenAction openAction) {
		openAction.completeOpenAspect();
		_executor.execute(openAction);
	}

	public void setExecutor(Executor executor) {
		_executor = executor;
	}

}
