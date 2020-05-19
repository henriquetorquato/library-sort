package librarysort.generators;

import java.util.List;
import java.util.Random;

public class CategoryGenerator extends GeneratorBase<String> implements IGenerator<String> {

	private final int ComposeSize = 2;
	
	private List<String> categories;
	
	public CategoryGenerator(Random random) throws Exception {
		super(random);
		this.categories = ResourceLoader.Load("categories.txt");
	}
	
	@Override
	public String GetNext() {
		String[] components = new String[ComposeSize];
		for(int i = 0; i < ComposeSize; i++) {
			components[i] = GetRandom(this.categories);
		}
		
		return String.join(" ", components);
	}

}
