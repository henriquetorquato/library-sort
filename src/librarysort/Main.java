package librarysort;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.Callable;

import librarysort.generators.AuthorGenerator;
import librarysort.generators.BookGenerator;
import librarysort.generators.CategoryGenerator;
import librarysort.generators.IGenerator;
import librarysort.models.Author;
import librarysort.models.Book;
import librarysort.models.ShelfBuilder;
import librarysort.sorting.BookMultithreadedQuickSort;
import librarysort.sorting.ISort;
import librarysort.sorting.ShelfMultithreadedMergeSort;

public class Main {
	
	public static void main(String[] args) throws Exception {
		try 
		{		
			// Create the random object for the generators
			var random = new Random();
			
			// Generate the used categories
			var categories = GenerateResources("Categories", new CategoryGenerator(random), 1000, String.class);
			
			// Generate the used authors
			var authors = GenerateResources("Authors", new AuthorGenerator(random), 1000, Author.class);
			
			// Generate the books using the defined categories and authors
			var books = GenerateResources("Books", new BookGenerator(random, authors, categories), 10, Book.class);
			
			// Sort the books by category
			var sortedBooks = RunSortingThreads("Books", books, new BookMultithreadedQuickSort());
			
			// Distributes the sorted books to the shelfs, using the book per shelf limit
			var shelfs = ShelfBuilder.buildShelfs(sortedBooks, 5);
			
			var sortedShelfs = RunSortingThreads("Shelfs", shelfs, new ShelfMultithreadedMergeSort());
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}
	
	public static <TResource> TResource[] GenerateResources(String name, IGenerator<TResource> generator, int amount, Class<TResource> type) {	
		System.out.printf("> Generating %d %s", amount, name);
		
		var result = TimedResult.measure(new Callable<TResource[]>() {
			public TResource[] call() {
				var resources = (TResource[]) Array.newInstance(type, amount);
				
				for (int i = 0; i < amount; i++) {
					var resource = generator.getNext();
					resources[i] = resource;
				}
				
				return resources;
			}
		});
		
		System.out.printf(" - Took %dms\n", result.getTime());
		return result.getResource();
	}
	
	public static <TResource> TResource[] RunSortingThreads(String name, TResource[] resource, ISort<TResource> sorting) {
		System.out.printf("> Sorting %s using %s", name, sorting.getMethod());
		
		var result = TimedResult.measure(new Callable<TResource[]>() {
			public TResource[] call() {
				return sorting.sort(resource);
			}
		});
		
		System.out.printf(" - Took %dms\n", result.getTime());
		return result.getResource();
	}
	
	public static void PrintBooks(Book[] books) {
		for (int i = 0; i < books.length; i++) {
			System.out.println(books[i].getCategory());
		}
		System.out.println();
	}

}
 