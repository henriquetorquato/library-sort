package librarysort.models;

public class ShelfBuilder {
	
	/*
	 * The books need to be sorted at this step,
	 * because of the way that the shelf distribution work.
	 */
	public static Shelf[] buildShelfs(Book[] books, int shelfLimit) {
		// Create the shelfs
		var shelfAmount = books.length / shelfLimit; 
		var shelfs = new Shelf[shelfAmount];
		
		// Set initial book index
		var curentBook = 0;
		
		for (int s = 0; s < shelfs.length; s++) {
			// Create shelf using the defined limit
			var shelf = new Shelf(shelfLimit);
			
			// Add books to the shelf up to the limit
			while (shelf.haveSpace()) {
				if (curentBook < books.length) {
					shelf.addBook(books[curentBook++]);
				}
			}
		
			// Calls build id to use the category names
			shelf.buildId(s);
			
			// Adds the created shelf to the shelf list
			shelfs[s] = shelf;
		}
		
		// Return the created shelfs
		return shelfs;
	}
	
}
