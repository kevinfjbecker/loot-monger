package listener;


import java.util.ArrayList;

import message.event.IGameEvent;


public class GameEventNotifier {

	private ArrayList<IGameEventListener> gameEventListenerList;

	public GameEventNotifier() {
		gameEventListenerList = new ArrayList<IGameEventListener>();
	}

	public void notifyListeners(IGameEvent gameEvent) {
		for (int i = 0; i < gameEventListenerList.size(); i++)
			gameEventListenerList.get(i).observe(gameEvent);
	}

	public void addListener(IGameEventListener gameEventListener) {
		gameEventListenerList.add(gameEventListener);
	}

	public void removeListener(IGameEventListener gameEventListener) {
		gameEventListenerList.remove(gameEventListener);
	}

	public void clearListenters() {
		gameEventListenerList.clear();
	}
}
