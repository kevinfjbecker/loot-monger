package event.handler;

import java.util.ArrayList;
import java.util.List;

import event.Event;

public class EventStack {

	private List<Event> _list;

	public EventStack() {
		_list = new ArrayList<Event>();
	}

	public void push(Event event) {
		_list.add(0, event);
	}

	public Event pop() {
		return _list.remove(0);
	}

	public Event top() {
		return _list.get(0);
	}

	public boolean isEmpty() {
		return _list.isEmpty();
	}

	public String toString() {
		String s = "event stack: " + _list.toString() + "\n";
		s += "              ^" ; // offsets "event stack: ["
		return s;
	}

}
