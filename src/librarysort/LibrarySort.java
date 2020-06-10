package librarysort;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import librarysort.generators.AuthorGenerator;
import librarysort.generators.BookGenerator;
import librarysort.generators.CategoryGenerator;
import librarysort.generators.IGenerator;
import librarysort.models.Author;
import librarysort.models.Book;
import librarysort.models.Shelf;
import librarysort.models.ShelfBuilder;
import librarysort.sorting.BookMergeSort;
import librarysort.sorting.BookMultithreadedQuickSort;
import librarysort.sorting.BookQuickSort;
import librarysort.sorting.ISort;
import librarysort.sorting.ShelfSortingRunnable;

public class LibrarySort {
	
	public static void main(String[] args) throws Exception {
		try 
		{		
			// Create the random object for the generators
			var random = new Random();
			
			// Creates a instance for the ShelfBuilder with the book limit
			// Book limit of 50
			var shelfBuilder = new ShelfBuilder(100);
			
			// Generate the used categories
			var categories = GenerateResources("Categories", new CategoryGenerator(random), 1000, String.class);
			
			// Generate the used authors
			var authors = GenerateResources("Authors", new AuthorGenerator(random), 1000, Author.class);
			
			// Generate the books using the defined categories and authors
			var books = GenerateResources("Books", new BookGenerator(random, authors, categories), 50000, Book.class);
			
			// Sort the books by category
			var sortedBooks = RunBookSortingThreads(books, new BookMultithreadedQuickSort());
			
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
		System.out.printf("> Generating %d %s", amount, name);
		
		var resources = (TResource[]) Array.newInstance(type, amount);
		var start = Instant.now();
		
		for (int i = 0; i < amount; i++) {
			var resource = generator.GetNext();
			resources[i] = resource;
		}
		
		var end = Instant.now();
		System.out.printf(" - Took %dms", Duration.between(start, end).toMillis());
		System.out.println();
		
		return List.of(resources);
	}
	
	public static Book[] RunBookSortingThreads(List<Book> books, ISort<Book> sorting) {	
		System.out.printf("> Sorting Books using %s", sorting.getMethod());
		
		var bookArray = new Book[books.size()];
		bookArray = books.toArray(bookArray);
		
		var start = Instant.now();
		var sorted = sorting.sort(bookArray);
		var end = Instant.now();
		
		System.out.printf(" - Took %dms", Duration.between(start, end).toMillis());
		System.out.println();
		
		return sorted;
	}
	
	public static void RunShelfSortingThreads(Shelf[] shelfs, ISort<Book> sorting) {
		System.out.println("> Building sorting runnables...");
		
		var runnables = new ShelfSortingRunnable[shelfs.length];
		for (int i = 0; i < shelfs.length; i++) {			
			runnables[i] = new ShelfSortingRunnable(
				Integer.toString(i),
				shelfs[i],
				sorting);
		}
		
		System.out.println("> Starting threads...");
		
		var start = Instant.now();
		
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
		
		var end = Instant.now();
		System.out.printf("> Finished getting result for all shelfs - Took %dms\n", Duration.between(start, end).toMillis());
	}

}
 