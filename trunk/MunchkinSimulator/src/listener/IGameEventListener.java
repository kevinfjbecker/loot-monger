package listener;

import message.event.IGameEvent;

public interface IGameEventListener {

	public void observe(IGameEvent gameEvent);
}
