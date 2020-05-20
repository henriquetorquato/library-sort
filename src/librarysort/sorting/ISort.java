package librarysort.sorting;

public interface ISort<T> {
	
	// Get the name of the sorting method
	public String GetMethod();
	
	// Sort items
	public T[] Sort(T[] items);
	
}
