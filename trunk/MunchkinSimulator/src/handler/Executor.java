package handler;

import message.command.IGameCommand;
import message.event.IGameEvent;

public class Executor extends AHandlerLink {

	public Executor(IGameEventHandler handler) {
		super(handler);
	}

	public void processEvent(IGameEvent gameEvent) {
		if (gameEvent instanceof IGameCommand)
			((IGameCommand) gameEvent).execute();
	}

}
