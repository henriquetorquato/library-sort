package librarysort.generators;

// Defines the methods of a object generator
public interface IGenerator<T> {
	
	// Creates a new instance
	public T GetNext(String id);
	
}
