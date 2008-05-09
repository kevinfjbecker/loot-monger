package player;

import java.util.Comparator;


public class CHighestToLowestWithRollOff implements Comparator<Player> {

	public int compare(Player arg0, Player arg1) {
		if (arg1.getLevel() == arg0.getLevel()) {
			for (int i = 0;; i++)
				if (arg1.getRollOff(i) != arg0.getRollOff(i))
					return (arg1.getRollOff(i) - arg0.getRollOff(i));
		}
		return (arg1.getLevel() - arg0.getLevel());
	}

}
