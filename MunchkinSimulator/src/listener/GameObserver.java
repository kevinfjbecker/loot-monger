package listener;

import handler.AHandlerLink;
import handler.IGameEventHandler;
import message.event.IGameEvent;

public class GameObserver implements IGameEventListener, IGameEventHandler {

	private AHandlerLink handlerChain;
	private GameEventNotifier gameEventNotifier;
	
	public void setHandlerChain(AHandlerLink handlerChain){
		this.handlerChain = handlerChain;
		gameEventNotifier = new GameEventNotifier();
	}
	
	public GameEventNotifier getGameEventNotifier(){
		return(gameEventNotifier);
	}
	
	public void setGameObserver(IGameEventListener gameObserver) {
		getGameEventNotifier().addListener(gameObserver);
	}
	
	public void observe(IGameEvent gameEvent) {
		handle(gameEvent);
		getGameEventNotifier().notifyListeners(gameEvent);
	}

	public void handle(IGameEvent gameEvent) {
		handlerChain.handle(gameEvent);
	}

}
