package org.snhu.cs320.contact;

// This allows assertions to be used
import static org.assertj.core.api.Assertions.assertThat;

// This imports the components for JUnit to function
import org.junit.jupiter.api.Test;

// This imports the components for Parameterized testing
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContactTest {
	// This test is used to test the Contact 
	@Test
	void testObjectCreation() throws Exception {
		// A test object is first made here:
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		// This assertion checks that all values were stored correctly
		assertThat(contact)
		.hasFieldOrPropertyWithValue("ID", "777")
		.hasFieldOrPropertyWithValue("firstName", "Adrienne")
		.hasFieldOrPropertyWithValue("lastName", "Sturgeon")
		.hasFieldOrPropertyWithValue("phoneNum", "1234567890")
		.hasFieldOrPropertyWithValue("address", "123 Generic Highway");
	}
	
	// This test makes sure the the setters for contact are setter fields correctly
	@Test
	void testSetters() throws Exception {
		// I used the same object here for the test:
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		contact.setFirstName("Indie");
		contact.setLastName("Eliza");
		contact.setPhoneNum("9876543210");
		contact.setAddress("321 Meow Street");
		
		// I then tested that these fields were updated properly
		assertThat(contact)
		.hasFieldOrPropertyWithValue("firstName", "Indie")
		.hasFieldOrPropertyWithValue("lastName", "Eliza")
		.hasFieldOrPropertyWithValue("phoneNum", "9876543210")
		.hasFieldOrPropertyWithValue("address", "321 Meow Street");
	}
	
	@CsvSource ({
		"'', Adrienne, Sturgeon, 1234567890, 123 Generic Highway", // Test blank ID
		", Adrienne, Sturgeon, 1234567890, 123 Generic Highway",   // Test null ID
		"' ', Adrienne, Sturgeon, 1234567890, 123 Generic Highway", // Test variant of blank ID
		"12345678901, Adrienne, Sturgeon, 1234567890, 123 Generic Highway", // Test ID that is too long
		
		"123, ' ', Sturgeon, 1234567890, 123 Generic Highway", // Test blank first name
		"123, , Sturgeon, 1234567890, 123 Generic Highway", // Test null first name
		"123, AdrienneTooLong, Sturgeon, 1234567890, 123 Generic Highway", // Test firstName that is too long
		
		"123, Adrienne, ' ', 1234567890, 123 Generic Highway", // Test blank last name
		"123, Adrienne, , 1234567890, 123 Generic Highway", // Test null last name
		"123, Adrienne, SturgeonTooLong, 1234567890, 123 Generic Highway", // Test lastName that is too long
		
		"123, Adrienne, Sturgeon, ' ', 123 Generic Highway", // Test blank phone number
		"123, Adrienne, Sturgeon, , 123 Generic Highway", // Test null phone number
		"123, Adrienne, Sturgeon, 12345678901, 123 Generic Highway", // Test phone number that is too long
		"123, Adrienne, Sturgeon, 12345678, 123 Generic Highway", // Test phone number that is too short
		"123, Adrienne, Sturgeon, 12345678A, 123 Generic Highway", // Test phone number with letters
		"123, Adrienne, Sturgeon, 12345678!, 123 Generic Highway", // Test phone number with punctuation
		
		"123, Adrienne, Sturgeon, 1234567890, ' '", // Test blank address
		"123, Adrienne, Sturgeon, 1234567890, ", // Test null address
		"123, Adrienne, Sturgeon, 1234567890, 123 Generic Highway Too Long Address Testing", // Test address more than 30 char
	})
	@ParameterizedTest
	// This is meant to fail objects created incorrectly:
	void testObjectCreationFail(String ID, String firstName, String lastName, String phone, String address) {
		// This should show exceptions if there is a failure with one of the test cases
		assertThatThrownBy(() -> new Contact(ID, firstName, lastName, phone, address))
			.isNotNull();
	}
	
	@CsvSource({
		"' ',", // Test blank name
		"AdrienneTooLong," // Test name that is too long
	})
	@ParameterizedTest
	void testFirstNameSetter(String firstName) throws Exception {
		// A contact object is created here
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		
		// If there is invalid input for the setter the test should see and exception and pass
		assertThatThrownBy(() -> contact.setFirstName(firstName))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // Test blank name
		"SturgeonTooLong," // Test name that is too long
	})
	@ParameterizedTest
	void testLastNameSetter(String lastName) throws Exception {
		// A contact object is created here
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		
		// If there is invalid input for the setter the test should see and exception and pass
		assertThatThrownBy(() -> contact.setLastName(lastName))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // Test blank number
		"12345678976,", // Test number that is too long
		"1,", // Test number that is too short
		"123456789a,", // Test number with letters
		"123456789!," // Test numbers with punctuation
	})
	@ParameterizedTest
	void testPhoneNumSetter(String phoneNum) throws Exception {
		// A contact object is created here
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		
		// If there is invalid input for the setter the test should see and exception and pass
		assertThatThrownBy(() -> contact.setPhoneNum(phoneNum))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // Test blank address
		"12345 Really Really Long Address", // Test address that is too long
	})
	@ParameterizedTest
	void testAddressSetter(String address) throws Exception {
		// A contact object is created here
		Contact contact = new Contact("777", "Adrienne", "Sturgeon", "1234567890", "123 Generic Highway");
		
		// If there is invalid input for the setter the test should see and exception and pass
		assertThatThrownBy(() -> contact.setAddress(address))
		.isNotNull();
	}
}
