package librarysort.generators;

import java.util.List;
import java.util.Random;

import librarysort.models.Book;

public class BookGenerator extends GeneratorBase<Book> implements IGenerator<Book> {

	private List<String> authors;
	
	public BookGenerator(Random random) throws Exception {
		super(random);
		this.authors = ResourceLoader.Load("authors.txt");
	}
	
	@Override
	public Book GetNext() {
		var author = GetRandom(authors);
		var book = new Book("a", "teste1", author);
		return book;
	}

}
