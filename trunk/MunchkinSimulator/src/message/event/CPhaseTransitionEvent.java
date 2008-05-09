package message.event;

import turn.IPhase;
import type.IType;

public class CPhaseTransitionEvent implements IGameEvent {

	public final IType type;

	public final IPhase phase;

	public CPhaseTransitionEvent(IType type, IPhase phase) {
		this.type = type;
		this.phase = phase;
	}

}
