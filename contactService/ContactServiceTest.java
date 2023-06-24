package contactService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;;


@TestMethodOrder(OrderAnnotation.class)
class ContactServiceTest {

	static ContactService testService;
	static int contactListInitSize;
	static int contactListSize;
	static String newFirst, newLast, newPhone, newAddress;
	static String tooLongFirst, tooLongLast, tooLongPhone, tooLongAddress;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ContactService.addContact("first1", "last1", "phone1", "address1");
		ContactService.addContact("first2", "last2", "phone2", "address2");
		
		contactListInitSize = ContactService.getContactsSize();
		// contactListSize = contactListInitSize;
		
		newFirst = "newFirst";
		newLast = "newLast";
		newPhone = "newPhone";
		newAddress = "newAddress";
		
		tooLongFirst = new String(new char[11]).replace("\0", "*");		// length = 11
		tooLongLast = new String(new char[11]).replace("\0", "*");		// length = 11
		tooLongLast = new String(new char[11]).replace("\0", "*");		// length = 11
		tooLongAddress = new String(new char[31]).replace("\0", "*");	// length = 31
		
		System.out.println("Contacts list before testing:");
		ContactService.printAll();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("\nContacts list after testing:");
		ContactService.printAll();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	@Tag("add_contact")
	@DisplayName("Add a new contact with null constructor arguments")
	void addNullContact() {
		assertAll(
				"All values throw exception when passed null",
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(null, null, null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(newFirst, null, null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(newFirst, newLast, 
							null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(newFirst, newLast, 
							newPhone, null);
				}));
		
		// Check that exception has prevented contact from being added
		assertEquals(ContactService.getContactsSize(), contactListInitSize);
	}
	
	@Test
	@Order(2)
	@Tag("add_contact")
	@DisplayName("Add a new contact with too-long constructor arguments")
	void addTooLongContact() {
		assertAll(
				"All values throw exception when passed null",
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(tooLongFirst, tooLongLast, 
							tooLongPhone, tooLongAddress);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(newFirst, tooLongFirst, 
							tooLongPhone, tooLongAddress);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(newFirst, newLast, 
							tooLongPhone, tooLongAddress);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.addContact(newFirst, newLast, 
							newPhone, tooLongAddress);
				}));
		
		// Check that exception has prevented contact from being added
		assertEquals(ContactService.getContactsSize(), contactListInitSize);
	}
	
	@Test
	@Order(3)
	@Tag("field_update")
	@DisplayName("Update fields in first contact")
	void updateFields1() {
		Contact testContact = ContactService.searchById("0000000000");
		
		String oldFirst = testContact.getFirst();
		String oldLast = testContact.getLast();
		String oldPhone = testContact.getPhone();
		String oldAddress = testContact.getAddress();
		
		ContactService.updateFirst("0000000000", newFirst);
		ContactService.updateLast("0000000000", newLast);
		ContactService.updatePhone("0000000000", newPhone);
		ContactService.updateAddress("0000000000", newAddress);
		
		assertAll(
				"Contact 0000000000 no longer has old values",
				() -> assertFalse(testContact.getFirst().equals(oldFirst)),
				() -> assertFalse(testContact.getLast().equals(oldLast)),
				() -> assertFalse(testContact.getPhone().equals(oldPhone)),
				() -> assertFalse(testContact.getAddress().equals(oldAddress))
				);
		
		assertAll(
				"Contact 0000000000 correctly set new values",
				() -> assertTrue(testContact.getFirst().equals(newFirst)),
				() -> assertTrue(testContact.getLast().equals(newLast)),
				() -> assertTrue(testContact.getPhone().equals(newPhone)),
				() -> assertTrue(testContact.getAddress().equals(newAddress))
				);
	}
	
	@Test
	@Order(4)
	@Tag("field_update")
	@DisplayName("Invalid update fields in first contact")
	void updateFieldsInvalid() {
		
		assertAll(
				"Exception when updating fields with null arguments",
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updateFirst("0000000000", null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updateLast("0000000000", null);
				}), 
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updatePhone("0000000000", null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updateAddress("0000000000", null);
				}));
		
		assertAll(
				"Exception when updating fields with too-long arguments",
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updateFirst("0000000000", tooLongFirst);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updateLast("0000000000", tooLongLast);
				}), 
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updatePhone("0000000000", tooLongPhone);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					ContactService.updateAddress("0000000000", tooLongAddress);
				}));
		}
	
	@Test
	@Order(5)
	@Tag("field_update")
	@DisplayName("Update fields in second contact")
	void updateFields2() {
		Contact testContact = ContactService.searchById("0000000001");
		
		String oldFirst = testContact.getFirst();
		String oldLast = testContact.getLast();
		String oldPhone = testContact.getPhone();
		String oldAddress = testContact.getAddress();
		
		ContactService.updateFirst("0000000001", newFirst);
		ContactService.updateLast("0000000001", newLast);
		ContactService.updatePhone("0000000001", newPhone);
		ContactService.updateAddress("0000000001", newAddress);
		
		assertAll(
				"Contact 0000000001 no longer has old values",
				() -> assertFalse(testContact.getFirst().equals(oldFirst)),
				() -> assertFalse(testContact.getLast().equals(oldLast)),
				() -> assertFalse(testContact.getPhone().equals(oldPhone)),
				() -> assertFalse(testContact.getAddress().equals(oldAddress))
				);
		
		assertAll(
				"Contact 0000000001 correctly set new values",
				() -> assertTrue(testContact.getFirst().equals(newFirst)),
				() -> assertTrue(testContact.getLast().equals(newLast)),
				() -> assertTrue(testContact.getPhone().equals(newPhone)),
				() -> assertTrue(testContact.getAddress().equals(newAddress))
				);
	}
	
	@Test
	@Order(6)
	@Tag("field_update")
	@DisplayName("Update fields in nonexistent contact")
	void updateFieldsNullObject() {
		assertAll(
				"Updating fields in nonexistent contact always returns false",
				() -> assertFalse(ContactService.updateFirst("----------", newFirst)),
				() -> assertFalse(ContactService.updateLast("----------", newLast)),
				() -> assertFalse(ContactService.updatePhone("----------", newPhone)),
				() -> assertFalse(ContactService.updateAddress("----------", newAddress))
				);
	}

	@Test
	@Order(7)
	@Tag("delete")
	@DisplayName("Delete first contact")
	void deleteContact1() {
		contactListSize = ContactService.getContactsSize();
		
		// deleteContact method returns true after successful deletion
		assertTrue(ContactService.deleteContact("0000000000"));
		
		// deleted contact no longer in contacts list
		assertTrue(ContactService.searchById("0000000000") == null);
		
		// contacts list size has decreased
		assertTrue(ContactService.getContactsSize() < contactListSize);
	}
	
	@Test
	@Order(8)
	@Tag("delete")
	@DisplayName("Delete second contact")
	void deleteContact2() {
		assertTrue(ContactService.deleteContact("0000000001"));
		assertTrue(ContactService.searchById("0000000001") == null);
	}
	
	@Test
	@Order(9)
	@Tag("delete")
	@DisplayName("Delete nonexistent contact")
	void deleteContactNullObject() {
		assertFalse(ContactService.deleteContact("----------"));
	}
}
