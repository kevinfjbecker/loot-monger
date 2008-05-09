package action;

import event.DiscardRequired;
import gameobject.BeserkBuff;
import gameobject.Player;

public class Berserk implements OpenAction {

	private BeserkBuff _berserkBuff;

	private Action _discardAction;

	private Player _player;

	public Berserk(Player player) {
		_berserkBuff = new BeserkBuff();
		_player = player;
	}

	public void completeOpenAspect() {
		_discardAction = _player.respond(new DiscardRequired());
	}

	public void execute() {
		_discardAction.execute();
		_player.addBuff(_berserkBuff);
	}

	public String toString(){
		return "Berserk";
	}
	
	public void undo() {
		_player.removeBuff(_berserkBuff);
		_discardAction.undo();
	}

}
