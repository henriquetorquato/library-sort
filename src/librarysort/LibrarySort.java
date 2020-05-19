package librarysort;

import java.util.Random;

import librarysort.generators.AuthorGenerator;
import librarysort.generators.BookGenerator;
import librarysort.generators.CategoryGenerator;

public class LibrarySort {

	public static void main(String[] args) throws Exception {
		try 
		{
			var random = new Random();
			var categoryGenerator = new CategoryGenerator(random);
			var authorGenerator = new AuthorGenerator(random);
			
			var categories = categoryGenerator.GetNext(10);
			var authors = authorGenerator.GetNext(10);
			
			var bookGenerator = new BookGenerator(random, authors, categories);
			
			var book = bookGenerator.GetNext();
			System.out.println(book.ToString());
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}

}
