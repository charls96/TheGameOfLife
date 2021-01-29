import java.time.LocalDate;

public class User {

	private String nif;
	private String name;
	private String surnames;
	private String address;
	private String mail;
	private String birthDate;
	private String registeredDate;
	private String password;
	private String role;

	public User(String nif, String name, String surnames
			, String address, String mail, String birthDate,
			String registeredDate, String password, String role) {
		this.setNif(nif);
		this.setName(name);
		this.setSurnames(surnames);
		this.setAddress(address);
		this.setMail(mail);
		this.setBirthDate(birthDate);
		this.setRegisteredDate(registeredDate);
		this.setPassword(password);
		this.setRole(role);
	}

	public User() {
		this.nif = new String();
		this.name = new String();
		this.surnames = new String();
		this.address = new String();
		this.mail = new String();
		this.birthDate = new String();
		this.registeredDate = new String();
		this.password = new String();
		this.role = new String();
	}

	public User(User user) {

		assert user != null;

		this.nif = new String(user.nif);
		this.name = new String(user.name);
		this.surnames = new String(user.surnames);
		this.address = new String(user.address);
		this.mail = new String(user.mail);
		this.birthDate = new String(user.birthDate);
		this.registeredDate = new String(user.registeredDate);
		this.password = new String(user.password);
		this.role = new String(user.role);	
	}

	public String getNif() {
		return nif;
	}

	public String getName() {

		return name;
	}

	public String getSurnames() {
		return surnames;
	}

	public String getAddress() {
		return address;
	}

	public String getMail() {
		return mail;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setNif(String nif) {	

		assert nif != null;

		if (isValidNif(nif)) {
			this.nif = nif;
		}
	}

	private boolean isValidNif(String nif) {

		// TO-DO lógica validación

		return true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setBirthDate(String birthDate) {

		assert birthDate != null;

		if (isValidBirthDate()) {
			this.birthDate = birthDate;
		}
	}

	private boolean isValidBirthDate() {

		// TO-DO lógica validación

		return true;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public User clone() {
		return  new User(this);
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