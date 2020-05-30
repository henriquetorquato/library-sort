package librarysort.sorting;

import librarysort.models.Book;
import librarysort.models.ISink;

public class BookQuickSort extends Sort implements ISort<Book> {
	
	/*
	 * Implementation reference:
	 * https://www.geeksforgeeks.org/java-program-for-quicksort/
	 */
	
	private final ISink sink;
	
	public BookQuickSort(ISink sink) {
		this.sink = sink;
	}
	
	@Override
	public String GetMethod() {
		return "QuickSort";
	}
	
	@Override
	public Book[] Sort(Book[] items) {
		this.sink.PrintLine();
		sort(items, 0, items.length - 1);
		return items;
	}
	
	// Sort a complete array
	private void sort(Book[] books, int low, int high) {
		if (low < high) {
			// Order at pivot and get next position 
			int index = partition(books, low, high);
			
			// Calls recursive order for the other partitions
			sort(books, low, index - 1);
			sort(books, index + 1, high);
		}
	}
	
	// Orders partition and returns pivot
	private int partition(Book[] books, int low, int high) {
		// Get last element from partition as pivot
		var pivot = books[high];
		
		// Set the partition start
		var x = (low - 1);
		
		// Stating from low, up to high
		for (int y = low; y < high; y++) {
			var current = books[y];
			
			this.sink.ReplaceLine(String.format("> Comparing %s", current.toString()));
			
			// Checks if current comes before pivot in alphabetical order, or if both are equal
			// current <= pivot
			if (lessThanOrEqual(current.getCategory(), pivot.getCategory())) {
				// Move to next book
				x++;
				
				// Swaps both books
				var temp = books[x];
				books[x] = books[y];
				books[y] = temp;
			}
		}
		
		// Places pivot at the end
		var temp = books[x + 1];
		books[x + 1] = books[high];
		books[high] = temp;
		
		// Return the next partition position
		return x + 1;
	}

}
