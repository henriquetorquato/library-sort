package librarysort;

import java.util.Random;
import librarysort.generators.BookGenerator;
import librarysort.generators.CategoryGenerator;

public class LibrarySort {

	public static void main(String[] args) throws Exception {
		try 
		{
			var random = new Random();
			var bookGenerator = new BookGenerator(random);
			var categoryGenerator = new CategoryGenerator(random);
			
			var book = bookGenerator.GetNext();
			var category = categoryGenerator.GetNext();
			
			System.out.println(book.GetAuthor());
			System.out.println(category);
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}

}
