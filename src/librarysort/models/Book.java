package librarysort.models;

public class Book {
	
	private String id;
	
	private String name;
	
	private String author;
	
	public Book(String id, String name, String author) { 
		this.id = id; 
		this.name = name;
		this.author = author;
	}

	public String GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetAuthor() {
		return author;
	}
	
}
