package librarysort.generators;

import java.util.Random;

public class CategoryGenerator extends GeneratorBase<String> implements IGenerator<String> {

	private final int ComposeSize = 2;
	
	private String[] categories;
	
	public CategoryGenerator(Random random) throws Exception {
		super(random);
		this.categories = ResourceLoader.Get("words.txt");
	}
	
	@Override
	public String getNext() {
		var components = new String[ComposeSize];
		fillWithRandom(components, categories);
		
		return String.join(" ", components);
	}

}
