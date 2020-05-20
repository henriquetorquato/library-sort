package librarysort.sorting;

import librarysort.models.Book;

public class BookQuickSort implements ISort<Book> {
	
	/*
	 * Implementation reference:
	 * https://www.geeksforgeeks.org/java-program-for-quicksort/
	 */
	
	@Override
	public String GetMethod() {
		return "QuickSort";
	}
	
	@Override
	public Book[] Sort(Book[] items) {
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
			
			// Checks if current comes before pivot in alphabetical order, or if both are equal
			// current <= pivot
			if (lessThanOrEqual(current.GetCategory(), pivot.GetCategory())) {
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
	
	// Used to compare if a comes before b in alphabetical order, or if both strings are equal
	private Boolean lessThanOrEqual(String a, String b) {
		var result = a.compareTo(b);
		return result <= 0;
	}

}
