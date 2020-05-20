package librarysort.generators;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import librarysort.models.Author;
import librarysort.models.Book;

public class BookGenerator extends GeneratorBase<Book> implements IGenerator<Book> {
	
	private List<Author> authors;
	private List<String> categories;
	private List<String> names;
	
	private List<String> templates = List.of(
		"The %s of %s",
		"A %s of %s",
		"%s %s"
	);
	
	public BookGenerator(
		Random random,
		List<Author> authors,
		List<String> categories) throws Exception
	{	
		super(random);
		this.authors = authors;
		this.categories = categories;
		this.names = ResourceLoader.Get("names.txt");
	}
	
	@Override
	public Book GetNext() {
		var template = GetRandom(this.templates);
		var names = GetRandom(this.names, 2);
		
		return new Book(
			UUID.randomUUID().toString(),
			String.format(template, names.get(0), names.get(1)),
			GetRandom(this.authors),
			GetRandom(this.categories));
	}

}
