package palace.logical.card;

public interface LogicalPlayArea extends Logical, Iterable<LogicalCard> {

	void add(LogicalCard cardPlayed);

	LogicalPlayer getPlayer();

	void remove(Logical logical);
	
	int size();

}
