package librarysort.generators;

import java.util.Random;
import java.util.UUID;
import librarysort.models.Author;
import librarysort.models.Book;

public class BookGenerator extends GeneratorBase<Book> implements IGenerator<Book> {
	
	private final int ComposeSize = 2;
	
	private Author[] authors;
	private String[] categories;
	private String[] names;
	
	private String[] templates = new String[] {
		"%s and the %s",
		"%s of %s",
		"%s %s"
	};
	
	public BookGenerator(
		Random random,
		Author[] authors,
		String[] categories) throws Exception
	{	
		super(random);
		this.authors = authors;
		this.categories = categories;
		this.names = ResourceLoader.Get("names.txt");
	}
	
	@Override
	public Book getNext() {
		var template = getRandom(this.templates);
		var components = new String[ComposeSize];
		fillWithRandom(components, this.names);
		
		return new Book(
			UUID.randomUUID().toString(),
			String.format(template, components[0], components[1]),
			getRandom(this.authors),
			getRandom(this.categories));
	}

}
