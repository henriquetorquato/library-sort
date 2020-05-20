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
import librarysort.sorting.BookQuickSort;
import librarysort.sorting.ISort;

public class LibrarySort {

	private static final String ProgressTemplate = "[%d/%d] %s\r";
	
	public static void main(String[] args) throws Exception {
		try 
		{
			var random = new Random();
			
			var categories = GenerateResources("Categories", new CategoryGenerator(random), 10, String[].class);
			var authors = GenerateResources("Authors", new AuthorGenerator(random), 10, Author[].class);
			
			var bookGenerator = new BookGenerator(random, authors, categories);
			var books = GenerateResources("Books", bookGenerator, 1000, Book[].class);
			
			var sortedBooks = SortResources("Books", new BookQuickSort(), books, Book[].class);
			for (var book : sortedBooks) {
				System.out.println(book.toString());
			}
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}
	
	public static <TResource> List<TResource> GenerateResources(String name, IGenerator<TResource> generator, int amount, Class<TResource[]> type) {
		System.out.println(String.format("> Generating %d %s", amount, name));
		
		var resources = type.cast(Array.newInstance(type.getComponentType(), amount));
		for (int i = 0; i < amount; i++) {
			var resource = generator.GetNext();
			resources[i] = resource;
			System.out.print(String.format(ProgressTemplate, i + 1, amount, resource.toString()));
		}
		
		return List.of(resources);
	}
	
	public static <TResource> List<TResource> SortResources(String name, ISort<TResource> sort, List<TResource> resources, Class<TResource[]> type) {
		System.out.println(String.format("> Sorting %s using %s", name, sort.GetMethod()));
		
		// Create a new instance of array
		var arr = type.cast(Array.newInstance(type.getComponentType(), resources.size()));
		
		// Copy list to array
		resources.toArray(arr);
		
		// Sort array and covert to list (why?)
		return List.of(sort.Sort(arr));
	}

}
 