package librarysort.sorting;

import java.time.Duration;
import java.time.Instant;

import librarysort.models.Book;
import librarysort.models.ISink;
import librarysort.models.Shelf;

public class ShelfSortingRunnable implements Runnable {
	
	private String id;
	private Shelf result;
	private Boolean completed;
	private final ISort<Book> sorting;
	
	public ShelfSortingRunnable(String id, Shelf shelf, ISort<Book> sorting) {
		this.id = id;
		this.sorting = sorting;
		this.result = shelf;
		this.completed = false;
	}
	
	@Override
	public void run() {	
		var start = Instant.now();
		
		var books = this.sorting.sort(this.result.getBooks());
		this.result.setBooks(books);
		this.completed = true;
		
		var end = Instant.now();
		
		System.out.printf("> Thread %s - Took %dms\n", this.id, Duration.between(start, end).toMillis());
	}
	
	public Shelf getResult() {
		return this.result;
	}
	
	public Boolean isCompleted() {
		return this.completed;
	}
	
}
