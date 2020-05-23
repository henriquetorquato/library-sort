package librarysort.models;

public class ShelfBuilder {

	private int shelfLimit;
	
	public ShelfBuilder(int shelfLimit) {
		this.shelfLimit = shelfLimit;
	}
	
	/*
	 * The books need to be sorted at this step,
	 * because of the way that the shelf distribution work.
	 */
	public Shelf[] buildShelfs(Book[] books) {
		// Create the shelfs
		var shelfAmount = books.length / this.shelfLimit; 
		var shelfs = new Shelf[shelfAmount];
		
		// Set initial book index
		var curentBook = 0;
		
		for (int s = 0; s < shelfs.length; s++) {
			// Create shelf using the defined limit
			var shelf = new Shelf(shelfLimit);
			
			// Add books to the shelf up to the limit
			for (int b = curentBook; b < shelfLimit; b++) {
				if (b < books.length) {
					shelf.addBook(books[b]);
					curentBook++;
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
