package palace.toolkit.visitor;

public interface Visitable<T> {

	void accept(Visitor<T> v);
	
}
