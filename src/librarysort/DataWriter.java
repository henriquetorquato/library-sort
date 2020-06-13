package librarysort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import librarysort.models.Author;
import librarysort.models.Book;
import librarysort.models.Shelf;

public class DataWriter {

	private final static String FileName = "shelfs.txt";
	
	public static void write(Data data) {
		System.out.printf("Writing results to %s...", FileName);
		
		try {
			var file = getFile();
			var writer = new FileWriter(file);
			
			var header = getHeader(data);
			writer.write(header);
			
			for (int i = 0; i < data.shelfs.length; i++) {
				var body = getShelfBody(i, data.shelfs[i]);
				writer.write(body);
			}
			
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static File getFile() throws IOException {
		var file = new File(FileName);
		
		if (file.exists()) {
			file.delete();
		}
		
		file.createNewFile();
		return file;
	}
	
	private static String getHeader(Data data) {
		return new StringBuilder()
			.append(String.format("Sorted %d book's on %d shelfs\n", data.books.length, data.shelfs.length))
			.append(String.format("Used %d generated Authors for %d generated gategories\n", data.authors.length, data.categories.length))
			.append("\n-")
			.toString();
	}
	
	private static String getShelfBody(int index, Shelf shelf) {
		var body = new StringBuilder()
				.append(String.format("\n\n[%d] Shelf (%s):", index, shelf.getId()));
		
		for (var book : shelf.getBooks()) {
			body.append(String.format("\n\t%s", book.toString()));
		}
		
		return body.toString();
	}
	
	public static class Data {
		public Shelf[] shelfs;
		public Book[] books;
		public Author[] authors;
		public String[] categories;		
	}
}
