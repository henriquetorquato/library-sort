package librarysort.generators;

import java.util.List;
import java.util.Random;

import librarysort.models.Book;

public class BookGenerator implements IGenerator<Book> {

	private List<String> authors;	
	private ListSelector<String> listSelector;
	
	public BookGenerator(Random random) throws Exception {
		LoadResources();
		this.listSelector = new ListSelector<String>(random);
	}
	
	@Override
	public Book GetNext(String id) {
		var author = this.listSelector.GetRandom(authors);
		var book = new Book("teste1", author);
		return book;
	}
	
	private void LoadResources() throws Exception {
		var currentPath = System.getProperty("user.dir");
		var authorsPath = String.format("%s\\%s", currentPath, "bin/authors.txt");
		
		this.authors = ResourceLoader.Load(authorsPath);
	}

}
