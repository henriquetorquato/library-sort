package librarysort.generators;

import java.util.List;
import java.util.Random;

public class ListSelector<T> {

	private Random random;
	
	public ListSelector(Random random) {
		this.random = random;
	}
	
	public T GetRandom(List<T> list) {
		return list.get(random.nextInt(list.size()));
	}
	
}
