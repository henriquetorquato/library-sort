package librarysort.sorting;

public abstract class Sort {
	
	// Used to compare if a comes before b in alphabetical order, or if both strings are equal
	public Boolean lessThanOrEqual(String a, String b) {
		var result = a.compareTo(b);
		return result <= 0;
	}
	
}
