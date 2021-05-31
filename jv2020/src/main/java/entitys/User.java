package entitys;

import utils.EasyDate;

public class User extends Person implements Identifiable {

	public enum RoleUser { GUEST, REGISTERED, ADMIN };

	private EasyDate registeredDate;
	private Password password;
	private RoleUser role;

	public User(Nif nif, String name, String surnames,
			Address address, Mail mail, EasyDate birthDate,
			EasyDate registeredDate, Password password, RoleUser role) {		
		super(nif, name, surnames, address, mail, birthDate);
		
		this.setRegisteredDate(registeredDate);
		this.setPassword(password);
		this.setRole(role);
	}

	public User() {
		super();			
		this.registeredDate = EasyDate.today();	
		this.password = new Password("Miau#00");
		this.role = RoleUser.REGISTERED;
	}

	public User(User user) {
		assert user != null;
		this.nif = new Nif(user.nif);
		this.name = new String(user.name);
		this.surnames = new String(user.surnames);
		this.address = new Address(user.address);
		this.mail = new Mail(user.mail);	
		this.birthDate = user.birthDate.clone();
		this.registeredDate = user.registeredDate.clone();
		this.password = new Password(user.password);
		this.role = RoleUser.REGISTERED;	
	}

	@Override
	public String getId() {
		return this.nif.getText();
	}

	public EasyDate getRegisteredDate() {
		return this.registeredDate;
	}

	public Password getPassword() {
		return this.password;
	}

	public RoleUser getRole() {
		return this.role;
	}

	public void setRegisteredDate(EasyDate registeredDate) {
		assert registeredDate != null;

		if (isValidRegisteredDate(registeredDate)) {
			this.registeredDate = registeredDate;
		}
	}

	private boolean isValidRegisteredDate(EasyDate registeredDate) {	
		return !registeredDate.isAfter(EasyDate.now());
	}

	public void setPassword(Password password) {
		assert password != null;
		this.password = password;

	}

	public void setRole(RoleUser role) {
		this.role = role;
	}

	@Override
	public User clone() {
		return  new User(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surnames == null) ? 0 : surnames.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registeredDate == null) {
			if (other.registeredDate != null)
				return false;
		} else if (!registeredDate.equals(other.registeredDate))
			return false;
		if (role != other.role)
			return false;
		if (surnames == null) {
			if (other.surnames != null)
				return false;
		} else if (!surnames.equals(other.surnames))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s",
						"nif:", nif, 
						"name:", name, 
						"surnames:", surnames, 
						"address:", address, 
						"mail:", mail, 
						"birthDate:", birthDate, 
						"registeredDate:", registeredDate, 
						"password:", password, 
						"role:", role
				);
	}


}