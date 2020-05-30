package librarysort.models;

import java.util.ArrayList;

public class Shelf {

	private String id;
	
	private int limit;
	
	private int length;
	
	private Book[] books;
	
	private ArrayList<String> categories;
	
	public Shelf(int limit) {
		this.length = 0;
		this.books = new Book[limit];
		this.categories = new ArrayList<String>();
		this.limit = limit;
	}
	
	public Boolean haveSpace() {
		return length < limit;
	}
	
	public void addBook(Book book) {
		this.addCategory(book.getCategory());
		this.books[this.length++] = book;
	}
	
	public Book[] getBooks() {
		return this.books;
	}
	
	public void setBooks(Book[] books) {
		this.books = books;
	}
	
	public ArrayList<String> getCategoies() {
		return this.categories;
	}
	
	public String getId() {
		return this.id;
	}
	
	/*
	 * Uses the shelf index to build the id.
	 * The id format is as follows:
	 * 	0AB:
	 *   - 0 -> the shelf index.
	 *   - A -> the first letter of the first category.
	 *   - B -> the first letter of the last category.
	 */
	public void buildId(int index) {
		var builder = new StringBuilder();
		builder.append(Integer.toString(index));
		
		if (this.categories.size() > 0) {
			var first = this.categories.get(0);
			builder.append(first.charAt(0));
			
			var last = this.categories.get(this.categories.size() - 1);
			builder.append(last.charAt(0));
		}
		
		this.id = builder.toString();
	}
	
	private void addCategory(String category) {
		if (!this.categories.contains(category)) {
			this.categories.add(category);
		}
	}
	
}
