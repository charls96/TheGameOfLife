package models;

import utils.Regex;

public class Address {
	private String street;
	private String number;
	private String postalCode;
	private String location;

	public Address(String street, String number, String postalCode, String location) {
		this.setStreet(street);
		this.setNumber(number);
		this.setPostalCode(postalCode);
		this.setLocation(location);
	}

	public Address() {
		this("Street", "00", "00000", "Location");
	}

	public Address(Address address) {
		this(address.street, address.number, address.postalCode, address.location);
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		if (isValidStreet(street)) {
			this.street = street;
		}
	}

	private boolean isValidStreet(String calle) {
		return calle != null && calle.matches(Regex.STREET_NAME);
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		if (isValidNumber(number)) {
			this.number = number;
		}
	}

	private boolean isValidNumber(String number) {
		return number != null && number.matches(Regex.POSTAL_NUMBER);
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		if (isValidPostalCode(postalCode)) {
			this.postalCode = postalCode;
		}
	}

	private boolean isValidPostalCode(String postalCode) {
		return postalCode != null && postalCode.matches(Regex.POSTAL_CODE);
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		if (isValidLocation(location)) {
			this.location = location;
		}
	}
	
	private boolean isValidLocation(String location) {
		return location != null && location.matches(Regex.LOCATION_NAME);
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s", 
				street, number, postalCode, location);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	@Override
	public Address clone() {
		return new Address(this);
	}

}
