package librarysort.generators;

import java.util.Random;

public abstract class GeneratorBase<T> implements IGenerator<T> {

	private Random random;
	
	public GeneratorBase(Random random) {
		this.random = random;
	}
	
	public abstract T getNext();
	
	@Override
	public void fill(T[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = getNext();
		}
	}
	
	public <TList> void fillWithRandom(TList[] array, TList[] source) {
		for (int i = 0; i < array.length; i++) {
			array[i] = getRandom(source);
		}
	}
	
	public <TList> TList getRandom(TList[] list) {
		var index = random.nextInt(list.length);
		return list[index];
	}
	
}
