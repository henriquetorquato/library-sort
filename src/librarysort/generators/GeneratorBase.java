package librarysort.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GeneratorBase<T> implements IGenerator<T> {

	private Random random;
	
	public GeneratorBase(Random random) {
		this.random = random;
	}
	
	public abstract T GetNext();

	@Override
	public List<T> GetNext(int size) {
		ArrayList<T> items = new ArrayList<T>();
		for(int i = 0; i < size; i++) {
			items.add(GetNext());
		}
		
		return items;
	}
	
	public String GetRandom(List<String> list) {
		return list.get(this.random.nextInt(list.size()));
	}
	
}
