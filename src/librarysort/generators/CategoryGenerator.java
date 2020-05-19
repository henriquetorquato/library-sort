package librarysort.generators;

import java.util.List;
import java.util.Random;

public class CategoryGenerator extends GeneratorBase<String> implements IGenerator<String> {

	private final int ComposeSize = 2;
	
	private List<String> categories;
	
	public CategoryGenerator(Random random) throws Exception {
		super(random);
		this.categories = ResourceLoader.Get("words.txt");
	}
	
	@Override
	public String GetNext() {
		var components = GetRandom(this.categories, ComposeSize);		
		return String.join(" ", components);
	}

}
