package librarysort.sorting;
import librarysort.models.Book;
import librarysort.models.Shelf;

public class ShelfSortingRunnable implements Runnable {
	
	private Shelf result;
	private Boolean completed;
	private final ISort<Book> sorting;
	
	public ShelfSortingRunnable(Shelf shelf, ISort<Book> sorting) {
		this.sorting = sorting;
		this.result = shelf;
		this.completed = false;
	}
	
	@Override
	public void run() {			
		var books = this.sorting.sort(this.result.getBooks());
		this.result.setBooks(books);
		this.completed = true;
	}
	
	public Shelf getResult() {
		return this.result;
	}
	
	public Boolean isCompleted() {
		return this.completed;
	}
	
}
