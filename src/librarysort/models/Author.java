package librarysort.models;

public class Author {

	private String firstName;
	
	private String lastName;
	
	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String GetFirstName() {
		return firstName;
	}
	
	public String GetLastName() {
		return lastName;
	}
	
	public String ToString() {
		return String.format("%s, %s", lastName.toUpperCase(), firstName);
	}
	
}
