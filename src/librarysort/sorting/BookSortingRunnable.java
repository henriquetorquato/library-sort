package librarysort.sorting;

import librarysort.models.Book;
import librarysort.models.ISink;
import librarysort.models.Shelf;

public class BookSortingRunnable extends BookQuickSort implements Runnable {

	private final Book[] books;	
	private final int low;
	private final int high;
	
	private int index;
	private Boolean completed;
	
	public BookSortingRunnable(Book[] books, int low, int high) {
		this.books = books;
		this.low = low;
		this.high = high;
		this.completed = false;
	}

	@Override
	public String getMethod() {
		return "QuickSortThread";
	}
	
	@Override
	public void run()
	{	
		if (this.low < this.high) {
			this.index = partition(this.books, this.low, this.high);
			this.completed = true;
		}
				
		this.completed = true;
	}
	
	public Boolean isCompleted() {
		return this.completed;
	}
	
	public Book[] getResult() {
		return this.books;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getLow() {
		return this.low;
	}
	
	public int getHigh() {
		return this.high;
	}
	
}
