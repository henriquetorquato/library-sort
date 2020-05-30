package librarysort.sorting;

import librarysort.models.Book;
import librarysort.models.ISink;
import librarysort.models.Shelf;

public class ShelfSortingRunnable implements Runnable {

	private String id;
	
	private final ISort<Book> sorting;
	private final ISink sink;
	
	private Shelf result;
	private Boolean completed;
	
	public ShelfSortingRunnable(String id, Shelf shelf, ISort<Book> sorting, ISink sink) {
		this.id = id;
		this.sorting = sorting;
		this.sink = sink;
		this.result = shelf;
		this.completed = false;
	}
	
	@Override
	public void run() {
		sink.PrintLine(
				String.format(">> Starting sorting shelf %s using %s", this.id, this.result.getId(), sorting.GetMethod()));
		
		var books = this.sorting.Sort(this.result.getBooks());
		this.result.setBooks(books);
		this.completed = true;
		
		sink.PrintLine(
				String.format(">> Finished sorting shelf %s", this.id, this.result.getId()));
	}
	
	public Shelf getResult() {
		return this.result;
	}
	
	public Boolean isCompleted() {
		return this.completed;
	}
	
}
