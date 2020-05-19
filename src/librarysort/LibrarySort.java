package librarysort;

import java.util.Random;
import librarysort.generators.BookGenerator;

public class LibrarySort {

	public static void main(String[] args) throws Exception {
		try 
		{
			var random = new Random();
			var bookGenerator = new BookGenerator(random);
			
			var book = bookGenerator.GetNext("teste");
			System.out.print(book.GetAuthor());
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}

}
