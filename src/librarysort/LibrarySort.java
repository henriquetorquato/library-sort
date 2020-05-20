package librarysort;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import librarysort.generators.AuthorGenerator;
import librarysort.generators.BookGenerator;
import librarysort.generators.CategoryGenerator;
import librarysort.generators.IGenerator;
import librarysort.models.Author;

public class LibrarySort {

	private static final String ProgressTemplate = "[%d/%d] %s\r";
	
	public static void main(String[] args) throws Exception {
		try 
		{
			var random = new Random();
			var categoryGenerator = new CategoryGenerator(random);
			var authorGenerator = new AuthorGenerator(random);
			
			var categories = (List<String>) GenerateResources("Categories", categoryGenerator, 10, String[].class);
			var authors = (List<Author>) GenerateResources("Authors", authorGenerator, 10, Author[].class);
			
			var bookGenerator = new BookGenerator(random, authors, categories);
			
			var book = bookGenerator.GetNext();
			System.out.println(book.toString());
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

}
