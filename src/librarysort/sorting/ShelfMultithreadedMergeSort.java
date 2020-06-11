package librarysort.sorting;

import java.util.ArrayList;
import java.util.List;

import librarysort.models.Shelf;

public class ShelfMultithreadedMergeSort implements ISort<Shelf> {

	private final List<ShelfSortingRunnable> runnables;
	
	public ShelfMultithreadedMergeSort() {
		this.runnables = new ArrayList<ShelfSortingRunnable>();
	}
	
	@Override
	public String getMethod() {
		return "MultithreadedMergeSort";
	}

	@Override
	public Shelf[] sort(Shelf[] items) {
		// Create and start shelf sorting threads
		for (int i = 0; i < items.length; i++) {
			var runnable = new ShelfSortingRunnable(i, items[i]);
			var thread = new Thread(runnable);
			
			thread.start();
			this.runnables.add(runnable);
		}
		
		// While there is alive threads
		while (this.runnables.size() > 0) {
			for (int i = 0; i < this.runnables.size(); i++) {
				var runnable = this.runnables.get(i);
				
				// If thread is completed
				if (!runnable.isCompleted()) {
					continue;
				}
				
				// Update item with sorted shelf
				var index = runnable.getIndex();
				items[index] = runnable.getResult();
				
				// Remove the item from the thread list
				this.runnables.remove(i);
			}
		}
		
		return items;
	}

}
