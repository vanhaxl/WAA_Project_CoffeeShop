package edu.mum.coffee.domain;

public class Person implements Identity {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private String phone;
	private boolean enable;

	public Person() {
		
	}
	
	public Person(String firstName, String lastName, String email, Address address, String phone, boolean enable) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.enable = enable;
	}

	public int getId() {
		return id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person: {" + "id:" + String.valueOf(id) 
			+ ", firstName:" + String.valueOf(firstName) 
			+ ", lastName:" + String.valueOf(lastName) + "}";
	}
}
