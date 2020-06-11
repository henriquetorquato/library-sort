package librarysort.generators;

// Defines the methods of a object generator
public interface IGenerator<T> {
	
	// Creates a new instance
	public T getNext();
	
	// Creates n new instances calling the abstract `GetNext`
	public void fill(T[] array);
	
}
