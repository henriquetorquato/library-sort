package librarysort.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import librarysort.models.Book;

public class BookMultithreadedQuickSort implements ISort<Book> {

	private final List<BookSortingRunnable> runnables;
	
	public BookMultithreadedQuickSort() {
		this.runnables = new ArrayList<BookSortingRunnable>();
	}
	
	@Override
	public String getMethod() {
		return "MultithreadedQuickSort";
	}

	@Override
	public Book[] sort(Book[] items) {
		// Create the initial thread
		createThread(items, 0, items.length - 1);
		
		// While there is a thread alive
		while (this.runnables.size() > 0) {
			for (int i = 0; i < this.runnables.size(); i++) {
				var runnable = this.runnables.get(i);
				
				if (!runnable.isCompleted()) {
					continue;
				}
				
				var low = runnable.getLow();
				var high = runnable.getHigh();
				
				setValues(items, runnable.getResult(), low, high);
				var index = runnable.getIndex();
				
				// If thread resulted in another partition
				if (low < high) {					
					// Start next partition threads
					createThread(items, low, index - 1);
					createThread(items, index + 1, high);
				}
				
				runnables.remove(i);
			}
		}
		
		return items;
	}
	
	private void createThread(Book[] books, int low, int high) {
		var section = Arrays.copyOfRange(books, low, high + 1);
		var runnable = new BookSortingRunnable(section, low, high);
		var thread = new Thread(runnable);
		
		thread.start();
		runnables.add(runnable);
	}
	
	private void setValues(Book[] target, Book[] source, int start, int end) {
		var sourceIndex = 0;
		for (int i = start; i <= end; i++) {
			target[i] = source[sourceIndex++];
		}
	}
}
