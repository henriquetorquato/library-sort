package librarysort.generators;

import java.util.Random;
import java.util.UUID;
import librarysort.models.Author;
import librarysort.models.Book;

public class BookGenerator extends GeneratorBase<Book> implements IGenerator<Book> {
	
	private final int ComposeSizeMin = 3;
	private final int ComposeSizeMax = 10;
	
	private Author[] authors;
	private String[] categories;
	private String[] words;
	private final Random random;
	
	public BookGenerator(
		Random random,
		Author[] authors,
		String[] categories) throws Exception
	{	
		super(random);
		this.random = random;
		this.authors = authors;
		this.categories = categories;
		this.words = ResourceLoader.Get("words.txt");
	}
	
	@Override
	public Book getNext() {	
		return new Book(
			UUID.randomUUID().toString(),
			getName(),
			getRandom(this.authors),
			getRandom(this.categories));
	}
	
	public String getName() {
		var size = random.nextInt((ComposeSizeMax - ComposeSizeMin) + 1) + ComposeSizeMin;
		var components = new String[size];
		fillWithRandom(components, words);
		
		var name = String.join(" ", components);
		name = String.format("%s%s", name.substring(0, 1).toUpperCase(), name.substring(1)); 
		return name;
	}

}
