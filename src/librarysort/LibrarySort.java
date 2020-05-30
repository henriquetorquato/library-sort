package librarysort;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import librarysort.generators.AuthorGenerator;
import librarysort.generators.BookGenerator;
import librarysort.generators.CategoryGenerator;
import librarysort.generators.IGenerator;
import librarysort.models.Author;
import librarysort.models.Book;
import librarysort.models.ISink;
import librarysort.models.Shelf;
import librarysort.models.ShelfBuilder;
import librarysort.sorting.BookMergeSort;
import librarysort.sorting.BookQuickSort;
import librarysort.sorting.ISort;
import librarysort.sorting.ShelfSortingRunnable;

public class LibrarySort {

	private static final String ProgressTemplate = "> [%d/%d] %s";
	private static ISink sink;
	
	public static void main(String[] args) throws Exception {
		try 
		{
			// Creates the sink instance
			sink = new ConsoleSink(true);
			
			// Create the random object for the generators
			var random = new Random();
			
			// Creates a instance for the ShelfBuilder with the book limit
			// Book limit of 50
			var shelfBuilder = new ShelfBuilder(50);
			
			// Generate the used categories
			var categories = GenerateResources("Categories", new CategoryGenerator(random), 10, String.class);
			
			// Generate the used authors
			var authors = GenerateResources("Authors", new AuthorGenerator(random), 10, Author.class);
			
			// Generate the books using the defined categories and authors
			var books = GenerateResources("Books", new BookGenerator(random, authors, categories), 10000, Book.class);
			
			// Sort the books by category
			var sortedBooks = SortResources("Books", new BookQuickSort(sink), books, Book.class);
			
			// Distributes the sorted books to the shelfs, using the book per shelf limit
			var shelfs = shelfBuilder.buildShelfs(sortedBooks);
			
			RunShelfSortingThreads(shelfs, new BookMergeSort());
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}
	
	public static <TResource> List<TResource> GenerateResources(String name, IGenerator<TResource> generator, int amount, Class<TResource> type) {
		sink.PrintLine(String.format("> Generating %d %s", amount, name));
		
		var resources = (TResource[]) Array.newInstance(type, amount);
		for (int i = 0; i < amount; i++) {
			var resource = generator.GetNext();
			resources[i] = resource;
			
			sink.ReplaceLine(String.format(ProgressTemplate, i + 1, amount, resource.toString()));
		}
		
		return List.of(resources);
	}
	
	public static <TResource> TResource[] SortResources(String name, ISort<TResource> sorting, List<TResource> resources, Class<TResource> type) {
		sink.PrintLine(String.format("> Sorting %s using %s", name, sorting.GetMethod()));
		
		// Create a new instance of array
		var arr = (TResource[]) Array.newInstance(type, resources.size());
		
		// Copy list to array
		resources.toArray(arr);
		
		// Sort array
		return sorting.Sort(arr);
	}
	
	public static void RunShelfSortingThreads(Shelf[] shelfs, ISort<Book> sorting) {
		sink.PrintLine("> Building sorting runnables...");
		
		var runnables = new ShelfSortingRunnable[shelfs.length];
		for (int i = 0; i < shelfs.length; i++) {
			sink.PrintLine(
					String.format(">> Building sorting runnable %d", i));
			
			runnables[i] = new ShelfSortingRunnable(
				Integer.toString(i),
				shelfs[i],
				sorting,
				sink);
		}
		
		sink.PrintLine("> Starting threads...");
		
		for (var runnable : runnables) {
			var thread = new Thread(runnable);
			thread.start();
		}
		
		var finishedThreadCount = 0;
		while (finishedThreadCount < runnables.length) {
			for (int i = 0; i < runnables.length; i++) {
				if (runnables[i].isCompleted()) {
					shelfs[i] = runnables[i].getResult();
					finishedThreadCount++;
				}
			}
		}
		
		sink.PrintLine("> Finished getting result for all shelfs");
	}

}
 