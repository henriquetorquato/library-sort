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
	
	public <TList> TList GetRandom(List<TList> list) {
		var arr = list.toArray();
		return list.get(this.random.nextInt(arr.length));
	}
	
	public <TList> List<TList> GetRandom(List<TList> list, int count) {
		var components = new Object[count];
		for(int i = 0; i < count; i++) {
			components[i] = GetRandom(list);
		}
		
		return (List<TList>) List.of(components);
	}
	
}
