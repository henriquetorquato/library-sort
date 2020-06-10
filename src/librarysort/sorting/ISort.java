package librarysort.sorting;

public interface ISort<T> {
	
	// Get the name of the sorting method
	public String getMethod();
	
	// Sort items
	public T[] sort(T[] items);
	
}
