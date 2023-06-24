package contactService;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ContactService {
	
	private static List<Contact> contacts = new ArrayList<Contact>();

	private static long nextId = 0;
	
	/**
	 * Searches for a contact in contacts list by given id
	 * 
	 * @param id ID to search
	 * @return Searched contact (or null object if not found)
	 */
	public static Contact searchById(String id) {
		Contact contact = null;
		
		Iterator<Contact> contactIter = contacts.iterator();
		while(contactIter.hasNext()) {
			contact = contactIter.next();
			if (contact.getId().equals(id)) {
				break;
			} else {
				contact = null;
			}
		}
		
		return contact;
	}
	
	/**
	 * for testing: print all contacts in contacts list
	 */
	public static void printAll() {
		Contact contact = null;
		Iterator<Contact> contactIter = contacts.iterator();
		while(contactIter.hasNext()) {
			contact = contactIter.next();
			System.out.println(contact);
		}
	}
	/**
	 * for testing: get size of contacts list
	 */
	public static int getContactsSize() {
		return contacts.size();
	}
	
	/**
	 * Search for a contact by id and delete it from contacts list
	 * 
	 * @param id ID to search
	 * @return True if found and deleted, false if not
	 */
	public static boolean deleteContact(String id) {
		Contact contact = searchById(id);
		if (contact == null) {
			return false;
		} else {
			contacts.remove(contact);
			return true;
		}
	}
	
	/**
	 * Convert id from long to string
	 * 
	 * @param id long ID to convert
	 * @return ID as string
	 */
	private static String idToString(long id) {
		String idString = String.format("%010d", id);		
		return idString;
	}
	
	/**
	 * Add a contact given all information
	 * 
	 * @param firstName contact's first name
	 * @param lastName contact's last name
	 * @param phone contact's phone
	 * @param address contact's address
	 * @return contact
	 */
	public static Contact addContact(String firstName, String lastName, 
			String phone, String address) {
		Contact contact = null;
		
		contact = new Contact(idToString(nextId), firstName, lastName, phone, address);
		nextId += 1;
		
		contacts.add(contact);
		return contact;
	}
	
	/**
	 * Search for a contact by ID and update its first name field
	 * 
	 * @param id ID to search
	 * @param firstName new first name
	 * @return true if updated, false if not found
	 */
	public static boolean updateFirst(String id, String firstName) {
		Contact contact = searchById(id);
		
		if (contact == null) {
			return false;
		} else {
			contact.setFirst(firstName);
		}
		
		return true;
	}
	
	/**
	 * Search for a contact by ID and update its last name field
	 * 
	 * @param id ID to search
	 * @param lastName
	 * @return true if updated, false if not found
	 */
	public static boolean updateLast(String id, String lastName) {
		Contact contact = searchById(id);
		
		if (contact == null) {
			return false;
		} else {
			contact.setLast(lastName);
		}
		
		return true;
	}
	
	/**
	 * Search for a contact by ID and update its phone field
	 * 
	 * @param id ID to search
	 * @param phone
	 * @return true if updated, false if not found
	 */
	public static boolean updatePhone(String id, String phone) {
		Contact contact = searchById(id);
		
		if (contact == null) {
			return false;
		} else {
			contact.setPhone(phone);
		}
		
		return true;
	}
	
	/**
	 * Search for a contact by ID and update its address field
	 * 
	 * @param id ID to search
	 * @param address
	 * @return true if updated, false if not found
	 */
	public static boolean updateAddress(String id, String address) {
		Contact contact = searchById(id);
		
		if (contact == null) {
			return false;
		} else {
			contact.setAddress(address);
		}
		
		return true;
	}
}

