package dice;

public abstract class ASixSidedDie {
	public static int roll() {
		return (((int) (Math.random() * 6.0)) + 1);
	}
}
