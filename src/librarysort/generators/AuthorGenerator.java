package librarysort.generators;

import java.util.Random;
import librarysort.models.Author;

public class AuthorGenerator extends GeneratorBase<Author> implements IGenerator<Author> {

	private final int ComposeSize = 2;
	
	private String[] names;
	
	public AuthorGenerator(Random random) throws Exception {
		super(random);
		this.names = ResourceLoader.Get("names.txt");
	}

	@Override
	public Author getNext() {
		var names = new String[ComposeSize];
		fillWithRandom(names, this.names);
		
		return new Author(names[0], names[1]);
	}

}
