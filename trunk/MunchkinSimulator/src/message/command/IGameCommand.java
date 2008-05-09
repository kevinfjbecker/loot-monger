package message.command;

import message.event.IGameEvent;

public interface IGameCommand extends IGameEvent {

	void execute();
}
