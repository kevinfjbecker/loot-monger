package gameobject;

import java.util.ArrayList;
import action.Action;
import event.Event;

public interface GetOptions {

	ArrayList<Action> getOptions(Event event);

}
