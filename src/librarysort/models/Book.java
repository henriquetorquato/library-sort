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

	public String GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public Author GetAuthor() {
		return author;
	}
	
	public String GetCategory() {
		return category;
	}
	
	public String ToString() {
		return String.format("%s. %s (%s)", author.ToString(), name, category);
	}
	
}
