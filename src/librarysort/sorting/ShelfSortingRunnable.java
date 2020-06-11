package librarysort.sorting;

import librarysort.models.Shelf;

public class ShelfSortingRunnable extends BookMergeSort implements Runnable {
	
	private int index;
	private Shelf result;
	private Boolean completed;
	
	public ShelfSortingRunnable(int index, Shelf shelf) {
		this.index = index;
		this.result = shelf;
		this.completed = false;
	}
	
	@Override
	public void run() {
		var books = sort(result.getBooks());
		result.setBooks(books);
		completed = true;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public Shelf getResult() {
		return this.result;
	}
	
	public Boolean isCompleted() {
		return this.completed;
	}
	
}
