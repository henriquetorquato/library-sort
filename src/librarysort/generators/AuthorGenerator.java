package librarysort.generators;

import java.util.List;
import java.util.Random;
import librarysort.models.Author;

public class AuthorGenerator extends GeneratorBase<Author> implements IGenerator<Author> {

	private List<String> names;
	
	public AuthorGenerator(Random random) throws Exception {
		super(random);
		this.names = ResourceLoader.Get("names.txt");
	}

	@Override
	public Author GetNext() {
		var names = GetRandom(this.names, 2);
		return new Author(names.get(0), names.get(1));
	}

}
