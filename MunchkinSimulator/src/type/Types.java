package type;



public abstract class Types {

	public static final IType startOf;
	public static final IType endOf;
	
	static {
		startOf = new CStartOfPhaseType();
		endOf = new CEndOfPhaseType();
	}
}
