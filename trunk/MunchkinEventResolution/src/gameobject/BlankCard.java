
package gameobject;

import java.util.ArrayList;

import action.Action;
import event.Event;

public class BlankCard implements Card {

	public ArrayList<Action> getOptions(Event event) {
		return null;
	}

	public String toString(){
		return "BlankCard";
	}
	
}
