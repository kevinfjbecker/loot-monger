package event.handler;

import java.util.ArrayList;

import event.Event;



public class EventNotifier {

	private ArrayList<EventListener> _listeners;

	public EventNotifier() {
		_listeners = new ArrayList<EventListener>();
	}

	public void notifyListeners(Event event) {
		for (EventListener eventListener : _listeners)
			eventListener.onEvent(event);
	}

	public void addListener(EventListener eventListener) {
		_listeners.add(eventListener);
	}

	public void removeListener(EventListener eventListener) {
		_listeners.remove(eventListener);
	}

	public void clearListenters() {
		_listeners.clear();
	}
}