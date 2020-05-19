package librarysort.generators;

import java.util.List;

// Defines the methods of a object generator
public interface IGenerator<T> {
	
	// Creates a new instance
	public T GetNext();
	
	// Creates n new instances
	public List<T> GetNext(int size);
	
}
