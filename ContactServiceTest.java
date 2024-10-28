package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
	
	// To properly test deletion, the database will need to be cleared before each test
	@BeforeEach
	void init() {
		ContactService.getInstance().psuedoDatabase.clear();
	}
	
	@Test
	// This tests that getInstance is returning only one instance
	void testInstance() {
		assertThat(ContactService.getInstance()).isNotNull();
	}
	
	// This tests that a new contact was added correctly
	@Test
	void addTest() throws Exception {
		// First a contact was created here
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		// A boolean is used to check that the contact was added
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().psuedoDatabase).containsEntry("777", contact);
	}
	
	@Test
	void deleteTest() throws Exception {
		// First a contact was created here again
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		// This asserts that the contact should be deleted
		assertThat(ContactService.getInstance().delete("777")).isTrue();
		// Lastly a check is performed to make sure the contact does not exist after deletion
		assertThat(ContactService.getInstance().psuedoDatabase).doesNotContainEntry("777", contact);
	}
	
	@Test 
	void updateTest() throws Exception {
	// A contact object is created again:
	Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
	// There is then a check to make sure the contact was added
	assertThat(ContactService.getInstance().add(contact)).isTrue();
	
	// I then create an updated contact object
	Contact updatedContact = new Contact("555", "Indie", "Eliza", "4325673456", "321 Meow Street");
	// I make sure that the new contact exists
	assertThat(ContactService.getInstance().update("777", updatedContact)).isTrue();
	// I then use assert statements to check that each variable was updated
	assertThat(ContactService.getInstance().psuedoDatabase).extracting("777")
		.hasFieldOrPropertyWithValue("firstName", "Indie")
		.hasFieldOrPropertyWithValue("lastName", "Eliza")
		.hasFieldOrPropertyWithValue("phoneNum", "4325673456")
		.hasFieldOrPropertyWithValue("address", "321 Meow Street");
	}

	// This test makes sure that a contact does not add twice
	@Test
	void failAddContactTest() throws Exception {
		// A contact object is added
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		// This checks that the contact was not added again
		assertThat(ContactService.getInstance().add(contact)).isFalse();
	}

	@Test
	// This tests that the contact was not deleted if the information is incorrect
	void failDeleteContactTest() throws Exception {
		// The contact is added here
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		// The contact does not delete with an incorrect ID
		assertThat(ContactService.getInstance().delete("999")).isFalse();
	}

	@Test
	// This makes sure the contact does not update if the ID is null
	void failUpdateContactTest() throws Exception {
		// A null value means the contact does not exist, and thus should not be updated
		Contact updatedContact = new Contact("555", "Indie", "Eliza", "4325673456", "321 Meow Street");
		assertThat(ContactService.getInstance().update("333", updatedContact)).isFalse();
	}
}
