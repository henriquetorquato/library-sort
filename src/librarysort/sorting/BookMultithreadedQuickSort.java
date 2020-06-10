package librarysort.sorting;

import java.util.ArrayList;
import java.util.List;

import librarysort.models.Book;
import librarysort.models.ISink;

public class BookMultithreadedQuickSort extends BookQuickSort implements ISort<Book> {

	private final List<BookSortingRunnable> threads;
	
	public BookMultithreadedQuickSort() {
		this.threads = new ArrayList<BookSortingRunnable>();
	}
	
	@Override
	public String getMethod() {
		return "MultithreadedQuickSort";
	}

	@Override
	public Book[] sort(Book[] items) {
		// Get the initial partition index
		int initialIndex = partition(items, 0, items.length - 1);
		
		// Start threads for the next partitions
		startThread(items.clone(), 0, initialIndex - 1);
		startThread(items.clone(), initialIndex + 1, items.length - 1);
		
		// While there is a thread alive
		while (this.threads.size() > 0) {
			for (int i = 0; i < this.threads.size(); i++) {
				var thread = this.threads.get(i);
				
				if (!thread.isCompleted()) {
					continue;
				}
				
				var low = thread.getLow();
				var high = thread.getHigh();
				
				setValues(items, thread.getResult(), low, high);
				var index = thread.getIndex();
				
				// If thread resulted in another partition
				if (low < high) {					
					// Start next partition threads
					startThread(items.clone(), low, index - 1);
					startThread(items.clone(), index + 1, high);
				}
				
				threads.remove(i);
			}
		}
		
		return items;
	}
	
	private void startThread(Book[] books, int low, int high) {
		var thread = new BookSortingRunnable(books, low, high);
		thread.run();
		threads.add(thread);
	}
	
	private void setValues(Book[] target, Book[] source, int start, int end) {
		for (int i = start; i <= end; i++) {
			target[i] = source[i];
		}
	}
}
