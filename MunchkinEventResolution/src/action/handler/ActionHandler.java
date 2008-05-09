package action.handler;

import action.Action;
import action.OpenAction;

public class ActionHandler {

	private Executor _executor;
	private OpenActionHandler _openActionHandler;

	public void handle(Action action) {
		if(action instanceof OpenAction){
			_openActionHandler.handle((OpenAction)action);
			return;
		}
		_executor.execute(action);
	}

	public void setExecutor(Executor executor) {
		_executor = executor;
	}
	
	public void setOpenActionHandler(OpenActionHandler openActionHandler){
		_openActionHandler = openActionHandler;
	}

}
