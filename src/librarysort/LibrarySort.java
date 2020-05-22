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
import librarysort.sorting.BookQuickSort;
import librarysort.sorting.ISort;

public class LibrarySort {

	private static final String ProgressTemplate = "[%d/%d] %s";
	private static ISink sink;
	
	public static void main(String[] args) throws Exception {
		try 
		{
			var random = new Random();
			sink = new ConsoleSink(false);
			
			var categories = GenerateResources("Categories", new CategoryGenerator(random), 10, String.class);
			var authors = GenerateResources("Authors", new AuthorGenerator(random), 10, Author.class);
			
			var bookGenerator = new BookGenerator(random, authors, categories);
			var books = GenerateResources("Books", bookGenerator, 1000, Book.class);
			
			SortResources("Books", new BookQuickSort(sink), books, Book.class);
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

}
 