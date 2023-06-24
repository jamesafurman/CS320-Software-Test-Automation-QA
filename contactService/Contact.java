package contactService;

public class Contact {
	private String id; // up to 10 chars
	private String firstName; // up to 10 chars
	private String lastName; // up to 10 chars
	private String phone; // up to 10 chars
	private String address; // up to 30 chars
		
	/**
	 * main constructor, takes an argument for each of the five fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param address
	 */
	public Contact(String id, String firstName, String lastName, String phone, String address) {
		if ((id == null) || (id.length() > 10)) {
			throw new IllegalArgumentException("Invalid ID");
		} else {
			this.id = id;
		}
		setFirst(firstName);
		setLast(lastName);
		setPhone(phone);
		setAddress(address);
	}
	
	/* 
	 * mutator methods truncate any too-long arguments
	 * chaining enabled
	 */
	public Contact setFirst(String firstName) {
		if ((firstName == null) || (firstName.length() > 10)) {
			throw new IllegalArgumentException("Invalid first name");
		} else {
			this.firstName = firstName;
		}
		
		return this;
	}
	public Contact setLast(String lastName) {
		if ((lastName == null) || (lastName.length() > 10)) {
			throw new IllegalArgumentException("Invalid last name");
		} else {
			this.lastName = lastName;
		}
		
		return this;
	}
	public Contact setPhone(String phone) {
		if ((phone == null) || (phone.length() > 10)) {
			throw new IllegalArgumentException("Invalid phone");
		} else {
			this.phone = phone;
		}

		return this;
	}
	public Contact setAddress(String address) {
		if ((address == null) || (address.length() > 30)) {
			throw new IllegalArgumentException("Invalid address");
		} else {
			this.address = address;
		}

		return this;
	}

	/*
	 * accessor methods
	 */
	public String getId() {
		return this.id;
	}
	public String getFirst() {
		return this.firstName;
	}
	public String getLast() {
		return this.lastName;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getAddress() {
		return this.address;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + this.getId() + "\n         name=" + this.getFirst() + " " + 
				this.getLast() + "\n         phone=" + this.getPhone() 
				+ "\n         address=" + this.getAddress() + "]";
	}
}