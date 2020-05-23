package librarysort.models;

public class Book {
	
	private String id;
	
	private String name;
	
	private Author author;
	
	private String category;
	
	public Book(String id, String name, Author author, String category) { 
		this.id = id; 
		this.name = name;
		this.author = author;
		this.category = category;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String toString() {
		return String.format("%s. %s (%s)", author.toString(), name, category);
	}
	
}
