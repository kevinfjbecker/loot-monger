
package handler;

import message.event.IGameEvent;

public interface IGameEventHandler {
	
	void handle(IGameEvent gameEvent);

}
