package org.snhu.cs320.contact;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactService {

	// This variable stores the instance so that the singleton pattern can be implemented
	private static ContactService INSTANCE;
	
	// This is where the contact data will be stored so that it can be updated later
	Map<String, Contact> psuedoDatabase = new ConcurrentHashMap<>();
	
	// The constructor must be private for the singleton pattern to be implemented here:
	private ContactService () {	}
	
	// If there is not already an instance of Contact Service one is created.
	// If an instance already exists it is returned. This means that only one instance of 
	// ContactService can exist at a time.
	public static synchronized ContactService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ContactService();
		}
		return INSTANCE;
	}
	
	// This is the method for adding a contact
	public boolean add(Contact contact) {
		// This checks that a null value is returned to ensure the contact was added
		return psuedoDatabase.putIfAbsent(contact.getID(), contact) == null;	
	}
	
	// This is the method for deleting a contact
	public boolean delete(String ID) {
		// Null should only be returned if the deletion fails
		return psuedoDatabase.remove(ID) != null;
	}
	
	// This is the method for updating a contact
	public boolean update(String ID, Contact updates) throws Exception {
		Contact existing = psuedoDatabase.get(ID);
		
		// This checks that there is a contact that matches
		if (existing == null) return false;
		
		// The contact information is then updated here
		existing.setFirstName(updates.getFirstName());
		existing.setLastName(updates.getLastName());
		existing.setPhoneNum(updates.getPhoneNum());
		existing.setAddress(updates.getAddress());
		
		return true;
	}
}
