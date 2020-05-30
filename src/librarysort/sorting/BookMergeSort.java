package librarysort.sorting;

import librarysort.models.Book;
import librarysort.models.ISink;

public class BookMergeSort extends Sort implements ISort<Book> {
	
	@Override
	public String GetMethod() {
		return "MergeSort";
	}

	@Override
	public Book[] Sort(Book[] items) {
		return sort(items);
	}
	
	// Sort the book array
	private Book[] sort(Book[] books) {
		// Array size limit check
		// If there only two items to compare, then is the minimum size
		if (books.length < 2) {
			return books;
		}
		
		// Get the middle of the array
		var middle = books.length / 2;
		
		// Split the array and create both half's
		var first = copyBooks(books, 0, middle);
		var second = copyBooks(books, middle, books.length);
		
		// Check for more array iterations
		first = sort(first);		
		second = sort(second);
		
		// Merge both half's of the array and returns the sorted array
		return merge(books, first, second);
	}
	
	// Merge two half's of an array
	private Book[] merge(Book[] books, Book[] first, Book[] second) {
		// Create indexes for all of the arrays
		int firstIndex = 0;
		int secondIndex = 0;
		int booksIndex = 0;
		
		// While there's a array not unchecked
		while (firstIndex < first.length && secondIndex < second.length) {
			// Compare the books names to check if is the first or not
			var isFirst = lessThanOrEqual(first[firstIndex].getName(), second[secondIndex].getName());
			
			if (isFirst) {
				// If is the first half, then use it.
				books[booksIndex++] = first[firstIndex++];
			} else {
				// Otherwise use the second
				books[booksIndex++] = second[secondIndex++];
			}
		}
		
		// Check if all the content of the first array has being copied to the final array
		while (firstIndex < first.length) {
			books[booksIndex++] = first[firstIndex++];
		}
		
		// Makes the same check, but now for the second array
		while (secondIndex < second.length) {
			books[booksIndex++] = second[secondIndex++];
		}
		
		// Return the merged books
		return books;
	}
	
	// Copies the content of a array to a new array
	private Book[] copyBooks(Book[] books, int start, int end) {
		var newBooks = new Book[end - start];
		var booksIndex = 0;
		for (int i = start; i < end; i++) {
			newBooks[booksIndex] = books[i];
			booksIndex++;
		}
		return newBooks;
	}

}
