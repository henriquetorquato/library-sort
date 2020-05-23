package librarysort.sorting;

import librarysort.models.Book;

public class BookMergeSort implements ISort<Book> {

	@Override
	public String GetMethod() {
		return "MergeSort";
	}

	@Override
	public Book[] Sort(Book[] items) {
		return items;
	}

}
