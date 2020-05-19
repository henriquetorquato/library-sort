package librarysort.models;

public class Book {
	
	private String name;
	
	private String author;
	
	public Book(String name, String author) {
		this.name = name;
		this.author = author;
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetAuthor() {
		return author;
	}
	
}
