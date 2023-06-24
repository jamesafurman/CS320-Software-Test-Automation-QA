package contactService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;

class ContactTest {
	
	static private String idValid, firstValid, lastValid, 
			phoneValid, addressValid;
	static private String idLengthBorderOut, firstLengthBorderOut, lastLengthBorderOut, 
		phoneLengthBorderOut, addressLengthBorderOut;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// Initialize valid values to length inside border
		idValid = new String(new char[10]).replace("\0", "*");		// length = 10
		firstValid = new String(new char[10]).replace("\0", "*");	// length = 10
		lastValid = new String(new char[10]).replace("\0", "*");	// length = 10
		phoneValid = new String(new char[10]).replace("\0", "*");	// length = 10
		addressValid = new String(new char[30]).replace("\0", "*");	// length = 30
		
		idLengthBorderOut = idValid + "*";							// length = 11
		firstLengthBorderOut = firstValid + "*";					// length = 11
		lastLengthBorderOut = lastValid + "*";						// length = 11
		phoneLengthBorderOut = phoneValid + "*";					// length = 11
		addressLengthBorderOut = addressValid + "*";				// length = 31
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Constructor passed valid args")
	void testLengthIn() {
		Contact testContact = new Contact(idValid, firstValid, 
				lastValid, phoneValid, addressValid);
		
		assertAll(
				"Object constructed with valid args has no null fields",
				() -> assertNotNull(testContact.getId()),
				() -> assertNotNull(testContact.getFirst()),
				() -> assertNotNull(testContact.getLast()),
				() -> assertNotNull(testContact.getPhone()),
				() -> assertNotNull(testContact.getAddress())
				);
		
		assertAll(
				"Object constructed with valid args has no oversized fields",
				() -> assertTrue(testContact.getId().length() <= 10),
				() -> assertTrue(testContact.getFirst().length() <= 10),
				() -> assertTrue(testContact.getLast().length() <= 10),
				() -> assertTrue(testContact.getPhone().length() <= 10),
				() -> assertTrue(testContact.getAddress().length() <= 30)
				);
	}
	
	@Test
	@Tag("valid")
	@DisplayName("Confirm toString method")
	void testToString() {
		Contact testContact = new Contact(idValid, firstValid, 
				lastValid, phoneValid, addressValid);
		
		assertTrue(testContact.toString().equals("Contact [id=" + idValid 
				+ "\n         name=" + firstValid + " " + lastValid 
				+ "\n         phone=" + phoneValid + "\n         address=" 
				+ addressValid + "]"));
	}
	
	@Test
	@Tag("border")
	@DisplayName("Constructor passed too-long args")
	void testLengthOut() {
		
		assertAll(
				"All values throw exception when passed null",
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idLengthBorderOut, firstLengthBorderOut,
							lastLengthBorderOut, phoneLengthBorderOut, addressLengthBorderOut);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstLengthBorderOut,
							lastLengthBorderOut, phoneLengthBorderOut, addressLengthBorderOut);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstValid,
							lastLengthBorderOut, phoneLengthBorderOut, addressLengthBorderOut);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstValid,
							lastValid, phoneLengthBorderOut, addressLengthBorderOut);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstValid,
							lastValid, phoneValid, addressLengthBorderOut);
				}));
	}
	
	@Test
	@Tag("null")
	@DisplayName("Constructor passed null args")
	void testNullArgs() {
		assertAll(
				"All values throw exception when passed null",
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(null, null, null, null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, null, null, null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstValid, null, null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstValid, lastValid, null, null);
				}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					new Contact(idValid, firstValid, lastValid, phoneValid, null);
				}));
	}

}
