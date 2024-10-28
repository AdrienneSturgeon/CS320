package org.snhu.cs320.contact;

// This is the class for the Contact objects
public class Contact {
	// These are the private fields for the data used to create a contact as Strings
	private String ID;         // This stores the unique ID
	private String firstName;  // This stores the contact's first name
	private String lastName;   // This stores the contact's last name
	private String phoneNum;   // This stores the phone number of the contact
	private String address;    // This stores the contact's address
	
	// This default constructor is used when a contact object is created
	public Contact(String ID, String firstName, String lastName, String phoneNum, String address) throws Exception {
		super();
		
		// This throws an exception if ID is null, too long, or blank:
		// trim() is used so that a ID with only a space cannot be used
		if (ID == null || ID.trim().length() < 1 || ID.length() > 10) {
			throw new Exception("Invalid ID");
		}
		
		this.ID = ID;
		
		// To avoid redundant code with exception handling, the setters are used here
		setFirstName(firstName);
		
		setLastName(lastName);
		
		setPhoneNum(phoneNum);
		
		setAddress(address);
	}
	
	// These are the getters and setters for all fields:
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws Exception {
		// This throws an exception if firstName is null, too long, or blank:
		// trim() is used so that a firstName with only a space cannot be used
		if (firstName == null || firstName.trim().length() < 1 || firstName.length() > 10) {
			throw new Exception("Invalid First Name");
		}
		
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception {
		if (lastName == null || lastName.trim().length() < 1 || lastName.length() > 10) {
			throw new Exception("Invalid Last Name");
		}
		this.lastName = lastName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) throws Exception {
		// This is modified to only allow digits, and no other characters
		if (phoneNum == null || phoneNum.length() > 10 || phoneNum.length() < 10 || phoneNum.matches(".*\\D+.*")) {
			throw new Exception("Invalid Phone Number");
		}
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws Exception {
		// All fields except address have the same requirements, and thus use similar methods for
		// catching exceptions. Address is allowed to have a length of 30 rather than 10.
		if (address == null || address.trim().length() < 1 || address.length() > 30) {
			throw new Exception("Invalid Adress");
		}
		this.address = address;
	}

	//Since ID is immutable, it does not have a setter.
	public String getID() {
		return ID;
	}
	
	
}
